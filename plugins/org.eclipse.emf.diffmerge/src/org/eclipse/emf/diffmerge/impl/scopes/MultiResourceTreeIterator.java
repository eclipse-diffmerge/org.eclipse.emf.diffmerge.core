/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017 Thales Global Services S.A.S.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A TreeIterator of model elements which is derived from the contents of
 * a List of Resources.
 * @author Olivier Constant
 */
public class MultiResourceTreeIterator implements TreeIterator<EObject> {
  
  /** A non-null iterator over the resources */
  protected final Iterator<? extends Resource> _resourceIterator;
  
  /** The current, potentially null, iterator over the contents of a resource */
  protected TreeIterator<EObject> _contentIterator;
  
  
  /**
   * Constructor
   * @param resourceIterator_p a non-null iterator over the resources
   */
  public MultiResourceTreeIterator(Iterator<? extends Resource> resourceIterator_p) {
    assert resourceIterator_p != null;
    _resourceIterator = resourceIterator_p;
    _contentIterator = null;
  }
  
  /**
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext() {
    update();
    return _contentIterator != null && _contentIterator.hasNext();
  }
  
  /**
   * @see java.util.Iterator#next()
   */
  public EObject next() {
    if (hasNext())
      return _contentIterator.next();
    throw new NoSuchElementException();
  }
  
  /**
   * @see org.eclipse.emf.common.util.TreeIterator#prune()
   */
  public void prune() {
    if (_contentIterator != null)
      // Previous calls to update() have no impact
      _contentIterator.prune();
  }
  
  /**
   * @see java.util.Iterator#remove()
   */
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Update the state of this iterator if needed
   */
  protected void update() {
    while ((_contentIterator == null || !_contentIterator.hasNext()) &&
        _resourceIterator.hasNext()) {
      Resource nextResource = _resourceIterator.next();
      _contentIterator = nextResource.getAllContents();
    }
  }
  
}
