/*********************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.model.egraphs.impl;

import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsFactory;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge;
import org.eclipse.emf.diffmerge.structures.model.egraphs.ENode;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EGraphsPackageImpl extends EPackageImpl implements EGraphsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eGraphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eHyperEdgeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EGraphsPackageImpl() {
		super(eNS_URI, EGraphsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link EGraphsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EGraphsPackage init() {
		if (isInited) return (EGraphsPackage)EPackage.Registry.INSTANCE.getEPackage(EGraphsPackage.eNS_URI);

		// Obtain or create and register package
		EGraphsPackageImpl theEGraphsPackage = (EGraphsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EGraphsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EGraphsPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theEGraphsPackage.createPackageContents();

		// Initialize created meta-data
		theEGraphsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEGraphsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EGraphsPackage.eNS_URI, theEGraphsPackage);
		return theEGraphsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEGraph() {
		return eGraphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEGraph_Contents() {
		return (EReference)eGraphEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getENode() {
		return eNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getENode_Element() {
		return (EAttribute)eNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getENode_Outgoing() {
		return (EReference)eNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getENode_Incoming() {
		return (EReference)eNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEHyperEdge() {
		return eHyperEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEHyperEdge_Label() {
		return (EAttribute)eHyperEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEHyperEdge_Source() {
		return (EReference)eHyperEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEHyperEdge_Targets() {
		return (EReference)eHyperEdgeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EGraphsFactory getEGraphsFactory() {
		return (EGraphsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		eGraphEClass = createEClass(EGRAPH);
		createEReference(eGraphEClass, EGRAPH__CONTENTS);

		eNodeEClass = createEClass(ENODE);
		createEAttribute(eNodeEClass, ENODE__ELEMENT);
		createEReference(eNodeEClass, ENODE__OUTGOING);
		createEReference(eNodeEClass, ENODE__INCOMING);

		eHyperEdgeEClass = createEClass(EHYPER_EDGE);
		createEAttribute(eHyperEdgeEClass, EHYPER_EDGE__LABEL);
		createEReference(eHyperEdgeEClass, EHYPER_EDGE__SOURCE);
		createEReference(eHyperEdgeEClass, EHYPER_EDGE__TARGETS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(eGraphEClass, EGraph.class, "EGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEGraph_Contents(), this.getENode(), null, "contents", null, 0, -1, EGraph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eNodeEClass, ENode.class, "ENode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getENode_Element(), ecorePackage.getEString(), "element", null, 1, 1, ENode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getENode_Outgoing(), this.getEHyperEdge(), this.getEHyperEdge_Source(), "outgoing", null, 0, -1, ENode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getENode_Incoming(), this.getEHyperEdge(), this.getEHyperEdge_Targets(), "incoming", null, 0, -1, ENode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eHyperEdgeEClass, EHyperEdge.class, "EHyperEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEHyperEdge_Label(), ecorePackage.getEString(), "label", null, 0, 1, EHyperEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEHyperEdge_Source(), this.getENode(), this.getENode_Outgoing(), "source", null, 1, 1, EHyperEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEHyperEdge_Targets(), this.getENode(), this.getENode_Incoming(), "targets", null, 0, -1, EHyperEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EGraphsPackageImpl
