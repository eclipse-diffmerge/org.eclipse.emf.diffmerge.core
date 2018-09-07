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
package org.eclipse.emf.diffmerge.tests.elements.Elements;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Strict Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSValue <em>SValue</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSValues <em>SValues</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyContent <em>SMany Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleContent <em>SSingle Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyRef <em>SMany Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleRef <em>SSingle Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromSingleRef <em>SMany From Single Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleFromManyRef <em>SSingle From Many Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromManyRef1 <em>SMany From Many Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromManyRef2 <em>SMany From Many Ref2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement()
 * @model
 * @generated
 */
public interface StrictElement extends Element {
	/**
   * Returns the value of the '<em><b>SValue</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SValue</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SValue</em>' attribute.
   * @see #setSValue(int)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SValue()
   * @model required="true"
   * @generated
   */
	int getSValue();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSValue <em>SValue</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>SValue</em>' attribute.
   * @see #getSValue()
   * @generated
   */
	void setSValue(int value);

	/**
   * Returns the value of the '<em><b>SValues</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Integer}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SValues</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SValues</em>' attribute list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SValues()
   * @model required="true"
   * @generated
   */
	EList<Integer> getSValues();

	/**
   * Returns the value of the '<em><b>SMany Content</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SMany Content</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SMany Content</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SManyContent()
   * @model containment="true" required="true"
   * @generated
   */
	EList<Element> getSManyContent();

	/**
   * Returns the value of the '<em><b>SSingle Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SSingle Content</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SSingle Content</em>' containment reference.
   * @see #setSSingleContent(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SSingleContent()
   * @model containment="true" required="true"
   * @generated
   */
	Element getSSingleContent();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleContent <em>SSingle Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>SSingle Content</em>' containment reference.
   * @see #getSSingleContent()
   * @generated
   */
  void setSSingleContent(Element value);

  /**
   * Returns the value of the '<em><b>SMany Ref</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SMany Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SMany Ref</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SManyRef()
   * @model required="true"
   * @generated
   */
	EList<Element> getSManyRef();

	/**
   * Returns the value of the '<em><b>SSingle Ref</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SSingle Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SSingle Ref</em>' reference.
   * @see #setSSingleRef(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SSingleRef()
   * @model required="true"
   * @generated
   */
	Element getSSingleRef();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleRef <em>SSingle Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>SSingle Ref</em>' reference.
   * @see #getSSingleRef()
   * @generated
   */
  void setSSingleRef(Element value);

  /**
   * Returns the value of the '<em><b>SMany From Single Ref</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleFromManyRef <em>SSingle From Many Ref</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SMany From Single Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SMany From Single Ref</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SManyFromSingleRef()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleFromManyRef
   * @model opposite="sSingleFromManyRef" required="true"
   * @generated
   */
	EList<StrictElement> getSManyFromSingleRef();

	/**
   * Returns the value of the '<em><b>SSingle From Many Ref</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromSingleRef <em>SMany From Single Ref</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SSingle From Many Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SSingle From Many Ref</em>' reference.
   * @see #setSSingleFromManyRef(StrictElement)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SSingleFromManyRef()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromSingleRef
   * @model opposite="sManyFromSingleRef" required="true"
   * @generated
   */
	StrictElement getSSingleFromManyRef();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSSingleFromManyRef <em>SSingle From Many Ref</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>SSingle From Many Ref</em>' reference.
   * @see #getSSingleFromManyRef()
   * @generated
   */
	void setSSingleFromManyRef(StrictElement value);

	/**
   * Returns the value of the '<em><b>SMany From Many Ref1</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromManyRef2 <em>SMany From Many Ref2</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SMany From Many Ref1</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SMany From Many Ref1</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SManyFromManyRef1()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromManyRef2
   * @model opposite="sManyFromManyRef2" required="true"
   * @generated
   */
	EList<StrictElement> getSManyFromManyRef1();

	/**
   * Returns the value of the '<em><b>SMany From Many Ref2</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromManyRef1 <em>SMany From Many Ref1</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SMany From Many Ref2</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>SMany From Many Ref2</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getStrictElement_SManyFromManyRef2()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement#getSManyFromManyRef1
   * @model opposite="sManyFromManyRef1" required="true"
   * @generated
   */
	EList<StrictElement> getSManyFromManyRef2();

} // StrictElement
