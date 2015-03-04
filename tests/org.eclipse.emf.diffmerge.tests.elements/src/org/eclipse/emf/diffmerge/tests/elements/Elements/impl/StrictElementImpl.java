/**
 */
package org.eclipse.emf.diffmerge.tests.elements.Elements.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.diffmerge.tests.elements.Elements.Element;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Strict Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSValue <em>SValue</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSValues <em>SValues</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSManyContent <em>SMany Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSSingleContent <em>SSingle Content</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSManyRef <em>SMany Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSSingleRef <em>SSingle Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSManyFromSingleRef <em>SMany From Single Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSSingleFromManyRef <em>SSingle From Many Ref</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSManyFromManyRef1 <em>SMany From Many Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.impl.StrictElementImpl#getSManyFromManyRef2 <em>SMany From Many Ref2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StrictElementImpl extends ElementImpl implements StrictElement {
	/**
   * The default value of the '{@link #getSValue() <em>SValue</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSValue()
   * @generated
   * @ordered
   */
	protected static final int SVALUE_EDEFAULT = 0;

	/**
   * The cached value of the '{@link #getSValue() <em>SValue</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSValue()
   * @generated
   * @ordered
   */
	protected int sValue = SVALUE_EDEFAULT;

	/**
   * The cached value of the '{@link #getSValues() <em>SValues</em>}' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSValues()
   * @generated
   * @ordered
   */
	protected EList<Integer> sValues;

	/**
   * The cached value of the '{@link #getSManyContent() <em>SMany Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSManyContent()
   * @generated
   * @ordered
   */
	protected EList<Element> sManyContent;

	/**
   * The cached value of the '{@link #getSSingleContent() <em>SSingle Content</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSSingleContent()
   * @generated
   * @ordered
   */
	protected Element sSingleContent;

	/**
   * The cached value of the '{@link #getSManyRef() <em>SMany Ref</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSManyRef()
   * @generated
   * @ordered
   */
	protected EList<Element> sManyRef;

	/**
   * The cached value of the '{@link #getSSingleRef() <em>SSingle Ref</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSSingleRef()
   * @generated
   * @ordered
   */
	protected Element sSingleRef;

	/**
   * The cached value of the '{@link #getSManyFromSingleRef() <em>SMany From Single Ref</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSManyFromSingleRef()
   * @generated
   * @ordered
   */
	protected EList<StrictElement> sManyFromSingleRef;

	/**
   * The cached value of the '{@link #getSSingleFromManyRef() <em>SSingle From Many Ref</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSSingleFromManyRef()
   * @generated
   * @ordered
   */
	protected StrictElement sSingleFromManyRef;

	/**
   * The cached value of the '{@link #getSManyFromManyRef1() <em>SMany From Many Ref1</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSManyFromManyRef1()
   * @generated
   * @ordered
   */
	protected EList<StrictElement> sManyFromManyRef1;

	/**
   * The cached value of the '{@link #getSManyFromManyRef2() <em>SMany From Many Ref2</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSManyFromManyRef2()
   * @generated
   * @ordered
   */
	protected EList<StrictElement> sManyFromManyRef2;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StrictElementImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ElementsPackage.Literals.STRICT_ELEMENT;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public int getSValue() {
    return sValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSValue(int newSValue) {
    int oldSValue = sValue;
    sValue = newSValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.STRICT_ELEMENT__SVALUE, oldSValue, sValue));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Integer> getSValues() {
    if (sValues == null) {
      sValues = new EDataTypeUniqueEList<Integer>(Integer.class, this, ElementsPackage.STRICT_ELEMENT__SVALUES);
    }
    return sValues;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Element> getSManyContent() {
    if (sManyContent == null) {
      sManyContent = new EObjectContainmentEList<Element>(Element.class, this, ElementsPackage.STRICT_ELEMENT__SMANY_CONTENT);
    }
    return sManyContent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element getSSingleContent() {
    return sSingleContent;
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSSingleContent(Element newSSingleContent, NotificationChain msgs) {
    Element oldSSingleContent = sSingleContent;
    sSingleContent = newSSingleContent;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT, oldSSingleContent, newSSingleContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSSingleContent(Element newSSingleContent) {
    if (newSSingleContent != sSingleContent) {
      NotificationChain msgs = null;
      if (sSingleContent != null)
        msgs = ((InternalEObject)sSingleContent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT, null, msgs);
      if (newSSingleContent != null)
        msgs = ((InternalEObject)newSSingleContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT, null, msgs);
      msgs = basicSetSSingleContent(newSSingleContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT, newSSingleContent, newSSingleContent));
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<Element> getSManyRef() {
    if (sManyRef == null) {
      sManyRef = new EObjectResolvingEList<Element>(Element.class, this, ElementsPackage.STRICT_ELEMENT__SMANY_REF);
    }
    return sManyRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element getSSingleRef() {
    if (sSingleRef != null && sSingleRef.eIsProxy()) {
      InternalEObject oldSSingleRef = (InternalEObject)sSingleRef;
      sSingleRef = (Element)eResolveProxy(oldSSingleRef);
      if (sSingleRef != oldSSingleRef) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementsPackage.STRICT_ELEMENT__SSINGLE_REF, oldSSingleRef, sSingleRef));
      }
    }
    return sSingleRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Element basicGetSSingleRef() {
    return sSingleRef;
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSSingleRef(Element newSSingleRef) {
    Element oldSSingleRef = sSingleRef;
    sSingleRef = newSSingleRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.STRICT_ELEMENT__SSINGLE_REF, oldSSingleRef, sSingleRef));
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<StrictElement> getSManyFromSingleRef() {
    if (sManyFromSingleRef == null) {
      sManyFromSingleRef = new EObjectWithInverseResolvingEList<StrictElement>(StrictElement.class, this, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF, ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF);
    }
    return sManyFromSingleRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StrictElement getSSingleFromManyRef() {
    if (sSingleFromManyRef != null && sSingleFromManyRef.eIsProxy()) {
      InternalEObject oldSSingleFromManyRef = (InternalEObject)sSingleFromManyRef;
      sSingleFromManyRef = (StrictElement)eResolveProxy(oldSSingleFromManyRef);
      if (sSingleFromManyRef != oldSSingleFromManyRef) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF, oldSSingleFromManyRef, sSingleFromManyRef));
      }
    }
    return sSingleFromManyRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StrictElement basicGetSSingleFromManyRef() {
    return sSingleFromManyRef;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetSSingleFromManyRef(StrictElement newSSingleFromManyRef, NotificationChain msgs) {
    StrictElement oldSSingleFromManyRef = sSingleFromManyRef;
    sSingleFromManyRef = newSSingleFromManyRef;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF, oldSSingleFromManyRef, newSSingleFromManyRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSSingleFromManyRef(StrictElement newSSingleFromManyRef) {
    if (newSSingleFromManyRef != sSingleFromManyRef) {
      NotificationChain msgs = null;
      if (sSingleFromManyRef != null)
        msgs = ((InternalEObject)sSingleFromManyRef).eInverseRemove(this, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF, StrictElement.class, msgs);
      if (newSSingleFromManyRef != null)
        msgs = ((InternalEObject)newSSingleFromManyRef).eInverseAdd(this, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF, StrictElement.class, msgs);
      msgs = basicSetSSingleFromManyRef(newSSingleFromManyRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF, newSSingleFromManyRef, newSSingleFromManyRef));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<StrictElement> getSManyFromManyRef1() {
    if (sManyFromManyRef1 == null) {
      sManyFromManyRef1 = new EObjectWithInverseResolvingEList.ManyInverse<StrictElement>(StrictElement.class, this, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2);
    }
    return sManyFromManyRef1;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<StrictElement> getSManyFromManyRef2() {
    if (sManyFromManyRef2 == null) {
      sManyFromManyRef2 = new EObjectWithInverseResolvingEList.ManyInverse<StrictElement>(StrictElement.class, this, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1);
    }
    return sManyFromManyRef2;
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
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getSManyFromSingleRef()).basicAdd(otherEnd, msgs);
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF:
        if (sSingleFromManyRef != null)
          msgs = ((InternalEObject)sSingleFromManyRef).eInverseRemove(this, ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF, StrictElement.class, msgs);
        return basicSetSSingleFromManyRef((StrictElement)otherEnd, msgs);
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getSManyFromManyRef1()).basicAdd(otherEnd, msgs);
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getSManyFromManyRef2()).basicAdd(otherEnd, msgs);
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
      case ElementsPackage.STRICT_ELEMENT__SMANY_CONTENT:
        return ((InternalEList<?>)getSManyContent()).basicRemove(otherEnd, msgs);
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT:
        return basicSetSSingleContent(null, msgs);
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF:
        return ((InternalEList<?>)getSManyFromSingleRef()).basicRemove(otherEnd, msgs);
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF:
        return basicSetSSingleFromManyRef(null, msgs);
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1:
        return ((InternalEList<?>)getSManyFromManyRef1()).basicRemove(otherEnd, msgs);
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2:
        return ((InternalEList<?>)getSManyFromManyRef2()).basicRemove(otherEnd, msgs);
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
      case ElementsPackage.STRICT_ELEMENT__SVALUE:
        return getSValue();
      case ElementsPackage.STRICT_ELEMENT__SVALUES:
        return getSValues();
      case ElementsPackage.STRICT_ELEMENT__SMANY_CONTENT:
        return getSManyContent();
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT:
        return getSSingleContent();
      case ElementsPackage.STRICT_ELEMENT__SMANY_REF:
        return getSManyRef();
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_REF:
        if (resolve) return getSSingleRef();
        return basicGetSSingleRef();
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF:
        return getSManyFromSingleRef();
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF:
        if (resolve) return getSSingleFromManyRef();
        return basicGetSSingleFromManyRef();
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1:
        return getSManyFromManyRef1();
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2:
        return getSManyFromManyRef2();
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
      case ElementsPackage.STRICT_ELEMENT__SVALUE:
        setSValue((Integer)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SVALUES:
        getSValues().clear();
        getSValues().addAll((Collection<? extends Integer>)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_CONTENT:
        getSManyContent().clear();
        getSManyContent().addAll((Collection<? extends Element>)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT:
        setSSingleContent((Element)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_REF:
        getSManyRef().clear();
        getSManyRef().addAll((Collection<? extends Element>)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_REF:
        setSSingleRef((Element)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF:
        getSManyFromSingleRef().clear();
        getSManyFromSingleRef().addAll((Collection<? extends StrictElement>)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF:
        setSSingleFromManyRef((StrictElement)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1:
        getSManyFromManyRef1().clear();
        getSManyFromManyRef1().addAll((Collection<? extends StrictElement>)newValue);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2:
        getSManyFromManyRef2().clear();
        getSManyFromManyRef2().addAll((Collection<? extends StrictElement>)newValue);
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
      case ElementsPackage.STRICT_ELEMENT__SVALUE:
        setSValue(SVALUE_EDEFAULT);
        return;
      case ElementsPackage.STRICT_ELEMENT__SVALUES:
        getSValues().clear();
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_CONTENT:
        getSManyContent().clear();
        return;
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT:
        setSSingleContent((Element)null);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_REF:
        getSManyRef().clear();
        return;
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_REF:
        setSSingleRef((Element)null);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF:
        getSManyFromSingleRef().clear();
        return;
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF:
        setSSingleFromManyRef((StrictElement)null);
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1:
        getSManyFromManyRef1().clear();
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2:
        getSManyFromManyRef2().clear();
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
      case ElementsPackage.STRICT_ELEMENT__SVALUE:
        return sValue != SVALUE_EDEFAULT;
      case ElementsPackage.STRICT_ELEMENT__SVALUES:
        return sValues != null && !sValues.isEmpty();
      case ElementsPackage.STRICT_ELEMENT__SMANY_CONTENT:
        return sManyContent != null && !sManyContent.isEmpty();
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT:
        return sSingleContent != null;
      case ElementsPackage.STRICT_ELEMENT__SMANY_REF:
        return sManyRef != null && !sManyRef.isEmpty();
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_REF:
        return sSingleRef != null;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF:
        return sManyFromSingleRef != null && !sManyFromSingleRef.isEmpty();
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF:
        return sSingleFromManyRef != null;
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF1:
        return sManyFromManyRef1 != null && !sManyFromManyRef1.isEmpty();
      case ElementsPackage.STRICT_ELEMENT__SMANY_FROM_MANY_REF2:
        return sManyFromManyRef2 != null && !sManyFromManyRef2.isEmpty();
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
    result.append(" (sValue: ");
    result.append(sValue);
    result.append(", sValues: ");
    result.append(sValues);
    result.append(')');
    return result.toString();
  }

} //StrictElementImpl
