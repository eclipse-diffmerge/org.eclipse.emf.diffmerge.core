/**
 * <copyright>
 * 
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
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

import org.eclipse.emf.common.util.BasicEMap.Entry;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.structures.common.comparable.ComparableTreeMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
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
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_CLASS_NAME_PROPERTY = "SEMANTIC_CLASS_NAME"; //$NON-NLS-1$
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_CONTAINING_FEATURE_PROPERTY = "SEMANTIC_CONTAINING_FEATURE"; //$NON-NLS-1$
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_ANNOTATION_SOURCE_PROPERTY = "SEMANTIC_ANNOTATION_SOURCE"; //$NON-NLS-1$
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_ENTRY_KEY_PROPERTY = "SEMANTIC_ENTRY_KEY"; //$NON-NLS-1$
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_ENTRY_VALUE_PROPERTY = "SEMANTIC_ENTRY_VALUE"; //$NON-NLS-1$

  /** The set of GMF ViewTypes for which no semantic ID is supported */
  protected static final Collection<String> NON_SEMANTIC_VIEWTYPES = Arrays.asList(
      "Note", "Text", "NoteAttachment"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  
  
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
   * Return a semantic ID for the given annotation
   * @param annotation_p a non-null element
   * @param scope_p a non-null scope that covers annotation_p
   * @return a potentially null string
   */
  protected String getAnnotationSemanticID(EAnnotation annotation_p, IModelScope scope_p) {
    String result = null;
    // Based on container ID and source
    EObject container = getContainer(annotation_p, scope_p);
    if (container != null) {
      String containerID = getMatchID(container, scope_p);
      if (containerID != null) {
        String annotationSource = annotation_p.getSource();
        Map<String, String> map = new ComparableTreeMap<String, String>();
        map.put(SEMANTIC_ID_CONTAINER_PROPERTY, containerID);
        map.put(SEMANTIC_ID_ANNOTATION_SOURCE_PROPERTY, annotationSource);
        result = map.toString();
      }
    }
    return result;
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
   * Return a semantic ID for the given Diagram
   * @param diagram_p a non-null element
   * @param scope_p a non-null scope that covers diagram_p
   * @return a potentially null string
   */
  protected String getDiagramSemanticID(Diagram diagram_p, IModelScope scope_p) {
    // Based on represented element
    EObject representedElement = diagram_p.getElement();
    Map<String, String> map = new ComparableTreeMap<String, String>();
    map.put(SEMANTIC_ID_DIAGRAM_PROPERTY, getMatchID(representedElement, scope_p));
    String result = map.toString();
    return result;
  }
  
  /**
   * Return a semantic ID for the given GMF object
   * @param element_p a non-null element considered as a GMF technical object
   * @param scope_p a non-null scope that covers element_p
   * @param viewType_p a potentially null view type for the element
   * @return a potentially null string
   */
  protected String getGMFObjectSemanticID(EObject element_p, IModelScope scope_p, String viewType_p) {
    String result = null;
    // Based on class name, containing feature, container ID and given view type
    EObject container = getContainer(element_p, scope_p);
    if (container != null) {
      String containerID = getMatchID(container, scope_p);
      if (containerID != null) {
        Map<String, String> map = new ComparableTreeMap<String, String>();
        map.put(SEMANTIC_ID_CLASS_NAME_PROPERTY, element_p.eClass().getName());
        map.put(SEMANTIC_ID_CONTAINING_FEATURE_PROPERTY,
            element_p.eContainingFeature().getName());
        map.put(SEMANTIC_ID_CONTAINER_PROPERTY, containerID);
        if (viewType_p != null) {
          map.put(SEMANTIC_ID_VIEWTYPE_PROPERTY, viewType_p);
        }
        result = map.toString();
      }
    }
    return result;
  }
  
  /**
   * Return a semantic ID for the given map entry
   * @param entry_p a non-null element which is also a BasisEMap.Entry
   * @param scope_p a non-null scope that covers entry_p
   * @return a potentially null string
   */
  protected String getMapEntrySemanticID(EObject entry_p, IModelScope scope_p) {
    String result = null;
    // Based on container ID, key and value
    if (entry_p instanceof Entry) {
      EObject container = getContainer(entry_p, scope_p);
      if (container != null) {
        String containerID = getMatchID(container, scope_p);
        if (containerID != null) {
          Map<String, String> map = new ComparableTreeMap<String, String>();
          map.put(SEMANTIC_ID_CONTAINER_PROPERTY, containerID);
          map.put(SEMANTIC_ID_ENTRY_KEY_PROPERTY, ((Entry<?, ?>) entry_p).getKey().toString());
          map.put(SEMANTIC_ID_ENTRY_VALUE_PROPERTY, ((Entry<?, ?>) entry_p).getValue().toString());
          result = map.toString();
        }
      }
    }
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
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#getSemanticID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getSemanticID(EObject element_p, IModelScope scope_p) {
    String result = null;
    if (element_p instanceof Diagram) {
      // Diagram
      result = getDiagramSemanticID((Diagram)element_p, scope_p);
    } else if (element_p instanceof EAnnotation) {
      // EAnnotation
      result = getAnnotationSemanticID((EAnnotation)element_p, scope_p);
    } else if (element_p instanceof Entry) {
      // BasicEMap.Entry and EObject at the same time
      result = getMapEntrySemanticID(element_p, scope_p);
    } else if (element_p instanceof View) {
      // View and not Diagram
      View view = (View)element_p;
      result = getViewSemanticID(view, scope_p);
    } else if (element_p instanceof Style || element_p instanceof Location ||
        element_p instanceof Bendpoints || element_p instanceof Anchor) {
      // Technical elements
      result = getGMFObjectSemanticID(element_p, scope_p, null);
    }
    if (result == null) {
      result = super.getSemanticID(element_p, scope_p);
    }
    return result;
  }
  
  /**
   * Return the element represented by the given view, if any
   * @param view_p a non-null view
   * @param scope_p a non-null scope that covers view_p
   * @return a potentially null element
   */
  protected EObject getViewElement(View view_p, IModelScope scope_p) {
    EObject result = null;
    final EReference VIEW_TO_ELEMENT = NotationPackage.eINSTANCE.getView_Element();
    if (view_p.eIsSet(VIEW_TO_ELEMENT) && scope_p instanceof IFeaturedModelScope) {
      IFeaturedModelScope scope = (IFeaturedModelScope)scope_p;
      List<EObject> values = scope.get(view_p, VIEW_TO_ELEMENT);
      if (values.size() == 1)
        result = values.get(0);
    } else {
      result = view_p.getElement(); // May have value even if unset
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
   * Return a semantic ID for the given View
   * @param view_p a non-null view
   * @param scope_p a non-null scope that covers view_p
   * @return a potentially null string
   */
  protected String getViewSemanticID(View view_p, IModelScope scope_p) {
    String result = null;
    String viewType = view_p.getType();
    if (viewType != null && !hasNoSemantics(view_p)) {
      EObject representedElement = getViewElement(view_p, scope_p);
      if (representedElement != null) {
        // Represented element is present
        if (useFineGrainedCriterion(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT)) {
          result = getViewElementBasedSemanticID(
              view_p, scope_p, representedElement, viewType);
        }
      } else {
        // Represented element is absent
        if (useFineGrainedCriterion(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE)) {
          result = getViewTypeBasedSemanticID(view_p, scope_p, viewType);
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
   * Return whether the given view has no semantics, i.e., it is purely graphical
   */
  protected boolean hasNoSemantics(View view_p) {
    String viewType = view_p.getType();
    return NON_SEMANTIC_VIEWTYPES.contains(viewType);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy#isDiscriminatingContainment(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isDiscriminatingContainment(EObject element_p, EReference containment_p) {
    return super.isDiscriminatingContainment(element_p, containment_p) ||
        containment_p == NotationPackage.eINSTANCE.getView_Styles();
  }
  
  /**
   * Return whether the given node can be considered as a Note
   * @param node_p a non-null node
   */
  protected boolean isNote(Node node_p) {
    String viewType = node_p.getType();
    return "Note".equals(viewType) || "Text".equals(viewType); //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  /**
   * Return whether the given connector is a NoteAttachment
   * @param connector_p a non-null connector
   */
  protected boolean isNoteAttachment(Connector connector_p) {
    return "NoteAttachment".equals(connector_p.getType()); //$NON-NLS-1$
  }
  
}
