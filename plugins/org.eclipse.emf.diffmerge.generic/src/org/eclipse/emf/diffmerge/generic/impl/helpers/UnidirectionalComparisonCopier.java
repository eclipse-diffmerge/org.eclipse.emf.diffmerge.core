/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.impl.helpers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A unidirectional copier from a given scope to another one.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public class UnidirectionalComparisonCopier<E> {
  
  /** The non-null role of the source for this copier */
  protected final Role _sourceRole;
  
  /** The initially null mapping this copier relies upon */
  protected IMapping.Editable<E> _mapping;
  
  /** The initially null source scope for this copier */
  protected ITreeDataScope<E> _sourceScope;
  
  /** The initially null target scope for this copier */
  protected IEditableTreeDataScope<E> _destinationScope;
  
  /** The potentially null diff policy */
  protected IDiffPolicy<E> _diffPolicy;
  
  /** The potentially null merge policy to apply */
  protected IMergePolicy<E> _mergePolicy;
  
  /** Whether out of scope values must be copied or ignored */
  protected boolean _copyOutOfScopeValues;
  
  
  /**
   * Constructor
   * @param sourceRole_p the non-null role being the source role of this copier
   *        w.r.t. the comparison
   */
  public UnidirectionalComparisonCopier(Role sourceRole_p) {
    _sourceRole = sourceRole_p;
    _mapping = null;
    _sourceScope = null;
    _destinationScope = null;
    _copyOutOfScopeValues = false;
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
  public E completeMatch(IMatch<E> partialMatch_p, IComparison.Editable<E> comparison_p) {
    setComparison(comparison_p);
    assert partialMatch_p.getUncoveredRole() == _sourceRole.opposite() &&
        !getCompletedMatches().contains(partialMatch_p);
    E element = partialMatch_p.get(_sourceRole);
    E result = copy(element);
    assert result != null;
    getCompletedMatches().add(_mapping.getMatchFor(element, _sourceRole));
    return result;
  }
  
  /**
   * Complete the references between all completed elements
   * @param comparison_p a non-null comparison defining a behavioral context
   */
  public void completeReferences(IComparison.Editable<E> comparison_p) {
    setComparison(comparison_p);
    copyReferences();
  }

  protected E createBareMatch(E sourceElement_p) {
    E result = _destinationScope.tNewBareElement(sourceElement_p);
    _mapping.mapIncrementally(sourceElement_p, _sourceRole, result,
        _sourceRole.opposite());
    return result;
  }
  
  /**
   * Return a (shallow) copy of the given element.
   * Precondition: this method has never been called on the same element before,
   *   except if clear() was called in the meantime.
   * @param sourceElement_p an element belonging to the source scope
   * @return a non-null copy
   */
  protected E copy(E sourceElement_p) {
    assert _mergePolicy != null;
    E result = createBareMatch(sourceElement_p);
    for (Object attribute : _sourceScope.mGetAttributes(sourceElement_p)) {
      if (coverAttribute(attribute)) {
        copyAttribute(attribute, sourceElement_p, result);
      }
    }
    return result;
  }
  
  /**
   * Copy the attribute values of the given element to the given copy
   * @param attribute_p a non-null attribute
   * @param element_p a non-null element
   * @param copy_p a non-null copy
   */
  protected void copyAttribute(Object attribute_p, E element_p, E copy_p) {
    for (Object value : _sourceScope.getAttributeValues(element_p, attribute_p)) {
      _destinationScope.addAttributeValue(copy_p, attribute_p, value);
    }
  }
  
  /**
   * Copy reference values between the source elements copied to the resulting copies
   */
  protected void copyReferences() {
    for (IMatch<E> updatedMatch : getCompletedMatches()) {
      copyReferences(updatedMatch);
    }
    // Update of containments may have changed physical storage, which may have an impact on IDs
    if (_mergePolicy != null) {
      for (IMatch<E> updatedMatch : getCompletedMatches()) {
        E source = updatedMatch.get(_sourceRole);
        E target = updatedMatch.get(_sourceRole.opposite());
        _mergePolicy.setID(source, _sourceScope, target, _destinationScope);
      }
    }
  }
  
  /**
   * Copy the cross-references of the destination element of the given match
   * @param match_p a non-null, non-partial match
   */
  protected void copyReferences(IMatch<E> match_p) {
    E source = match_p.get(_sourceRole);
    E destination = match_p.get(_sourceRole.opposite());
    assert source != null && destination != null;
    for (Object reference : _sourceScope.mGetReferences(source)) {
      if (!_sourceScope.mIsContainerReference(reference) &&
          coverReference(reference)) {
        copyReference(reference, source, destination);
      }
    }
  }
  
  /**
   * Copy the reference values of the given element to the given copy
   * @param reference_p a non-null reference
   * @param element_p a non-null element
   * @param copy_p a non-null copy
   */
  protected void copyReference(Object reference_p, E element_p, E copy_p) {
    // This implementation assumes that values need only be added
    List<E> sourceValues = _sourceScope.getReferenceValues(element_p, reference_p);
    Object opposite = _sourceScope.mGetOppositeReference(reference_p);
    for (E sourceValue : sourceValues) {
      IMatch<E> valueMatch = _mapping.getMatchFor(sourceValue, _sourceRole);
      if (valueMatch != null) {
        // Value in scope
        // If value is in copier or ref is unidirectional, it is not handled
        // by a ref presence diff so it must be copied
        boolean mustCopy = getCompletedMatches().contains(valueMatch) ||
          // Being a containment means there is an implicit opposite
          (opposite == null && !_sourceScope.mIsContainmentReference(reference_p));
        if (!mustCopy) {
          // Otherwise, check if it is actually handled by a ref presence diff
          // (it may not be because the opposite ref may not be covered by the diff policy)
          IMatch<E> holderMatch = _mapping.getMatchFor(element_p, _sourceRole);
          if (holderMatch != null) {
            mustCopy =
                holderMatch.getReferenceValueDifference(reference_p, sourceValue) == null;
          }
        }
        if (mustCopy) {
          E destinationValue = valueMatch.get(_sourceRole.opposite());
          if (destinationValue != null) {
            _destinationScope.addReferenceValue(copy_p, reference_p, destinationValue);
          }
        } // Else handled by a ref presence diff
      } else {
        // Value out of scope: keep as is if no side effect due to bidirectionality or containment
        if (_copyOutOfScopeValues && opposite == null &&
            !_sourceScope.mIsContainmentReference(reference_p) &&
            !_sourceScope.mIsContainerReference(reference_p) ||
            _diffPolicy != null && _diffPolicy.coverOutOfScopeValue(
                sourceValue, reference_p, _sourceScope)) {
          _destinationScope.addReferenceValue(copy_p, reference_p, sourceValue);
        }
      }
    }
  }
  
  /**
   * Return whether the given attribute must be copied
   * @param attribute_p a non-null attribute
   */
  protected boolean coverAttribute(Object attribute_p) {
    return _mergePolicy != null && _mergePolicy.copyAttribute(
        attribute_p, _destinationScope);
  }
  
  /**
   * Return whether the given reference must be copied
   * @param reference_p a non-null reference
   */
  protected boolean coverReference(Object reference_p) {
    return _mergePolicy != null && _mergePolicy.copyReference(
        reference_p, _destinationScope);
  }
  
  /**
   * Return the copy of the given element, if any
   * @param element_p a non-null element
   * @return a potentially null element
   */
  public E get(E element_p) {
    return get(element_p, true);
  }
  
  /**
   * Return the match of the given element, if any
   * @param element_p a non-null element, which for relevance should belong to the source scope
   * @param copyOnly_p whether the scope should be restricted to the matches
   *        updated by this copier, i.e., the result may only be a copy
   * @return a potentially null element
   */
  public E get(E element_p, boolean copyOnly_p) {
    E result = null;
    IMatch<E> match = _mapping.getMatchFor(element_p, _sourceRole);
    if (match != null && (!copyOnly_p || getCompletedMatches().contains(match))) {
      result = match.get(_sourceRole.opposite());
    }
    return result;
  }
  
  /**
   * Return the modifiable set of completed matches from the source role
   * to its opposite
   * @return a non-null, modifiable collection
   */
  protected Collection<IMatch<E>> getCompletedMatches() {
    return _mapping.getModifiableCompletedMatches(_sourceRole.opposite());
  }
  
  /**
   * Set the comparison which defines the behavioral context of this copier
   * @param comparison_p a non-null comparison
   */
  protected void setComparison(IComparison.Editable<E> comparison_p) {
    _mapping = comparison_p.getMapping();
    _sourceScope = comparison_p.getScope(_sourceRole);
    _destinationScope = comparison_p.getScope(_sourceRole.opposite());
    _diffPolicy = comparison_p.getLastDiffPolicy();
    _mergePolicy = comparison_p.getLastMergePolicy();
    if (_mergePolicy != null) {
      _copyOutOfScopeValues = _mergePolicy.copyOutOfScopeCrossReferences(
          _sourceScope, _destinationScope);
    }
  }
  
}
