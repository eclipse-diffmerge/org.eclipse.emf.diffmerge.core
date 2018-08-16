/**
 * <copyright>
 * 
 * Copyright (c) 2018 Thales Global Services S.A.S.
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

import java.util.Collection;

import org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.util.IPropertyChangeListener;


/**
 * An object that carries user properties.
 * @see UserProperty
 * @author Olivier Constant
 */
public interface IUserPropertyOwner extends IDisposable {
  
  /**
   * Add the given property change listener to the user property of the given ID
   * @param id_p a non-null user property ID
   * @param listener_p a non-null listener
   * @return whether the operation succeeded, i.e., the user property was found
   */
  boolean addUserPropertyChangeListener(Identifier<?> id_p, IPropertyChangeListener listener_p);
  
  /**
   * Add a user property of the given identifier with the given initial value
   * @param id_p a non-null user property identifier
   * @return whether the operation succeeded, i.e., no user property with the given
   *          ID was already present
   */
  <T> boolean addUserProperty(Identifier<T> id_p, T initialValue_p);
  
  /**
   * Return the user properties carried by this object
   * @return a non-null, potentially empty, unmodifiable collection
   */
  Collection<Identifier<?>> getUserProperties();
  
  /**
   * Return the value of the user property of the given ID, if any
   * @param <T> the type of the user property
   * @param id_p a non-null user property identifier
   * @return a potentially null object
   */
  <T> T getUserPropertyValue(Identifier<T> id_p);
  
  /**
   * Return whether a user property of the given identifier is present
   * @param id_p a non-null user property identifier
   */
  boolean hasUserProperty(Identifier<?> id_p);
  
  /**
   * Return whether the boolean user property of the given ID is present
   * and has value FALSE
   * @param id_p a non-null user property identifier
   */
  boolean isUserPropertyFalse(Identifier<Boolean> id_p);
  
  /**
   * Return whether the boolean user property of the given ID is present
   * and has value TRUE
   * @param id_p a non-null user property identifier
   */
  boolean isUserPropertyTrue(Identifier<Boolean> id_p);
  
  /**
   * Remove the user property of the given identifier if present
   * @param id_p a non-null user property identifier
   * @return whether the operation succeeded, i.e., a user property with the given
   *          ID was present
   */
  boolean removeUserProperty(Identifier<?> id_p);
  
  /**
   * Remove the given property change listener from the user property of the given ID
   * @param id_p a non-null user property ID
   * @param listener_p a non-null listener
   * @return whether the operation succeeded, i.e., the user property was found
   */
  boolean removeUserPropertyChangeListener(Identifier<?> id_p, IPropertyChangeListener listener_p);
  
  /**
   * Remove the given property change listener from all user properties
   * @param listener_p a non-null listener
   */
  void removeUserPropertyChangeListener(IPropertyChangeListener listener_p);
  
  /**
   * Set the value of the user property of the given ID, if any
   * @param <T> the type of the user property
   * @param id_p a non-null user property identifier
   * @param newValue_p a non-null object
   * @return whether the operation succeeded, i.e., the property was found
   */
  <T> boolean setUserPropertyValue(Identifier<T> id_p, T newValue_p);
  
  /**
   * Set the value of the boolean user property of the given ID, if any
   * @param id_p a non-null user property identifier
   * @param newValue_p a non-null object
   * @return whether the operation succeeded, i.e., the property was found
   */
  boolean setUserPropertyValue(Identifier<Boolean> id_p, boolean newValue_p);
  
}
