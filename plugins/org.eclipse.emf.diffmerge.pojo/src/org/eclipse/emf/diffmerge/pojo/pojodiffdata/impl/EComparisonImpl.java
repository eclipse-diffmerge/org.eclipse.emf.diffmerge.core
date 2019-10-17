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
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EComparisonImpl<E extends Object>
    extends GComparisonImpl<E, Object, Object> implements EComparison<E> {
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
  public EComparisonImpl(IEditableTreeDataScope<E> targetScope_p,
      IEditableTreeDataScope<E> referenceScope_p) {
    this(targetScope_p, referenceScope_p, null);
  }

  /**
   * Full constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @param ancestorScope_p the optional model scope playing the ANCESTOR comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableTreeDataScope<E> targetScope_p,
      IEditableTreeDataScope<E> referenceScope_p,
      IEditableTreeDataScope<E> ancestorScope_p) {
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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getMapping()
   * @generated NOT
   */
  @Override
  public EMapping<E> getMapping() {
    return (EMapping<E>) super.getMapping();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newAttributeValuePresence(org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role, boolean)
   * @generated NOT
   */
  public IAttributeValuePresence<E> newAttributeValuePresence(
      IMatch<E> elementMatch_p, Object attribute_p, Object value_p,
      Role presenceRole_p, boolean isOrder_p) {
    EAttributeValuePresence<E> result = new EAttributeValuePresenceImpl<E>(
        (EMatch<E>) elementMatch_p, attribute_p, value_p, presenceRole_p,
        isOrder_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newElementPresence(org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IElementPresence<E> newElementPresence(IMatch<E> elementMatch_p,
      IMatch<E> ownerMatch_p, Role presenceRole_p) {
    return new EElementPresenceImpl<E>((EMatch<E>) elementMatch_p,
        (EMatch<E>) ownerMatch_p, presenceRole_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#newMapping()
   * @generated NOT
   */
  @Override
  protected EMapping<E> newMapping() {
    return new EMappingImpl<E>();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newMatch(Object, Object, Object)
   * @generated NOT
   */
  public EMatch<E> newMatch(E targetElement_p, E referenceElement_p,
      E ancestorElement_p) {
    return new EMatchImpl<E>(targetElement_p, referenceElement_p,
        ancestorElement_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#newReferenceValuePresence(org.eclipse.emf.diffmerge.generic.api.IMatch, java.lang.Object, java.lang.Object, org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role, boolean)
   * @generated NOT
   */
  public IReferenceValuePresence<E> newReferenceValuePresence(
      IMatch<E> elementMatch_p, Object reference_p, E value_p,
      IMatch<E> valueMatch_p, Role presenceRole_p, boolean isOrder_p) {
    EReferenceValuePresence<E> result = new EReferenceValuePresenceImpl<E>(
        (EMatch<E>) elementMatch_p, reference_p, value_p,
        (EMatch<E>) valueMatch_p, presenceRole_p, isOrder_p);
    return result;
  }

} //GComparisonImpl
