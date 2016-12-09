/**
 * <copyright>
 * 
 * Copyright (c) 2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.model.egraphs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge;
import org.eclipse.emf.diffmerge.structures.model.egraphs.ENode;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ENode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.ENodeImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.ENodeImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.ENodeImpl#getIncoming <em>Incoming</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ENodeImpl extends MinimalEObjectImpl.Container implements ENode {
	/**
	 * The default value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected String element = ELEMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoing()
	 * @generated
	 * @ordered
	 */
	protected EList<EHyperEdge> outgoing;

	/**
	 * The cached value of the '{@link #getIncoming() <em>Incoming</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncoming()
	 * @generated
	 * @ordered
	 */
	protected EList<EHyperEdge> incoming;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ENodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EGraphsPackage.Literals.ENODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(String newElement) {
		String oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EGraphsPackage.ENODE__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EHyperEdge> getOutgoing() {
		if (outgoing == null) {
			outgoing = new EObjectContainmentWithInverseEList<EHyperEdge>(EHyperEdge.class, this, EGraphsPackage.ENODE__OUTGOING, EGraphsPackage.EHYPER_EDGE__SOURCE);
		}
		return outgoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EHyperEdge> getIncoming() {
		if (incoming == null) {
			incoming = new EObjectWithInverseResolvingEList.ManyInverse<EHyperEdge>(EHyperEdge.class, this, EGraphsPackage.ENODE__INCOMING, EGraphsPackage.EHYPER_EDGE__TARGETS);
		}
		return incoming;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EGraphsPackage.ENODE__OUTGOING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
			case EGraphsPackage.ENODE__INCOMING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncoming()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EGraphsPackage.ENODE__OUTGOING:
				return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
			case EGraphsPackage.ENODE__INCOMING:
				return ((InternalEList<?>)getIncoming()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EGraphsPackage.ENODE__ELEMENT:
				return getElement();
			case EGraphsPackage.ENODE__OUTGOING:
				return getOutgoing();
			case EGraphsPackage.ENODE__INCOMING:
				return getIncoming();
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
			case EGraphsPackage.ENODE__ELEMENT:
				setElement((String)newValue);
				return;
			case EGraphsPackage.ENODE__OUTGOING:
				getOutgoing().clear();
				getOutgoing().addAll((Collection<? extends EHyperEdge>)newValue);
				return;
			case EGraphsPackage.ENODE__INCOMING:
				getIncoming().clear();
				getIncoming().addAll((Collection<? extends EHyperEdge>)newValue);
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
			case EGraphsPackage.ENODE__ELEMENT:
				setElement(ELEMENT_EDEFAULT);
				return;
			case EGraphsPackage.ENODE__OUTGOING:
				getOutgoing().clear();
				return;
			case EGraphsPackage.ENODE__INCOMING:
				getIncoming().clear();
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
			case EGraphsPackage.ENODE__ELEMENT:
				return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
			case EGraphsPackage.ENODE__OUTGOING:
				return outgoing != null && !outgoing.isEmpty();
			case EGraphsPackage.ENODE__INCOMING:
				return incoming != null && !incoming.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (element: ");
		result.append(element);
		result.append(')');
		return result.toString();
	}

} //ENodeImpl
