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
package org.eclipse.emf.diffmerge.util.structures;


/**
 * An equality tester allows checking whether two objects must be
 * considered equal in the context where the tester is being used.
 * Equality must be an equivalence relation: it must be reflexive,
 * transitive and symmetric. Implementors must enforce these properties.
 * Equality testers are also assumed to be stateless. 
 * @author Olivier Constant
 */
public interface IEqualityTester {
  
  /**
   * Return whether the two given objects are considered equal by this tester.
   * Result must comply to reflexivity, symmetry and transitivity.
   * @param o1_p a potentially null object
   * @param o2_p a potentially null object
   */
  boolean areEqual(Object o1_p, Object o2_p);
  
  /**
   * Return a hash code for the given object. Result must be consistent with areEqual:
   * if areEqual(o1, o2) then hashCodeFor(o1) == hashCodeFor(o2).
   * @see Object#hashCode()
   * @param o_p a non-null object
   */
  int hashCodeFor(Object o_p);
  
  
  /**
   * Predefined tester based on equality by reference
   */
  IEqualityTester BY_REFERENCE = new IEqualityTester() {
    public boolean areEqual(Object o1_p, Object o2_p) { return o1_p == o2_p; }
    public int hashCodeFor(Object o_p) { return System.identityHashCode(o_p); }
  };
  
  /**
   * Predefined tester based on Object.equals(Object)
   */
  IEqualityTester BY_EQUALS = new IEqualityTester() {
    public boolean areEqual(Object o1_p, Object o2_p) {
      return o1_p == o2_p || o1_p != null && o1_p.equals(o2_p);
    }
    public int hashCodeFor(Object o_p) { return o_p.hashCode(); }
  };
  
}
