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

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.gmf.GMFDiffPolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;


/**
 * A diff policy for Sirius models.
 */
public class SiriusDiffPolicy extends GMFDiffPolicy {
  
  /** Separator */
  private static final char SEGMENT_SEPARATOR = '/';
  
  /**
   * The set of references whose order should be ignored (semantically
   * unordered references)
   */
  private static final Collection<EReference> SEMANTICALLY_UNORDERED_REFERENCES = Arrays
      .asList(DiagramPackage.eINSTANCE.getDDiagram_ActivatedLayers(),
          DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements(),
          ViewpointPackage.eINSTANCE.getDAnalysis_Models(),
          ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews(),
          ViewpointPackage.eINSTANCE
          .getDRepresentationElement_SemanticElements(),
          DiagramPackage.eINSTANCE.getEdgeTarget_IncomingEdges(),
          DiagramPackage.eINSTANCE.getEdgeTarget_OutgoingEdges());
  
  /** The set of references that can be ignored */
  private static final Collection<EReference> UNSIGNIFICANT_REFERENCES = Arrays
      .asList(ViewpointPackage.eINSTANCE.getDRepresentation_UiState(),
          DiagramPackage.eINSTANCE.getDDiagram_HiddenElements());
  
  /** The set of types that can be ignored */
  private static final Collection<EClass> UNSIGNIFICANT_TYPES = Arrays
      .asList(OrderingPackage.eINSTANCE.getEventEndsOrdering(),
          OrderingPackage.eINSTANCE.getInstanceRolesOrdering(),
          ViewpointPackage.eINSTANCE.getUIState());
  
  /**
   * The set of String attributes for which the empty string value must not be
   * distinguished from the null value
   */
  private static final Collection<EAttribute> IGNORING_EMPTY_STRING_ATTRIBUTES = Arrays
      .asList(DiagramPackage.eINSTANCE.getDDiagramElement_TooltipText());
  
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFDiffPolicy#considerEqual(java.lang.Object, java.lang.Object, org.eclipse.emf.ecore.EAttribute)
   */
  @Override
  public boolean considerEqual(Object value1_p, Object value2_p,
      EAttribute attribute_p) {
    boolean result = super.considerEqual(value1_p, value2_p, attribute_p);
    if (!result && ViewpointPackage.eINSTANCE.getDAnalysis_SemanticResources() == attribute_p) {
      // Get URI(s)
      URI refURI = ((ResourceDescriptor) value1_p).getResourceURI();
      URI trgtURI = ((ResourceDescriptor) value2_p).getResourceURI();
      // Get the position of project name in URI segments
      int positionInRefURI = getProjectPositionInURI(refURI);
      int positionInTrgtURI = getProjectPositionInURI(trgtURI);
      // Either make it relative or convert to string
      String refURIStr = (positionInRefURI != -1)? 
          makeRelativeToProject(refURI, positionInRefURI):
            refURI.toString();
      String trgtURIStr = (positionInTrgtURI != -1)?
          makeRelativeToProject(trgtURI, positionInTrgtURI):
            trgtURI.toString();
      return refURIStr.equals(trgtURIStr);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverFeature(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public boolean coverFeature(EStructuralFeature feature_p) {
    return !UNSIGNIFICANT_REFERENCES.contains(feature_p) &&
        super.coverFeature(feature_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverMatch(org.eclipse.emf.diffmerge.api.IMatch)
   */
  @Override
  public boolean coverMatch(IMatch match_p) {
    boolean result = super.coverMatch(match_p);
    if (result && match_p.isPartial()) {
      // Ignore certain transient elements (OK because no cross-ref)
      EObject element = match_p
          .get(match_p.getUncoveredRole().opposite());
      if (element != null)
        result = !UNSIGNIFICANT_TYPES.contains(element.eClass());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverValue(java.lang.Object, org.eclipse.emf.ecore.EAttribute)
   */
  @Override
  public boolean coverValue(Object value_p, EAttribute attribute_p) {
    boolean result;
    if (IGNORING_EMPTY_STRING_ATTRIBUTES.contains(attribute_p)
        && ((String) value_p).length() == 0)
      result = false;
    else
      result = super.coverValue(value_p, attribute_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy#doConsiderOrdered(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  protected boolean doConsiderOrdered(EStructuralFeature feature_p) {
    return super.doConsiderOrdered(feature_p)
        && !SEMANTICALLY_UNORDERED_REFERENCES.contains(feature_p);
  }
  
  /**
   * 
   * @return The position of the segment which corresponds to the project name.
   */
  protected int getProjectPositionInURI(URI uri_p) {
    if(uri_p.isPlatformResource()){
      return 2;			
    }
    return -1;
  }
  
  /**
   * 
   * @param uri_p The uri to make relative
   * @param projectPositionInURI_p The position of the segment which corresponds to the project
   *        name in the given uri
   * 
   * @return the relative uri as String
   */
  protected String makeRelativeToProject(URI uri_p, int projectPositionInURI_p) {
    StringBuilder result = new StringBuilder();
    for (int i = projectPositionInURI_p; i < uri_p.segments().length; i++) {
      result.append(SEGMENT_SEPARATOR);
      result.append(uri_p.segment(i));
    }
    return result.toString();
  }
  
}
