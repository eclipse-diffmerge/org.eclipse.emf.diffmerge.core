/**
 * <copyright>
 * 
 * Copyright (c) 2006-2017  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.sirius;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy;
import org.eclipse.emf.diffmerge.util.structures.comparable.ComparableTreeMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;


/**
 * A match policy for Sirius elements.
 */
public class SiriusMatchPolicy extends GMFMatchPolicy {
  
  /**
   * Return a semantic ID for the given annotation entry
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getAnnotationEntrySemanticID(AnnotationEntry element_p, IModelScope scope_p) {
    String result = null;
    if (getContainer(element_p, scope_p) instanceof DDiagram &&
        element_p.getSource() != null) {
      // AnnotationEntry in a DDiagram
      result = getContainerRelativeID(
          element_p, scope_p, "ANNOTATION:" + element_p.getSource(), null); //$NON-NLS-1$
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#getAvailableFineGrainedCriteria()
   */
  @Override
  public List<FineGrainedMatchCriterion> getAvailableFineGrainedCriteria() {
    List<FineGrainedMatchCriterion> result = super.getAvailableFineGrainedCriteria();
    result.add(0, CRITERION_SEMANTICS_DEFAULTCONTENTS);
    return result;
  }
  
  /**
   * Return a semantic ID for the given diagram element
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getDDiagramElementSemanticID(DDiagramElement element_p,
      IModelScope scope_p) {
    String result = null;
    if (useFineGrainedMatchCriterion(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT)) {
      // The semantic ID is defined from the diagram and the represented element,
      // the assumption being that an element cannot be represented more than once
      // in the same diagram.
      DDiagram diagram = element_p.getParentDiagram();
      EObject represented = element_p.getTarget();
      if (diagram != null && represented != null) {
        String typeID = element_p.eClass().getName();
        String diagramID = getMatchID(diagram, scope_p);
        if (diagramID != null) {
          String representedID = getMatchID(represented, scope_p);
          if (representedID != null) {
            Map<String, String> map = new ComparableTreeMap<String, String>();
            map.put(SEMANTIC_ID_TYPE_PROPERTY, typeID);
            map.put(SEMANTIC_ID_DIAGRAM_PROPERTY, diagramID);
            map.put(SEMANTIC_ID_ELEMENT_PROPERTY, representedID);
            result = map.toString();
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return a semantic ID for the given DView
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getDViewSemanticID(
      DView element_p, IModelScope scope_p) {
    String result = null;
    if (useFineGrainedMatchCriterion(CRITERION_SEMANTICS_DEFAULTCONTENTS)) {
      Viewpoint vp = element_p.getViewpoint();
      String vpName = (vp == null)? null: vp.getName();
      if (vpName != null)
        result = getContainerRelativeID(element_p, scope_p, vpName, null);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#getName(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getName(EObject element_p, IModelScope scope_p) {
    String result = null;
    if (element_p instanceof DView) {
      Viewpoint viewpoint = ((DView) element_p).getViewpoint();
      if (viewpoint != null)
        result = viewpoint.getName();
    } else if (element_p instanceof DRepresentationDescriptor) {
      result = ((DRepresentationDescriptor) element_p).getName();
    } else if (element_p instanceof DRepresentation) {
      result = ((DRepresentation) element_p).getName();
    } else if (element_p instanceof AnnotationEntry) {
      AnnotationEntry annotation = (AnnotationEntry)element_p;
      if (getContainer(element_p, scope_p) instanceof DDiagram &&
          annotation.getSource() != null) {
        // AnnotationEntry in a DDiagram
        result = "ANNOTATION_" + annotation.getSource(); //$NON-NLS-1$
      }
    }
    if (result == null)
      result = super.getName(element_p, scope_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#getSemanticID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getSemanticID(EObject element_p, IModelScope scope_p) {
    String result = null;
    if (element_p instanceof DView) {
      result = getDViewSemanticID((DView)element_p, scope_p);
    } else if (element_p instanceof DDiagramElement) {
      result = getDDiagramElementSemanticID((DDiagramElement)element_p, scope_p);
    } else if (element_p instanceof AnnotationEntry) {
      result = getAnnotationEntrySemanticID((AnnotationEntry)element_p, scope_p);
    }
    if (result == null)
      result = super.getSemanticID(element_p, scope_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#isDiscriminatingContainment(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isDiscriminatingContainment(EObject element_p, EReference containment_p) {
    return super.isDiscriminatingContainment(element_p, containment_p) ||
        containment_p == DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters();
  }
  
}
