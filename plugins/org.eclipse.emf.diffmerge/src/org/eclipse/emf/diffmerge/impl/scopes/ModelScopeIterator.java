/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.impl.scopes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EObject;


/**
 * A TreeIterator for model scopes.
 * @author Olivier Constant
 */
public class ModelScopeIterator implements TreeIterator<EObject> {
  
  /** The non-null model scope */
  private final IModelScope _scope;
  
  /** A non-null iterator over the root elements which are assumed to belong to the scope */
  private final Iterator<EObject> _rootIterator;
  
  /** The current, potentially null, iterator over the contents of a root */
  private TreeIterator<EObject> _rootContentIterator;
  
  /** Whether prune() has been called while a root was the current element */
  private boolean _prunedOnRoot;
  
  /** Whether the current element is one of the roots */
  private boolean _currentIsRoot;
  
  
  /**
   * Constructor
   * @param scope_p a non-null model scope
   */
  public ModelScopeIterator(IModelScope scope_p) {
    assert scope_p != null;
    _scope = scope_p;
    _rootIterator = _scope.getContents().iterator();
    _prunedOnRoot = false;
    _currentIsRoot = false;
  }
  
  /**
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext() {
    return hasNextNonRoot() || hasNextRoot();
  }
  
  /**
   * Return whether it is possible to get a non-root next element 
   */
  protected boolean hasNextNonRoot() {
    return !_prunedOnRoot && _rootContentIterator != null && _rootContentIterator.hasNext();
  }
  
  /**
   * Return whether it is possible to get a next root element
   */
  protected boolean hasNextRoot() {
    return _rootIterator.hasNext();
  }
  
  /**
   * @see java.util.Iterator#next()
   */
  public EObject next() {
    EObject result = null;
    if (hasNextNonRoot()) {
      result = _rootContentIterator.next();
      _currentIsRoot = false;
    } else if (hasNextRoot()) {
      result = _rootIterator.next();
      _rootContentIterator = _scope.getAllContents(result);
      _currentIsRoot = true;
    }
    _prunedOnRoot = false;
    if (result != null)
      return result;
    throw new NoSuchElementException();
  }
  
  /**
   * @see org.eclipse.emf.common.util.TreeIterator#prune()
   */
  public void prune() {
    if (_currentIsRoot) {
      _prunedOnRoot = true; 
    } else if (_rootContentIterator != null)  {
      _rootContentIterator.prune();
    }
  }
  
  /**
   * @see java.util.Iterator#remove()
   */
  public void remove() {
    throw new UnsupportedOperationException();
  }

}
