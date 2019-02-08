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
package org.eclipse.emf.diffmerge.generic.gdiffdata;

import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getAncestorScope <em>Ancestor Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getReferenceScope <em>Reference Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMatchPolicy <em>Last Match Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastDiffPolicy <em>Last Diff Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMergePolicy <em>Last Merge Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison()
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableComparison&lt;E, A, R&gt;"
 * @generated
 */
public interface EComparison<E, A, R> extends EIdentified, Editable<E, A, R> {
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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_AncestorScope()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableTreeDataScope&lt;E, A, R&gt;" transient="true"
   * @generated
   */
  IEditableTreeDataScope<E, A, R> getAncestorScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getAncestorScope <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ancestor Scope</em>' attribute.
   * @see #getAncestorScope()
   * @generated
   */
  void setAncestorScope(IEditableTreeDataScope<E, A, R> value);

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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_ReferenceScope()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableTreeDataScope&lt;E, A, R&gt;" required="true" transient="true"
   * @generated
   */
  IEditableTreeDataScope<E, A, R> getReferenceScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getReferenceScope <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Scope</em>' attribute.
   * @see #getReferenceScope()
   * @generated
   */
  void setReferenceScope(IEditableTreeDataScope<E, A, R> value);

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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_TargetScope()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableTreeDataScope&lt;E, A, R&gt;" required="true" transient="true"
   * @generated
   */
  IEditableTreeDataScope<E, A, R> getTargetScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getTargetScope <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Scope</em>' attribute.
   * @see #getTargetScope()
   * @generated
   */
  void setTargetScope(IEditableTreeDataScope<E, A, R> value);

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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_LastMatchPolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatchPolicy&lt;E, A, R&gt;" transient="true"
   * @generated
   */
  IMatchPolicy<E, A, R> getLastMatchPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMatchPolicy <em>Last Match Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Match Policy</em>' attribute.
   * @see #getLastMatchPolicy()
   * @generated
   */
  void setLastMatchPolicy(IMatchPolicy<E, A, R> value);

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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_LastDiffPolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IDiffPolicy&lt;E, A, R&gt;" transient="true"
   * @generated
   */
  IDiffPolicy<E, A, R> getLastDiffPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastDiffPolicy <em>Last Diff Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Diff Policy</em>' attribute.
   * @see #getLastDiffPolicy()
   * @generated
   */
  void setLastDiffPolicy(IDiffPolicy<E, A, R> value);

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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_LastMergePolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IMergePolicy&lt;E, A, R&gt;" transient="true"
   * @generated
   */
  IMergePolicy<E, A, R> getLastMergePolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMergePolicy <em>Last Merge Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Merge Policy</em>' attribute.
   * @see #getLastMergePolicy()
   * @generated
   */
  void setLastMergePolicy(IMergePolicy<E, A, R> value);

  /**
   * Returns the value of the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapping</em>' containment reference.
   * @see #setMapping(EMapping)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_Mapping()
   * @model containment="true" required="true"
   * @generated
   */
  EMapping<E, A, R> getMapping();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getMapping <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapping</em>' containment reference.
   * @see #getMapping()
   * @generated
   */
  void setMapping(EMapping<E, A, R> value);

} // EComparison
