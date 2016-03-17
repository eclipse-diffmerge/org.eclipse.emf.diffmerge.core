/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.impl.helpers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * A unidirectional copier from a given scope to another one.
 * The state as defined in the superclass is never modified, only behavior is reused.
 * @author Olivier Constant
 */
public class UnidirectionalComparisonCopier extends EcoreUtil.Copier {
  
  /** The serial version ID */
  private static final long serialVersionUID = 1L;
  
  /** The non-null role of the source for this copier */
  protected final Role _sourceRole;
  
  /** The initially null mapping this copier relies upon */
  protected IMapping.Editable _mapping;
  
  /** The initially null source scope for this copier */
  protected IFeaturedModelScope _sourceScope;
  
  /** The initially null target scope for this copier */
  protected IEditableModelScope _destinationScope;
  
  /** The potentially null merge policy to apply */
  protected IMergePolicy _mergePolicy;
  
  
  /**
   * Constructor
   * @param sourceRole_p the non-null role being the source role of this copier
   *        w.r.t. the comparison
   */
  public UnidirectionalComparisonCopier(Role sourceRole_p) {
    super(false, true);
    _sourceRole = sourceRole_p;
    _mapping = null;
    _sourceScope = null;
    _destinationScope = null;
  }
  
  /**
   * Return a (shallow) copy of the given element.
   * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copy(EObject)
   * Precondition: this method has never been called on the same element before,
   *   except if clear() was called in the meantime.
   * @param element_p an element belonging to the source scope
   * @return a non-null copy
   */
  @Override
  public EObject copy(EObject element_p) {
    EObject result = copyAsProxy(element_p);
    for (EAttribute attribute : element_p.eClass().getEAllAttributes()) {
      if (coverFeature(attribute))
        copyAttribute(attribute, element_p, result);
    }
    // No call to method put, so the state never changes
    return result;
  }
  
  /**
   * Complete the given partial match by copying its unmatched element and
   * updating this mapping accordingly.
   * The references of the element are not completed.
   * Postcondition: !partialMatch_p.isPartial()
   * @param partialMatch_p a non-null partial match
   * @param comparison_p a non-null comparison
   * @return a non-null element which is a clone of the element in partialMatch_p
   */
  public EObject completeMatch(IMatch partialMatch_p, IComparison.Editable comparison_p) {
    setComparison(comparison_p);
    assert partialMatch_p.getUncoveredRole() == _sourceRole.opposite() &&
        !getCompletedMatches().contains(partialMatch_p);
    EObject element = partialMatch_p.get(_sourceRole);
    EObject result = copy(element);
    assert result != null;
    _mapping.mapIncrementally(
        element, _sourceRole, result, _sourceRole.opposite());
    getCompletedMatches().add(_mapping.getMatchFor(element, _sourceRole));
    return result;
  }
  
  /**
   * Complete the references between all completed elements
   * @param comparison_p a non-null comparison defining a behavioral context
   */
  public void completeReferences(IComparison.Editable comparison_p) {
    setComparison(comparison_p);
    copyReferences();
  }
  
