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
package org.eclipse.emf.diffmerge.tests.elements.Elements.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getManyContent <em>Many Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getSingleContent <em>Single Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getManyContentWithUp <em>Many Content With Up</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getUpFromManyContent <em>Up From Many Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getSingleContentWithUp <em>Single Content With Up</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getUpFromSingleContent <em>Up From Single Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getManyRef <em>Many Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getSingleRef <em>Single Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getSingleFromSingleRef1 <em>Single From Single Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getSingleFromSingleRef2 <em>Single From Single Ref2</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getManyFromSingleRef <em>Many From Single Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getSingleFromManyRef <em>Single From Many Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getManyFromManyRef1 <em>Many From Many Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.ElementImpl#getManyFromManyRef2 <em>Many From Many Ref2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ElementImpl extends NamedElementImpl implements Element {
	/**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
	protected static final int VALUE_EDEFAULT = 0;

	/**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
	protected int value = VALUE_EDEFAULT;

	/**
   * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getValues()
   * @generated
   * @ordered
   */
	protected EList<Integer> values;

	/**
   * The cached value of the '{@link #getManyContent() <em>Many Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getManyContent()
   * @generated
   * @ordered
   */
	protected EList<Element> manyContent;

	/**
   * The cached value of the '{@link #getSingleContent() <em>Single Content</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSingleContent()
   * @generated
   * @ordered
   */
	protected Element singleContent;

	/**
   * The cached value of the '{@link #getManyContentWithUp() <em>Many Content With Up</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getManyContentWithUp()
   * @generated
   * @ordered
   */
  protected EList<Element> manyContentWithUp;

  /**
   * The cached value of the '{@link #getSingleContentWithUp() <em>Single Content With Up</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSingleContentWithUp()
   * @generated
   * @ordered
   */
  protected Element singleContentWithUp;

  /**
   * The cached value of the '{@link #getManyRef() <em>Many Ref</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getManyRef()
   * @generated
   * @ordered
   */
	protected EList<Element> manyRef;

	/**
   * The cached value of the '{@link #getSingleRef() <em>Single Ref</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSingleRef()
   * @generated
   * @ordered
   */
	protected Element singleRef;

	/**
   * The cached value of the '{@link #getSingleFromSingleRef1() <em>Single From Single Ref1</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSingleFromSingleRef1()
   * @generated
   * @ordered
   */
  protected Element singleFromSingleRef1;

  /**
   * The cached value of the '{@link #getSingleFromSingleRef2() <em>Single From Single Ref2</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSingleFromSingleRef2()
   * @generated
   * @ordered
   */
  protected Element singleFromSingleRef2;

  /**
   * The cached value of the '{@link #getManyFromSingleRef() <em>Many From Single Ref</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getManyFromSingleRef()
   * @generated
   * @ordered
   */
	protected EList<Element> manyFromSingleRef;

	/**
   * The cached value of the '{@link #getSingleFromManyRef() <em>Single From Many Ref</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSingleFromManyRef()
   * @generated
   * @ordered
   */
	protected Element singleFromManyRef;

	/**
   * The cached value of the '{@link #getManyFromManyRef1() <em>Many From Many Ref1</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getManyFromManyRef1()
   * @generated
   * @ordered
   */
	protected EList<Element> manyFromManyRef1;

	/**
   * The cached value of the '{@link #getManyFromManyRef2() <em>Many From Many Ref2</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getManyFromManyRef2()
   * @generated
   * @ordered
   */
	protected EList<Element> manyFromManyRef2;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ElementImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ElementsPackage.Literals.ELEMENT;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public int getValue() {
    return value;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setValue(int newValue) {
    int oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__VALUE, oldValue, value));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Integer> getValues() {
    if (values == null) {
      values = new EDataTypeUniqueEList<Integer>(Integer.class, this, ElementsPackage.ELEMENT__VALUES);
    }
    return values;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Element> getManyContent() {
    if (manyContent == null) {
      manyContent = new EObjectContainmentEList<Element>(Element.class, this, ElementsPackage.ELEMENT__MANY_CONTENT);
    }
    return manyContent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element getSingleContent() {
    return singleContent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetSingleContent(Element newSingleContent, NotificationChain msgs) {
    Element oldSingleContent = singleContent;
    singleContent = newSingleContent;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_CONTENT, oldSingleContent, newSingleContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSingleContent(Element newSingleContent) {
    if (newSingleContent != singleContent) {
      NotificationChain msgs = null;
      if (singleContent != null)
        msgs = ((InternalEObject)singleContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementsPackage.ELEMENT__SINGLE_CONTENT, null, msgs);
      if (newSingleContent != null)
        msgs = ((InternalEObject)newSingleContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ElementsPackage.ELEMENT__SINGLE_CONTENT, null, msgs);
      msgs = basicSetSingleContent(newSingleContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_CONTENT, newSingleContent, newSingleContent));
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Element> getManyContentWithUp() {
    if (manyContentWithUp == null) {
      manyContentWithUp = new EObjectContainmentWithInverseEList<Element>(Element.class, this, ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP, ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT);
    }
    return manyContentWithUp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element getUpFromManyContent() {
    if (eContainerFeatureID() != ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT) return null;
    return (Element)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUpFromManyContent(Element newUpFromManyContent, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject)newUpFromManyContent, ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpFromManyContent(Element newUpFromManyContent) {
    if (newUpFromManyContent != eInternalContainer() || (eContainerFeatureID() != ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT && newUpFromManyContent != null)) {
      if (EcoreUtil.isAncestor(this, newUpFromManyContent))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newUpFromManyContent != null)
        msgs = ((InternalEObject)newUpFromManyContent).eInverseAdd(this, ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP, Element.class, msgs);
      msgs = basicSetUpFromManyContent(newUpFromManyContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT, newUpFromManyContent, newUpFromManyContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element getSingleContentWithUp() {
    return singleContentWithUp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSingleContentWithUp(Element newSingleContentWithUp, NotificationChain msgs) {
    Element oldSingleContentWithUp = singleContentWithUp;
    singleContentWithUp = newSingleContentWithUp;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP, oldSingleContentWithUp, newSingleContentWithUp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSingleContentWithUp(Element newSingleContentWithUp) {
    if (newSingleContentWithUp != singleContentWithUp) {
      NotificationChain msgs = null;
      if (singleContentWithUp != null)
        msgs = ((InternalEObject)singleContentWithUp).eInverseRemove(this, ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT, Element.class, msgs);
      if (newSingleContentWithUp != null)
        msgs = ((InternalEObject)newSingleContentWithUp).eInverseAdd(this, ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT, Element.class, msgs);
      msgs = basicSetSingleContentWithUp(newSingleContentWithUp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP, newSingleContentWithUp, newSingleContentWithUp));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element getUpFromSingleContent() {
    if (eContainerFeatureID() != ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT) return null;
    return (Element)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUpFromSingleContent(Element newUpFromSingleContent, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject)newUpFromSingleContent, ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpFromSingleContent(Element newUpFromSingleContent) {
    if (newUpFromSingleContent != eInternalContainer() || (eContainerFeatureID() != ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT && newUpFromSingleContent != null)) {
      if (EcoreUtil.isAncestor(this, newUpFromSingleContent))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newUpFromSingleContent != null)
        msgs = ((InternalEObject)newUpFromSingleContent).eInverseAdd(this, ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP, Element.class, msgs);
      msgs = basicSetUpFromSingleContent(newUpFromSingleContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT, newUpFromSingleContent, newUpFromSingleContent));
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Element> getManyRef() {
    if (manyRef == null) {
      manyRef = new EObjectResolvingEList<Element>(Element.class, this, ElementsPackage.ELEMENT__MANY_REF);
    }
    return manyRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element getSingleRef() {
    if (singleRef != null && singleRef.eIsProxy()) {
      InternalEObject oldSingleRef = (InternalEObject)singleRef;
      singleRef = (Element)eResolveProxy(oldSingleRef);
      if (singleRef != oldSingleRef) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementsPackage.ELEMENT__SINGLE_REF, oldSingleRef, singleRef));
      }
    }
    return singleRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element basicGetSingleRef() {
    return singleRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSingleRef(Element newSingleRef) {
    Element oldSingleRef = singleRef;
    singleRef = newSingleRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_REF, oldSingleRef, singleRef));
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element getSingleFromSingleRef1() {
    if (singleFromSingleRef1 != null && singleFromSingleRef1.eIsProxy()) {
      InternalEObject oldSingleFromSingleRef1 = (InternalEObject)singleFromSingleRef1;
      singleFromSingleRef1 = (Element)eResolveProxy(oldSingleFromSingleRef1);
      if (singleFromSingleRef1 != oldSingleFromSingleRef1) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1, oldSingleFromSingleRef1, singleFromSingleRef1));
      }
    }
    return singleFromSingleRef1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element basicGetSingleFromSingleRef1() {
    return singleFromSingleRef1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSingleFromSingleRef1(Element newSingleFromSingleRef1, NotificationChain msgs) {
    Element oldSingleFromSingleRef1 = singleFromSingleRef1;
    singleFromSingleRef1 = newSingleFromSingleRef1;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1, oldSingleFromSingleRef1, newSingleFromSingleRef1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSingleFromSingleRef1(Element newSingleFromSingleRef1) {
    if (newSingleFromSingleRef1 != singleFromSingleRef1) {
      NotificationChain msgs = null;
      if (singleFromSingleRef1 != null)
        msgs = ((InternalEObject)singleFromSingleRef1).eInverseRemove(this, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2, Element.class, msgs);
      if (newSingleFromSingleRef1 != null)
        msgs = ((InternalEObject)newSingleFromSingleRef1).eInverseAdd(this, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2, Element.class, msgs);
      msgs = basicSetSingleFromSingleRef1(newSingleFromSingleRef1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1, newSingleFromSingleRef1, newSingleFromSingleRef1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element getSingleFromSingleRef2() {
    if (singleFromSingleRef2 != null && singleFromSingleRef2.eIsProxy()) {
      InternalEObject oldSingleFromSingleRef2 = (InternalEObject)singleFromSingleRef2;
      singleFromSingleRef2 = (Element)eResolveProxy(oldSingleFromSingleRef2);
      if (singleFromSingleRef2 != oldSingleFromSingleRef2) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2, oldSingleFromSingleRef2, singleFromSingleRef2));
      }
    }
    return singleFromSingleRef2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element basicGetSingleFromSingleRef2() {
    return singleFromSingleRef2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSingleFromSingleRef2(Element newSingleFromSingleRef2, NotificationChain msgs) {
    Element oldSingleFromSingleRef2 = singleFromSingleRef2;
    singleFromSingleRef2 = newSingleFromSingleRef2;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2, oldSingleFromSingleRef2, newSingleFromSingleRef2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSingleFromSingleRef2(Element newSingleFromSingleRef2) {
    if (newSingleFromSingleRef2 != singleFromSingleRef2) {
      NotificationChain msgs = null;
      if (singleFromSingleRef2 != null)
        msgs = ((InternalEObject)singleFromSingleRef2).eInverseRemove(this, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1, Element.class, msgs);
      if (newSingleFromSingleRef2 != null)
        msgs = ((InternalEObject)newSingleFromSingleRef2).eInverseAdd(this, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1, Element.class, msgs);
      msgs = basicSetSingleFromSingleRef2(newSingleFromSingleRef2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2, newSingleFromSingleRef2, newSingleFromSingleRef2));
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Element> getManyFromSingleRef() {
    if (manyFromSingleRef == null) {
      manyFromSingleRef = new EObjectWithInverseResolvingEList<Element>(Element.class, this, ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF, ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF);
    }
    return manyFromSingleRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element getSingleFromManyRef() {
    if (singleFromManyRef != null && singleFromManyRef.eIsProxy()) {
      InternalEObject oldSingleFromManyRef = (InternalEObject)singleFromManyRef;
      singleFromManyRef = (Element)eResolveProxy(oldSingleFromManyRef);
      if (singleFromManyRef != oldSingleFromManyRef) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF, oldSingleFromManyRef, singleFromManyRef));
      }
    }
    return singleFromManyRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element basicGetSingleFromManyRef() {
    return singleFromManyRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetSingleFromManyRef(Element newSingleFromManyRef, NotificationChain msgs) {
    Element oldSingleFromManyRef = singleFromManyRef;
    singleFromManyRef = newSingleFromManyRef;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF, oldSingleFromManyRef, newSingleFromManyRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSingleFromManyRef(Element newSingleFromManyRef) {
    if (newSingleFromManyRef != singleFromManyRef) {
      NotificationChain msgs = null;
      if (singleFromManyRef != null)
        msgs = ((InternalEObject)singleFromManyRef).eInverseRemove(this, ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF, Element.class, msgs);
      if (newSingleFromManyRef != null)
        msgs = ((InternalEObject)newSingleFromManyRef).eInverseAdd(this, ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF, Element.class, msgs);
      msgs = basicSetSingleFromManyRef(newSingleFromManyRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF, newSingleFromManyRef, newSingleFromManyRef));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Element> getManyFromManyRef1() {
    if (manyFromManyRef1 == null) {
      manyFromManyRef1 = new EObjectWithInverseResolvingEList.ManyInverse<Element>(Element.class, this, ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1, ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2);
    }
    return manyFromManyRef1;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Element> getManyFromManyRef2() {
    if (manyFromManyRef2 == null) {
      manyFromManyRef2 = new EObjectWithInverseResolvingEList.ManyInverse<Element>(Element.class, this, ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2, ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1);
    }
    return manyFromManyRef2;
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
      case ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getManyContentWithUp()).basicAdd(otherEnd, msgs);
      case ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetUpFromManyContent((Element)otherEnd, msgs);
      case ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP:
        if (singleContentWithUp != null)
          msgs = ((InternalEObject)singleContentWithUp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP, null, msgs);
        return basicSetSingleContentWithUp((Element)otherEnd, msgs);
      case ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetUpFromSingleContent((Element)otherEnd, msgs);
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1:
        if (singleFromSingleRef1 != null)
          msgs = ((InternalEObject)singleFromSingleRef1).eInverseRemove(this, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2, Element.class, msgs);
        return basicSetSingleFromSingleRef1((Element)otherEnd, msgs);
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2:
        if (singleFromSingleRef2 != null)
          msgs = ((InternalEObject)singleFromSingleRef2).eInverseRemove(this, ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1, Element.class, msgs);
        return basicSetSingleFromSingleRef2((Element)otherEnd, msgs);
      case ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getManyFromSingleRef()).basicAdd(otherEnd, msgs);
      case ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF:
        if (singleFromManyRef != null)
          msgs = ((InternalEObject)singleFromManyRef).eInverseRemove(this, ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF, Element.class, msgs);
        return basicSetSingleFromManyRef((Element)otherEnd, msgs);
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getManyFromManyRef1()).basicAdd(otherEnd, msgs);
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getManyFromManyRef2()).basicAdd(otherEnd, msgs);
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
      case ElementsPackage.ELEMENT__MANY_CONTENT:
        return ((InternalEList<?>)getManyContent()).basicRemove(otherEnd, msgs);
      case ElementsPackage.ELEMENT__SINGLE_CONTENT:
        return basicSetSingleContent(null, msgs);
      case ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP:
        return ((InternalEList<?>)getManyContentWithUp()).basicRemove(otherEnd, msgs);
      case ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT:
        return basicSetUpFromManyContent(null, msgs);
      case ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP:
        return basicSetSingleContentWithUp(null, msgs);
      case ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT:
        return basicSetUpFromSingleContent(null, msgs);
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1:
        return basicSetSingleFromSingleRef1(null, msgs);
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2:
        return basicSetSingleFromSingleRef2(null, msgs);
      case ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF:
        return ((InternalEList<?>)getManyFromSingleRef()).basicRemove(otherEnd, msgs);
      case ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF:
        return basicSetSingleFromManyRef(null, msgs);
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1:
        return ((InternalEList<?>)getManyFromManyRef1()).basicRemove(otherEnd, msgs);
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2:
        return ((InternalEList<?>)getManyFromManyRef2()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID()) {
      case ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT:
        return eInternalContainer().eInverseRemove(this, ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP, Element.class, msgs);
      case ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT:
        return eInternalContainer().eInverseRemove(this, ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP, Element.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ElementsPackage.ELEMENT__VALUE:
        return getValue();
      case ElementsPackage.ELEMENT__VALUES:
        return getValues();
      case ElementsPackage.ELEMENT__MANY_CONTENT:
        return getManyContent();
      case ElementsPackage.ELEMENT__SINGLE_CONTENT:
        return getSingleContent();
      case ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP:
        return getManyContentWithUp();
      case ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT:
        return getUpFromManyContent();
      case ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP:
        return getSingleContentWithUp();
      case ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT:
        return getUpFromSingleContent();
      case ElementsPackage.ELEMENT__MANY_REF:
        return getManyRef();
      case ElementsPackage.ELEMENT__SINGLE_REF:
        if (resolve) return getSingleRef();
        return basicGetSingleRef();
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1:
        if (resolve) return getSingleFromSingleRef1();
        return basicGetSingleFromSingleRef1();
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2:
        if (resolve) return getSingleFromSingleRef2();
        return basicGetSingleFromSingleRef2();
      case ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF:
        return getManyFromSingleRef();
      case ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF:
        if (resolve) return getSingleFromManyRef();
        return basicGetSingleFromManyRef();
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1:
        return getManyFromManyRef1();
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2:
        return getManyFromManyRef2();
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
      case ElementsPackage.ELEMENT__VALUE:
        setValue((Integer)newValue);
        return;
      case ElementsPackage.ELEMENT__VALUES:
        getValues().clear();
        getValues().addAll((Collection<? extends Integer>)newValue);
        return;
      case ElementsPackage.ELEMENT__MANY_CONTENT:
        getManyContent().clear();
        getManyContent().addAll((Collection<? extends Element>)newValue);
        return;
      case ElementsPackage.ELEMENT__SINGLE_CONTENT:
        setSingleContent((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP:
        getManyContentWithUp().clear();
        getManyContentWithUp().addAll((Collection<? extends Element>)newValue);
        return;
      case ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT:
        setUpFromManyContent((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP:
        setSingleContentWithUp((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT:
        setUpFromSingleContent((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__MANY_REF:
        getManyRef().clear();
        getManyRef().addAll((Collection<? extends Element>)newValue);
        return;
      case ElementsPackage.ELEMENT__SINGLE_REF:
        setSingleRef((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1:
        setSingleFromSingleRef1((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2:
        setSingleFromSingleRef2((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF:
        getManyFromSingleRef().clear();
        getManyFromSingleRef().addAll((Collection<? extends Element>)newValue);
        return;
      case ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF:
        setSingleFromManyRef((Element)newValue);
        return;
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1:
        getManyFromManyRef1().clear();
        getManyFromManyRef1().addAll((Collection<? extends Element>)newValue);
        return;
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2:
        getManyFromManyRef2().clear();
        getManyFromManyRef2().addAll((Collection<? extends Element>)newValue);
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
      case ElementsPackage.ELEMENT__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case ElementsPackage.ELEMENT__VALUES:
        getValues().clear();
        return;
      case ElementsPackage.ELEMENT__MANY_CONTENT:
        getManyContent().clear();
        return;
      case ElementsPackage.ELEMENT__SINGLE_CONTENT:
        setSingleContent((Element)null);
        return;
      case ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP:
        getManyContentWithUp().clear();
        return;
      case ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT:
        setUpFromManyContent((Element)null);
        return;
      case ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP:
        setSingleContentWithUp((Element)null);
        return;
      case ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT:
        setUpFromSingleContent((Element)null);
        return;
      case ElementsPackage.ELEMENT__MANY_REF:
        getManyRef().clear();
        return;
      case ElementsPackage.ELEMENT__SINGLE_REF:
        setSingleRef((Element)null);
        return;
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1:
        setSingleFromSingleRef1((Element)null);
        return;
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2:
        setSingleFromSingleRef2((Element)null);
        return;
      case ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF:
        getManyFromSingleRef().clear();
        return;
      case ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF:
        setSingleFromManyRef((Element)null);
        return;
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1:
        getManyFromManyRef1().clear();
        return;
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2:
        getManyFromManyRef2().clear();
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
      case ElementsPackage.ELEMENT__VALUE:
        return value != VALUE_EDEFAULT;
      case ElementsPackage.ELEMENT__VALUES:
        return values != null && !values.isEmpty();
      case ElementsPackage.ELEMENT__MANY_CONTENT:
        return manyContent != null && !manyContent.isEmpty();
      case ElementsPackage.ELEMENT__SINGLE_CONTENT:
        return singleContent != null;
      case ElementsPackage.ELEMENT__MANY_CONTENT_WITH_UP:
        return manyContentWithUp != null && !manyContentWithUp.isEmpty();
      case ElementsPackage.ELEMENT__UP_FROM_MANY_CONTENT:
        return getUpFromManyContent() != null;
      case ElementsPackage.ELEMENT__SINGLE_CONTENT_WITH_UP:
        return singleContentWithUp != null;
      case ElementsPackage.ELEMENT__UP_FROM_SINGLE_CONTENT:
        return getUpFromSingleContent() != null;
      case ElementsPackage.ELEMENT__MANY_REF:
        return manyRef != null && !manyRef.isEmpty();
      case ElementsPackage.ELEMENT__SINGLE_REF:
        return singleRef != null;
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF1:
        return singleFromSingleRef1 != null;
      case ElementsPackage.ELEMENT__SINGLE_FROM_SINGLE_REF2:
        return singleFromSingleRef2 != null;
      case ElementsPackage.ELEMENT__MANY_FROM_SINGLE_REF:
        return manyFromSingleRef != null && !manyFromSingleRef.isEmpty();
      case ElementsPackage.ELEMENT__SINGLE_FROM_MANY_REF:
        return singleFromManyRef != null;
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF1:
        return manyFromManyRef1 != null && !manyFromManyRef1.isEmpty();
      case ElementsPackage.ELEMENT__MANY_FROM_MANY_REF2:
        return manyFromManyRef2 != null && !manyFromManyRef2.isEmpty();
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
    result.append(" (value: ");
    result.append(value);
    result.append(", values: ");
    result.append(values);
    result.append(')');
    return result.toString();
  }

} //ElementImpl
