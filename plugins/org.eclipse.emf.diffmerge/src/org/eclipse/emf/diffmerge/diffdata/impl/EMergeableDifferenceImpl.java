/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.diffdata.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.Messages;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMergeable Difference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#getComparison <em>Comparison</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#isAlignedWithAncestor <em>Aligned With Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#isConflicting <em>Conflicting</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#isIgnored <em>Ignored</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#getMergeDestination <em>Merge Destination</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#getPossibleMergeDestinations <em>Possible Merge Destinations</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#getExplicitDependenciesForTarget <em>Explicit Dependencies For Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#getExplicitDependenciesForReference <em>Explicit Dependencies For Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#getImplicitDependenciesForTarget <em>Implicit Dependencies For Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EMergeableDifferenceImpl#getImplicitDependenciesForReference <em>Implicit Dependencies For Reference</em>}</li>
 * </ul>
 *
 * @generated
 */
@SuppressWarnings("boxing")
public abstract class EMergeableDifferenceImpl extends EIdentifiedImpl
    implements EMergeableDifference {
  /**
   * The cached value of the '{@link #getComparison() <em>Comparison</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComparison()
   * @generated
   * @ordered
   */
  protected EComparison comparison;

  /**
   * The default value of the '{@link #isAlignedWithAncestor() <em>Aligned With Ancestor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAlignedWithAncestor()
   * @generated
   * @ordered
   */
  protected static final boolean ALIGNED_WITH_ANCESTOR_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isAlignedWithAncestor() <em>Aligned With Ancestor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAlignedWithAncestor()
   * @generated
   * @ordered
   */
  protected boolean alignedWithAncestor = ALIGNED_WITH_ANCESTOR_EDEFAULT;

  /**
   * The default value of the '{@link #isConflicting() <em>Conflicting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isConflicting()
   * @generated
   * @ordered
   */
  protected static final boolean CONFLICTING_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isConflicting() <em>Conflicting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isConflicting()
   * @generated
   * @ordered
   */
  protected boolean conflicting = CONFLICTING_EDEFAULT;

  /**
   * The default value of the '{@link #isIgnored() <em>Ignored</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIgnored()
   * @generated
   * @ordered
   */
  protected static final boolean IGNORED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isIgnored() <em>Ignored</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIgnored()
   * @generated
   * @ordered
   */
  protected boolean ignored = IGNORED_EDEFAULT;

  /**
   * The default value of the '{@link #getMergeDestination() <em>Merge Destination</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMergeDestination()
   * @generated
   * @ordered
   */
  protected static final Role MERGE_DESTINATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMergeDestination() <em>Merge Destination</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMergeDestination()
   * @generated
   * @ordered
   */
  protected Role mergeDestination = MERGE_DESTINATION_EDEFAULT;

  /**
   * The cached value of the '{@link #getPossibleMergeDestinations() <em>Possible Merge Destinations</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPossibleMergeDestinations()
   * @generated
   * @ordered
   */
  protected EList<Role> possibleMergeDestinations;

  /**
   * The cached value of the '{@link #getExplicitDependenciesForTarget() <em>Explicit Dependencies For Target</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExplicitDependenciesForTarget()
   * @generated
   * @ordered
   */
  protected EList<IMergeableDifference> explicitDependenciesForTarget;

  /**
   * The cached value of the '{@link #getExplicitDependenciesForReference() <em>Explicit Dependencies For Reference</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExplicitDependenciesForReference()
   * @generated
   * @ordered
   */
  protected EList<IMergeableDifference> explicitDependenciesForReference;

  /**
   * The cached value of the '{@link #getImplicitDependenciesForTarget() <em>Implicit Dependencies For Target</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImplicitDependenciesForTarget()
   * @generated
   * @ordered
   */
  protected EList<IMergeableDifference> implicitDependenciesForTarget;

  /**
   * The cached value of the '{@link #getImplicitDependenciesForReference() <em>Implicit Dependencies For Reference</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImplicitDependenciesForReference()
   * @generated
   * @ordered
   */
  protected EList<IMergeableDifference> implicitDependenciesForReference;

  /**
   * The set of all implicit dependencies on the TARGET side (initially null, assigned once)
   * @generated NOT
   */
  private transient Collection<IMergeableDifference> _allImplicitDependenciesTarget;

  /**
   * The set of all implicit dependencies on the REFERENCE side (initially null, assigned once)
   * @generated NOT
   */
  private transient Collection<IMergeableDifference> _allImplicitDependenciesReference;

  /**
   * The set of all explicit dependencies on the TARGET side (initially null, assigned once)
   * @generated NOT
   */
  private transient Collection<IMergeableDifference> _allExplicitDependenciesTarget;

  /**
   * The set of all explicit dependencies on the REFERENCE side (initially null, assigned once)
   * @generated NOT
   */
  private transient Collection<IMergeableDifference> _allExplicitDependenciesReference;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EMergeableDifferenceImpl() {
    super();
    getPossibleMergeDestinations().add(Role.TARGET);
    getPossibleMergeDestinations().add(Role.REFERENCE);
    _allImplicitDependenciesTarget = null;
    _allImplicitDependenciesReference = null;
    _allExplicitDependenciesTarget = null;
    _allExplicitDependenciesReference = null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.EMERGEABLE_DIFFERENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EComparison getComparison() {
    if (comparison != null && comparison.eIsProxy()) {
      InternalEObject oldComparison = (InternalEObject) comparison;
      comparison = (EComparison) eResolveProxy(oldComparison);
      if (comparison != oldComparison) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              DiffdataPackage.EMERGEABLE_DIFFERENCE__COMPARISON, oldComparison,
              comparison));
      }
    }
    return comparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EComparison basicGetComparison() {
    return comparison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComparison(EComparison newComparison) {
    EComparison oldComparison = comparison;
    comparison = newComparison;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__COMPARISON, oldComparison,
          comparison));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAlignedWithAncestor() {
    return alignedWithAncestor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAlignedWithAncestor(boolean newAlignedWithAncestor) {
    boolean oldAlignedWithAncestor = alignedWithAncestor;
    alignedWithAncestor = newAlignedWithAncestor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR,
          oldAlignedWithAncestor, alignedWithAncestor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isConflicting() {
    return conflicting;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConflicting(boolean newConflicting) {
    boolean oldConflicting = conflicting;
    conflicting = newConflicting;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__CONFLICTING, oldConflicting,
          conflicting));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isIgnored() {
    return ignored;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIgnored(boolean newIgnored) {
    boolean oldIgnored = ignored;
    ignored = newIgnored;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__IGNORED, oldIgnored, ignored));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Role getMergeDestination() {
    return mergeDestination;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMergeDestination(Role newMergeDestination) {
    Role oldMergeDestination = mergeDestination;
    mergeDestination = newMergeDestination;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__MERGE_DESTINATION,
          oldMergeDestination, mergeDestination));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Role> getPossibleMergeDestinations() {
    if (possibleMergeDestinations == null) {
      possibleMergeDestinations = new EDataTypeUniqueEList<Role>(Role.class,
          this,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS);
    }
    return possibleMergeDestinations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IMergeableDifference> getExplicitDependenciesForTarget() {
    if (explicitDependenciesForTarget == null) {
      explicitDependenciesForTarget = new EObjectResolvingEList<IMergeableDifference>(
          IMergeableDifference.class, this,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET);
    }
    return explicitDependenciesForTarget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IMergeableDifference> getExplicitDependenciesForReference() {
    if (explicitDependenciesForReference == null) {
      explicitDependenciesForReference = new EObjectResolvingEList<IMergeableDifference>(
          IMergeableDifference.class, this,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE);
    }
    return explicitDependenciesForReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IMergeableDifference> getImplicitDependenciesForTarget() {
    if (implicitDependenciesForTarget == null) {
      implicitDependenciesForTarget = new EObjectResolvingEList<IMergeableDifference>(
          IMergeableDifference.class, this,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET);
    }
    return implicitDependenciesForTarget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IMergeableDifference> getImplicitDependenciesForReference() {
    if (implicitDependenciesForReference == null) {
      implicitDependenciesForReference = new EObjectResolvingEList<IMergeableDifference>(
          IMergeableDifference.class, this,
          DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE);
    }
    return implicitDependenciesForReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__COMPARISON:
      if (resolve)
        return getComparison();
      return basicGetComparison();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR:
      return isAlignedWithAncestor();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__CONFLICTING:
      return isConflicting();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IGNORED:
      return isIgnored();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__MERGE_DESTINATION:
      return getMergeDestination();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS:
      return getPossibleMergeDestinations();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET:
      return getExplicitDependenciesForTarget();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE:
      return getExplicitDependenciesForReference();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET:
      return getImplicitDependenciesForTarget();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE:
      return getImplicitDependenciesForReference();
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
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__COMPARISON:
      setComparison((EComparison) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR:
      setAlignedWithAncestor((Boolean) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__CONFLICTING:
      setConflicting((Boolean) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IGNORED:
      setIgnored((Boolean) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__MERGE_DESTINATION:
      setMergeDestination((Role) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS:
      getPossibleMergeDestinations().clear();
      getPossibleMergeDestinations()
          .addAll((Collection<? extends Role>) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET:
      getExplicitDependenciesForTarget().clear();
      getExplicitDependenciesForTarget()
          .addAll((Collection<? extends IMergeableDifference>) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE:
      getExplicitDependenciesForReference().clear();
      getExplicitDependenciesForReference()
          .addAll((Collection<? extends IMergeableDifference>) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET:
      getImplicitDependenciesForTarget().clear();
      getImplicitDependenciesForTarget()
          .addAll((Collection<? extends IMergeableDifference>) newValue);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE:
      getImplicitDependenciesForReference().clear();
      getImplicitDependenciesForReference()
          .addAll((Collection<? extends IMergeableDifference>) newValue);
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
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__COMPARISON:
      setComparison((EComparison) null);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR:
      setAlignedWithAncestor(ALIGNED_WITH_ANCESTOR_EDEFAULT);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__CONFLICTING:
      setConflicting(CONFLICTING_EDEFAULT);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IGNORED:
      setIgnored(IGNORED_EDEFAULT);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__MERGE_DESTINATION:
      setMergeDestination(MERGE_DESTINATION_EDEFAULT);
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS:
      getPossibleMergeDestinations().clear();
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET:
      getExplicitDependenciesForTarget().clear();
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE:
      getExplicitDependenciesForReference().clear();
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET:
      getImplicitDependenciesForTarget().clear();
      return;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE:
      getImplicitDependenciesForReference().clear();
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
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__COMPARISON:
      return comparison != null;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__ALIGNED_WITH_ANCESTOR:
      return alignedWithAncestor != ALIGNED_WITH_ANCESTOR_EDEFAULT;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__CONFLICTING:
      return conflicting != CONFLICTING_EDEFAULT;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IGNORED:
      return ignored != IGNORED_EDEFAULT;
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__MERGE_DESTINATION:
      return MERGE_DESTINATION_EDEFAULT == null ? mergeDestination != null
          : !MERGE_DESTINATION_EDEFAULT.equals(mergeDestination);
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__POSSIBLE_MERGE_DESTINATIONS:
      return possibleMergeDestinations != null
          && !possibleMergeDestinations.isEmpty();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_TARGET:
      return explicitDependenciesForTarget != null
          && !explicitDependenciesForTarget.isEmpty();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__EXPLICIT_DEPENDENCIES_FOR_REFERENCE:
      return explicitDependenciesForReference != null
          && !explicitDependenciesForReference.isEmpty();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_TARGET:
      return implicitDependenciesForTarget != null
          && !implicitDependenciesForTarget.isEmpty();
    case DiffdataPackage.EMERGEABLE_DIFFERENCE__IMPLICIT_DEPENDENCIES_FOR_REFERENCE:
      return implicitDependenciesForReference != null
          && !implicitDependenciesForReference.isEmpty();
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

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (alignedWithAncestor: "); //$NON-NLS-1$
    result.append(alignedWithAncestor);
    result.append(", conflicting: "); //$NON-NLS-1$
    result.append(conflicting);
    result.append(", ignored: "); //$NON-NLS-1$
    result.append(ignored);
    result.append(", mergeDestination: "); //$NON-NLS-1$
    result.append(mergeDestination);
    result.append(", possibleMergeDestinations: "); //$NON-NLS-1$
    result.append(possibleMergeDestinations);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IDifference#canMergeTo(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public boolean canMergeTo(Role destination_p) {
    return getPossibleMergeDestinations().contains(destination_p);
  }

  /**
   * Check that the given difference can merge in the given role independently of
   * its dependencies, by throwing an UnsupportedOperationException otherwise
   * @param difference_p a non-null difference
   * @param destination_p a non-null role which represents the destination of the merge
   * @generated NOT
   */
  protected void checkMerge(IMergeableDifference difference_p,
      Role destination_p) {
    if (getMergeDestination() != destination_p && !canMergeTo(destination_p))
      throw new UnsupportedOperationException(
          Messages.AbstractDifference_UnableToMerge + ": " + toString()); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable#doMergeIn(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public abstract void doMergeIn(Role destination_p);

  /**
   * Forbid this difference from being merged in the given direction
   * Postcondition: !canMergeTo(destination_p)
   * @param destination_p a non-null role
   * @generated NOT
   */
  protected final void forbidMergeTo(Role destination_p) {
    if (destination_p != null)
      getPossibleMergeDestinations().remove(destination_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference#getDirectImpliesDependencies(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public final Collection<IMergeableDifference> getDirectImpliesDependencies(
      Role role_p) {
    Collection<IMergeableDifference> result;
    Collection<IMergeableDifference> obtained;
    switch (role_p) {
    case TARGET:
      obtained = getImplicitDependenciesForTarget(false);
      if (obtained == null)
        result = Collections.emptyList();
      else
        result = Collections.unmodifiableCollection(obtained);
      break;
    default:
      obtained = getImplicitDependenciesForReference(false);
      if (obtained == null)
        result = Collections.emptyList();
      else
        result = Collections.unmodifiableCollection(obtained);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference#getDirectRequiresDependencies(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public final Collection<IMergeableDifference> getDirectRequiresDependencies(
      Role role_p) {
    Collection<IMergeableDifference> result;
    Collection<IMergeableDifference> obtained;
    switch (role_p) {
    case TARGET:
      obtained = getExplicitDependenciesForTarget(false);
      if (obtained == null)
        result = Collections.emptyList();
      else
        result = Collections.unmodifiableCollection(obtained);
      break;
    default:
      obtained = getExplicitDependenciesForReference(false);
      if (obtained == null)
        result = Collections.emptyList();
      else
        result = Collections.unmodifiableCollection(obtained);
    }
    return result;
  }

  /**
   * Return all the explicit dependencies on the reference side
   * @param create_p whether the result must be computed if not available yet, otherwise null may be returned
   * @generated NOT
   */
  public EList<IMergeableDifference> getExplicitDependenciesForReference(
      boolean create_p) {
    return create_p ? getExplicitDependenciesForReference()
        : explicitDependenciesForReference;
  }

  /**
   * Return all the implicit dependencies on the reference side
   * @param create_p whether the result must be computed if not available yet, otherwise null may be returned
   * @generated NOT
   */
  public EList<IMergeableDifference> getImplicitDependenciesForReference(
      boolean create_p) {
    return create_p ? getImplicitDependenciesForReference()
        : implicitDependenciesForReference;
  }

  /**
   * Return all the explicit dependencies on the target side
   * @param create_p whether the result must be computed if not available yet, otherwise null may be returned
   * @generated NOT
   */
  public EList<IMergeableDifference> getExplicitDependenciesForTarget(
      boolean create_p) {
    return create_p ? getExplicitDependenciesForTarget()
        : explicitDependenciesForTarget;
  }

  /**
   * Return all the implicit dependencies on the target side
   * @param create_p whether the result must be computed if not available yet, otherwise null may be returned
   * @generated NOT
   */
  public EList<IMergeableDifference> getImplicitDependenciesForTarget(
      boolean create_p) {
    return create_p ? getImplicitDependenciesForTarget()
        : implicitDependenciesForTarget;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference#getImpliesDependencies(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public final Collection<IMergeableDifference> getImpliesDependencies(
      Role role_p) {
    Collection<IMergeableDifference> result = (role_p == Role.TARGET)
        ? _allImplicitDependenciesTarget
        : _allImplicitDependenciesReference;
    if (result == null) {
      Collection<IMergeableDifference> required = getRequiresDependencies(
          role_p);
      Collection<IMergeableDifference> allRequired = new FArrayList<IMergeableDifference>(
          required.size() + 1, null);
      allRequired.add(this);
      allRequired.addAll(required);
      result = new DifferenceDependencyRelation(role_p, false)
          .getTransitiveClosure(allRequired);
      if (role_p == Role.TARGET)
        _allImplicitDependenciesTarget = result;
      else
        _allImplicitDependenciesReference = result;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference#getRequiresDependencies(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public final Collection<IMergeableDifference> getRequiresDependencies(
      Role role_p) {
    Collection<IMergeableDifference> result = (role_p == Role.TARGET)
        ? _allExplicitDependenciesTarget
        : _allExplicitDependenciesReference;
    if (result == null) {
      result = new DifferenceDependencyRelation(role_p, true)
          .getTransitiveClosure(this);
      if (role_p == Role.TARGET)
        _allExplicitDependenciesTarget = result;
      else
        _allExplicitDependenciesReference = result;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IDifference#isMerged()
   * @generated NOT
   */
  public final boolean isMerged() {
    return getMergeDestination() != null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference#mergeTo(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public Collection<IDifference> mergeTo(Role destination_p) {
    // Checking ability to merge
    checkMerge(this, destination_p);
    if (isMerged())
      return Collections.emptyList();
    Collection<IMergeableDifference> allRequired = getRequiresDependencies(
        destination_p);
    for (IMergeableDifference required : allRequired)
      checkMerge(required, destination_p);
    // Core behavior
    markAsMergedIn(destination_p);
    Collection<IDifference> result = new HashSet<IDifference>();
    for (IMergeableDifference required : allRequired) {
      if (!required.isMerged()) {
        ((IMergeableDifference.Editable) required)
            .markAsMergedIn(destination_p);
        ((IMergeableDifference.Editable) required).doMergeIn(destination_p);
        result.add(required);
      }
    }
    doMergeIn(destination_p);
    result.add(this);
    // Mark implicit dependencies as merged
    for (IMergeableDifference implicit : getImpliesDependencies(
        destination_p)) {
      if (!implicit.isMerged()) {
        ((IMergeableDifference.Editable) implicit)
            .markAsMergedIn(destination_p);
        result.add(implicit);
      }
    }
    return Collections.unmodifiableCollection(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IDifference.Editable#markAsConflicting()
   * @generated NOT
   */
  public void markAsConflicting() {
    markAsDifferentFromAncestor();
    setConflicting(true);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IDifference.Editable#markAsDifferentFromAncestor()
   * @generated NOT
   */
  public void markAsDifferentFromAncestor() {
    setAlignedWithAncestor(false);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable#markAsMergedIn(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public void markAsMergedIn(Role destination_p) {
    if (getMergeDestination() == null) {
      setMergeDestination(destination_p);
      getPossibleMergeDestinations().clear();
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable#markImplies(org.eclipse.emf.diffmerge.api.diff.IMergeableDifference, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public final void markImplies(IMergeableDifference difference_p,
      Role role_p) {
    Collection<IMergeableDifference> toChange;
    switch (role_p) {
    case TARGET:
      toChange = getImplicitDependenciesForTarget(true);
      break;
    default:
      toChange = getImplicitDependenciesForReference(true);
    }
    if (!toChange.contains(difference_p))
      toChange.add(difference_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.diff.IMergeableDifference.Editable#markRequires(org.eclipse.emf.diffmerge.api.diff.IMergeableDifference, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public final void markRequires(IMergeableDifference difference_p,
      Role role_p) {
    Collection<IMergeableDifference> toChange;
    switch (role_p) {
    case TARGET:
      toChange = getExplicitDependenciesForTarget(true);
      break;
    default:
      toChange = getExplicitDependenciesForReference(true);
    }
    if (!toChange.contains(difference_p))
      toChange.add(difference_p);
  }

  /**
   * A definition of inter-difference dependencies as a mathematical binary relation
   * over differences.
   * This class can be used to compute the transitive closure of inter-difference dependencies.
   * @generated NOT
   */
  protected static class DifferenceDependencyRelation
      extends AbstractEndorelation<IMergeableDifference> {

    /** The non-null role for dependency computation */
    private final Role _role;

    /** Whether to use explicit or implicit inter-difference dependencies */
    private final boolean _isExplicit;

    /**
     * Constructor
     * @param role_p the role to which this dependency is relative (TARGET or REFERENCE)
     * @param isExplicit_p whether to use explicit or implicit inter-difference dependencies
     */
    protected DifferenceDependencyRelation(Role role_p, boolean isExplicit_p) {
      super(IEqualityTester.BY_REFERENCE);
      _role = role_p;
      _isExplicit = isExplicit_p;
    }

    /**
     * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
     */
    public Collection<IMergeableDifference> get(
        IMergeableDifference element_p) {
      Collection<IMergeableDifference> result;
      if (_isExplicit) {
        result = new FHashSet<IMergeableDifference>(
            element_p.getDirectRequiresDependencies(_role),
            IEqualityTester.BY_REFERENCE);
        for (IMergeableDifference implicit : element_p
            .getDirectImpliesDependencies(_role)) {
          result.addAll(implicit.getDirectRequiresDependencies(_role));
        }
      } else {
        result = element_p.getDirectImpliesDependencies(_role);
      }
      return result;
    }
  }

} //EMergeableDifferenceImpl
