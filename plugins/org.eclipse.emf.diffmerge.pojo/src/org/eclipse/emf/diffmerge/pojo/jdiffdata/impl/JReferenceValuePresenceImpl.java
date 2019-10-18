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
package org.eclipse.emf.diffmerge.pojo.jdiffdata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EReference Value Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JReferenceValuePresenceImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JReferenceValuePresenceImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JReferenceValuePresenceImpl<E extends Object>
    extends GReferenceValuePresenceImpl<E, Object, Object>
    implements JReferenceValuePresence<E> {
  /**
   * The default value of the '{@link #getFeature() <em>Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeature()
   * @generated
   * @ordered
   */
  protected static final Object FEATURE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFeature() <em>Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeature()
   * @generated
   * @ordered
   */
  protected Object feature = FEATURE_EDEFAULT;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected E value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JReferenceValuePresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param elementMatch_p the non-null match for the element holding the value
   * @param reference_p the non-null reference holding the value
   * @param value_p the value element, which is non-null unless valueMatch_p is not null
   * @param valueMatch_p the optional match corresponding to the value held
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  public JReferenceValuePresenceImpl(JMatch<E> elementMatch_p,
      Object reference_p, E value_p, JMatch<E> valueMatch_p,
      Role presenceRole_p, boolean isOrder_p) {
    super(elementMatch_p, reference_p, value_p, valueMatch_p, presenceRole_p,
        isOrder_p);
    setFeature(reference_p); // Override default value.
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return JdiffdataPackage.Literals.JREFERENCE_VALUE_PRESENCE;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl#getFeature()
   * @generated NOT
   */
  @Override
  public Object getFeature() {
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFeature(Object newFeature) {
    Object oldFeature = feature;
    feature = newFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__FEATURE, oldFeature,
          feature));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl#setReference(java.lang.Object)
   * @generated NOT
   */
  @Override
  public void setReference(Object reference_p) {
    setFeature(reference_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public E getValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setValue(E newValue) {
    E oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__VALUE, oldValue, value));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl#getSymmetrical()
   * @generated NOT
   */
  @Override
  public JReferenceValuePresence<E> getSymmetrical() {
    return (JReferenceValuePresence<E>) super.getSymmetrical();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl#getSymmetricalOwnership()
   * @generated NOT
   */
  @Override
  public JReferenceValuePresence<E> getSymmetricalOwnership() {
    return (JReferenceValuePresence<E>) super.getSymmetricalOwnership();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GReferenceValuePresenceImpl#getValueMatch()
   * @generated NOT
   */
  @Override
  public JMatch<E> getValueMatch() {
    return (JMatch<E>) super.getValueMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GElementRelativePresenceImpl#getElementMatch()
   * @generated NOT
   */
  @Override
  public JMatch<E> getElementMatch() {
    return (JMatch<E>) super.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMergeableDifferenceImpl#getComparison()
   * @generated NOT
   */
  @Override
  public JComparison<E> getComparison() {
    return (JComparison<E>) super.getComparison();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__FEATURE:
      return getFeature();
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__VALUE:
      return getValue();
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
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__FEATURE:
      setFeature(newValue);
      return;
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__VALUE:
      setValue((E) newValue);
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
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__FEATURE:
      setFeature(FEATURE_EDEFAULT);
      return;
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__VALUE:
      setValue((E) null);
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
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__FEATURE:
      return FEATURE_EDEFAULT == null ? feature != null
          : !FEATURE_EDEFAULT.equals(feature);
    case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__VALUE:
      return value != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID,
      Class<?> baseClass) {
    if (baseClass == JComparisonElement.class) {
      switch (derivedFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == JMergeableDifference.class) {
      switch (derivedFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == JElementRelativePresence.class) {
      switch (derivedFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == JValuePresence.class) {
      switch (derivedFeatureID) {
      case JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__FEATURE:
        return JdiffdataPackage.JVALUE_PRESENCE__FEATURE;
      default:
        return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID,
      Class<?> baseClass) {
    if (baseClass == JComparisonElement.class) {
      switch (baseFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == JMergeableDifference.class) {
      switch (baseFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == JElementRelativePresence.class) {
      switch (baseFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == JValuePresence.class) {
      switch (baseFeatureID) {
      case JdiffdataPackage.JVALUE_PRESENCE__FEATURE:
        return JdiffdataPackage.JREFERENCE_VALUE_PRESENCE__FEATURE;
      default:
        return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (feature: "); //$NON-NLS-1$
    result.append(feature);
    result.append(", value: "); //$NON-NLS-1$
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //GReferenceValuePresenceImpl
