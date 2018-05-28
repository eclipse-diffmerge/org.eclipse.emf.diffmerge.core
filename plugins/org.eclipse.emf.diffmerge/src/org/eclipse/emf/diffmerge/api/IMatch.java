/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.api;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;



/**
 * A match between elements which also contains differences between those elements.
 * @see IPureMatch
 * @author Olivier Constant
 */
public interface IMatch extends IPureMatch {
  
  /**
   * Return all differences which depend on this match (container differences included).
   * The resulting collection may become obsolete if differences are later added.
   * @return a non-null, potentially empty, unmodifiable collection
   */
  List<IDifference> getAllDifferences();
  
  /**
   * Return the attribute differences associated to the given attribute.
   * The resulting collection may become obsolete if differences are later added.
   * @param attribute_p a non-null attribute
   * @return a non-null, possibly empty, unmodifiable collection
   */
  Collection<IAttributeValuePresence> getAttributeDifferences(EAttribute attribute_p);
  
  /**
   * Return the attribute differences associated to the given attribute
   * @param attribute_p a non-null attribute
   * @param value_p a non-null object
   * @return a possibly null attribute value presence
   */
  IAttributeValuePresence getAttributeValueDifference(EAttribute attribute_p, Object value_p);
  
  /**
   * Return the set of attributes for which differences exist.
   * The resulting collection may become obsolete if differences are later added.
   * @return a non-null, potentially empty, unmodifiable collection
   */
  Collection<EAttribute> getAttributesWithDifferences();
  
  /**
   * Return the difference corresponding to the presence of an unmatched
   * element, if any.
   * Class invariant:
   *  isPartial() || getElementPresenceDifference() == null
   * @return a potentially null difference
   */
  IElementPresence getElementPresenceDifference();
  
  /**
   * Return the number of differences which are not related to the containment
   * tree (on attributes and cross references)
   */
  int getNbNoContainmentDifferences();
  
  /**
   * Return the difference concerned with the ownership of the elements
   * represented by this match in the given role
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @return a potentially null reference value presence
   */
  IReferenceValuePresence getOwnershipDifference(Role role_p);
  
  /**
   * Return the order difference on the given feature in the given role, if any
   * @param feature_p a non-null feature
   * @param role_p a non-null role which is TARGET or REFERENCE
   * @return a potentially null value presence such that isOrder()
   */
  IValuePresence getOrderDifference(EStructuralFeature feature_p, Role role_p);
  
  /**
   * Return the presence differences in the given role which are related to this match.
   * The resulting collection may become obsolete if differences are later added.
   * @param role_p a non-null role
   * @return an unmodifiable non-null collection of differences
   */
  Collection<IDifference> getPresenceDifferencesIn(Role role_p);
  
  /**
   * Return the reference differences associated to the given reference.
   * The resulting collection may become obsolete if differences are later added.
   * @param reference_p a non-null reference
   * @return a non-null, possibly empty, unmodifiable collection
   */
  Collection<IReferenceValuePresence> getReferenceDifferences(
      EReference reference_p);
  
  /**
   * Return the reference difference that represents the referencing of the given
   * value element via the given reference
   * @param reference_p a non-null reference
   * @param value_p a non-null element
   * @return a possibly null reference value presence
   */
  IReferenceValuePresence getReferenceValueDifference(EReference reference_p,
      EObject value_p);
  
  /**
   * Return the set of references for which differences exist.
   * The resulting collection may become obsolete if differences are later added.
   * @return a non-null, potentially empty, unmodifiable collection
   */
  Collection<EReference> getReferencesWithDifferences();
  
  /**
   * Return the differences whose origin is this match
   * (containment differences included, container differences excluded).
   * The resulting collection may become obsolete if differences are later added.
   * @return a non-null, potentially empty, unmodifiable collection
   */
  List<IDifference> getRelatedDifferences();
  
  /**
   * Return whether this match corresponds to an element which has been moved
   * in the containment tree
   */
  boolean isAMove();
  
  
  /**
   * A match with editing features.
   * All concrete classes implementing IMatch must also implement this interface.
   */
  interface Editable extends IMatch, IPureMatch.Editable {
    /**
     * Add the given difference to the differences related to this match
     * @param difference_p a non-null difference
     */
    void addRelatedDifference(IDifference difference_p);
    
    /**
     * Register the given difference as an ownership difference on this match
     * @param presence_p a non-null reference value presence which is such that
     *                   the reference is a containment and the value is this match
     */
    void addOwnershipDifference(IReferenceValuePresence presence_p);
  }
  
}
