/**
 * <copyright>
 * 
 * Copyright (c) 2010-2016  Thales Global Services S.A.S.
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.util.structures.comparable.ComparableTreeMap;
import org.eclipse.emf.diffmerge.util.structures.comparable.IComparableStructure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;


/**
 * A match policy for GMF elements.
 * @author Olivier Constant
 */
public class GMFMatchPolicy extends ConfigurableMatchPolicy {
  
  /** The set of GMF ViewTypes for which no semantic ID is supported */
  protected static final Collection<String> __NO_SEMANTIC_VIEWTYPES = Arrays.asList(
      "Note", "NoteAttachment"); //$NON-NLS-1$ //$NON-NLS-2$
  
  
  /**
   * Constructor
   */
  public GMFMatchPolicy() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getSemanticID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope, boolean)
   */
  @Override
  protected IComparableStructure<?> getSemanticID(EObject element_p, IModelScope scope_p,
      boolean inScopeOnly_p) {
    // Intended return types: ComparableLinkedList<String>,
    //  ComparableTreeMap<String, ComparableLinkedList<String>>
    IComparableStructure<?> result = null;
    if (element_p instanceof View && !(element_p instanceof Diagram))
      result = getViewSemanticID((View)element_p, scope_p, inScopeOnly_p);
    if (result == null)
      result = super.getSemanticID(element_p, scope_p, inScopeOnly_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getUniqueName(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope, boolean)
   */
  @Override
  protected String getUniqueName(EObject element_p, IModelScope scope_p, boolean inScopeOnly_p) {
    String result;
    if (element_p instanceof Diagram)
      result = ((Diagram)element_p).getName();
    else
      result = super.getUniqueName(element_p, scope_p, inScopeOnly_p);
    return result;
  }
  
  /**
   * Return a semantic ID for the given View
   * @param view_p a non-null view
   * @param scope_p a non-null scope that covers element_p
   * @param inScopeOnly_p whether only the scope may be considered, or the underlying EMF model
   * @return a potentially null 
   */
  protected IComparableStructure<?> getViewSemanticID(View view_p,
      IModelScope scope_p, boolean inScopeOnly_p) {
    // The semantic ID is defined from the diagram and the represented element,
    // the assumption being that an element cannot be represented more than once
    // in the same diagram.
    ComparableTreeMap<String, IComparableStructure<?>> result = null;
    if (scope_p instanceof IFeaturedModelScope) {
      IFeaturedModelScope scope = (IFeaturedModelScope)scope_p;
      Diagram diagram = view_p.getDiagram();
      String viewType = view_p.getType();
      if (diagram != null && viewType != null && !__NO_SEMANTIC_VIEWTYPES.contains(viewType)) {
        List<EObject> values = scope.get(view_p, NotationPackage.eINSTANCE.getView_Element());
        IComparableStructure<String> typeID = getEncapsulateOrNull(view_p.eClass().getName());
        if (values.size() == 1) {
          // Represented element is present
          EObject represented = values.get(0);
          IComparableStructure<?> diagramID = getMatchID(diagram, scope_p);
          if (diagramID != null) {
            IComparableStructure<?> representedID = getMatchID(represented, scope_p);
            if (representedID != null) {
              result = new ComparableTreeMap<String, IComparableStructure<?>>();
              result.put("SEMANTIC_ID_TYPE", typeID); //$NON-NLS-1$
              result.put("SEMANTIC_ID_DIAGRAM", diagramID); //$NON-NLS-1$
              result.put("SEMANTIC_ID_ELEMENT", representedID); //$NON-NLS-1$
              result.put("SEMANTIC_ID_VIEWTYPE", getEncapsulateOrNull(viewType)); //$NON-NLS-1$
            }
          }
        } else {
          // Represented element is absent
          EObject container = getContainer(view_p, scope_p, inScopeOnly_p);
          if (container != null) {
            IComparableStructure<?> containerID = getMatchID(container, scope_p);
            if (containerID != null) {
              result = new ComparableTreeMap<String, IComparableStructure<?>>();
              result.put("SEMANTIC_ID_TYPE", typeID); //$NON-NLS-1$
              result.put("SEMANTIC_ID_CONTAINER", containerID); //$NON-NLS-1$
              result.put("SEMANTIC_ID_VIEWTYPE", getEncapsulateOrNull(viewType)); //$NON-NLS-1$
            }
          }
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#isDiscriminatingContainment(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isDiscriminatingContainment(EObject element_p, EReference containment_p) {
    return super.isDiscriminatingContainment(element_p, containment_p) ||
        containment_p == NotationPackage.eINSTANCE.getView_Styles();
  }
  
}
