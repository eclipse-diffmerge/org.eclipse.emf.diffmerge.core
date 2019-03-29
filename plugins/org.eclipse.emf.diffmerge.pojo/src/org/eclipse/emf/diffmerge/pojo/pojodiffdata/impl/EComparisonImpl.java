/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl;

import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EComparisonImpl extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl<Object, Object, Object>
    implements EComparison {
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
  public EComparisonImpl(IEditableTreeDataScope<Object> targetScope_p,
      IEditableTreeDataScope<Object> referenceScope_p) {
    this(targetScope_p, referenceScope_p, null);
  }

  /**
   * Full constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @param ancestorScope_p the optional model scope playing the ANCESTOR comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableTreeDataScope<Object> targetScope_p,
      IEditableTreeDataScope<Object> referenceScope_p,
      IEditableTreeDataScope<Object> ancestorScope_p) {
    super(targetScope_p, referenceScope_p, ancestorScope_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PojodiffdataPackage.Literals.ECOMPARISON;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getMapping()
   * @generated NOT
   */
  @Override
  public EMapping getMapping() {
    return (EMapping) super.getMapping();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newAttributeValuePresence(org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role, boolean)
   * @generated NOT
   */
  public IAttributeValuePresence<Object> newAttributeValuePresence(
      IMatch<Object> elementMatch_p, Object attribute_p, Object value_p,
      Role presenceRole_p, boolean isOrder_p) {
    EAttributeValuePresence result = new EAttributeValuePresenceImpl(
        (EMatch) elementMatch_p, (EAttribute) attribute_p, value_p,
        presenceRole_p, isOrder_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newElementPresence(org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.IMatch)
   * @generated NOT
   */
  public IElementPresence<Object> newElementPresence(
      IMatch<Object> elementMatch_p, IMatch<Object> ownerMatch_p) {
    return new EElementPresenceImpl((EMatch) elementMatch_p,
        (EMatch) ownerMatch_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#newMapping()
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
  public EMatch newMatch(Object targetElement_p, Object referenceElement_p,
      Object ancestorElement_p) {
    return new EMatchImpl(targetElement_p, referenceElement_p,
        ancestorElement_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newReferenceValuePresence(org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role, boolean)
   * @generated NOT
   */
  public IReferenceValuePresence<Object> newReferenceValuePresence(
      IMatch<Object> elementMatch_p, Object reference_p, Object value_p,
      IMatch<Object> valueMatch_p, Role presenceRole_p, boolean isOrder_p) {
    EReferenceValuePresence result = new EReferenceValuePresenceImpl(
        (EMatch) elementMatch_p, (EReference) reference_p, value_p,
        (EMatch) valueMatch_p, presenceRole_p, isOrder_p);
    return result;
  }

} //EComparisonImpl
