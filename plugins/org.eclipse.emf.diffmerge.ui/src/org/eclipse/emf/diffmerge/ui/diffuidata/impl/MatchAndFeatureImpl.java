/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.diffuidata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Match And Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl#isAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl#getMatch <em>Match</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.MatchAndFeatureImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MatchAndFeatureImpl extends EObjectImpl
    implements MatchAndFeature {
  /**
   * The default value of the '{@link #isAttribute() <em>Attribute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAttribute()
   * @generated
   * @ordered
   */
  protected static final boolean ATTRIBUTE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isAttribute() <em>Attribute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAttribute()
   * @generated
   * @ordered
   */
  protected boolean attribute = ATTRIBUTE_EDEFAULT;

  /**
   * The cached value of the '{@link #getMatch() <em>Match</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMatch()
   * @generated
   * @ordered
   */
  protected IMatch<?> match;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MatchAndFeatureImpl() {
    super();
  }

  /**
   * Constructor
   * @param match_p a non-null match
   * @param feature_p a non-null feature
   * @generated NOT
   */
  public MatchAndFeatureImpl(IMatch<?> match_p, Object feature_p,
      boolean isAttribute_p) {
    match = match_p;
    feature = feature_p;
    attribute = isAttribute_p;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffuidataPackage.Literals.MATCH_AND_FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAttribute() {
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAttribute(boolean newAttribute) {
    boolean oldAttribute = attribute;
    attribute = newAttribute;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffuidataPackage.MATCH_AND_FEATURE__ATTRIBUTE, oldAttribute,
          attribute));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMatch<?> getMatch() {
    if (match != null && ((EObject) match).eIsProxy()) {
      InternalEObject oldMatch = (InternalEObject) match;
      match = (IMatch<?>) eResolveProxy(oldMatch);
      if (match != oldMatch) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffuidataPackage.MATCH_AND_FEATURE__MATCH, oldMatch, match));
      }
    }
    return match;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMatch<?> basicGetMatch() {
    return match;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
          DiffuidataPackage.MATCH_AND_FEATURE__FEATURE, oldFeature, feature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("boxing")
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case DiffuidataPackage.MATCH_AND_FEATURE__ATTRIBUTE:
      return isAttribute();
    case DiffuidataPackage.MATCH_AND_FEATURE__MATCH:
      if (resolve)
        return getMatch();
      return basicGetMatch();
    case DiffuidataPackage.MATCH_AND_FEATURE__FEATURE:
      return getFeature();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("boxing")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case DiffuidataPackage.MATCH_AND_FEATURE__ATTRIBUTE:
      setAttribute((Boolean) newValue);
      return;
    case DiffuidataPackage.MATCH_AND_FEATURE__FEATURE:
      setFeature(newValue);
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
    case DiffuidataPackage.MATCH_AND_FEATURE__ATTRIBUTE:
      setAttribute(ATTRIBUTE_EDEFAULT);
      return;
    case DiffuidataPackage.MATCH_AND_FEATURE__FEATURE:
      setFeature(FEATURE_EDEFAULT);
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
    case DiffuidataPackage.MATCH_AND_FEATURE__ATTRIBUTE:
      return attribute != ATTRIBUTE_EDEFAULT;
    case DiffuidataPackage.MATCH_AND_FEATURE__MATCH:
      return match != null;
    case DiffuidataPackage.MATCH_AND_FEATURE__FEATURE:
      return FEATURE_EDEFAULT == null ? feature != null
          : !FEATURE_EDEFAULT.equals(feature);
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
    if (eIsProxy())
      return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (attribute: "); //$NON-NLS-1$
    result.append(attribute);
    result.append(", feature: "); //$NON-NLS-1$
    result.append(feature);
    result.append(')');
    return result.toString();
  }

} //MatchAndFeatureImpl
