/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.diffdata.impl;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.ECrossReferenceEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EMappingImpl extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl<EObject, EAttribute, EReference>
    implements EMapping {

  /**
   * A non-null, stateful but frozen cross-referencer for the TARGET scope
   * @generated NOT
   */
  private final ScopeCrossReferencer _targetCrossReferencer;

  /**
   * A non-null, stateful but frozen cross-referencer for the REFERENCE scope
   * @generated NOT
   */
  private final ScopeCrossReferencer _referenceCrossReferencer;

  /**
   * A non-null cross reference adapter on this mapping for retrieving matches from model elements
   * @generated NOT
   */
  private MatchCrossReferenceAdapter _matchAdapter;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EMappingImpl() {
    super();
    _targetCrossReferencer = new ScopeCrossReferencer(Role.TARGET);
    _referenceCrossReferencer = new ScopeCrossReferencer(Role.REFERENCE);
    _matchAdapter = new MatchCrossReferenceAdapter();
    eAdapters().add(_matchAdapter);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EMAPPING;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMappingImpl#clear()
   * @generated NOT
   */
  @Override
  public void clear() {
    _targetCrossReferencer.clear();
    _referenceCrossReferencer.clear();
    eAdapters().remove(_matchAdapter);
    _matchAdapter = new MatchCrossReferenceAdapter();
    eAdapters().add(_matchAdapter);
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping#crossReference(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public void crossReference(Role role_p) {
    ScopeCrossReferencer referencer = (role_p == Role.TARGET)
        ? _targetCrossReferencer
        : _referenceCrossReferencer;
    referencer.crossReference();
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping#getComparison()
   */
  @Override
  public EComparison getComparison() {
    return (EComparison) super.getComparison();
  }

  /**
   * @see org.eclipse.emf.diffmerge.diffdata.EMapping#getCrossReferences(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public EList<Setting> getCrossReferences(EObject element_p, Role role_p) {
    EList<Setting> result = null;
    ScopeCrossReferencer referencer = null;
    if (role_p == Role.TARGET) {
      referencer = _targetCrossReferencer;
    } else if (role_p == Role.REFERENCE) {
      referencer = _referenceCrossReferencer;
    }
    if (referencer != null) {
      result = (EList<Setting>) referencer.get(element_p); // OK because of SCR#newCollection()
    }
    if (result == null) {
      result = ECollections.emptyEList();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getMatchFor(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public EMatch getMatchFor(EObject element_p, Role role_p) {
    EMatch result = null;
    if (role_p != null) {
      EReference matchReference;
      switch (role_p) {
      case ANCESTOR:
        matchReference = DiffdataPackage.eINSTANCE.getEMatch_Ancestor();
        break;
      case REFERENCE:
        matchReference = DiffdataPackage.eINSTANCE.getEMatch_Reference();
        break;
      default:
        matchReference = DiffdataPackage.eINSTANCE.getEMatch_Target();
      }
      if (element_p != null) {
        Collection<Setting> settings = _matchAdapter
            .getNonNavigableInverseReferences(element_p);
        for (Setting setting : settings) {
          if (setting.getEStructuralFeature() == matchReference) {
            result = (EMatch) setting.getEObject();
            break;
          }
        }
      }
    }
    return result;
  }

  /**
   * Remove dependencies (references) to the given element after its removal from the
   * scope of the given role in the given comparison
   * @param role_p TARGET or REFERENCE
   * @param element_p a non-null element
   * @return whether all dependencies have been successfully removed
   * @generated NOT
   */
  public boolean removeDependencies(Role role_p, EObject element_p) {
    boolean result = true;
    IEditableTreeDataScope<EObject> scope = getComparison().getScope(role_p);
    for (EStructuralFeature.Setting setting : getCrossReferences(element_p,
        role_p)) {
      boolean removed = scope.removeReferenceValue(setting.getEObject(),
          setting.getEStructuralFeature(), element_p);
      result = result && removed;
    }
    return result;
  }

  /**
   * A cross reference adapter for retrieving matches from model elements.
   * @generated NOT
   */
  protected static class MatchCrossReferenceAdapter
      extends ECrossReferenceAdapter {
    /**
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#isIncluded(org.eclipse.emf.ecore.EReference)
     */
    @Override
    protected boolean isIncluded(EReference reference_p) {
      return reference_p == DiffdataPackage.eINSTANCE.getEMatch_Ancestor()
          || reference_p == DiffdataPackage.eINSTANCE.getEMatch_Reference()
          || reference_p == DiffdataPackage.eINSTANCE.getEMatch_Target();
    }
  }

  /**
   * A cross-referencer for handling cross-references that are not covered by differences.
   * @generated NOT
   */
  protected class ScopeCrossReferencer extends EcoreUtil.CrossReferencer {
    /** The serial version */
    private static final long serialVersionUID = 1L;

    /** The non-null role played by the scope to cross-reference */
    protected final Role _role;

    /**
     * Constructor
     * @param role_p a role which is TARGET or REFERENCE
     */
    public ScopeCrossReferencer(Role role_p) {
      super(Collections.emptyList());
      _role = role_p;
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#crossReference()
     */
    @Override
    public void crossReference() { // Increases visibility
      super.crossReference();
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#crossReference(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected boolean crossReference(EObject element_p, EReference reference_p,
        EObject crossReferenced_p) {
      boolean result = false;
      EMatch referencingMatch = getMatchFor(element_p, _role);
      EMatch referencedMatch = getMatchFor(crossReferenced_p, _role);
      // Unidirectional, modifiable cross-references between unmatched elements
      if (referencingMatch != null && referencedMatch != null) {
        result = referencingMatch.isPartial() && referencedMatch.isPartial();
      }
      return result;
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#getCrossReferences(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected EContentsEList.FeatureIterator<EObject> getCrossReferences(
        EObject eObject) {
      return new ECrossReferenceEList.FeatureIteratorImpl<EObject>(eObject) {
        /**
         * @see org.eclipse.emf.ecore.util.EContentsEList.FeatureIteratorImpl#isIncluded(org.eclipse.emf.ecore.EStructuralFeature)
         */
        @Override
        protected boolean isIncluded(EStructuralFeature feature_p) {
          return super.isIncludedEntry(feature_p)
              && ScopeCrossReferencer.this.isIncluded((EReference) feature_p);
        }

        /**
         * @see org.eclipse.emf.ecore.util.EContentsEList.FeatureIteratorImpl#resolve()
         */
        @Override
        protected boolean resolve() {
          return ScopeCrossReferencer.this.resolve();
        }
      };
    }

    /**
     * Return the role covered by this cross-referencer
     * @return TARGET or REFERENCE
     */
    public Role getRole() {
      return _role;
    }

    /**
     * Return whether the given cross-reference should be covered by this cross-referencer
     * @param reference_p a non-null cross-reference
     */
    protected boolean isIncluded(EReference reference_p) {
      // Modifiable cross-references only
      return reference_p.isChangeable() && !reference_p.isDerived();
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#newCollection()
     */
    @Override
    protected Collection<EStructuralFeature.Setting> newCollection() {
      return new FArrayList<EStructuralFeature.Setting>();
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#newContentsIterator()
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected TreeIterator<Notifier> newContentsIterator() {
      return (TreeIterator) getComparison().getScope(_role).iterator();
    }

    /**
     * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#resolve()
     */
    @Override
    protected boolean resolve() {
      return false;
    }
  }

} //EMappingImpl
