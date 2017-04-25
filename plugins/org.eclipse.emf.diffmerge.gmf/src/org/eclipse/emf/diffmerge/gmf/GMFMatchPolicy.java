/**
 * <copyright>
 * 
 * Copyright (c) 2010-2017  Thales Global Services S.A.S.
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
import java.util.Map;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.util.structures.comparable.ComparableTreeMap;
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
  
  /** A criterion for semantic matching of views by type */
  public static final FineGrainedMatchCriterion CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE =
      new FineGrainedMatchCriterion(MatchCriterionKind.SEMANTICS,
          Messages.GMFMatchPolicy_Criterion_ShapeType,
          Messages.GMFMatchPolicy_Criterion_ShapeType_Tooltip);
  
  /** A criterion for semantic matching of views by elements */
  public static final FineGrainedMatchCriterion CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT =
      new FineGrainedMatchCriterion(MatchCriterionKind.SEMANTICS,
          Messages.GMFMatchPolicy_Criterion_RepresentedElement,
          Messages.GMFMatchPolicy_Criterion_RepresentedElement_Tooltip);
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_VIEWTYPE_PROPERTY = "SEMANTIC_VIEWTYPE"; //$NON-NLS-1$
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_ELEMENT_PROPERTY = "SEMANTIC_ELEMENT"; //$NON-NLS-1$
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_DIAGRAM_PROPERTY = "SEMANTIC_DIAGRAM"; //$NON-NLS-1$
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_TYPE_PROPERTY = "SEMANTIC_TYPE"; //$NON-NLS-1$
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_CONTAINER_PROPERTY = "SEMANTIC_ID_CONTAINER"; //$NON-NLS-1$
  
  /** The set of GMF ViewTypes for which no semantic ID is supported */
  protected static final Collection<String> NON_SEMANTIC_VIEWTYPES = Arrays.asList(
      "Note", "NoteAttachment"); //$NON-NLS-1$ //$NON-NLS-2$
  
  
  /**
   * Default constructor
   */
  public GMFMatchPolicy() {
    super();
  }
  
  /**
   * Constructor
   * @param policy_p a non-null policy whose configuration to clone
   */
  public GMFMatchPolicy(GMFMatchPolicy policy_p) {
    this();
    update(policy_p);
  }
  
  /**
   * @see java.lang.Object#clone()
   */
  @Override
  public GMFMatchPolicy clone() throws CloneNotSupportedException {
    // Override in subclasses if the configurable state is extended or modified
    return new GMFMatchPolicy(this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getAvailableFineGrainedCriteria()
   */
  @Override
  public List<FineGrainedMatchCriterion> getAvailableFineGrainedCriteria() {
    List<FineGrainedMatchCriterion> result = super.getAvailableFineGrainedCriteria();
    result.add(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT);
    result.add(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getSemanticID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getSemanticID(EObject element_p, IModelScope scope_p) {
    String result = null;
    if (element_p instanceof View && !(element_p instanceof Diagram))
      result = getViewSemanticID((View)element_p, scope_p);
    if (result == null)
      result = super.getSemanticID(element_p, scope_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getName(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getName(EObject element_p, IModelScope scope_p) {
    String result;
    if (element_p instanceof Diagram)
      result = ((Diagram)element_p).getName();
    else
      result = super.getName(element_p, scope_p);
    return result;
  }
  
  /**
   * Return a semantic ID for the given View
   * @param view_p a non-null view
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null 
   */
  protected String getViewSemanticID(View view_p, IModelScope scope_p) {
    String result = null;
    if (scope_p instanceof IFeaturedModelScope) {
      IFeaturedModelScope scope = (IFeaturedModelScope)scope_p;
      String viewType = view_p.getType();
      if (viewType != null && !NON_SEMANTIC_VIEWTYPES.contains(viewType)) {
        List<EObject> values = scope.get(view_p, NotationPackage.eINSTANCE.getView_Element());
        if (values.size() == 1) {
          // Represented element is present
          if (useFineGrainedCriterion(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT))
            result = getViewElementBasedSemanticID(
                view_p, scope_p, values.get(0), viewType);
        } else {
          // Represented element is absent
          if (useFineGrainedCriterion(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE))
            result = getViewTypeBasedSemanticID(view_p, scope_p, viewType);
        }
      }
    }
    return result;
  }
  
  /**
   * Return a semantic ID for the given view based on its diagram and its represented
   * element. Warning: this assumes that two views with the same properties in the same
   * diagram cannot represent the same element.
   * @param view_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @param viewType the view type of the view
   * @return a potentially null object
   */
  protected String getViewElementBasedSemanticID(View view_p, IModelScope scope_p,
      EObject represented_p, String viewType) {
    String result = null;
    Diagram diagram = view_p.getDiagram();
    if (diagram != null) {
      String diagramID = getMatchID(diagram, scope_p);
      if (diagramID != null) {
        String representedID = getMatchID(represented_p, scope_p);
        if (representedID != null) {
          Map<String, String> map = new ComparableTreeMap<String, String>();
          map.put(SEMANTIC_ID_TYPE_PROPERTY, view_p.eClass().getName());
          map.put(SEMANTIC_ID_DIAGRAM_PROPERTY, diagramID);
          map.put(SEMANTIC_ID_ELEMENT_PROPERTY, representedID);
          map.put(SEMANTIC_ID_VIEWTYPE_PROPERTY, viewType);
          result = map.toString();
        }
      }
    }
    return result;
  }
  
  /**
   * Return a semantic ID for the given view based on its given properties
   * @param view_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @param viewType the view type of the view
   * @return a potentially null object
   */
  protected String getViewTypeBasedSemanticID(View view_p, IModelScope scope_p,
      String viewType) {
    String result = null;
    EObject container = getContainer(view_p, scope_p);
    if (container != null) {
      String containerID = getMatchID(container, scope_p);
      if (containerID != null) {
        Map<String, String> map = new ComparableTreeMap<String, String>();
        map.put(SEMANTIC_ID_TYPE_PROPERTY, view_p.eClass().getName());
        map.put(SEMANTIC_ID_CONTAINER_PROPERTY, containerID);
        map.put(SEMANTIC_ID_VIEWTYPE_PROPERTY, viewType);
        result = map.toString();
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
