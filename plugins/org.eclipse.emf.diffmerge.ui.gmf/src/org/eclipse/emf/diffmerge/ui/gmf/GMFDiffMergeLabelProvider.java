/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.gmf;

import java.util.Collection;

import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;


/**
 * A custom label provider for comparisons encompassing GMF elements.
 * @author Olivier Constant
 */
public class GMFDiffMergeLabelProvider extends DiffMergeLabelProvider {
  
  /** The instance of this class (singleton pattern) */
  private static GMFDiffMergeLabelProvider __instance = null;
  
  /**
   * Return the instance of this class (singleton pattern)
   * @return a non-null object
   */
  public static GMFDiffMergeLabelProvider getInstance() {
    if (__instance == null)
      __instance = new GMFDiffMergeLabelProvider();
    return __instance;
  }
  
  
  /**
   * Constructor
   */
  public GMFDiffMergeLabelProvider() {
    // Nothing needed
  }
  
  /**
   * Return a user-friendly variant of the given string using upper case
   * characters and spaces
   * @param string_p a non-null string
   * @return a non-null string
   */
  protected String formatTechnicalName(String string_p) {
    StringBuilder builder = new StringBuilder();
    boolean first = true;
    char lastChar = ' ';
    for (char aChar : string_p.toCharArray()) {
      if (first) {
        builder.append(Character.toUpperCase(aChar));
        first = false;
      } else {
        if (Character.isUpperCase(aChar) && Character.isLowerCase(lastChar))
          builder.append(' ');
        builder.append(aChar);
      }
      lastChar = aChar;
    }
    return builder.toString();
  }
  
  /**
   * Return a label for the given element which is solely derived from its
   * containment reference
   * @param element_p a non-null element
   * @return a potentially null string
   */
  protected String getContainmentText(EObject element_p) {
    String result = null;
    EReference containment = element_p.eContainmentFeature();
    if (containment != null && isSignificant(containment.getName()))
      result = formatTechnicalName(containment.getName());
    return result;
  }
  
  /**
   * Return a label for the given object which is further characterized by the given object
   * as its "type".
   * Warning: This operation calls the getText operation on both elements.
   * @param element_p a potentially null element
   * @param type_p a non-null element
   * @return a non-null string
   */
  protected String getExplicitlyTypedElementText(Object element_p, Object type_p) {
    StringBuilder builder = new StringBuilder();
    boolean elementLabelOK = false;
    if (element_p != null) {
      String innerLabel = getText(element_p);
      if (isSignificant(innerLabel)) {
        builder.append(innerLabel);
        elementLabelOK = true;
      }
    }
    if (!elementLabelOK) {
      builder.append('<');
      builder.append(Messages.GMFDiffMergeLabelProvider_Unnamed);
      builder.append('>');
    }
    builder.append("  "); //$NON-NLS-1$
    builder.append('[');
    builder.append(formatTechnicalName(getText(type_p)));
    builder.append(']');
    return builder.toString();
  }
  
  /**
   * Return a label for the given element that represents a layout
   * @param element_p a non-null element
   * @return a non-null string
   */
  protected String getLayoutText(EObject element_p) {
    return Messages.GMFDiffMergeLabelProvider_Layout;
  }
  
  /**
   * Return a label for the given element which is further characterized by the given
   * qualifying elements
   * @param element_p a potentially null element
   * @param qualifiers_p a potentially empty set of qualifying elements
   * @return a non-null string
   */
  protected String getManyQualifiedElementText(EObject element_p, Object... qualifiers_p) {
    StringBuilder builder = new StringBuilder();
    if (element_p != null) {
      builder.append(formatTechnicalName(element_p.eClass().getName()));
      if (qualifiers_p.length > 0)
        builder.append(' ');
    }
    if (qualifiers_p.length > 0) {
      builder.append('[');
      boolean firstIteration = true;
      for (Object qualifier : qualifiers_p) {
        if (firstIteration)
          firstIteration = false;
        else
          builder.append(", "); //$NON-NLS-1$
        String innerLabel = getText(qualifier);
        builder.append(innerLabel);
      }
      builder.append(']');
    }
    return builder.toString();
  }
  
