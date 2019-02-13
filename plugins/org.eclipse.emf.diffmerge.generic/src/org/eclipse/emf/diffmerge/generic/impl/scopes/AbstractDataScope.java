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

import org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope;


/**
 * A partial implementation of IDataScope.
 * 
 * @param <E> The type of data elements.
 * 
 * @author Olivier Constant
 */
public abstract class AbstractDataScope<E>
implements IDataScope<E> {
  
  /** A potentially null object that identifies the origin of the scope */
  private Object _originator;
  
  
  /**
   * Default constructor
   */
  protected AbstractDataScope() {
    _originator = null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope#covers(java.lang.Object)
   */
  public boolean covers(E element_p) {
    // Operator == is used for element identification
    for (E current : this) {
      if (current == element_p)
        return true;
    }
    return false;
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
   * Set the originator of this scope.
   * If null, then the default originator will be used.
   * @see IDataScope#getOriginator()
   * @param originator_p a potentially null object
   */
  public void setOriginator(Object originator_p) {
    _originator = originator_p;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.scopes.IDataScope#size()
   */
  public int size() {
    int result = 0;
    for (@SuppressWarnings("unused") E element : this) {
      result++;
    }
    return result;
  }
  
}
