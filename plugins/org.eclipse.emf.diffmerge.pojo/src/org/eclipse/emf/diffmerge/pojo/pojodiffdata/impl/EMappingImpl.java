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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EComparison;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMatch;
import org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GMapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMappingImpl#getAncestorMatchMap <em>Ancestor Match Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMappingImpl#getReferenceMatchMap <em>Reference Match Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.pojodiffdata.impl.EMappingImpl#getTargetMatchMap <em>Target Match Map</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EMappingImpl<E extends Object>
    extends GMappingImpl<E, Object, Object> implements EMapping<E> {

  /**
   * The cached value of the '{@link #getAncestorMatchMap() <em>Ancestor Match Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestorMatchMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EMatch<?>> ancestorMatchMap;
  /**
   * The cached value of the '{@link #getReferenceMatchMap() <em>Reference Match Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceMatchMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EMatch<?>> referenceMatchMap;
  /**
   * The cached value of the '{@link #getTargetMatchMap() <em>Target Match Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetMatchMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EMatch<?>> targetMatchMap;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EMappingImpl() {
    super();
    eAdapters().add(new MatchMapSynchronizerAdapter<E>());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PojodiffdataPackage.Literals.EMAPPING;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @SuppressWarnings("serial")
  public EMap<Object, EMatch<?>> getAncestorMatchMap() {
    if (ancestorMatchMap == null) {
      ancestorMatchMap = new EcoreEMap<Object, EMatch<?>>(
          PojodiffdataPackage.Literals.ELEMENT_TO_MATCH_ENTRY,
          ElementToMatchEntryImpl.class, this,
          PojodiffdataPackage.EMAPPING__ANCESTOR_MATCH_MAP) {
        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForKey()
         */
        @Override
        protected boolean useEqualsForKey() {
          return false;
        }

        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForValue()
         */
        @Override
        protected boolean useEqualsForValue() {
          return false;
        }
      };
    }
    return ancestorMatchMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @SuppressWarnings("serial")
  public EMap<Object, EMatch<?>> getReferenceMatchMap() {
    if (referenceMatchMap == null) {
      referenceMatchMap = new EcoreEMap<Object, EMatch<?>>(
          PojodiffdataPackage.Literals.ELEMENT_TO_MATCH_ENTRY,
          ElementToMatchEntryImpl.class, this,
          PojodiffdataPackage.EMAPPING__REFERENCE_MATCH_MAP) {
        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForKey()
         */
        @Override
        protected boolean useEqualsForKey() {
          return false;
        }

        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForValue()
         */
        @Override
        protected boolean useEqualsForValue() {
          return false;
        }
      };
    }
    return referenceMatchMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @SuppressWarnings("serial")
  public EMap<Object, EMatch<?>> getTargetMatchMap() {
    if (targetMatchMap == null) {
      targetMatchMap = new EcoreEMap<Object, EMatch<?>>(
          PojodiffdataPackage.Literals.ELEMENT_TO_MATCH_ENTRY,
          ElementToMatchEntryImpl.class, this,
          PojodiffdataPackage.EMAPPING__TARGET_MATCH_MAP) {
        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForKey()
         */
        @Override
        protected boolean useEqualsForKey() {
          return false;
        }

        /**
         * @see org.eclipse.emf.common.util.BasicEMap#useEqualsForValue()
         */
        @Override
        protected boolean useEqualsForValue() {
          return false;
        }
      };
    }
    return targetMatchMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl#clear()
   * @generated NOT
   */
  @Override
  public void clear() {
    super.clear();
    getAncestorMatchMap().clear();
    getReferenceMatchMap().clear();
    getTargetMatchMap().clear();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IMapping#getMatchFor(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  @SuppressWarnings("unchecked")
  public EMatch<E> getMatchFor(Object potentialElement, Role role) {
    EMap<Object, EMatch<?>> matchMap = getMatchMap(role);
    return (EMatch<E>) matchMap.get(potentialElement);
  }

  /**
   * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.EMapping#getMatchMap(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public EMap<Object, EMatch<?>> getMatchMap(Role role) {
    EMap<Object, EMatch<?>> result;
    switch (role) {
    case REFERENCE:
      result = getReferenceMatchMap();
      break;
    case TARGET:
      result = getTargetMatchMap();
      break;
    default:
      result = getAncestorMatchMap();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl#map(java.lang.Object, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  @Override
  public EMatch<E> map(E element, Role role) {
    return (EMatch<E>) super.map(element, role);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl#getComparison()
   * @generated NOT
   */
  @Override
  public EComparison<E> getComparison() {
    return (EComparison<E>) super.getComparison();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMappingImpl#doDisconnect(org.eclipse.emf.diffmerge.generic.api.Role, java.lang.Object)
   * @generated NOT
   */
  @Override
  protected boolean doDisconnect(Role role_p, E element_p) {
    // Cannot determine what to disconnect: delegate to scope
    return getComparison().getScope(role_p).disconnect(element_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd,
      int featureID, NotificationChain msgs) {
    switch (featureID) {
    case PojodiffdataPackage.EMAPPING__ANCESTOR_MATCH_MAP:
      return ((InternalEList<?>) getAncestorMatchMap()).basicRemove(otherEnd,
          msgs);
    case PojodiffdataPackage.EMAPPING__REFERENCE_MATCH_MAP:
      return ((InternalEList<?>) getReferenceMatchMap()).basicRemove(otherEnd,
          msgs);
    case PojodiffdataPackage.EMAPPING__TARGET_MATCH_MAP:
      return ((InternalEList<?>) getTargetMatchMap()).basicRemove(otherEnd,
          msgs);
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
    case PojodiffdataPackage.EMAPPING__ANCESTOR_MATCH_MAP:
      if (coreType)
        return getAncestorMatchMap();
      else
        return getAncestorMatchMap().map();
    case PojodiffdataPackage.EMAPPING__REFERENCE_MATCH_MAP:
      if (coreType)
        return getReferenceMatchMap();
      else
        return getReferenceMatchMap().map();
    case PojodiffdataPackage.EMAPPING__TARGET_MATCH_MAP:
      if (coreType)
        return getTargetMatchMap();
      else
        return getTargetMatchMap().map();
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
    case PojodiffdataPackage.EMAPPING__ANCESTOR_MATCH_MAP:
      ((EStructuralFeature.Setting) getAncestorMatchMap()).set(newValue);
      return;
    case PojodiffdataPackage.EMAPPING__REFERENCE_MATCH_MAP:
      ((EStructuralFeature.Setting) getReferenceMatchMap()).set(newValue);
      return;
    case PojodiffdataPackage.EMAPPING__TARGET_MATCH_MAP:
      ((EStructuralFeature.Setting) getTargetMatchMap()).set(newValue);
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
    case PojodiffdataPackage.EMAPPING__ANCESTOR_MATCH_MAP:
      getAncestorMatchMap().clear();
      return;
    case PojodiffdataPackage.EMAPPING__REFERENCE_MATCH_MAP:
      getReferenceMatchMap().clear();
      return;
    case PojodiffdataPackage.EMAPPING__TARGET_MATCH_MAP:
      getTargetMatchMap().clear();
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
    case PojodiffdataPackage.EMAPPING__ANCESTOR_MATCH_MAP:
      return ancestorMatchMap != null && !ancestorMatchMap.isEmpty();
    case PojodiffdataPackage.EMAPPING__REFERENCE_MATCH_MAP:
      return referenceMatchMap != null && !referenceMatchMap.isEmpty();
    case PojodiffdataPackage.EMAPPING__TARGET_MATCH_MAP:
      return targetMatchMap != null && !targetMatchMap.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * An adapter that is in charge of synchronizing the match maps with
   * the other contents of the mapping.
   * @generated NOT
   */
  protected static class MatchMapSynchronizerAdapter<E> extends AdapterImpl {
    /**
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#isAdapterForType(java.lang.Object)
     */
    @Override
    public boolean isAdapterForType(Object type_p) {
      return type_p == MatchMapSynchronizerAdapter.class;
    }

    /**
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void notifyChanged(Notification msg_p) {
      // Important: It is assumed that the modifiable contents of mappings are modified
      // by atomic addition/removal of elements, except for global clear which is handled
      // outside this adapter.
      Object feature = msg_p.getFeature();
      int eventType = msg_p.getEventType();
      Object notifier = msg_p.getNotifier();
      if (feature == GdiffdataPackage.eINSTANCE
          .getGMapping_ModifiableContents()) {
        EMapping<E> mapping = (EMapping<E>) notifier;
        if (eventType == Notification.ADD) {
          // Match addition
          EMatch<E> addedMatch = (EMatch<E>) msg_p.getNewValue();
          addedMatch.eAdapters().add(this);
          for (Role role : Role.values()) {
            E element = addedMatch.get(role);
            if (element != null) {
              EMap<Object, EMatch<?>> matchMap = mapping.getMatchMap(role);
              matchMap.put(element, addedMatch);
            }
          }
        } else if (eventType == Notification.REMOVE) {
          // Match removal
          EMatch<E> removedMatch = (EMatch<E>) msg_p.getOldValue();
          removedMatch.eAdapters().remove(this);
          for (Role role : Role.values()) {
            Object element = removedMatch.get(role);
            if (element != null) {
              EMap<Object, EMatch<?>> matchMap = mapping.getMatchMap(role);
              EMatch<?> mappedMatch = matchMap.get(element);
              if (mappedMatch == removedMatch) {
                matchMap.removeKey(element);
              }
            }
          }
        }
      } else if (notifier instanceof EMatch) {
        EMatch<E> match = (EMatch<E>) notifier;
        if (eventType == Notification.SET) {
          // Match setting
          Role concernedRole = (feature == PojodiffdataPackage.eINSTANCE
              .getEMatch_Ancestor())
                  ? Role.ANCESTOR
                  : (feature == PojodiffdataPackage.eINSTANCE
                      .getEMatch_Reference()) ? Role.REFERENCE
                          : (feature == PojodiffdataPackage.eINSTANCE
                              .getEMatch_Target()) ? Role.TARGET : null;
          if (concernedRole != null) {
            EMap<Object, EMatch<?>> matchMap = match.getMapping()
                .getMatchMap(concernedRole);
            E oldElement = (E) msg_p.getOldValue();
            E newElement = (E) msg_p.getNewValue();
            if (oldElement != null) {
              EMatch<?> mappedMatch = matchMap.get(oldElement);
              if (mappedMatch == match) {
                matchMap.removeKey(oldElement);
              }
            }
            if (newElement != null) {
              matchMap.put(newElement, match);
            }
          }
        }
      }
    }
  }

} //GMappingImpl
