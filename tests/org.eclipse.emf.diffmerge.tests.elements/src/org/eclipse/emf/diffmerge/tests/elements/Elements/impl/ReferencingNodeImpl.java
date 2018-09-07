/*********************************************************************
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.tests.elements.Elements.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Node;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ReferencingNode;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Referencing Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ReferencingNodeImpl#getReferenced <em>Referenced</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferencingNodeImpl extends NodeImpl implements ReferencingNode {
	/**
   * The cached value of the '{@link #getReferenced() <em>Referenced</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferenced()
   * @generated
   * @ordered
   */
	protected Node referenced;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ReferencingNodeImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ElementsPackage.Literals.REFERENCING_NODE;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Node getReferenced() {
    if (referenced != null && referenced.eIsProxy()) {
      InternalEObject oldReferenced = (InternalEObject)referenced;
      referenced = (Node)eResolveProxy(oldReferenced);
      if (referenced != oldReferenced) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementsPackage.REFERENCING_NODE__REFERENCED, oldReferenced, referenced));
      }
    }
    return referenced;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Node basicGetReferenced() {
    return referenced;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setReferenced(Node newReferenced) {
    Node oldReferenced = referenced;
    referenced = newReferenced;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.REFERENCING_NODE__REFERENCED, oldReferenced, referenced));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ElementsPackage.REFERENCING_NODE__REFERENCED:
        if (resolve) return getReferenced();
        return basicGetReferenced();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ElementsPackage.REFERENCING_NODE__REFERENCED:
        setReferenced((Node)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case ElementsPackage.REFERENCING_NODE__REFERENCED:
        setReferenced((Node)null);
        return;
    }
    super.eUnset(featureID);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case ElementsPackage.REFERENCING_NODE__REFERENCED:
        return referenced != null;
    }
    return super.eIsSet(featureID);
  }

} //ReferencingNodeImpl
