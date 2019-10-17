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
package org.eclipse.emf.diffmerge.generic.api;

import java.util.Collection;

import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableDataScope;


/**
 * A policy that handles meta or schema-related aspects as well as technological aspects
 * of data scopes.
 * It defines rules for structural constraints. It provides ID management, element
 * creation mechanisms and other meta information.
 * It typically reflects aspects of the underlying data technology that must be taken
 * into account by a diff/merge engine. It is typically implemented once for a family
 * of technologically homogeneous scopes.
 * Methods that deal with meta aspects are prefixed with "m".
 * Methods that deal with technical aspects are prefixed with "t".
 * 
 * @param <E> The type of data elements.
 * 
 * @author Matthieu Helleboid
 * @author Olivier Constant
 */
public interface IDataPolicy<E> {
  
  /**
   * Return the set of attributes which are applicable to the given element.
   * @param element_p a non-null element
   * @return a non-null, potentially empty, unmodifiable ordered set
   */
  Collection<?> mGetAttributes(E element_p);
  
  /**
   * Return the opposite of the given reference, if any.
   * Opposite references manifest bidirectional links between elements and are
   * thus assumed to be automatically kept in sync by the data technology.
   * @param reference_p a non-null reference
   * @return a potentially null reference
   */
  Object mGetOppositeReference(Object reference_p);
  
  /**
   * Return the set of references which are applicable to the given element.
   * @param element_p a non-null element which belongs to getReferences(element_p)
   * @return a non-null, potentially empty, unmodifiable ordered set
   */
  Collection<?> mGetReferences(E element_p);
  
  /**
   * Return an object that represents the type of the given element
   * @param element_p a non-null element
   * @return a non-null type
   */
  Object mGetType(E element_p);
  
  /**
   * Return whether the given reference is the opposite of a containment.
   * Class invariant: isContainerReference(r) ==
   *  getOppositeReference(r) != null &&
   *    isContainmentReference(getOppositeReference(r))
   * @see IDataPolicy#mIsContainmentReference(Object)
   * @see IDataPolicy#mGetOppositeReference(Object)
   * @param reference_p a non-null reference
   */
  boolean mIsContainerReference(Object reference_p);
  
  /**
   * Return whether the given reference manifests a parent-child relationship
   * in a forest of elements. The data technology is assumed to automatically
   * preserve the forest structure by, e.g., removing reference values and moving
   * elements as a result of other changes.
   * @param reference_p a non-null reference
   */
  boolean mIsContainmentReference(Object reference_p);
  
  /**
   * Return whether the given attribute tolerates that its values be changed
   * @param attribute_p a non-null attribute
   */
  boolean mIsChangeableAttribute(Object attribute_p);
  
  /**
   * Return whether the given reference tolerates that its values be changed
   * @param reference_p a non-null reference
   */
  boolean mIsChangeableReference(Object reference_p);
  
  /**
   * Return whether the given attribute stores intrinsic IDs, that is,
   * IDs for the owning elements that are unique within the data scope.
   * @param attribute_p a non-null attribute
   */
  boolean mIsIDAttribute(Object attribute_p);
  
  /**
   * Return whether the given attribute tolerates more than one value on the
   * same element
   * @param attribute_p a non-null attribute
   */
  boolean mIsManyAttribute(Object attribute_p);
  
  /**
   * Return whether the given reference tolerates more than one value on the
   * same element
   * @param reference_p a non-null reference
   */
  boolean mIsManyReference(Object reference_p);
  
  /**
   * Return whether the given attribute tolerates zero value
   * @param attribute_p a non-null attribute
   */
  boolean mIsOptionalAttribute(Object attribute_p);
  
  /**
   * Return whether the given reference tolerates zero value
   * @param reference_p a non-null reference
   */
  boolean mIsOptionalReference(Object reference_p);
  
  /**
   * Return the ID of the given element, if any
   * @param element_p a non-null element
   * @param intrinsic_p whether an intrinsic (attribute-based) ID must be returned,
   *          or extrinsic (dependent on the persistence layer)
   * @return a potentially null object
   */
  Object tGetID(E element_p, boolean intrinsic_p);
  
  /**
   * Return whether a value of the given cross-reference must be explicitly deleted
   * when the value element is deleted. This operation aims at enabling the usage context
   * to determine what connections (reference values) must be removed prior to element removals.
   * If !tIsElementDisconnectionRequired(), then this operation has no impact.
   * @param reference_p a non-null reference
   */
  boolean tIsDisconnectionRequired(Object reference_p);
  
  /**
   * Return whether elements in a scope must be disconnected from others, in terms of
   * reference values, prior to their removal
   * @see IEditableDataScope#disconnect(Object)
   */
  boolean tIsElementDisconnectionRequired();
  
  /**
   * Create and return a new bare element, i.e., an element without particular values
   * on its attributes or references, as the match of the given source element.
   * Note that the given source element may be of any arbitrary nature.
   * @param source_p a non-null object
   * @return an element that cannot be null
   */
  E tNewBareElement(Object source_p);
  
  /**
   * Set the ID of the given element
   * @param element_p a non-null element
   * @param id_p a potentially null object
   * @param intrinsic_p whether the intrinsic (attribute-based) or extrinsic
   *          (dependent on the persistence layer) ID is concerned
   * @return a potentially null object
   */
  boolean tSetID(E element_p, Object id_p, boolean intrinsic_p);
  
}
