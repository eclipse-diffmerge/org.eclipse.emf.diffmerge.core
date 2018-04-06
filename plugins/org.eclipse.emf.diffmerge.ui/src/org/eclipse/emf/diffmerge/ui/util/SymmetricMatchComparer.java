/**
 * <copyright>
 * 
 * Copyright (c) 2018 Thales Global Services S.A.S and others.
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
package org.eclipse.emf.diffmerge.ui.util;

import static org.eclipse.emf.diffmerge.api.Role.ANCESTOR;
import static org.eclipse.emf.diffmerge.api.Role.REFERENCE;
import static org.eclipse.emf.diffmerge.api.Role.TARGET;

import org.eclipse.emf.diffmerge.api.IPureMatch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IElementComparer;


/**
 * An element comparer that considers matches symmetrically in a left/right
 * perspective. It allows swapping left/right sides while preserving
 * the representation of comparison contents in a structured viewer.
 * @see IElementComparer
 * @author Olivier Constant
 */
public class SymmetricMatchComparer implements IElementComparer {
  
  /**
   * @see org.eclipse.jface.viewers.IElementComparer#equals(java.lang.Object, java.lang.Object)
   */
  public boolean equals(Object a_p, Object b_p) {
    boolean result = a_p == b_p;
    if (!result && a_p instanceof IPureMatch && b_p instanceof IPureMatch) {
      IPureMatch a = (IPureMatch) a_p;
      IPureMatch b = (IPureMatch) b_p;
      EObject aA = a.get(ANCESTOR);
      EObject bA = b.get(ANCESTOR);
      EObject aT = a.get(TARGET);
      EObject bT = b.get(TARGET);
      EObject aR = a.get(REFERENCE);
      EObject bR = b.get(REFERENCE);
      result = // Equality by reference of elements, symmetrically on T/R
          aA == bA &&
          (aT == bT && aR == bR ||
          aT == bR && aR == bT);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.IElementComparer#hashCode(java.lang.Object)
   */
  public int hashCode(Object element_p) {
    int result = 0;
    if (element_p != null) {
      if (element_p instanceof IPureMatch) {
        IPureMatch match = (IPureMatch)element_p;
        if (match.get(TARGET) != null)
          result += match.get(TARGET).hashCode();
        if (match.get(REFERENCE) != null)
          result += match.get(REFERENCE).hashCode();
        if (match.get(ANCESTOR) != null)
          result += match.get(ANCESTOR).hashCode();
      } else {
        result = element_p.hashCode();
      }
    }
    return result;
  }
  
}