  /**
   * Return a raw copy of the given element with only the proxy URI being set
   * @param element_p a non-null element
   * @return a non-null element of the same EClass and proxy URI
   */
  protected EObject copyAsProxy(EObject element_p) {
    EObject result = createCopy(element_p);
    copyProxyURI(element_p, result);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyAttribute(EAttribute, EObject, EObject)
   */
  @Override
  protected void copyAttribute(EAttribute attribute_p, EObject element_p,
      EObject copy_p) {
    for (Object value : _sourceScope.get(element_p, attribute_p))
      _destinationScope.add(copy_p, attribute_p, value);
  }
  
  /**
   * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyReferences()
   */
  @Override
  public void copyReferences() {
    for (IMatch updatedMatch : getCompletedMatches())
      copyReferences(updatedMatch);
    // Update of containments may have changed resources, which may have an impact on IDs
    if (_mergePolicy != null) {
      for (IMatch updatedMatch : getCompletedMatches()) {
        EObject source = updatedMatch.get(_sourceRole);
        EObject target = updatedMatch.get(_sourceRole.opposite());
        BidirectionalComparisonCopier.handleIDCopy(
            source, _sourceScope, target, _destinationScope, _mergePolicy);
      }
    }
  }
  
  /**
   * Copy the cross-references of the destination element of the given match
   * @param match_p a non-null, non-partial match
   */
  protected void copyReferences(IMatch match_p) {
    EObject source = match_p.get(_sourceRole);
    EObject destination = match_p.get(_sourceRole.opposite());
    assert source != null && destination != null;
    for (EReference reference : source.eClass().getEAllReferences()) {
      if (!reference.isContainer() && coverFeature(reference))
        copyReference(reference, source, destination);
    }
  }
  
  /**
   * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyReference(EReference, EObject, EObject)
   */
  @Override
  protected void copyReference(EReference reference_p, EObject source_p,
      EObject destination_p) {
    // This implementation assumes that values need only be added
    List<EObject> sourceValues = _sourceScope.get(source_p, reference_p);
    for (EObject sourceValue : sourceValues) {
      IMatch valueMatch = _mapping.getMatchFor(sourceValue, _sourceRole);
      if (valueMatch != null) {
        // Value in scope
        // If value is in copier or ref is unidirectional, it is not handled
        // by a ref presence diff so it must be copied
        boolean mustCopy = getCompletedMatches().contains(valueMatch) ||
          // Being a containment means there is an implicit opposite
          (reference_p.getEOpposite() == null && !reference_p.isContainment());
        if (!mustCopy) {
          // Otherwise, check if it is actually handled by a ref presence diff
          // (it may not be because the opposite ref may not be covered by the diff policy)
          IMatch holderMatch = _mapping.getMatchFor(source_p, _sourceRole);
          if (holderMatch != null)
            mustCopy = holderMatch.getReferenceValueDifference(
                reference_p, valueMatch) == null;
        }
        if (mustCopy) {
          EObject destinationValue = valueMatch.get(_sourceRole.opposite());
          if (destinationValue != null)
            _destinationScope.add(destination_p, reference_p, destinationValue);
        } // Else handled by a ref presence diff
      } else {
        // Value out of scope: keep as is if no side effect due to bidirectionality or containment
        if (useOriginalReferences && reference_p.getEOpposite() == null &&
            !reference_p.isContainment() && !reference_p.isContainer()) {
          _destinationScope.add(destination_p, reference_p, sourceValue);
        }
      }
    }
  }
  
  /**
   * Return whether the given feature must be copied
   * @param feature_p a non-null feature
   */
  protected boolean coverFeature(EStructuralFeature feature_p) {
    return _mergePolicy != null && _mergePolicy.copyFeature(feature_p, _destinationScope);
  }
  
  /**
   * @see java.util.LinkedHashMap#get(java.lang.Object)
   */
  @Override
  public EObject get(Object key_p) {
    return get(key_p, true);
  }
  
  /**
   * Return the element from the destination role which matches with the given element
   * @param key_p a potentially null object, which for relevance should be a
   *        non-null element in the source role
   * @param inCopierOnly_p whether the scope should be restricted to the matches
   *        updated by this copier
   * @return a potentially null element
   */
  public EObject get(Object key_p, boolean inCopierOnly_p) {
    EObject result = null;
    if (key_p instanceof EObject) {
      IMatch match = _mapping.getMatchFor((EObject)key_p, _sourceRole);
      if (match != null && (!inCopierOnly_p || getCompletedMatches().contains(match)))
        result = match.get(_sourceRole.opposite());
    }
    return result;
  }
  
  /**
   * Return the modifiable set of completed matches from the source role
   * to its opposite
   * @return a non-null, modifiable collection
   */
  protected Collection<IMatch> getCompletedMatches() {
    return _mapping.getModifiableCompletedMatches(_sourceRole.opposite());
  }
  
  /**
   * Set the comparison which defines the behavioral context of this copier
   * @param comparison_p a non-null comparison
   */
  protected void setComparison(IComparison.Editable comparison_p) {
    _mapping = comparison_p.getMapping();
    _sourceScope = comparison_p.getScope(_sourceRole);
    _destinationScope = comparison_p.getScope(_sourceRole.opposite());
    _mergePolicy = comparison_p.getLastMergePolicy();
    if (_mergePolicy != null)
      useOriginalReferences = _mergePolicy.copyOutOfScopeCrossReferences(
          _sourceScope, _destinationScope);
  }
  
}
