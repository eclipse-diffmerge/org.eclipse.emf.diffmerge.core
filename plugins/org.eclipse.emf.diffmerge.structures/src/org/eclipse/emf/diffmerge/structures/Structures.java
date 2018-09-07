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
package org.eclipse.emf.diffmerge.structures;

import java.util.Iterator;
import java.util.Map;


/**
 * A utility class related to data structures.
 * 
 * @author Olivier Constant
 */
public final class Structures {
  
  /** A representation of the null value */
  public static final String NULL = "null"; //$NON-NLS-1$
  
  
  /**
   * Return a compact string representation of the given iterable
   * @param iterable_p a potentially null iterable
   * @return a non-null string
   */
  public static String toIterableString(Iterable<?> iterable_p) {
    if (iterable_p == null)
      return NULL;
    StringBuilder builder = new StringBuilder();
    builder.append('[');
    Iterator<?> it = iterable_p.iterator();
    while (it.hasNext()) {
      Object element = it.next();
      String segment;
      if (element == iterable_p)
        segment = "(this collection)"; //$NON-NLS-1$
      else if (element == null)
        segment = NULL;
      else
        segment = element.toString();
      builder.append(segment);
      if (it.hasNext())
        builder.append('.');
    }
    builder.append(']');
    return builder.toString();
  }
  
  /**
   * Return a compact string representation of the given map entry
   * @param entry_p a potentially null map entry
   * @return a non-null string
   */
  public static String toMapEntryString(Map.Entry<?, ?> entry_p) {
    if (entry_p == null)
      return NULL;
    StringBuilder builder = new StringBuilder();
    Object key = entry_p.getKey();
    String keySegment;
    if (key == entry_p)
      keySegment = "(this entry)"; //$NON-NLS-1$
    else if (key == null)
      keySegment = NULL;
    else
      keySegment = key.toString();
    Object value = entry_p.getValue();
    String valueSegment;
    if (value == entry_p)
      valueSegment = "(this entry)"; //$NON-NLS-1$
    else if (value == null)
      valueSegment = NULL;
    else
      valueSegment = value.toString();
    builder.append(keySegment);
    builder.append(':');
    builder.append(valueSegment);
    return builder.toString();
  }
  
  /**
   * Return a compact string representation of the given map
   * @param map_p a potentially null map
   * @return a non-null string
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static String toMapString(Map map_p) {
    if (map_p == null)
      return NULL;
    StringBuilder builder = new StringBuilder();
    builder.append('[');
    Iterator<Map.Entry> it = map_p.entrySet().iterator();
    while (it.hasNext()) {
      Object entry = it.next();
      String segment;
      if (entry == map_p) // this is highly improbable, but still...
        segment = "(this)"; //$NON-NLS-1$
      else if (entry == null)
        segment = NULL;
      else
        segment = entry.toString();
      builder.append(segment);
      if (it.hasNext())
        builder.append(',');
    }
    builder.append(']');
    return builder.toString();
  }
  
}