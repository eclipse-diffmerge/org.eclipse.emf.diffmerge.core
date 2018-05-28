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
package org.eclipse.emf.diffmerge.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * Utility class for handling a few low-level concerns in EMF instance models.
 * @author Olivier Constant
 */
public final class ModelImplUtil {
  
  /**
   * Private constructor
   */
  private ModelImplUtil() {
    // Forbids instantiation
  }
  
  /**
   * Return the intrinsic ID of the given element as defined by its ID attribute, or null if none
   * @see EcoreUtil#getID(EObject)
   * @param element_p a potentially null element
   * @return a potentially null String
   */
  public static String getIntrinsicID(EObject element_p) {
    String result = null;
    if (element_p != null)
      result = EcoreUtil.getID(element_p);
    return result;
  }
  
  /**
   * Return the (extrinsic) XML ID of the given element, or null if none
   * @param element_p a potentially null element
   * @return a potentially null String
   */
  public static String getXMLID(EObject element_p) {
    String result = null;
    if (element_p != null && element_p.eResource() instanceof XMLResource)
      result = ((XMLResource)element_p.eResource()).getID(element_p);
    return result;
  }
  
  /**
   * This is a variant of EcoreUtil#resolve(EObject, EObject) which never
   * loads resources.
   * @see EcoreUtil#resolve(EObject, EObject)
   * @param proxy_p a non-null element such that proxy_p.eIsProxy()
   * @param objectContext_p a non-null element via which proxy_p was obtained
   * @return a non-null element which is the resolved proxy if resolution succeeded,
   *         or proxy_p otherwise
   */
  public static EObject resolveIfLoaded(EObject proxy_p, EObject objectContext_p) {
    Resource resourceContext =
      objectContext_p != null ? objectContext_p.eResource() : null;
    ResourceSet rsContext =
      resourceContext != null ? resourceContext.getResourceSet() : null;
    return resolveIfLoaded(proxy_p, rsContext);
  }
  
  /**
   * This is a variant of EcoreUtil#resolve(EObject, ResourceSet) which never
   * loads resources.
   * @see EcoreUtil#resolve(EObject, ResourceSet)
   * @param proxy_p a non-null element such that proxy_p.eIsProxy()
   * @param resourceSet_p a potentially null resource set
   * @return a non-null element which is the resolved proxy if resolution succeeded,
   *         or proxy_p otherwise
   */
  private static EObject resolveIfLoaded(EObject proxy_p, ResourceSet resourceSet_p) {
    URI proxyURI = ((InternalEObject)proxy_p).eProxyURI();
    if (proxyURI != null) {
      try {
        EObject resolvedObject = null;
        if (resourceSet_p != null) {
          resolvedObject = resourceSet_p.getEObject(proxyURI, false); // False only
        } else {
          EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(
              proxyURI.trimFragment().toString());
          if (ePackage != null) {
            Resource resource = ePackage.eResource();
            if (resource != null) {
              resolvedObject = resource.getEObject(proxyURI.fragment().toString());
            }
          }
        }
        if (resolvedObject != null && resolvedObject != proxy_p) {
          return resolveIfLoaded(resolvedObject, resourceSet_p);
        }
      } catch (RuntimeException exception) {
        // Failure to resolve is ignored.
      }
    }
    return proxy_p;
  }
  
  /**
   * Set the intrinsic ID of the given element as defined by its ID Attribute, if applicable
   * and if it does not break ID uniqueness, otherwise do nothing
   * @param element_p a non-null element
   * @param id_p a non-null ID
   * @return whether the ID was actually set
   */
  public static boolean setIntrinsicID(EObject element_p, String id_p) {
    assert element_p != null && id_p != null;
    boolean result = false;
    if (element_p.eClass().getEIDAttribute() != null &&
        (element_p.eResource() == null ||
         element_p.eResource().getEObject(id_p) == null)) {
      EcoreUtil.setID(element_p, id_p);
      result = true;
    }
    return result;
  }
  
  /**
   * Set the (extrinsic) XML ID of the given element if applicable and if it does
   * not break ID uniqueness
   * @param element_p a non-null element
   * @param id_p a potentially null ID
   * @return whether the ID was actually set
   */
  public static boolean setXMLID(EObject element_p, String id_p) {
    assert element_p != null;
    boolean result = false;
    if (id_p != null && element_p.eResource() instanceof XMLResource) {
      XMLResource resource = (XMLResource)element_p.eResource();
      if (resource.getEObject(id_p) == null) {
        resource.setID(element_p, id_p);
        result = true;
      }
    }
    return result;
  }
  
}
