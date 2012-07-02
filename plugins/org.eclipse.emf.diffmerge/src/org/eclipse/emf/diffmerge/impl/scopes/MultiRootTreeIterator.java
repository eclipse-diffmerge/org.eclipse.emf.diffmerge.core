/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
 * A TreeIterator of model elements which is derived from a model scope on
 * a list of root model elements, those roots being included in the iteration.
 * @author Olivier Constant
 */
public class MultiRootTreeIterator implements TreeIterator<EObject> {
  
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
   * @param rootIterator_p a non-null iterator over the root elements which are assumed to belong to the scope
   */
  public MultiRootTreeIterator(IModelScope scope_p, Iterator<EObject> rootIterator_p) {
    assert scope_p != null && rootIterator_p != null;
    _scope = scope_p;
    _rootIterator = rootIterator_p;
    _prunedOnRoot = false;
    _currentIsRoot = false;
  }
  
  /**
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext() {
    return _rootContentIterator != null && _rootContentIterator.hasNext() ||
      _rootIterator.hasNext();
  }
  
  /**
   * @see java.util.Iterator#next()
   */
  public EObject next() {
    EObject result = null;
    if (!_prunedOnRoot && _rootContentIterator != null &&
        _rootContentIterator.hasNext()) {
      result = _rootContentIterator.next();
      _currentIsRoot = false;
    } else if (_rootIterator.hasNext()) {
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
