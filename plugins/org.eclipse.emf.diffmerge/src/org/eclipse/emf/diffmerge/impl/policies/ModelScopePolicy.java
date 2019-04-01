/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.generic.api.IScopePolicy;
import org.eclipse.emf.diffmerge.util.ModelImplUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;


/**
 * A scope policy for EMF models.
 * 
 * @author Olivier Constant
 */
public class ModelScopePolicy implements IScopePolicy<EObject> {
  
  /** The singleton object */
  protected static final ModelScopePolicy __instance = new ModelScopePolicy();
  
  
  /**
   * Default constructor
   */
  protected ModelScopePolicy() {
    // Stateless
  }
  
  /**
   * Return the singleton instance
   * @return a non-null object
   */
  public static ModelScopePolicy getInstance() {
    return __instance;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetAttributes(java.lang.Object)
   */
  public List<?> mGetAttributes(EObject element_p) {
    return element_p.eClass().getEAllAttributes();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetOppositeReference(java.lang.Object)
   */
  public Object mGetOppositeReference(Object reference_p) {
    return ((EReference)reference_p).getEOpposite();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetReferences(java.lang.Object)
   */
  public List<?> mGetReferences(EObject element_p) {
    return element_p.eClass().getEAllReferences();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mGetType(java.lang.Object)
   */
  public Object mGetType(EObject element_p) {
    return element_p.eClass();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsContainerReference(java.lang.Object)
   */
  public boolean mIsContainerReference(Object reference_p) {
    return ((EReference)reference_p).isContainer();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsContainmentReference(java.lang.Object)
   */
  public boolean mIsContainmentReference(Object reference_p) {
    return ((EReference)reference_p).isContainment();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsChangeableAttribute(java.lang.Object)
   */
  public boolean mIsChangeableAttribute(Object attribute_p) {
    return ((EAttribute)attribute_p).isChangeable();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsChangeableReference(java.lang.Object)
   */
  public boolean mIsChangeableReference(Object reference_p) {
    return ((EReference)reference_p).isChangeable();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsIDAttribute(java.lang.Object)
   */
  public boolean mIsIDAttribute(Object attribute_p) {
    return ((EAttribute)attribute_p).isID();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsManyAttribute(java.lang.Object)
   */
  public boolean mIsManyAttribute(Object attribute_p) {
    return ((EAttribute)attribute_p).isMany();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsManyReference(java.lang.Object)
   */
  public boolean mIsManyReference(Object reference_p) {
    return ((EReference)reference_p).isMany();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsManyAttribute(java.lang.Object)
   */
  public boolean mIsOptionalAttribute(Object attribute_p) {
    return ((EAttribute)attribute_p).getLowerBound() == 0;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#mIsManyReference(java.lang.Object)
   */
  public boolean mIsOptionalReference(Object reference_p) {
    return ((EReference)reference_p).getLowerBound() == 0;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tGetID(java.lang.Object, boolean)
   */
  public Object tGetID(EObject element_p, boolean intrinsic_p) {
    Object result = null;
    if (intrinsic_p) {
      result = ModelImplUtil.getIntrinsicID(element_p);
    } else {
      result = ModelImplUtil.getXMLID(element_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tIsDisconnectionRequired(java.lang.Object)
   */
  public boolean tIsDisconnectionRequired(Object reference_p) {
    return mIsChangeableReference(reference_p) && !((EReference)reference_p).isDerived();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tIsElementDisconnectionRequired()
   */
  public boolean tIsElementDisconnectionRequired() {
    return true;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tNewBareElement(java.lang.Object)
   */
  public EObject tNewBareElement(Object source_p) {
    EObject result;
    if (source_p instanceof EObject) {
      EObject sourceElement = (EObject)source_p;
      EClass eClass = sourceElement.eClass();
      result = eClass.getEPackage().getEFactoryInstance().create(eClass);
      if (sourceElement.eIsProxy()) {
        URI proxyURI = ((InternalEObject)sourceElement).eProxyURI();
        ((InternalEObject)result).eSetProxyURI(proxyURI);
      }
    } else {
      result = EcoreFactory.eINSTANCE.createEObject();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IScopePolicy#tSetID(java.lang.Object, java.lang.Object, boolean)
   */
  public boolean tSetID(EObject element_p, Object id_p, boolean intrinsic_p) {
    boolean result = false;
    String actualID = (id_p == null)? null: id_p.toString();
    if (intrinsic_p) {
      result = ModelImplUtil.setIntrinsicID(element_p, actualID);
    } else {
      result = ModelImplUtil.setXMLID(element_p, actualID);
    }
    return result;
  }
  
}
