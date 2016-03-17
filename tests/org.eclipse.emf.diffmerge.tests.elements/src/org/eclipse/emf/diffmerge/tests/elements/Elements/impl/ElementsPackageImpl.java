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
package org.eclipse.emf.diffmerge.tests.elements.Elements.impl;

import org.eclipse.emf.diffmerge.tests.elements.Elements.Edge;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.IdentifiedElement;
import org.eclipse.emf.diffmerge.tests.elements.Elements.NamedElement;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Node;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ReferencingNode;
import org.eclipse.emf.diffmerge.tests.elements.Elements.Root;
import org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement;

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
public class ElementsPackageImpl extends EPackageImpl implements ElementsPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass identifiedElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass namedElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass rootEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass elementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass strictElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass nodeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass edgeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass referencingNodeEClass = null;

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
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private ElementsPackageImpl() {
    super(eNS_URI, ElementsFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ElementsPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static ElementsPackage init() {
    if (isInited) return (ElementsPackage)EPackage.Registry.INSTANCE.getEPackage(ElementsPackage.eNS_URI);

    // Obtain or create and register package
    ElementsPackageImpl theElementsPackage = (ElementsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ElementsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ElementsPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theElementsPackage.createPackageContents();

    // Initialize created meta-data
    theElementsPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theElementsPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ElementsPackage.eNS_URI, theElementsPackage);
    return theElementsPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getIdentifiedElement() {
    return identifiedElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getIdentifiedElement_Id() {
    return (EAttribute)identifiedElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getNamedElement() {
    return namedElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getNamedElement_Name() {
    return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getRoot() {
    return rootEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getRoot_Name() {
    return (EAttribute)rootEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getRoot_Content() {
    return (EReference)rootEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getElement() {
    return elementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getElement_Value() {
    return (EAttribute)elementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getElement_Values() {
    return (EAttribute)elementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_ManyContent() {
    return (EReference)elementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_SingleContent() {
    return (EReference)elementEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_ManyRef() {
    return (EReference)elementEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_SingleRef() {
    return (EReference)elementEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_ManyFromSingleRef() {
    return (EReference)elementEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_SingleFromManyRef() {
    return (EReference)elementEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_ManyFromManyRef1() {
    return (EReference)elementEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getElement_ManyFromManyRef2() {
    return (EReference)elementEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getStrictElement() {
    return strictElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getStrictElement_SValue() {
    return (EAttribute)strictElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EAttribute getStrictElement_SValues() {
    return (EAttribute)strictElementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SManyContent() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SSingleContent() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SManyRef() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SSingleRef() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SManyFromSingleRef() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SSingleFromManyRef() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SManyFromManyRef1() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getStrictElement_SManyFromManyRef2() {
    return (EReference)strictElementEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getNode() {
    return nodeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getNode_Incoming() {
    return (EReference)nodeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getNode_Outgoing() {
    return (EReference)nodeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getNode_SubNodes() {
    return (EReference)nodeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getEdge() {
    return edgeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getEdge_Target() {
    return (EReference)edgeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getEdge_Source() {
    return (EReference)edgeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EClass getReferencingNode() {
    return referencingNodeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EReference getReferencingNode_Referenced() {
    return (EReference)referencingNodeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ElementsFactory getElementsFactory() {
    return (ElementsFactory)getEFactoryInstance();
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
    identifiedElementEClass = createEClass(IDENTIFIED_ELEMENT);
    createEAttribute(identifiedElementEClass, IDENTIFIED_ELEMENT__ID);

    namedElementEClass = createEClass(NAMED_ELEMENT);
    createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

    rootEClass = createEClass(ROOT);
    createEAttribute(rootEClass, ROOT__NAME);
    createEReference(rootEClass, ROOT__CONTENT);

    elementEClass = createEClass(ELEMENT);
    createEAttribute(elementEClass, ELEMENT__VALUE);
    createEAttribute(elementEClass, ELEMENT__VALUES);
    createEReference(elementEClass, ELEMENT__MANY_CONTENT);
    createEReference(elementEClass, ELEMENT__SINGLE_CONTENT);
    createEReference(elementEClass, ELEMENT__MANY_REF);
    createEReference(elementEClass, ELEMENT__SINGLE_REF);
    createEReference(elementEClass, ELEMENT__MANY_FROM_SINGLE_REF);
    createEReference(elementEClass, ELEMENT__SINGLE_FROM_MANY_REF);
    createEReference(elementEClass, ELEMENT__MANY_FROM_MANY_REF1);
    createEReference(elementEClass, ELEMENT__MANY_FROM_MANY_REF2);

    strictElementEClass = createEClass(STRICT_ELEMENT);
    createEAttribute(strictElementEClass, STRICT_ELEMENT__SVALUE);
    createEAttribute(strictElementEClass, STRICT_ELEMENT__SVALUES);
    createEReference(strictElementEClass, STRICT_ELEMENT__SMANY_CONTENT);
    createEReference(strictElementEClass, STRICT_ELEMENT__SSINGLE_CONTENT);
    createEReference(strictElementEClass, STRICT_ELEMENT__SMANY_REF);
    createEReference(strictElementEClass, STRICT_ELEMENT__SSINGLE_REF);
    createEReference(strictElementEClass, STRICT_ELEMENT__SMANY_FROM_SINGLE_REF);
    createEReference(strictElementEClass, STRICT_ELEMENT__SSINGLE_FROM_MANY_REF);
    createEReference(strictElementEClass, STRICT_ELEMENT__SMANY_FROM_MANY_REF1);
    createEReference(strictElementEClass, STRICT_ELEMENT__SMANY_FROM_MANY_REF2);

    nodeEClass = createEClass(NODE);
    createEReference(nodeEClass, NODE__INCOMING);
    createEReference(nodeEClass, NODE__OUTGOING);
    createEReference(nodeEClass, NODE__SUB_NODES);

    edgeEClass = createEClass(EDGE);
    createEReference(edgeEClass, EDGE__TARGET);
    createEReference(edgeEClass, EDGE__SOURCE);

    referencingNodeEClass = createEClass(REFERENCING_NODE);
    createEReference(referencingNodeEClass, REFERENCING_NODE__REFERENCED);
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
    namedElementEClass.getESuperTypes().add(this.getIdentifiedElement());
    rootEClass.getESuperTypes().add(this.getIdentifiedElement());
    elementEClass.getESuperTypes().add(this.getNamedElement());
    strictElementEClass.getESuperTypes().add(this.getElement());
    nodeEClass.getESuperTypes().add(this.getNamedElement());
    edgeEClass.getESuperTypes().add(this.getNamedElement());
    referencingNodeEClass.getESuperTypes().add(this.getNode());

    // Initialize classes and features; add operations and parameters
    initEClass(identifiedElementEClass, IdentifiedElement.class, "IdentifiedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIdentifiedElement_Id(), ecorePackage.getEString(), "id", null, 1, 1, IdentifiedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(rootEClass, Root.class, "Root", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRoot_Name(), ecorePackage.getEString(), "name", null, 0, 1, Root.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRoot_Content(), this.getNamedElement(), null, "content", null, 0, -1, Root.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(elementEClass, Element.class, "Element", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getElement_Value(), ecorePackage.getEInt(), "value", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getElement_Values(), ecorePackage.getEInt(), "values", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_ManyContent(), this.getElement(), null, "manyContent", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_SingleContent(), this.getElement(), null, "singleContent", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_ManyRef(), this.getElement(), null, "manyRef", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_SingleRef(), this.getElement(), null, "singleRef", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_ManyFromSingleRef(), this.getElement(), this.getElement_SingleFromManyRef(), "manyFromSingleRef", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_SingleFromManyRef(), this.getElement(), this.getElement_ManyFromSingleRef(), "singleFromManyRef", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_ManyFromManyRef1(), this.getElement(), this.getElement_ManyFromManyRef2(), "manyFromManyRef1", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElement_ManyFromManyRef2(), this.getElement(), this.getElement_ManyFromManyRef1(), "manyFromManyRef2", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(strictElementEClass, StrictElement.class, "StrictElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStrictElement_SValue(), ecorePackage.getEInt(), "sValue", null, 1, 1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getStrictElement_SValues(), ecorePackage.getEInt(), "sValues", null, 1, -1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SManyContent(), this.getElement(), null, "sManyContent", null, 1, -1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SSingleContent(), this.getElement(), null, "sSingleContent", null, 1, 1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SManyRef(), this.getElement(), null, "sManyRef", null, 1, -1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SSingleRef(), this.getElement(), null, "sSingleRef", null, 1, 1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SManyFromSingleRef(), this.getStrictElement(), this.getStrictElement_SSingleFromManyRef(), "sManyFromSingleRef", null, 1, -1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SSingleFromManyRef(), this.getStrictElement(), this.getStrictElement_SManyFromSingleRef(), "sSingleFromManyRef", null, 1, 1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SManyFromManyRef1(), this.getStrictElement(), this.getStrictElement_SManyFromManyRef2(), "sManyFromManyRef1", null, 1, -1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStrictElement_SManyFromManyRef2(), this.getStrictElement(), this.getStrictElement_SManyFromManyRef1(), "sManyFromManyRef2", null, 1, -1, StrictElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNode_Incoming(), this.getEdge(), this.getEdge_Target(), "incoming", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNode_Outgoing(), this.getEdge(), this.getEdge_Source(), "outgoing", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNode_SubNodes(), this.getNamedElement(), null, "subNodes", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(edgeEClass, Edge.class, "Edge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEdge_Target(), this.getNode(), this.getNode_Incoming(), "target", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEdge_Source(), this.getNode(), this.getNode_Outgoing(), "source", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(referencingNodeEClass, ReferencingNode.class, "ReferencingNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getReferencingNode_Referenced(), this.getNode(), null, "referenced", null, 1, 1, ReferencingNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ElementsPackageImpl
