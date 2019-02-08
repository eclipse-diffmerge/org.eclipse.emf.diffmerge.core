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
package org.eclipse.emf.diffmerge.generic.api.diff;


/**
 * A difference which is due to the unmatched presence of a value on some feature
 * in a given comparison role.
 *
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public interface IValuePresence<E, A, R> extends IElementRelativeDifference<E, A, R>,
IPresenceDifference<E, A, R>, IMergeableDifference<E, A, R> {
  
  /**
   * Return the feature holding the value
   * @return a non-null feature
   */
  Object getFeature();
  
  /**
   * Return the difference, if any, which is symmetrical to this one. 
   * @see IValuePresence#isSymmetricalTo(IValuePresence)
   * @return a potentially null value presence (always null if upper bound is not 1 and !isOrder())
   */
  IValuePresence<E, A, R> getSymmetrical();
  
  /**
   * Return the non-null value being held
   * @return a non-null object
   */
  Object getValue();
  
  /**
   * Return whether the feature of this difference tolerates that its values be changed
   */
  boolean isChangeableFeature();
  
  /**
   * Return whether the feature of this difference tolerates more than one value on the
   * same element
   */
  boolean isManyFeature();
  
  /**
   * Return whether the unmatched presence is solely due to a different ordering.
   * If true, then [getFeature() == null || getFeature().isMany()] and
   * getSymmetrical() returns the opposite ordering difference.
   */
  boolean isOrder();
  
  /**
   * Return whether the given value presence corresponds to this one in the opposite role.
   * True may only be returned if the setting (element and feature) is the same.
   * If the feature is of upper bound 1 or isOrder(), then true is returned when the
   * given value presence describes a different value in the same setting.
   * If the feature is many and !isOrder(), then false is always returned.
   * @param peer_p a non-null value presence
   */
  boolean isSymmetricalTo(IValuePresence<E, A, R> peer_p);
  
}
