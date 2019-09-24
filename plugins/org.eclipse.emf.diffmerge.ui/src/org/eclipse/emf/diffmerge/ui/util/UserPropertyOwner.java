/*********************************************************************
 * Copyright (c) 2018-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier;
import org.eclipse.jface.util.IPropertyChangeListener;


/**
 * A straightforward implementation of IUserPropertyOwner.
 * @see IUserPropertyOwner
 * @author Olivier Constant
 */
public class UserPropertyOwner implements IUserPropertyOwner {
  
  /** The user properties carried */
  protected final Map<Identifier<?>, UserProperty<?>> _userProperties;
  
  
  /**
   * Default constructor
   */
  public UserPropertyOwner() {
    _userProperties = new HashMap<Identifier<?>, UserProperty<?>>();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#addUserProperty(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, java.lang.Object)
   */
  public <T> boolean addUserProperty(Identifier<T> id_p, T initialValue_p) {
    boolean result = false;
    if (!hasUserProperty(id_p)) {
      UserProperty<T> prop = id_p.createProperty(initialValue_p);
      _userProperties.put(id_p, prop);
      result = true;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#addUserPropertyChangeListener(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, org.eclipse.jface.util.IPropertyChangeListener)
   */
  public boolean addUserPropertyChangeListener(Identifier<?> id_p,
      IPropertyChangeListener listener_p) {
    boolean result = false;
    UserProperty<?> prop = getUserProperty(id_p);
    if (prop != null) {
      prop.addPropertyChangeListener(listener_p);
      result = true;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
   */
  public void dispose() {
    for (UserProperty<?> prop : _userProperties.values()) {
      prop.dispose();
    }
    _userProperties.clear();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#getUserProperties()
   */
  public Collection<Identifier<?>> getUserProperties() {
    return Collections.unmodifiableCollection(_userProperties.keySet());
  }
  
  /**
   * Return the user property of the given ID
   * @param <T> the type of the user property
   * @param id_p a non-null user property ID
   * @return a potentially null user property
   */
  @SuppressWarnings("unchecked")
  protected <T> UserProperty<T> getUserProperty(Identifier<T> id_p) {
    return (UserProperty<T>)_userProperties.get(id_p); // OK by construction of the map
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#getUserPropertyValue(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public <T> T getUserPropertyValue(Identifier<T> id_p) {
    T result = null;
    UserProperty<T> prop = getUserProperty(id_p);
    if (prop != null) {
      result = prop.getValue();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#hasUserProperty(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean hasUserProperty(Identifier<?> id_p) {
    return getUserProperty(id_p) != null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#isUserPropertyFalse(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean isUserPropertyFalse(Identifier<Boolean> id_p) {
    return hasUserProperty(id_p) && !getUserPropertyValue(id_p).booleanValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#isUserPropertyTrue(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean isUserPropertyTrue(Identifier<Boolean> id_p) {
    return hasUserProperty(id_p) && getUserPropertyValue(id_p).booleanValue();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#removeUserProperty(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier)
   */
  public boolean removeUserProperty(Identifier<?> id_p) {
    Object prop = _userProperties.remove(id_p);
    return prop != null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#removeUserPropertyChangeListener(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, org.eclipse.jface.util.IPropertyChangeListener)
   */
  public boolean removeUserPropertyChangeListener(Identifier<?> id_p,
      IPropertyChangeListener listener_p) {
    boolean result = false;
    UserProperty<?> prop = getUserProperty(id_p);
    if (prop != null) {
      prop.removePropertyChangeListener(listener_p);
      result = true;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#removeUserPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removeUserPropertyChangeListener(IPropertyChangeListener listener_p) {
    for (UserProperty<?> prop : _userProperties.values()) {
      prop.removePropertyChangeListener(listener_p);
    }
  }
  
  /**
   * Set the value of the user property of the given ID, if any
   * @param <T> the type of the user property
   * @param id_p a non-null user property identifier
   * @param newValue_p a non-null object
   * @return whether the operation succeeded
   */
  public <T> boolean setUserPropertyValue(Identifier<T> id_p, T newValue_p) {
    assert newValue_p != null;
    boolean result = false;
    UserProperty<T> property = getUserProperty(id_p);
    if (property != null) {
      property.setValue(newValue_p);
      result = true;
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IUserPropertyOwner#setUserPropertyValue(org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier, boolean)
   */
  public boolean setUserPropertyValue(Identifier<Boolean> id_p, boolean newValue_p) {
    return setUserPropertyValue(id_p, Boolean.valueOf(newValue_p));
  }
  
}
