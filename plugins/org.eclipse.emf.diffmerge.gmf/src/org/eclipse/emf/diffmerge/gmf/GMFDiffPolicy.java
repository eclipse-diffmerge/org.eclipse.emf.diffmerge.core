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
package org.eclipse.emf.diffmerge.gmf;

import java.util.List;

import org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;


/**
 * A diff policy for GMF models.
 * @author Olivier Constant
 */
public class GMFDiffPolicy extends ConfigurableDiffPolicy {
  
  /** The very special View::children reference (could theoretically be null) */
  protected static final EStructuralFeature VIEW_CHILDREN =
      NotationPackage.eINSTANCE.getView().getEStructuralFeature("children"); //$NON-NLS-1$
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy#considerEqual(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean considerEqual(Object value1_p, Object value2_p, Object attribute_p) {
    boolean result = super.considerEqual(value1_p, value2_p, attribute_p);
    if (!result) {
      if (NotationPackage.eINSTANCE.getRelativeBendpoints_Points().equals(attribute_p))
        result = equalPoints((List<?>)value1_p, (List<?>)value2_p);
      else if (value1_p instanceof RelativeBendpoint && value2_p instanceof RelativeBendpoint)
        result = equalRelativeBendpoint(
            (RelativeBendpoint)value1_p, (RelativeBendpoint)value2_p);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableDiffPolicy#doConsiderOrdered(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  protected boolean doConsiderOrdered(EStructuralFeature feature_p) {
    return super.doConsiderOrdered(feature_p) && feature_p != VIEW_CHILDREN;
  }
  
  /**
   * Return whether the given GMF RelativeBendpoints must be considered equal.
   * RelativeBendpoint is a simple data type class containing a few primitively
   * typed attributes. It does not redefine the Object::equals method.
   * @param point1_p a non-null RelativeBendpoint
   * @param point2_p a non-null RelativeBendpoint
   */
  protected boolean equalRelativeBendpoint(RelativeBendpoint point1_p,
      RelativeBendpoint point2_p) {
    return
      point1_p.getSourceX() == point2_p.getSourceX() &&
      point1_p.getSourceY() == point2_p.getSourceY() &&
      point1_p.getTargetX() == point2_p.getTargetX() &&
      point1_p.getTargetY() == point2_p.getTargetY();
  }
  
  /**
   * Return whether the given lists of GMF RelativeBendpoints must be considered equal.
   * This kind of list is the data type of the RelativeBendpoints.points attribute.
   * @param list1_p a non-null, potentially empty list
   * @param list2_p a non-null, potentially empty list
   */
  protected boolean equalPoints(List<?> list1_p, List<?> list2_p) {
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
