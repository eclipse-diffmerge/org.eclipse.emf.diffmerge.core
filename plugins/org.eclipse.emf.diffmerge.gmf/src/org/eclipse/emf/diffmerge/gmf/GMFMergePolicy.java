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
package org.eclipse.emf.diffmerge.gmf;

import java.util.Set;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;


/**
 * A merge policy for GMF elements.
 * @author Olivier Constant
 */
public class GMFMergePolicy extends DefaultMergePolicy {
  
  /** Whether graphical elements must be merged when semantic elements are merged */
  private boolean _graphicalFromSemantic;
  
  
  /**
   * Constructor
   */
  public GMFMergePolicy() {
    _graphicalFromSemantic = false;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#isSingleMandatory(org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isSingleMandatory(EReference reference_p) {
    return super.isSingleMandatory(reference_p) ||
        reference_p == NotationPackage.eINSTANCE.getView_Element();
  }
  
  /**
   * Extend the given addition group for the given element within the given scope
   * based on GMF peculiarities
   * @param group_p a non-null, modifiable collection
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   */
  protected void extendGMFAdditionGroup(Set<EObject> group_p, EObject element_p,
      IFeaturedModelScope scope_p) {
    // Node -> Node content
    if (element_p instanceof Node) {
      Node elementNode = (Node)element_p;
      EObject representedElement = elementNode.getElement();
      for (EObject child : element_p.eContents()) {
        boolean addChild = true;
        if (child instanceof View) {
          View childView = (View)child;
          EObject childRepresentedElement = childView.getElement();
          addChild = childRepresentedElement == null || childRepresentedElement == representedElement;
        }
        if (addChild)
          group_p.add(child);
      }
    }
    // Edge -> Edge content
    if (element_p instanceof Edge)
      group_p.addAll(element_p.eContents());
    // Semantic element -> Views
    if (isGraphicalFromSemantic())
      extendGMFAdditionGroupSemanticTarget(group_p, element_p, scope_p);
  }
  
  /**
   * Extend the given addition group for the given element within the given scope
   * so that GMF elements are bound to their semantic elements
   * @param group_p a non-null, modifiable collection
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   */
  protected void extendGMFAdditionGroupSemanticTarget(Set<EObject> group_p, EObject element_p,
      IFeaturedModelScope scope_p) {
    ECrossReferenceAdapter crAdapter = ECrossReferenceAdapter.getCrossReferenceAdapter(element_p);
    if (crAdapter != null) {
      for (EStructuralFeature.Setting setting : crAdapter.getNonNavigableInverseReferences(element_p, false)) {
        if (setting.getEStructuralFeature() == NotationPackage.eINSTANCE.getView_Element())
          group_p.add(setting.getEObject());
      }
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy#getAdditionGroup(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public Set<EObject> getAdditionGroup(EObject element_p, IFeaturedModelScope scope_p) {
    Set<EObject> result = super.getAdditionGroup(element_p, scope_p);
    extendGMFAdditionGroup(result, element_p, scope_p);
    return result;
  }
  
  /**
   * Return whether graphical elements must be merged when semantic elements are merged
   */
  public boolean isGraphicalFromSemantic() {
    return _graphicalFromSemantic;
  }
  
  /**
   * Set whether graphical elements must be merged when semantic elements are merged
   */
  public void setGraphicalFromSemantic(boolean graphicalFromSemantic_p) {
    _graphicalFromSemantic = graphicalFromSemantic_p;
  }
  
}
