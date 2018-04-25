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
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.diffmerge.api.IComparison.Editable;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EComparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getAncestorScope <em>Ancestor Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getReferenceScope <em>Reference Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMatchPolicy <em>Last Match Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastDiffPolicy <em>Last Diff Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMergePolicy <em>Last Merge Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison()
 * @model superTypes="org.eclipse.emf.diffmerge.diffdata.EIdentified org.eclipse.emf.diffmerge.diffdata.IEditableComparison"
 * @generated
 */
public interface EComparison extends EIdentified, Editable {
  /**
   * Returns the value of the '<em><b>Ancestor Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ancestor Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ancestor Scope</em>' attribute.
   * @see #setAncestorScope(IEditableModelScope)
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison_AncestorScope()
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.IEditableModelScope" transient="true"
   * @generated
   */
  IEditableModelScope getAncestorScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getAncestorScope <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ancestor Scope</em>' attribute.
   * @see #getAncestorScope()
   * @generated
   */
  void setAncestorScope(IEditableModelScope value);

  /**
   * Returns the value of the '<em><b>Reference Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Scope</em>' attribute.
   * @see #setReferenceScope(IEditableModelScope)
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison_ReferenceScope()
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.IEditableModelScope" required="true" transient="true"
   * @generated
   */
  IEditableModelScope getReferenceScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getReferenceScope <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Scope</em>' attribute.
   * @see #getReferenceScope()
   * @generated
   */
  void setReferenceScope(IEditableModelScope value);

  /**
   * Returns the value of the '<em><b>Target Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Scope</em>' attribute.
   * @see #setTargetScope(IEditableModelScope)
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison_TargetScope()
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.IEditableModelScope" required="true" transient="true"
   * @generated
   */
  IEditableModelScope getTargetScope();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getTargetScope <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Scope</em>' attribute.
   * @see #getTargetScope()
   * @generated
   */
  void setTargetScope(IEditableModelScope value);

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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison_LastMatchPolicy()
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.IMatchPolicy" transient="true"
   * @generated
   */
  IMatchPolicy getLastMatchPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMatchPolicy <em>Last Match Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Match Policy</em>' attribute.
   * @see #getLastMatchPolicy()
   * @generated
   */
  void setLastMatchPolicy(IMatchPolicy value);

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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison_LastDiffPolicy()
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.IDiffPolicy" transient="true"
   * @generated
   */
  IDiffPolicy getLastDiffPolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastDiffPolicy <em>Last Diff Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Diff Policy</em>' attribute.
   * @see #getLastDiffPolicy()
   * @generated
   */
  void setLastDiffPolicy(IDiffPolicy value);

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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison_LastMergePolicy()
   * @model dataType="org.eclipse.emf.diffmerge.diffdata.IMergePolicy" transient="true"
   * @generated
   */
  IMergePolicy getLastMergePolicy();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getLastMergePolicy <em>Last Merge Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Last Merge Policy</em>' attribute.
   * @see #getLastMergePolicy()
   * @generated
   */
  void setLastMergePolicy(IMergePolicy value);

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
   * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEComparison_Mapping()
   * @model containment="true" required="true"
   * @generated
   */
  EMapping getMapping();

  /**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.diffdata.EComparison#getMapping <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapping</em>' containment reference.
   * @see #getMapping()
   * @generated
   */
  void setMapping(EMapping value);

} // EComparison
