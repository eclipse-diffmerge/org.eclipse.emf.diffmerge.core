/**
 * <copyright>
 * 
 * Copyright (c) 2006-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.sirius;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.ui.gmf.GMFDiffMergeLabelProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.Viewpoint;


/**
 * A custom label provider for comparisons encompassing Sirius elements.
 */
public class SiriusDiffMergeLabelProvider extends GMFDiffMergeLabelProvider {
  
  /** The instance of this class (singleton pattern) */
  private static SiriusDiffMergeLabelProvider __instance = null;
  
  /**
   * Return the instance of this class (singleton pattern)
   * @return a non-null object
   */
  public static SiriusDiffMergeLabelProvider getInstance() {
    if (__instance == null)
      __instance = new SiriusDiffMergeLabelProvider();
    return __instance;
  }
  
  
  /**
   * Constructor
   */
  public SiriusDiffMergeLabelProvider() {
    // Nothing needed
  }
  
  /**
   * Return a label for the given Sirius DAnalysis Custom Data element
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getDAnalysisCustomDataText(DAnalysisCustomData element_p) {
    return Messages.SiriusDiffMergeLabelProvider_DAnalysisCustomDataLabel;
  }
  
  /**
   * Return a label for the given Sirius DAnalysis element
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getDAnalysisText(DAnalysis element_p) {
    String result = null;
    Resource resource = element_p.eResource();
    if (resource != null && resource.getURI() != null)
      result = URI.decode(resource.getURI().lastSegment());
    return result;
  }
  
  /**
   * Return a label for the given Sirius representation element
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getDRepresentationElementText(
      DRepresentationElement element_p) {
    String result = getExplicitlyTypedElementText(
        element_p.getName(), element_p.getMapping());
    return result;
  }
  
  /**
   * Return a label for the given Sirius representation
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getDRepresentationText(DRepresentation element_p) {
    String result = super.getText(element_p);
    result = String.format(
        Messages.SiriusDiffMergeLabelProvider_DRepresentationLabel, result);
    return result;
  }
  
  /**
   * Return a label for the given Sirius view element
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getDViewText(DView element_p) {
    String result = null;
    Viewpoint viewpoint = element_p.getViewpoint();
    if (viewpoint != null) {
      result = viewpoint.getLabel();
      if (result == null)
        result = viewpoint.getName();
    }
    return result;
  }
  
  /**
   * Return a label for the given Sirius node style element
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getNodeStyleText(NodeStyle element_p) {
    return ((EObject)element_p).eClass().getName() + " " + //$NON-NLS-1$
        formatTechnicalName(DiagramPackage.eINSTANCE.getNodeStyle().getName());
  }
  
  /**
   * Return a label for the given RGBValues element
   * @param element_p a non-null RGBValues element
   * @return a potentially null string
   */
  protected String getRGBValuesText(RGBValues element_p) {
    StringBuilder builder = new StringBuilder();
    builder.append('(');
    builder.append(element_p.getRed());
    builder.append(',');
    builder.append(element_p.getGreen());
    builder.append(',');
    builder.append(element_p.getBlue());
    builder.append(')');
    return builder.toString();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    String result = null;
    // ****** Sirius
    if (element_p instanceof DAnalysis) {
      result = getDAnalysisText((DAnalysis)element_p);
    } else if (element_p instanceof DView) {
      result = getDViewText((DView)element_p);
    } else if (element_p instanceof DRepresentation) {
      result = getDRepresentationText((DRepresentation)element_p);
    } else if (element_p instanceof DRepresentationElement) {
      result = getDRepresentationElementText((DRepresentationElement)element_p);
    } else if (element_p instanceof NodeStyle) {
      result = getNodeStyleText((NodeStyle)element_p);
    } else if (element_p instanceof ContainerStyle || element_p instanceof EdgeStyle ||
        element_p instanceof BasicLabelStyle) {
      result = getManyQualifiedElementText((EObject)element_p);
    } else if (element_p instanceof DAnalysisCustomData) {
      result = getDAnalysisCustomDataText((DAnalysisCustomData)element_p);
    } else if (element_p instanceof RGBValues) {
      result = getRGBValuesText((RGBValues)element_p);
    }
    if (result == null)
      result = super.getText(element_p);
    return result;
  }
  
}
