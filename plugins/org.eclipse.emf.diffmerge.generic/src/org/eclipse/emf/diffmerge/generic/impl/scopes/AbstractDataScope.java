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

import java.util.Collection;

import org.eclipse.emf.diffmerge.generic.api.IDataPolicy;
import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;


/**
 * A partial implementation of IDataScope that delegates meta and technological aspects
 * to an auxiliary data policy.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public abstract class AbstractDataScope<E>
implements IDataScope<E> {
  
  /** A non-null data policy to which meta and technological aspects can be delegated */
  private final IDataPolicy<E> _dataPolicyDelegate;
  
  /** A potentially null object that identifies the origin of the scope */
  private Object _originator;
  
  
  /**
   * Default constructor
   */
  protected AbstractDataScope() {
    _dataPolicyDelegate = defineDataPolicy();
    _originator = null;
  }
  
  /**
   * Return the data policy to which meta and technological aspects can be delegated.
   * This is only called at initialization time.
   * @return a non-null data policy
   */
  protected abstract IDataPolicy<E> defineDataPolicy();
  
  /**
   * Return the data policy to which meta and technological aspects can be delegated
   * @return a non-null data policy
   */
  protected IDataPolicy<E> getDataPolicy() {
    return _dataPolicyDelegate;
  }
  
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
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mGetAttributes(java.lang.Object)
   */
  public Collection<?> mGetAttributes(E element_p) {
    return getDataPolicy().mGetAttributes(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mGetOppositeReference(java.lang.Object)
   */
  public Object mGetOppositeReference(Object reference_p) {
    return getDataPolicy().mGetOppositeReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mGetReferences(java.lang.Object)
   */
  public Collection<?> mGetReferences(E element_p) {
    return getDataPolicy().mGetReferences(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mGetType(java.lang.Object)
   */
  public Object mGetType(E element_p) {
    return getDataPolicy().mGetType(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsChangeableAttribute(java.lang.Object)
   */
  public boolean mIsChangeableAttribute(Object attribute_p) {
    return getDataPolicy().mIsChangeableAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsChangeableReference(java.lang.Object)
   */
  public boolean mIsChangeableReference(Object reference_p) {
    return getDataPolicy().mIsChangeableReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsContainerReference(java.lang.Object)
   */
  public boolean mIsContainerReference(Object reference_p) {
    return getDataPolicy().mIsContainerReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsContainmentReference(java.lang.Object)
   */
  public boolean mIsContainmentReference(Object reference_p) {
    return getDataPolicy().mIsContainmentReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsIDAttribute(java.lang.Object)
   */
  public boolean mIsIDAttribute(Object attribute_p) {
    return getDataPolicy().mIsIDAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsManyAttribute(java.lang.Object)
   */
  public boolean mIsManyAttribute(Object attribute_p) {
    return getDataPolicy().mIsManyAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsManyReference(java.lang.Object)
   */
  public boolean mIsManyReference(Object reference_p) {
    return getDataPolicy().mIsManyReference(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsOptionalAttribute(java.lang.Object)
   */
  public boolean mIsOptionalAttribute(Object attribute_p) {
    return getDataPolicy().mIsOptionalAttribute(attribute_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#mIsOptionalReference(java.lang.Object)
   */
  public boolean mIsOptionalReference(Object reference_p) {
    return getDataPolicy().mIsOptionalReference(reference_p);
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
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#tGetID(java.lang.Object, boolean)
   */
  public Object tGetID(E element_p, boolean intrinsic_p) {
    return getDataPolicy().tGetID(element_p, intrinsic_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#tIsDisconnectionRequired(java.lang.Object)
   */
  public boolean tIsDisconnectionRequired(Object reference_p) {
    return getDataPolicy().tIsDisconnectionRequired(reference_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#tIsElementDisconnectionRequired()
   */
  public boolean tIsElementDisconnectionRequired() {
    return getDataPolicy().tIsElementDisconnectionRequired();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#tNewBareElement(java.lang.Object)
   */
  public E tNewBareElement(Object source_p) {
    return getDataPolicy().tNewBareElement(source_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IDataPolicy#tSetID(java.lang.Object, java.lang.Object, boolean)
   */
  public boolean tSetID(E element_p, Object id_p, boolean intrinsic_p) {
    return getDataPolicy().tSetID(element_p, id_p, intrinsic_p);
  }
  
}
