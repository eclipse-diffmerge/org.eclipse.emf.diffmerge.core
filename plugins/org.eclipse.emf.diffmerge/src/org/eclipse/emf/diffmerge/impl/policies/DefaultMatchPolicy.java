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
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.Comparator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IPersistentModelScope;
import org.eclipse.emf.diffmerge.util.ModelImplUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * A default match policy based on unique IDs (intrinsic/extrinsic/URI) of model elements.
 * @author Olivier Constant
 */
public class DefaultMatchPolicy implements IMatchPolicy {
  
  /** Whether match IDs must be maintained for better traceability */
  private boolean _keepMatchIDs;
  
  
  /**
   * Default constructor
   */
  public DefaultMatchPolicy() {
    _keepMatchIDs = false;
  }
  
  /**
   * Return the extrinsic ID of the given element from the given scope, or null if none
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers the element
   * @return a potentially null object
   */
  protected Comparable<?> getExtrinsicID(EObject element_p, IModelScope scope_p) {
    Comparable<?> result = null;
    if (scope_p instanceof IPersistentModelScope) {
      Object extrinsic = ((IPersistentModelScope)scope_p).getExtrinsicID(element_p);
      if (extrinsic instanceof Comparable<?>)
        result = (Comparable<?>)extrinsic;
    }
    if (result == null) // Try XML ID in a last resort
      result = ModelImplUtil.getXMLID(element_p);
    return result;
  }
  
  /**
   * Return the intrinsic ID of the given element as defined by its ID attribute, or null if none
   * @see EcoreUtil#getID(EObject)
   * @param element_p a potentially null element
   * @return a potentially null String
   */
  protected String getIntrinsicID(EObject element_p) {
    return ModelImplUtil.getIntrinsicID(element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMatchPolicy#getMatchID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  public Object getMatchID(EObject element_p, IModelScope scope_p) {
    Comparable<?> result = getIntrinsicID(element_p);
    if (result == null)
      result = getExtrinsicID(element_p, scope_p);
    if (result == null)
      result = getURIBasedMatchID(element_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMatchPolicy#getMatchIDComparator()
   */
  public Comparator<Object> getMatchIDComparator() {
    // Redefine (for example by returning null) if getMatchID
    // may return objects that are not Comparable or that are
    // incompatible sorts of Comparable.
    return NATURAL_ORDER_COMPARATOR;
  }
  
  /**
   * Return a match ID for the given element based on its URI, if any
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getURIBasedMatchID(EObject element_p) {
    String result = null;
    URI uri = EcoreUtil.getURI(element_p);
    if (uri != null) {
      if (uri.isPlatformResource() && uri.fragment() != null)
        result = uri.fragment(); // Ignore name of containing file
      else
        result = uri.toString();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.api.IMatchPolicy#keepMatchIDs()
   */
  public boolean keepMatchIDs() {
    return _keepMatchIDs;
  }
  
  /**
   * Set whether match ID information must be maintained for better traceability
   * but at the price of a larger memory footprint
   * @param keep_p whether to maintain match ID information
   */
  public void setKeepMatchIDs(boolean keep_p) {
    _keepMatchIDs = keep_p;
  }
  
}
