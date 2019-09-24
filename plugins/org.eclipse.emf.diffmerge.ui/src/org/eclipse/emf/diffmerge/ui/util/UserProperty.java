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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.compare.IPropertyChangeNotifier;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;


/**
 * An editable, observable, identified property that has a non-null value.
 * Designed to be used in a UI.
 * @param <T> the type of the property
 * @author Olivier Constant
 */
public class UserProperty<T> implements IPropertyChangeNotifier,
IDisposable {
  
  /**
   * An identifier for a property. It associates a name and a type.
   * @param <T> the type of the property
   */
  public static class Identifier<T> {
    /** The non-null name of the property */
    private final String _propertyName;
    /**
     * Default constructor
     */
    public Identifier() {
      this(EcoreUtil.generateUUID());
    }
    /**
     * Constructor
     * @param propertyName_p the unique name of the property
     */
    public Identifier(String propertyName_p) {
      _propertyName = propertyName_p;
    }
    /**
     * Create and return a user property with this ID
     * @param initialValue_p a non-null object
     * @return a non-null user property
     */
    public UserProperty<T> createProperty(T initialValue_p) {
      return new UserProperty<T>(this, initialValue_p);
    }
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj_p) {
      boolean result = false;
      if (obj_p instanceof Identifier<?>) {
        String peerPropertyName = ((Identifier<?>)obj_p).getPropertyName();
        result = getPropertyName().equals(peerPropertyName);
      }
      return result;
    }
    /**
     * Return the unique name of the property
     * @return a non-null string
     */
    public String getPropertyName() {
      return _propertyName;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return _propertyName.hashCode();
    }
    /**
     * Return whether the given event corresponds to a change on a property that has this ID
     * @param event_p a non-null event
     */
    public boolean matches(PropertyChangeEvent event_p) {
      return getPropertyName().equals(event_p.getProperty());
    }
  }
  
  
  /** The non-null unique ID of the property */
  private final Identifier<T> _id;
  
  /** The value of the property */
  private T _value;
  
  /** The non-null set of property change listeners */
  private final Set<IPropertyChangeListener> _changeListeners;
  
  
  /**
   * Constructor
   * @param initialValue_p a non-null object
   */
  protected UserProperty(Identifier<T> id_p, T initialValue_p) {
    assert initialValue_p != null;
    _id = id_p;
    _value = initialValue_p;
    _changeListeners = new HashSet<IPropertyChangeListener>(1);
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#addPropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
    _changeListeners.add(listener_p);
  }
  
  /**
   * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
   */
  public void dispose() {
    _value = null;
    _changeListeners.clear();
  }
  
  /**
   * Notify listeners that the value of the property has changed
   * @param oldValue_p the value of the property prior to the change
   */
  protected void firePropertyChangeEvent(T oldValue_p) {
    PropertyChangeEvent event = new PropertyChangeEvent(
        this, getID().getPropertyName(), oldValue_p, getValue());
    for (IPropertyChangeListener listener : _changeListeners) {
      listener.propertyChange(event);
    }
  }
  
  /**
   * Return the ID of the property
   * @return a non-null ID
   */
  public Identifier<T> getID() {
    return _id;
  }
  
  /**
   * Return the value of the property
   * @return a non-null object
   */
  public T getValue() {
    return _value;
  }
  
  /**
   * @see org.eclipse.compare.IPropertyChangeNotifier#removePropertyChangeListener(org.eclipse.jface.util.IPropertyChangeListener)
   */
  public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
    _changeListeners.remove(listener_p);
  }
  
  /**
   * Set the value of the property
   * @param newValue_p a non-null object
   */
  public void setValue(T newValue_p) {
    assert newValue_p != null;
    T oldValue = getValue();
    if (!oldValue.equals(newValue_p)) {
      _value = newValue_p;
      firePropertyChangeEvent(oldValue);
    }
  }
  
}
