package org.eclipse.emf.diffmerge.ui.gmf;

import org.eclipse.emf.diffmerge.ui.util.DiffMergeLabelProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;


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
    EReference containment = ((EObject)element_p).eContainmentFeature();
    if (containment != null && isSignificant(containment.getName())) {
      result = formatTechnicalName(containment.getName());
    }
    return result;
  }
  
  /**
   * Return a label for the given element which is further characterized by the given
   * qualifying elements
   * @param element_p a non-null element
   * @param qualifiers_p a potentially empty set of qualifying elements
   * @return a non-null string
   */
  protected String getManyQualifiedElementText(EObject element_p, Object... qualifiers_p) {
    StringBuilder builder = new StringBuilder();
    builder.append(formatTechnicalName(element_p.eClass().getName()));
    if (qualifiers_p.length > 0) {
      builder.append(" ["); //$NON-NLS-1$
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
    } else if (element_p instanceof LayoutConstraint || element_p instanceof Style ||
        element_p instanceof Bendpoints) {
      result = getManyQualifiedElementText((EObject)element_p);
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
    Object[] viewElements = new EObject[0];
    if (view_p.eIsSet(NotationPackage.eINSTANCE.getView_Element()))
      viewElements = new Object[] {view_p.getElement()};
    result = getManyQualifiedElementText(view_p, viewElements);
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
