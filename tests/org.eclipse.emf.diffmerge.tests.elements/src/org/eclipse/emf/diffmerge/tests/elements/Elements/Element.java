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
package org.eclipse.emf.diffmerge.tests.elements.Elements;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getValues <em>Values</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyContent <em>Many Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleContent <em>Single Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyContentWithUp <em>Many Content With Up</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromManyContent <em>Up From Many Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleContentWithUp <em>Single Content With Up</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromSingleContent <em>Up From Single Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyRef <em>Many Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleRef <em>Single Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef1 <em>Single From Single Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef2 <em>Single From Single Ref2</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromSingleRef <em>Many From Single Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromManyRef <em>Single From Many Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromManyRef1 <em>Many From Many Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromManyRef2 <em>Many From Many Ref2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement()
 * @model
 * @generated
 */
public interface Element extends NamedElement {
	/**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(int)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_Value()
   * @model
   * @generated
   */
	int getValue();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
	void setValue(int value);

	/**
   * Returns the value of the '<em><b>Values</b></em>' attribute list.
   * The list contents are of type {@link java.lang.Integer}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' attribute list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_Values()
   * @model
   * @generated
   */
	EList<Integer> getValues();

	/**
   * Returns the value of the '<em><b>Many Content</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many Content</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Many Content</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_ManyContent()
   * @model containment="true"
   * @generated
   */
	EList<Element> getManyContent();

	/**
   * Returns the value of the '<em><b>Single Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Single Content</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Single Content</em>' containment reference.
   * @see #setSingleContent(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_SingleContent()
   * @model containment="true"
   * @generated
   */
	Element getSingleContent();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleContent <em>Single Content</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single Content</em>' containment reference.
   * @see #getSingleContent()
   * @generated
   */
	void setSingleContent(Element value);

	/**
   * Returns the value of the '<em><b>Many Content With Up</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromManyContent <em>Up From Many Content</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Many Content With Up</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Many Content With Up</em>' containment reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_ManyContentWithUp()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromManyContent
   * @model opposite="upFromManyContent" containment="true"
   * @generated
   */
  EList<Element> getManyContentWithUp();

  /**
   * Returns the value of the '<em><b>Up From Many Content</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyContentWithUp <em>Many Content With Up</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Up From Many Content</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Up From Many Content</em>' container reference.
   * @see #setUpFromManyContent(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_UpFromManyContent()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyContentWithUp
   * @model opposite="manyContentWithUp" transient="false"
   * @generated
   */
  Element getUpFromManyContent();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromManyContent <em>Up From Many Content</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Up From Many Content</em>' container reference.
   * @see #getUpFromManyContent()
   * @generated
   */
  void setUpFromManyContent(Element value);

  /**
   * Returns the value of the '<em><b>Single Content With Up</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromSingleContent <em>Up From Single Content</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Single Content With Up</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Single Content With Up</em>' containment reference.
   * @see #setSingleContentWithUp(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_SingleContentWithUp()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromSingleContent
   * @model opposite="upFromSingleContent" containment="true"
   * @generated
   */
  Element getSingleContentWithUp();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleContentWithUp <em>Single Content With Up</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single Content With Up</em>' containment reference.
   * @see #getSingleContentWithUp()
   * @generated
   */
  void setSingleContentWithUp(Element value);

  /**
   * Returns the value of the '<em><b>Up From Single Content</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleContentWithUp <em>Single Content With Up</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Up From Single Content</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Up From Single Content</em>' container reference.
   * @see #setUpFromSingleContent(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_UpFromSingleContent()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleContentWithUp
   * @model opposite="singleContentWithUp" transient="false"
   * @generated
   */
  Element getUpFromSingleContent();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getUpFromSingleContent <em>Up From Single Content</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Up From Single Content</em>' container reference.
   * @see #getUpFromSingleContent()
   * @generated
   */
  void setUpFromSingleContent(Element value);

  /**
   * Returns the value of the '<em><b>Many Ref</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Many Ref</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_ManyRef()
   * @model
   * @generated
   */
	EList<Element> getManyRef();

	/**
   * Returns the value of the '<em><b>Single Ref</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Single Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Single Ref</em>' reference.
   * @see #setSingleRef(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_SingleRef()
   * @model
   * @generated
   */
	Element getSingleRef();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleRef <em>Single Ref</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single Ref</em>' reference.
   * @see #getSingleRef()
   * @generated
   */
	void setSingleRef(Element value);

	/**
   * Returns the value of the '<em><b>Single From Single Ref1</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef2 <em>Single From Single Ref2</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Single From Single Ref1</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Single From Single Ref1</em>' reference.
   * @see #setSingleFromSingleRef1(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_SingleFromSingleRef1()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef2
   * @model opposite="singleFromSingleRef2"
   * @generated
   */
  Element getSingleFromSingleRef1();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef1 <em>Single From Single Ref1</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single From Single Ref1</em>' reference.
   * @see #getSingleFromSingleRef1()
   * @generated
   */
  void setSingleFromSingleRef1(Element value);

  /**
   * Returns the value of the '<em><b>Single From Single Ref2</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef1 <em>Single From Single Ref1</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Single From Single Ref2</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Single From Single Ref2</em>' reference.
   * @see #setSingleFromSingleRef2(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_SingleFromSingleRef2()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef1
   * @model opposite="singleFromSingleRef1"
   * @generated
   */
  Element getSingleFromSingleRef2();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromSingleRef2 <em>Single From Single Ref2</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single From Single Ref2</em>' reference.
   * @see #getSingleFromSingleRef2()
   * @generated
   */
  void setSingleFromSingleRef2(Element value);

  /**
   * Returns the value of the '<em><b>Many From Single Ref</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromManyRef <em>Single From Many Ref</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many From Single Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Many From Single Ref</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_ManyFromSingleRef()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromManyRef
   * @model opposite="singleFromManyRef"
   * @generated
   */
	EList<Element> getManyFromSingleRef();

	/**
   * Returns the value of the '<em><b>Single From Many Ref</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromSingleRef <em>Many From Single Ref</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Single From Many Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Single From Many Ref</em>' reference.
   * @see #setSingleFromManyRef(Element)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_SingleFromManyRef()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromSingleRef
   * @model opposite="manyFromSingleRef"
   * @generated
   */
	Element getSingleFromManyRef();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getSingleFromManyRef <em>Single From Many Ref</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Single From Many Ref</em>' reference.
   * @see #getSingleFromManyRef()
   * @generated
   */
	void setSingleFromManyRef(Element value);

	/**
   * Returns the value of the '<em><b>Many From Many Ref1</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromManyRef2 <em>Many From Many Ref2</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many From Many Ref1</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Many From Many Ref1</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_ManyFromManyRef1()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromManyRef2
   * @model opposite="manyFromManyRef2"
   * @generated
   */
	EList<Element> getManyFromManyRef1();

	/**
   * Returns the value of the '<em><b>Many From Many Ref2</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromManyRef1 <em>Many From Many Ref1</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many From Many Ref2</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Many From Many Ref2</em>' reference list.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getElement_ManyFromManyRef2()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element#getManyFromManyRef1
   * @model opposite="manyFromManyRef1"
   * @generated
   */
	EList<Element> getManyFromManyRef2();

} // Element
