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
package org.eclipse.emf.diffmerge.generic.impl.scopes;

import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.IScopePolicy;
import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;


/**
 * A partial implementation of IDataScope that delegates meta and technological aspects
 * to an auxiliary scope policy.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public abstract class AbstractDataScope<E>
implements IDataScope<E> {
  
  /** A non-null scope policy to which meta and technological aspects can be delegated */
  private final IScopePolicy<E> _scopePolicyDelegate;
  
  /** A potentially null object that identifies the origin of the scope */
  private Object _originator;
  
  
  /**
   * Default constructor
   */
  protected AbstractDataScope() {
    _scopePolicyDelegate = defineScopePolicy();
    _originator = null;
  }
  
  /**
   * Return the scope policy to which meta and technological aspects can be delegated.
   * This is only called at initialization time.
   * @return a non-null scope policy
   */
  protected abstract IScopePolicy<E> defineScopePolicy();
  
  /**
   * Return an object that characterizes or identifies this scope by default
   * @return a non-null object
   */
  protected Object getDefaultOriginator() {
    return this;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope#getOriginator()
   */
  public Object getOriginator() {
    return _originator != null? _originator: getDefaultOriginator();
  }
  
  /**
   * Return the scope policy to which meta and technological aspects can be delegated
   * @return a non-null scope policy
   */
  protected IScopePolicy<E> getScopePolicy() {
    return _scopePolicyDelegate;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetAttributes(java.lang.Object)
   */
  public List<?> mGetAttributes(E element_p) {
    return getScopePolicy().mGetAttributes(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetOppositeReference(java.lang.Object)
   */
  public Object mGetOppositeReference(Object reference_p) {
    return getScopePolicy().mGetOppositeReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetReferences(java.lang.Object)
   */
  public List<?> mGetReferences(E element_p) {
    return getScopePolicy().mGetReferences(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetType(java.lang.Object)
   */
  public Object mGetType(E element_p) {
    return getScopePolicy().mGetType(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsChangeableAttribute(java.lang.Object)
   */
  public boolean mIsChangeableAttribute(Object attribute_p) {
    return getScopePolicy().mIsChangeableAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsChangeableReference(java.lang.Object)
   */
  public boolean mIsChangeableReference(Object reference_p) {
    return getScopePolicy().mIsChangeableReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsContainerReference(java.lang.Object)
   */
  public boolean mIsContainerReference(Object reference_p) {
    return getScopePolicy().mIsContainerReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsContainmentReference(java.lang.Object)
   */
  public boolean mIsContainmentReference(Object reference_p) {
    return getScopePolicy().mIsContainerReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsIDAttribute(java.lang.Object)
   */
  public boolean mIsIDAttribute(Object attribute_p) {
    return getScopePolicy().mIsIDAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsManyAttribute(java.lang.Object)
   */
  public boolean mIsManyAttribute(Object attribute_p) {
    return getScopePolicy().mIsManyAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsManyReference(java.lang.Object)
   */
  public boolean mIsManyReference(Object reference_p) {
    return getScopePolicy().mIsManyReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsOptionalAttribute(java.lang.Object)
   */
  public boolean mIsOptionalAttribute(Object attribute_p) {
    return getScopePolicy().mIsOptionalAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsOptionalReference(java.lang.Object)
   */
  public boolean mIsOptionalReference(Object reference_p) {
    return getScopePolicy().mIsOptionalReference(reference_p);
  }
  
  /**
   * Set the originator of this scope.
   * If null, then the default originator will be used.
   * @see IDataScope#getOriginator()
   * @param originator_p a potentially null object
   */
  public void setOriginator(Object originator_p) {
    _originator = originator_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tGetID(java.lang.Object, boolean)
   */
  public Object tGetID(E element_p, boolean intrinsic_p) {
    return getScopePolicy().tGetID(element_p, intrinsic_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tIsDeletionRequired(java.lang.Object)
   */
  public boolean tIsDeletionRequired(Object reference_p) {
    return getScopePolicy().tIsDeletionRequired(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tNewBareElement(java.lang.Object)
   */
  public E tNewBareElement(Object source_p) {
    return getScopePolicy().tNewBareElement(source_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tSetID(java.lang.Object, java.lang.Object, boolean)
   */
  public boolean tSetID(E element_p, Object id_p, boolean intrinsic_p) {
    return getScopePolicy().tSetID(element_p, id_p, intrinsic_p);
  }
  
}
