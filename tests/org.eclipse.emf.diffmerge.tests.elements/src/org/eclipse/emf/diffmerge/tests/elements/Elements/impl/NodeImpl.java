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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.diffmerge.tests.elements.Elements.Edge;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.NamedElement;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Node;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.NodeImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.NodeImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.NodeImpl#getSubNodes <em>Sub Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends NamedElementImpl implements Node {
	/**
   * The cached value of the '{@link #getIncoming() <em>Incoming</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getIncoming()
   * @generated
   * @ordered
   */
	protected EList<Edge> incoming;

	/**
   * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOutgoing()
   * @generated
   * @ordered
   */
	protected EList<Edge> outgoing;

	/**
   * The cached value of the '{@link #getSubNodes() <em>Sub Nodes</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSubNodes()
   * @generated
   * @ordered
   */
	protected EList<NamedElement> subNodes;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected NodeImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ElementsPackage.Literals.NODE;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Edge> getIncoming() {
    if (incoming == null) {
      incoming = new EObjectWithInverseResolvingEList<Edge>(Edge.class, this, ElementsPackage.NODE__INCOMING, ElementsPackage.EDGE__TARGET);
    }
    return incoming;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Edge> getOutgoing() {
    if (outgoing == null) {
      outgoing = new EObjectWithInverseResolvingEList<Edge>(Edge.class, this, ElementsPackage.NODE__OUTGOING, ElementsPackage.EDGE__SOURCE);
    }
    return outgoing;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<NamedElement> getSubNodes() {
    if (subNodes == null) {
      subNodes = new EObjectContainmentEList<NamedElement>(NamedElement.class, this, ElementsPackage.NODE__SUB_NODES);
    }
    return subNodes;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ElementsPackage.NODE__INCOMING:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncoming()).basicAdd(otherEnd, msgs);
      case ElementsPackage.NODE__OUTGOING:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
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
      case ElementsPackage.NODE__INCOMING:
        return ((InternalEList<?>)getIncoming()).basicRemove(otherEnd, msgs);
      case ElementsPackage.NODE__OUTGOING:
        return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
      case ElementsPackage.NODE__SUB_NODES:
        return ((InternalEList<?>)getSubNodes()).basicRemove(otherEnd, msgs);
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
      case ElementsPackage.NODE__INCOMING:
        return getIncoming();
      case ElementsPackage.NODE__OUTGOING:
        return getOutgoing();
      case ElementsPackage.NODE__SUB_NODES:
        return getSubNodes();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ElementsPackage.NODE__INCOMING:
        getIncoming().clear();
        getIncoming().addAll((Collection<? extends Edge>)newValue);
        return;
      case ElementsPackage.NODE__OUTGOING:
        getOutgoing().clear();
        getOutgoing().addAll((Collection<? extends Edge>)newValue);
        return;
      case ElementsPackage.NODE__SUB_NODES:
        getSubNodes().clear();
        getSubNodes().addAll((Collection<? extends NamedElement>)newValue);
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
      case ElementsPackage.NODE__INCOMING:
        getIncoming().clear();
        return;
      case ElementsPackage.NODE__OUTGOING:
        getOutgoing().clear();
        return;
      case ElementsPackage.NODE__SUB_NODES:
        getSubNodes().clear();
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
      case ElementsPackage.NODE__INCOMING:
        return incoming != null && !incoming.isEmpty();
      case ElementsPackage.NODE__OUTGOING:
        return outgoing != null && !outgoing.isEmpty();
      case ElementsPackage.NODE__SUB_NODES:
        return subNodes != null && !subNodes.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //NodeImpl
