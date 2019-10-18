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
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GAttributeValuePresenceImpl;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JAttributeValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparisonElement;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JElementRelativePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMergeableDifference;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JValuePresence;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EAttribute Value Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JAttributeValuePresenceImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JAttributeValuePresenceImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JAttributeValuePresenceImpl<E extends Object>
    extends GAttributeValuePresenceImpl<E, Object, Object>
    implements JAttributeValuePresence<E> {
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
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final Object VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected Object value = VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JAttributeValuePresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param elementMatch_p the non-null match for the element holding the value
   * @param attribute_p the non-null attribute holding the value
   * @param value_p the non-null value held
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  public JAttributeValuePresenceImpl(JMatch<E> elementMatch_p,
      Object attribute_p, Object value_p, Role presenceRole_p,
      boolean isOrder_p) {
    super(elementMatch_p, attribute_p, value_p, presenceRole_p, isOrder_p);
    setFeature(attribute_p); // Override default value.
    setValue(value_p); // Override default value.
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return JdiffdataPackage.Literals.JATTRIBUTE_VALUE_PRESENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
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
          JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__FEATURE, oldFeature,
          feature));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GAttributeValuePresenceImpl#setAttribute(java.lang.Object)
   * @generated NOT
   */
  @Override
  public void setAttribute(Object attribute_p) {
    setFeature(attribute_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setValue(Object newValue) {
    Object oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__VALUE, oldValue, value));
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
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__FEATURE:
      return getFeature();
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__VALUE:
      return getValue();
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
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__FEATURE:
      setFeature(newValue);
      return;
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__VALUE:
      setValue(newValue);
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
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__FEATURE:
      setFeature(FEATURE_EDEFAULT);
      return;
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__VALUE:
      setValue(VALUE_EDEFAULT);
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
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__FEATURE:
      return FEATURE_EDEFAULT == null ? feature != null
          : !FEATURE_EDEFAULT.equals(feature);
    case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__VALUE:
      return VALUE_EDEFAULT == null ? value != null
          : !VALUE_EDEFAULT.equals(value);
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
      case JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__FEATURE:
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
        return JdiffdataPackage.JATTRIBUTE_VALUE_PRESENCE__FEATURE;
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

} //GAttributeValuePresenceImpl