  /**
   * Return a label for the given element which is further characterized by the given
   * qualifying element
   * @param element_p a non-null element
   * @param qualifier_p a non-null element
   * @return a non-null string
   */
  protected String getQualifiedElementText(EObject element_p, Object qualifier_p) {
    return getManyQualifiedElementText(element_p, new Object[] {qualifier_p});
  }
  
  /**
   * Return a label for the given relative bendpoint
   * @param bendpoint_p a non-null relative bendpoint
   * @return a non-null string
   */
  protected String getRelativeBendpointText(RelativeBendpoint bendpoint_p) {
    StringBuilder builder = new StringBuilder();
    builder.append('(');
    builder.append(bendpoint_p.getSourceX());
    builder.append(',');
    builder.append(' ');
    builder.append(bendpoint_p.getSourceY());
    builder.append(')');
    builder.append("->"); //$NON-NLS-1$
    builder.append('(');
    builder.append(bendpoint_p.getTargetX());
    builder.append(',');
    builder.append(' ');
    builder.append(bendpoint_p.getTargetY());
    builder.append(')');
    return builder.toString();
  }
  
  /**
   * Return a label for the given element that represents a graphical style
   * @param element_p a non-null element
   * @return a non-null string
   */
  protected String getStyleText(EObject element_p) {
    return formatTechnicalName(element_p.eClass().getName());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    String result;
    // ****** GMF
    if (element_p instanceof Diagram) {
      Diagram diagram = (Diagram)element_p;
      String diagramName = diagram.getName();
      if (isSignificant(diagramName))
        result = diagram.eClass().getName() + " " + diagramName; //$NON-NLS-1$
      else
        result = getViewText(diagram);
    } else if (element_p instanceof View) {
      result = getViewText((View)element_p);
    } else if (isLayout(element_p)) {
      result = getLayoutText((EObject)element_p);
    } else if (element_p instanceof Style) {
      result = getStyleText((EObject)element_p);
    } else if (element_p instanceof Anchor) {
      result = NotationPackage.eINSTANCE.getAnchor().getName();
    } else if (element_p instanceof RelativeBendpoint) {
      result = getRelativeBendpointText((RelativeBendpoint)element_p);
    } else if (element_p instanceof Collection<?> && !((Collection<?>)element_p).isEmpty() &&
        ((Collection<?>)element_p).iterator().next() instanceof RelativeBendpoint) {
      result = getManyQualifiedElementText(null, ((Collection<?>)element_p).toArray());
    } else if (element_p instanceof String) {
      result = (String)element_p;
    } else {
      result = super.getText(element_p);
    }
    return result;
  }
  
  /**
   * Return a label for the given view
   * @param view_p a non-null view
   * @return a non-null string
   */
  protected String getViewText(View view_p) {
    String result;
    EObject represented = null;
    if (view_p.eIsSet(NotationPackage.eINSTANCE.getView_Element()))
      represented = view_p.getElement();
    result = getExplicitlyTypedElementText(represented, view_p.eClass().getName());
    return result;
  }
  
  /**
   * Return whether the given element represents a layout
   * @param element_p a non-null object
   */
  protected boolean isLayout(Object element_p) {
    boolean result = false;
    if (element_p instanceof EObject) {
    EReference containment = ((EObject)element_p).eContainmentFeature();
    result =
        containment == NotationPackage.eINSTANCE.getNode_LayoutConstraint() ||
        containment == NotationPackage.eINSTANCE.getEdge_Bendpoints();
    }
    return result;
  }
  
  /**
   * Return whether the given string is non-null and non-empty
   * @param string_p a potentially null string
   */
  protected boolean isSignificant(String string_p) {
    return string_p != null && string_p.length() > 0;
  }
  
}
