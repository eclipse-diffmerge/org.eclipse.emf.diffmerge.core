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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A partial implementation of IComparison based on EMF.
 * Type parameter <S> should be understood as <S extends IEditableTreeDataScope<E>>.
 * <!-- end-model-doc -->
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
 * @model abstract="true" superTypes="org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified org.eclipse.emf.diffmerge.generic.gdiffdata.IEditableComparison&lt;E&gt;"
 * @generated
 */
public interface EComparison<E, A, R, S> extends EIdentified, Editable<E> {
  /**
   * Returns the value of the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ancestor Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ancestor Scope</em>' attribute.
   * @see #setAncestorScope(Object)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_AncestorScope()
   * @model transient="true"
   * @generated
   */
  S getAncestorScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getAncestorScope <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ancestor Scope</em>' attribute.
   * @see #getAncestorScope()
   * @generated
   */
  void setAncestorScope(S value);

  /**
   * Returns the value of the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Scope</em>' attribute.
   * @see #setReferenceScope(Object)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_ReferenceScope()
   * @model required="true" transient="true"
   * @generated
   */
  S getReferenceScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getReferenceScope <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Scope</em>' attribute.
   * @see #getReferenceScope()
   * @generated
   */
  void setReferenceScope(S value);

  /**
   * Returns the value of the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Scope</em>' attribute.
   * @see #setTargetScope(Object)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_TargetScope()
   * @model required="true" transient="true"
   * @generated
   */
  S getTargetScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getTargetScope <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Scope</em>' attribute.
   * @see #getTargetScope()
   * @generated
   */
  void setTargetScope(S value);

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
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IMatchPolicy&lt;E&gt;" transient="true"
   * @generated
   */
  IMatchPolicy<E> getLastMatchPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMatchPolicy <em>Last Match Policy</em>}' attribute.
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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_LastDiffPolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IDiffPolicy&lt;E&gt;" transient="true"
   * @generated
   */
  IDiffPolicy<E> getLastDiffPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastDiffPolicy <em>Last Diff Policy</em>}' attribute.
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
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_LastMergePolicy()
   * @model dataType="org.eclipse.emf.diffmerge.generic.gdiffdata.IMergePolicy&lt;E&gt;" transient="true"
   * @generated
   */
  IMergePolicy<E> getLastMergePolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getLastMergePolicy <em>Last Merge Policy</em>}' attribute.
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
   * @see #setMapping(EMapping)
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage#getEComparison_Mapping()
   * @model containment="true" required="true"
   * @generated
   */
  EMapping<E, A, R, S> getMapping();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison#getMapping <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapping</em>' containment reference.
   * @see #getMapping()
   * @generated
   */
  void setMapping(EMapping<E, A, R, S> value);

} // EComparison
