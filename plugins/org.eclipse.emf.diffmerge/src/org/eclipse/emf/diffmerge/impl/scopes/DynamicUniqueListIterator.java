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
package org.eclipse.emf.diffmerge.impl.scopes;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * An iterator over unique Lists (ordered sets) which supports dynamic
 * modification of the List. The behavior is as follows:
 * - If no call to next() has been made, next() returns the first element
 *   of the List, if any. If the List is empty, hasNext() returns false.
 * - Otherwise, if the element returned by the last call to next() still
 *   belongs to the List, its successor in the List is returned, if any.
 *   In other cases, hasNext() returns false.
 * Access to the List by multiple threads is not supported.
 * @author Olivier Constant
 */
public class DynamicUniqueListIterator<E> implements Iterator<E> {

  /** The non-null list being iterated */
  private final List<? extends E> _list;
  
  /** The current element of the list, that is the element returned
      by the last call to next(), or null if no such call happened */
  private E _current;
  
  
  /**
   * Constructor
   * @param list_p a non-null list
   */
  public DynamicUniqueListIterator(List<? extends E> list_p) {
    assert list_p != null;
    _list = list_p;
    _current = null;
  }
  
  /**
   * @see java.util.Iterator#hasNext()
   */
  public boolean hasNext() {
    boolean result;
    if (_current == null)
      result = !_list.isEmpty();
    else {
      int pos = _list.indexOf(_current);
      result = pos >= 0 && pos < _list.size()-1;
    }
    return result;
  }

  /**
   * @see java.util.Iterator#next()
   */
  public E next() {
    E result = null;
    if (_current == null) {
      if (!_list.isEmpty())
        result = _list.get(0);
    } else {
      int pos = _list.indexOf(_current);
      if (pos >= 0 && pos < _list.size()-1)
        result = _list.get(pos + 1);
    }
    if (result != null) {
      _current = result;
      return result;
    }
    throw new NoSuchElementException();
  }

  /**
   * @see java.util.Iterator#remove()
   */
  public void remove() {
    throw new UnsupportedOperationException();
  }

}
