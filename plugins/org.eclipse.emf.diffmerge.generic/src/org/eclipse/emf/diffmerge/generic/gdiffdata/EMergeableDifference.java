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
package org.eclipse.emf.diffmerge.generic.gdiffdata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMergeable Difference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isAlignedWithAncestor <em>Aligned With Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isConflicting <em>Conflicting</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isIgnored <em>Ignored</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getMergeDestination <em>Merge Destination</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getPossibleMergeDestinations <em>Possible Merge Destinations</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getExplicitDependenciesForTarget <em>Explicit Dependencies For Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getExplicitDependenciesForReference <em>Explicit Dependencies For Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getImplicitDependenciesForTarget <em>Implicit Dependencies For Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getImplicitDependenciesForReference <em>Implicit Dependencies For Reference</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement&lt;E, A, R&gt; org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableMergeableDifference&lt;E&gt;"
 * @generated
 */
public interface EMergeableDifference<E, A, R>
    extends EIdentified, EComparisonElement<E, A, R>, Editable<E> {
  /**
   * Returns the value of the '<em><b>Aligned With Ancestor</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Aligned With Ancestor</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Aligned With Ancestor</em>' attribute.
   * @see #setAlignedWithAncestor(boolean)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_AlignedWithAncestor()
   * @model default="true" required="true"
   * @generated
   */
  boolean isAlignedWithAncestor();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isAlignedWithAncestor <em>Aligned With Ancestor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Aligned With Ancestor</em>' attribute.
   * @see #isAlignedWithAncestor()
   * @generated
   */
  void setAlignedWithAncestor(boolean value);

  /**
   * Returns the value of the '<em><b>Conflicting</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conflicting</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conflicting</em>' attribute.
   * @see #setConflicting(boolean)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_Conflicting()
   * @model default="false" required="true"
   * @generated
   */
  boolean isConflicting();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isConflicting <em>Conflicting</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conflicting</em>' attribute.
   * @see #isConflicting()
   * @generated
   */
  void setConflicting(boolean value);

  /**
   * Returns the value of the '<em><b>Ignored</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ignored</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ignored</em>' attribute.
   * @see #setIgnored(boolean)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_Ignored()
   * @model required="true"
   * @generated
   */
  boolean isIgnored();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#isIgnored <em>Ignored</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ignored</em>' attribute.
   * @see #isIgnored()
   * @generated
   */
  void setIgnored(boolean value);

  /**
   * Returns the value of the '<em><b>Merge Destination</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Merge Destination</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Merge Destination</em>' attribute.
   * @see #setMergeDestination(Role)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_MergeDestination()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role"
   * @generated
   */
  Role getMergeDestination();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference#getMergeDestination <em>Merge Destination</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Merge Destination</em>' attribute.
   * @see #getMergeDestination()
   * @generated
   */
  void setMergeDestination(Role value);

  /**
   * Returns the value of the '<em><b>Possible Merge Destinations</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.Role}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Possible Merge Destinations</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Possible Merge Destinations</em>' attribute list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_PossibleMergeDestinations()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.Role"
   * @generated
   */
  EList<Role> getPossibleMergeDestinations();

  /**
   * Returns the value of the '<em><b>Explicit Dependencies For Target</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Explicit Dependencies For Target</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Explicit Dependencies For Target</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_ExplicitDependenciesForTarget()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMergeableDifference&lt;E&gt;"
   * @generated
   */
  EList<IMergeableDifference<E>> getExplicitDependenciesForTarget();

  /**
   * Returns the value of the '<em><b>Explicit Dependencies For Reference</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Explicit Dependencies For Reference</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Explicit Dependencies For Reference</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_ExplicitDependenciesForReference()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMergeableDifference&lt;E&gt;"
   * @generated
   */
  EList<IMergeableDifference<E>> getExplicitDependenciesForReference();

  /**
   * Returns the value of the '<em><b>Implicit Dependencies For Target</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Implicit Dependencies For Target</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Implicit Dependencies For Target</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_ImplicitDependenciesForTarget()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMergeableDifference&lt;E&gt;"
   * @generated
   */
  EList<IMergeableDifference<E>> getImplicitDependenciesForTarget();

  /**
   * Returns the value of the '<em><b>Implicit Dependencies For Reference</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference}<code>&lt;E&gt;</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Implicit Dependencies For Reference</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Implicit Dependencies For Reference</em>' reference list.
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEMergeableDifference_ImplicitDependenciesForReference()
   * @model type="org.eclipse.emf.diffmerge.generic.gdiffdata.IMergeableDifference&lt;E&gt;"
   * @generated
   */
  EList<IMergeableDifference<E>> getImplicitDependenciesForReference();

} // EMergeableDifference
