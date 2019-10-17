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

import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A partial implementation of IComparison based on EMF.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getAncestorScope <em>Ancestor Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getReferenceScope <em>Reference Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMatchPolicy <em>Last Match Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastDiffPolicy <em>Last Diff Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMergePolicy <em>Last Merge Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.GIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableComparison&lt;E&gt;"
 * @generated
 */
public interface GComparison<E, A, R> extends GIdentified, Editable<E> {
  /**
   * Returns the value of the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ancestor Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ancestor Scope</em>' attribute.
   * @see #setAncestorScope(IEditableTreeDataScope)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison_AncestorScope()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableTreeDataScope&lt;E&gt;" transient="true"
   * @generated
   */
  IEditableTreeDataScope<E> getAncestorScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getAncestorScope <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ancestor Scope</em>' attribute.
   * @see #getAncestorScope()
   * @generated
   */
  void setAncestorScope(IEditableTreeDataScope<E> value);

  /**
   * Returns the value of the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Scope</em>' attribute.
   * @see #setReferenceScope(IEditableTreeDataScope)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison_ReferenceScope()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableTreeDataScope&lt;E&gt;" required="true" transient="true"
   * @generated
   */
  IEditableTreeDataScope<E> getReferenceScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getReferenceScope <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Scope</em>' attribute.
   * @see #getReferenceScope()
   * @generated
   */
  void setReferenceScope(IEditableTreeDataScope<E> value);

  /**
   * Returns the value of the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Scope</em>' attribute.
   * @see #setTargetScope(IEditableTreeDataScope)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison_TargetScope()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableTreeDataScope&lt;E&gt;" required="true" transient="true"
   * @generated
   */
  IEditableTreeDataScope<E> getTargetScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getTargetScope <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Scope</em>' attribute.
   * @see #getTargetScope()
   * @generated
   */
  void setTargetScope(IEditableTreeDataScope<E> value);

  /**
   * Returns the value of the '<em><b>Last Match Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Last Match Policy</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Last Match Policy</em>' attribute.
   * @see #setLastMatchPolicy(IMatchPolicy)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison_LastMatchPolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatchPolicy&lt;E&gt;" transient="true"
   * @generated
   */
  IMatchPolicy<E> getLastMatchPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMatchPolicy <em>Last Match Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Match Policy</em>' attribute.
   * @see #getLastMatchPolicy()
   * @generated
   */
  void setLastMatchPolicy(IMatchPolicy<E> value);

  /**
   * Returns the value of the '<em><b>Last Diff Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Last Diff Policy</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Last Diff Policy</em>' attribute.
   * @see #setLastDiffPolicy(IDiffPolicy)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison_LastDiffPolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IDiffPolicy&lt;E&gt;" transient="true"
   * @generated
   */
  IDiffPolicy<E> getLastDiffPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastDiffPolicy <em>Last Diff Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Diff Policy</em>' attribute.
   * @see #getLastDiffPolicy()
   * @generated
   */
  void setLastDiffPolicy(IDiffPolicy<E> value);

  /**
   * Returns the value of the '<em><b>Last Merge Policy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Last Merge Policy</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Last Merge Policy</em>' attribute.
   * @see #setLastMergePolicy(IMergePolicy)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison_LastMergePolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IMergePolicy&lt;E&gt;" transient="true"
   * @generated
   */
  IMergePolicy<E> getLastMergePolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getLastMergePolicy <em>Last Merge Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Merge Policy</em>' attribute.
   * @see #getLastMergePolicy()
   * @generated
   */
  void setLastMergePolicy(IMergePolicy<E> value);

  /**
   * Returns the value of the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapping</em>' containment reference.
   * @see #setMapping(GMapping)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getGComparison_Mapping()
   * @model containment="true" required="true"
   * @generated
   */
  GMapping<E, A, R> getMapping();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison#getMapping <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapping</em>' containment reference.
   * @see #getMapping()
   * @generated
   */
  void setMapping(GMapping<E, A, R> value);

} // GComparison
