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
package org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparisonElement;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EValuePresence;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;

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
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EReferenceValuePresenceImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EReferenceValuePresenceImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EReferenceValuePresenceImpl extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl<Object, Object, Object>
    implements EReferenceValuePresence {
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
  protected EReferenceValuePresenceImpl() {
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
  public EReferenceValuePresenceImpl(EMatch elementMatch_p, Object reference_p,
      Object value_p, EMatch valueMatch_p, Role presenceRole_p,
      boolean isOrder_p) {
    super(elementMatch_p, reference_p, value_p, valueMatch_p, presenceRole_p,
        isOrder_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PojodiffdataPackage.Literals.EREFERENCE_VALUE_PRESENCE;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getFeature()
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
          PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__FEATURE, oldFeature,
          feature));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#setReference(java.lang.Object)
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
  public Object getValue() {
    return value;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#setValue(java.lang.Object)
   */
  @Override
  public void setValue(Object newValue) {
    Object oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE, oldValue,
          value));
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getSymmetrical()
   * @generated NOT
   */
  @Override
  public EReferenceValuePresence getSymmetrical() {
    return (EReferenceValuePresence) super.getSymmetrical();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getSymmetricalOwnership()
   * @generated NOT
   */
  @Override
  public EReferenceValuePresence getSymmetricalOwnership() {
    return (EReferenceValuePresence) super.getSymmetricalOwnership();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#getValueMatch()
   * @generated NOT
   */
  @Override
  public EMatch getValueMatch() {
    return (EMatch) super.getValueMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EElementRelativePresenceImpl#getElementMatch()
   * @generated NOT
   */
  @Override
  public EMatch getElementMatch() {
    return (EMatch) super.getElementMatch();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EMergeableDifferenceImpl#getComparison()
   * @generated NOT
   */
  @Override
  public EComparison getComparison() {
    return (EComparison) super.getComparison();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EReferenceValuePresenceImpl#removeDependencies(java.lang.Object)
   * @generated NOT
   */
  @Override
  protected void removeDependencies(Object element_p) {
    //    getComparison().getMapping().removeDependencies(getPresenceRole(),
    //        element_p);
    //TODO
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__FEATURE:
      return getFeature();
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
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
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__FEATURE:
      setFeature(newValue);
      return;
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
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
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__FEATURE:
      setFeature(FEATURE_EDEFAULT);
      return;
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
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
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__FEATURE:
      return FEATURE_EDEFAULT == null ? feature != null
          : !FEATURE_EDEFAULT.equals(feature);
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
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
    if (baseClass == EComparisonElement.class) {
      switch (derivedFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == EMergeableDifference.class) {
      switch (derivedFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == EElementRelativePresence.class) {
      switch (derivedFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == EValuePresence.class) {
      switch (derivedFeatureID) {
      case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__FEATURE:
        return PojodiffdataPackage.EVALUE_PRESENCE__FEATURE;
      case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE:
        return PojodiffdataPackage.EVALUE_PRESENCE__VALUE;
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
    if (baseClass == EComparisonElement.class) {
      switch (baseFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == EMergeableDifference.class) {
      switch (baseFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == EElementRelativePresence.class) {
      switch (baseFeatureID) {
      default:
        return -1;
      }
    }
    if (baseClass == EValuePresence.class) {
      switch (baseFeatureID) {
      case PojodiffdataPackage.EVALUE_PRESENCE__FEATURE:
        return PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__FEATURE;
      case PojodiffdataPackage.EVALUE_PRESENCE__VALUE:
        return PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE__VALUE;
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

} //EReferenceValuePresenceImpl