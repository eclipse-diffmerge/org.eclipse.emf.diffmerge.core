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
package org.eclipse.emf.diffmerge.util.structures;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * A sequence of Comparable elements which is itself Comparable.
 * The natural order of sequences is derived from the natural order of the elements
 * similarly as the order of strings is derived from the alphanumeric order of characters.
 * @param <T> the type of the elements in the sequence
 * @author Olivier Constant
 */
public class ComparableSequence<T extends Comparable<T>>
implements Comparable<ComparableSequence<T>> {
  
  /** The non-null list of elements which form the path.
      We use a LinkedList in order to be able to prepend elements */
  private final LinkedList<T> _contents;
  
  
  /**
   * Default constructor
   */
  public ComparableSequence() {
    _contents = new LinkedList<T>();
  }
  
  /**
   * Full constructor
   * @param element_p the contents of the path
   */
  public ComparableSequence(List<? extends T> element_p) {
    this();
    _contents.addAll(element_p);
  }
  
  /**
   * Full constructor
   * @param elements_p the elements which form the path
   */
  public ComparableSequence(T[] elements_p) {
    this(Arrays.asList(elements_p));
  }
  
  /**
   * Append the given fragment to this qualified name
   * @param element_p a non-null object
   */
  public void append(T element_p) {
    _contents.addLast(element_p);
  }
  
  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(ComparableSequence<T> path_p) {
    Iterator<T> it1 = getContents().iterator();
    Iterator<T> it2 = path_p.getContents().iterator();
    while (it1.hasNext() && it2.hasNext()) {
      int result = it1.next().compareTo(it2.next());
      if (result != 0)
        return result;
    }
    return size() - path_p.size();
  }
  
  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj_p) {
    return obj_p instanceof ComparableSequence &&
      ((ComparableSequence<?>)obj_p).getContents().equals(getContents());
  }
  
  /**
   * Return the path as a list
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<T> getContents() {
    return Collections.unmodifiableList(_contents);
  }
  
  /**
   * Return the char which is being used as separator in method toString()
   */
  protected char getSeparator() {
    return '.';
  }
  
  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return getContents().hashCode();
  }
  
  /**
   * Return whether this path is empty, i.e., it does not contain any element
   */
  public boolean isEmpty() {
    return size() == 0;
  }
  
  /**
   * Prepend the given element to this path
   * @param element_p a non-null object
   */
  public void prepend(T element_p) {
    _contents.addFirst(element_p);
  }
  
  /**
   * Return the number of elements in this path
   */
  public int size() {
    return getContents().size();
  }
  
  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    final char SEPARATOR = getSeparator();
    boolean first = true;
    for (Comparable<?> fragment : _contents) {
      if (first) first = false; else builder.append(SEPARATOR);
      builder.append(fragment);
    }
    return builder.toString();
  }
  
}
