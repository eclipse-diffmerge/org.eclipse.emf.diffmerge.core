/*********************************************************************
 * Copyright (c) 2006-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
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
  
  /**
   * The set of references whose order should be ignored (semantically
   * unordered references)
   */
  private static final Collection<EReference> SEMANTICALLY_UNORDERED_REFERENCES = Arrays
      .asList(DiagramPackage.eINSTANCE.getDDiagram_ActivatedLayers(),
          DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements(),
          ViewpointPackage.eINSTANCE.getDAnalysis_Models(),
          ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews(),
          ViewpointPackage.eINSTANCE.getDAnalysis_SelectedViews(),
          ViewpointPackage.eINSTANCE.getDRepresentationElement_SemanticElements(),
          DiagramPackage.eINSTANCE.getEdgeTarget_IncomingEdges(),
          DiagramPackage.eINSTANCE.getEdgeTarget_OutgoingEdges());
  
  /** The set of features that can be ignored */
  private static final Collection<EStructuralFeature> UNSIGNIFICANT_FEATURES =
      Arrays.<EStructuralFeature>asList(
          ViewpointPackage.eINSTANCE.getDRepresentation_UiState(),
          DiagramPackage.eINSTANCE.getDDiagram_HiddenElements());
  
  /** The set of features that cannot be ignored even through they are peculiar */
  private static final Collection<EStructuralFeature> SIGNIFICANT_FEATURES =
      Arrays.<EStructuralFeature>asList(
          ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation());
  
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
      result = equalResourceDescriptors(
          (ResourceDescriptor)value1_p, (ResourceDescriptor)value2_p);
    }
    if (!result && DiagramPackage.eINSTANCE.getWorkspaceImage_WorkspacePath() == attribute_p) {
      result = equalResourcePaths(
          (String)value1_p, (String)value2_p);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverFeature(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public boolean coverFeature(EStructuralFeature feature_p) {
    return SIGNIFICANT_FEATURES.contains(feature_p) ||
        !UNSIGNIFICANT_FEATURES.contains(feature_p) && super.coverFeature(feature_p);
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
   * Return whether the given Sirius ResourceDescriptors must be considered equal
   * @param desc1_p a non-null object
   * @param desc2_p a non-null object
   */
  protected boolean equalResourceDescriptors(ResourceDescriptor desc1_p,
      ResourceDescriptor desc2_p) {
    URI uri1 = desc1_p.getResourceURI();
    URI uri2 = desc2_p.getResourceURI();
    return (uri1 == null && uri2 == null) || (uri1 != null && uri1.equals(uri2));
  }
  
  /**
   * Return whether two workspace path are equal. 
   * Only the segments of the path are considered, not the protocol.
   */
  protected boolean equalResourcePaths(String value1_p, String value2_p) {
    boolean result;
    try {
      URI uri1 = URI.createURI(value1_p);
      URI uri2 = URI.createURI(value2_p);
      result = uri1.segmentsList().equals(uri2.segmentsList());
    } catch (Exception e) {
      result = false;
    }
    return result;
  }
  
}
