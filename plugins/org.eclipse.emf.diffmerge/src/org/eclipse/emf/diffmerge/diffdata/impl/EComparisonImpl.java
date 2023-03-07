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
package org.eclipse.emf.diffmerge.diffdata.impl;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.diffmerge.api.scopes.IComparisonDependantScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl;
import org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation;
import org.eclipse.emf.diffmerge.impl.helpers.MatchOperation;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EComparisonImpl extends
    GComparisonImpl<EObject, EAttribute, EReference> implements EComparison {

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EComparisonImpl() {
    super();
  }

  /**
   * Simplified constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableTreeDataScope<EObject> targetScope_p,
      IEditableTreeDataScope<EObject> referenceScope_p) {
    this(targetScope_p, referenceScope_p, null);
  }

  /**
   * Full constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @param ancestorScope_p the optional model scope playing the ANCESTOR comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableTreeDataScope<EObject> targetScope_p,
      IEditableTreeDataScope<EObject> referenceScope_p,
      IEditableTreeDataScope<EObject> ancestorScope_p) {
    super(targetScope_p, referenceScope_p, ancestorScope_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.ECOMPARISON;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getMapping()
   * @generated NOT
   */
  @Override
  public EMapping getMapping() {
    return (EMapping) super.getMapping();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getMatchOperation(org.eclipse.emf.diffmerge.generic.api.IMatchPolicy, java.util.Map)
   * @generated NOT
   */
  @Override
  protected IExpensiveOperation getMatchOperation(
      IMatchPolicy<EObject> policy_p, Map<Role, Set<Object>> duplicateIDs_p) {
    return new MatchOperation(this, policy_p, duplicateIDs_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newAttributeValuePresence(org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role, boolean)
   * @generated NOT
   */
  public IAttributeValuePresence<EObject> newAttributeValuePresence(
      IMatch<EObject> elementMatch_p, Object attribute_p, Object value_p,
      Role presenceRole_p, boolean isOrder_p) {
    EAttributeValuePresence result = new EAttributeValuePresenceImpl(
        (EMatch) elementMatch_p, (EAttribute) attribute_p, value_p,
        presenceRole_p, isOrder_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newElementPresence(org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IElementPresence<EObject> newElementPresence(
      IMatch<EObject> elementMatch_p, IMatch<EObject> ownerMatch_p,
      Role presenceRole_p) {
    return new EElementPresenceImpl((EMatch) elementMatch_p,
        (EMatch) ownerMatch_p, presenceRole_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#newMapping()
   * @generated NOT
   */
  @Override
  protected EMapping newMapping() {
    return new EMappingImpl();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newMatch(Object, Object, Object)
   * @generated NOT
   */
  public EMatch newMatch(EObject targetElement_p, EObject referenceElement_p,
      EObject ancestorElement_p) {
    return new EMatchImpl(targetElement_p, referenceElement_p,
        ancestorElement_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newReferenceValuePresence(org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role, boolean)
   * @generated NOT
   */
  public IReferenceValuePresence<EObject> newReferenceValuePresence(
      IMatch<EObject> elementMatch_p, Object reference_p, EObject value_p,
      IMatch<EObject> valueMatch_p, Role presenceRole_p, boolean isOrder_p) {
    EReferenceValuePresence result = new EReferenceValuePresenceImpl(
        (EMatch) elementMatch_p, (EReference) reference_p, value_p,
        (EMatch) valueMatch_p, presenceRole_p, isOrder_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getDefaultDiffPolicy()
   * @generated NOT
   */
  @Override
  protected IDiffPolicy<EObject> getDefaultDiffPolicy() {
    return new DefaultDiffPolicy();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getDefaultMatchPolicy()
   * @generated NOT
   */
  @Override
  protected IMatchPolicy<EObject> getDefaultMatchPolicy() {
    return new DefaultMatchPolicy();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getDefaultMergePolicy()
   * @generated NOT
   */
  @Override
  protected IMergePolicy<EObject> getDefaultMergePolicy() {
    return new DefaultMergePolicy();
  }

  /**
   * If the scopes require the comparison object, add it before compute
   * 
   * @generated NOT
   */
  @Override
  public IStatus compute(IMatchPolicy<EObject> matchPolicy_p,
      IDiffPolicy<EObject> diffPolicy_p, IMergePolicy<EObject> mergePolicy_p,
      IProgressMonitor monitor_p) {
    if (targetScope instanceof IComparisonDependantScope)
      ((IComparisonDependantScope) targetScope).setComparison(this);
    if (referenceScope instanceof IComparisonDependantScope)
      ((IComparisonDependantScope) referenceScope).setComparison(this);

    return super.compute(matchPolicy_p, diffPolicy_p, mergePolicy_p, monitor_p);
  }

} //GComparisonImpl
