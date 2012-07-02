/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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

import java.util.List;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.swt.graphics.Color;


/**
 * A diff policy for GMF models.
 * @author Olivier Constant
 */
public class GMFDiffPolicy extends DefaultDiffPolicy {
  
  /**
   * @see com.thalesgroup.mde.shared.compare.policies.DefaultDiffPolicy#considerEqual(Object, Object, EAttribute)
   */
  @Override
  public boolean considerEqual(Object value1_p, Object value2_p, EAttribute attribute_p) {
    boolean result = super.considerEqual(value1_p, value2_p, attribute_p);
    if (!result) {
      if (NotationPackage.eINSTANCE.getRelativeBendpoints_Points().equals(attribute_p))
        result = equalPoints((List<?>)value1_p, (List<?>)value2_p);
      else if (value1_p instanceof RelativeBendpoint && value2_p instanceof RelativeBendpoint)
        result = equalRelativeBendpoint(
            (RelativeBendpoint)value1_p, (RelativeBendpoint)value2_p);
      else if (value1_p instanceof Color && value2_p instanceof Color)
        result = equalColor((Color)value1_p, (Color)value2_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#coverMatch(org.eclipse.emf.diffmerge.api.IMatch)
   */
  @Override
  public boolean coverMatch(IMatch match_p) {
    boolean result = super.coverMatch(match_p);
    if (result && match_p.isPartial()) {
      // Ignore elements owned by a transient containment
      EObject element = match_p.get(match_p.getUncoveredRole().opposite());
      EReference containment = element.eContainmentFeature();
      if (containment != null)
        result = !containment.isTransient();
    }
    return result;
  }
  
  /**
   * Defines a custom equality relation on SWT Color.
   */
  private boolean equalColor(Color color1_p, Color color2_p) {
    return
      color1_p.getBlue() == color2_p.getBlue() &&
      color1_p.getRed() == color2_p.getRed() &&
      color1_p.getGreen() == color2_p.getGreen();
  }
  
  /**
   * Defines a custom equality relation on GMF RelativeBendpoint.
   * RelativeBendpoint is a simple datatype-oriented class containing a few
   * primitively typed attributes. It does not redefine the Object::equals method.
   */
  private boolean equalRelativeBendpoint(RelativeBendpoint point1_p,
      RelativeBendpoint point2_p) {
    return
      point1_p.getSourceX() == point2_p.getSourceX() &&
      point1_p.getSourceY() == point2_p.getSourceY() &&
      point1_p.getTargetX() == point2_p.getTargetX() &&
      point1_p.getTargetY() == point2_p.getTargetY();
  }
  
  /**
   * Defines a custom equality relation on lists of RelativeBendpoints.
   * This kind of list is the data-type of the RelativeBendpoints.points attribute.
   */
  private boolean equalPoints(List<?> list1_p, List<?> list2_p) {
    if (list1_p.size() != list2_p.size())
      return false;
    for (int i=0; i<list1_p.size(); i++) {
      Object val1 = list1_p.get(i);
      Object val2 = list2_p.get(i);
      if (!equalRelativeBendpoint(
          (RelativeBendpoint)val1, (RelativeBendpoint)val2))
        return false;
    }
    return true;
  }
  
}
