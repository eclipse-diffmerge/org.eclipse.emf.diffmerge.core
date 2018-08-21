/**
 * <copyright>
 * 
 * Copyright (c) 2006-2018 Thales Global Services S.A.S.
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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy;
import org.eclipse.emf.diffmerge.structures.common.comparable.ComparableTreeMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;


/**
 * A match policy for Sirius elements.
 */
public class SiriusMatchPolicy extends GMFMatchPolicy {
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_SOURCE_PROPERTY = "SEMANTIC_ID_SOURCE_PROPERTY"; //$NON-NLS-1$
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_TARGET_PROPERTY = "SEMANTIC_ID_TARGET_PROPERTY"; //$NON-NLS-1$
  
  /** A representation of the corresponding semantic property */
  protected static final String SEMANTIC_ID_INDEX_PROPERTY = "SEMANTIC_ID_INDEX_PROPERTY"; //$NON-NLS-1$
  
  /** The set of GMF ViewTypes for which no semantic ID is supported. With Sirius, GMF Notes can have sub items. */
  protected static final Collection<String> NON_SEMANTIC_SIRIUS_VIEWTYPES = Arrays.asList(
      "Description", "DiagramName"); //$NON-NLS-1$ //$NON-NLS-2$
  
  /** The set of description packages which can be used in Sirius resources */
  protected static final Collection<? extends EPackage> SIRIUS_DESCRIPTION_PACKAGES =
    Arrays.asList(
        org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE,
        org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE,
        org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE,
        org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
    );
  
  
  /**
   * Default constructor
   */
  public SiriusMatchPolicy() {
    super();
  }
  
  /**
   * Constructor
   * @param policy_p a non-null policy whose configuration to clone
   */
  public SiriusMatchPolicy(SiriusMatchPolicy policy_p) {
    this();
    update(policy_p);
  }
  
  /**
   * @see java.lang.Object#clone()
   */
  @Override
  public SiriusMatchPolicy clone() throws CloneNotSupportedException {
    // Override in subclasses if the configurable state is extended or modified
    return new SiriusMatchPolicy(this);
  }
  
  /**
   * Return a semantic ID for the given annotation entry
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getAnnotationEntrySemanticID(AnnotationEntry element_p, IModelScope scope_p) {
    String result = null;
    if (element_p.getSource() != null) {
      result = getContainerRelativeID(element_p, scope_p, element_p.getSource(), null);
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
   * Return a semantic ID for the given annotation
   * @param annotation_p a non-null element
   * @param scope_p a non-null scope that covers annotation_p
   * @return a potentially null string
   */
  protected String getDAnnotationSemanticID(DAnnotation annotation_p, IModelScope scope_p) {
    String result = null;
    if (annotation_p.getSource() != null) {
      // Based on container ID and source
      result = getContainerRelativeID(annotation_p, scope_p, annotation_p.getSource(), null);
    }
    return result;
  }
  
  /**
   * Return a semantic ID for the given DView
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getDViewSemanticID(DView element_p, IModelScope scope_p) {
    String result = null;
    if (useFineGrainedCriterion(CRITERION_SEMANTICS_DEFAULTCONTENTS)) {
      Viewpoint vp = element_p.getViewpoint();
      String vpName = (vp == null) ? null : vp.getName();
      if (vpName != null) {
        result = getContainerRelativeID(element_p, scope_p, vpName, null);
      }
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
      AnnotationEntry annotation = (AnnotationEntry) element_p;
      if (annotation.getSource() != null) {
        result = "ANNOTATION_" + annotation.getSource(); //$NON-NLS-1$
      }
    }
    if (result == null)
      result = super.getName(element_p, scope_p);
    return result;
  }
  
  /**
   * Return a unique index that discriminates the given note attachment within the given container
   * @param noteAttachment_p a non-null element
   * @param container_p a non-null element
   * @param scope_p a non-null scope that covers noteAttachment_p
   * @return a null or positive int, or -1 in case of failure
   */
  protected int getNoteAttachmentIndex(Connector noteAttachment_p, EObject container_p,
      IModelScope scope_p) {
    int noteIndex = -1;
    String type = noteAttachment_p.getType();
    for (EObject child : getContents(container_p, scope_p)) {
      if (child instanceof Connector) {
        Connector connector = (Connector)child;
        if ((connector.getType() == type)) {
          if (connector.getSource() == noteAttachment_p.getSource() &&
              connector.getTarget() == noteAttachment_p.getTarget()) {
            noteIndex++;
            if (child == noteAttachment_p) {
              break;
            }
          }
        }
      }
    }
    return noteIndex;
  }
  
  /**
   * Return a semantic ID for the given NoteAttachment
   * @param noteAttachment_p a non-null element
   * @param scope_p a non-null scope that covers noteAttachment_p
   * @return a potentially null string
   */
  protected String getNoteAttachmentSemanticID(Connector noteAttachment_p, IModelScope scope_p) {
    Map<String, String> map = new ComparableTreeMap<String, String>();
    EObject container = getContainer(noteAttachment_p, scope_p);
    if (container != null) {
      String containerID = getMatchID(container, scope_p);
      if (containerID != null) {
        int noteIndex = getNoteAttachmentIndex(noteAttachment_p, container, scope_p);
        if (noteIndex != -1) {
          map.put(SEMANTIC_ID_INDEX_PROPERTY, Integer.toString(noteIndex));
        }
      }
    }
    if (noteAttachment_p.getSource() != null) {
      map.put(SEMANTIC_ID_SOURCE_PROPERTY, getSemanticID(noteAttachment_p.getSource(), scope_p));
    }
    if (noteAttachment_p.getTarget() != null) {
      map.put(SEMANTIC_ID_TARGET_PROPERTY, getSemanticID(noteAttachment_p.getTarget(), scope_p));
    }
    return map.toString();
  }
  
  /**
   * Return a semantic ID for the given note element
   * @param noteElement_p a non-null element such that isNoteElement(note_p)
   * @param scope_p a non-null scope that covers noteAttachment_p
   * @return a potentially null string
   */
  protected String getNoteElementSemanticID(Node noteElement_p, IModelScope scope_p) {
    String result = null;
    // Based on container ID and index in the containing list
    String viewType = noteElement_p.getType();
    if (viewType != null) {
      result = getContainerRelativeID(noteElement_p, scope_p, viewType, null);
    }
    return result;
  }
  
  /**
   * Return a unique index that discriminates the given note within the given container
   * @param note_p a non-null element
   * @param container_p a non-null element
   * @param scope_p a non-null scope that contains note_p
   * @return a positive or null int, or -1 in case of failure
   */
  protected int getNoteIndex(View note_p, EObject container_p, IModelScope scope_p) {
    int noteIndex = -1;
    String type = note_p.getType();
    for (EObject child : getContents(container_p, scope_p)) {
      if (child instanceof View && type.equals(((View)child).getType())) {
        noteIndex++;
        if (child == note_p) {
          break;
        }
      }
    }
    return noteIndex;
  }
  
  /**
   * Return a semantic ID for the given note
   * @param note_p a non-null element
   * @param scope_p a non-null scope that contains note_p
   * @return a potentially null string
   */
  protected String getNoteSemanticID(Node note_p, IModelScope scope_p) {
    String result = null;
    // Based on container ID, view type and index in the containing list
    EObject container = getContainer(note_p, scope_p);
    if (container != null) {
      String containerID = getMatchID(container, scope_p);
      if (containerID != null) {
        int noteIndex = getNoteIndex(note_p, container, scope_p);
        if (noteIndex != -1) {
          Map<String, String> map = new ComparableTreeMap<String, String>();
          map.put(SEMANTIC_ID_CONTAINER_PROPERTY, containerID);
          map.put(SEMANTIC_ID_TYPE_PROPERTY, note_p.getType());
          map.put(SEMANTIC_ID_INDEX_PROPERTY, Integer.toString(noteIndex));
          result = map.toString();
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#getSemanticID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getSemanticID(EObject element_p, IModelScope scope_p) {
    String result = null;
    if (element_p instanceof DView) {
      // DView
      result = getDViewSemanticID((DView)element_p, scope_p);
    } else if (element_p instanceof AnnotationEntry) {
      // AnnotationEntry
      result = getAnnotationEntrySemanticID((AnnotationEntry)element_p, scope_p);
    } else if (element_p instanceof DAnnotation) {
      // DAnnotation
      result = getDAnnotationSemanticID((DAnnotation)element_p, scope_p);
    } else if (isViewpointElement(element_p)) {
      // Viewpoint element
      result = getViewpointElementSemanticID(element_p, scope_p);
    } else if (useFineGrainedCriterion(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE)) {
      // Special cases
      if (element_p instanceof Node && isNote((Node)element_p)) {
        // Note
        result = getNoteSemanticID((Node)element_p, scope_p);
      } else if (element_p instanceof Connector && isNoteAttachment((Connector)element_p)) {
        // Note attachment
        result = getNoteAttachmentSemanticID((Connector)element_p, scope_p);
      } else if (element_p instanceof Node && isNoteElement((Node)element_p)) {
        // Note element
        result = getNoteElementSemanticID((Node)element_p, scope_p);
      }
    }
    if (result == null) {
      result = super.getSemanticID(element_p, scope_p);
    }
    return result;
  }
  
  /**
   * Return the index of the given element among its siblings
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return an int that is not expected to be negative
   */
  protected int getSiblingIndex(EObject element_p, IModelScope scope_p) {
    List<EObject> elements = getSiblings(element_p, scope_p);
    return elements.indexOf(element_p);
  }
  
  /**
   * Return a semantic ID for the given element assuming it is a viewpoint element
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null string
   */
  protected String getViewpointElementSemanticID(EObject element_p, IModelScope scope_p) {
    String result = null;
    EObject container = getContainer(element_p, scope_p);
    if (container != null) {
      String containerID = getMatchID(container, scope_p);
      if (containerID != null) {
        int noteIndex = getSiblingIndex(element_p, scope_p);
        if (noteIndex != -1) {
          Map<String, String> map = new ComparableTreeMap<String, String>();
          map.put(SEMANTIC_ID_CONTAINER_PROPERTY, containerID);
          map.put(SEMANTIC_ID_CONTAINING_FEATURE_PROPERTY, element_p.eContainingFeature().getName());
          map.put(SEMANTIC_ID_INDEX_PROPERTY, Integer.toString(noteIndex));
          result = map.toString();
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#hasNoSemantics(org.eclipse.gmf.runtime.notation.View)
   */
  @Override
  protected boolean hasNoSemantics(View view_p) {
    // Sirius Notes can contain additional elements. 
    // Even if they can refer to a semantic element, they must not be computed with getViewSemanticID.
    return NON_SEMANTIC_VIEWTYPES.contains(view_p.getType()) ||
        NON_SEMANTIC_SIRIUS_VIEWTYPES.contains(view_p.getType());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy#isDiscriminatingContainment(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isDiscriminatingContainment(EObject element_p, EReference containment_p) {
    return super.isDiscriminatingContainment(element_p, containment_p) ||
        containment_p == DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters();
  }
  
  /**
   * Return whether the given node is a sub element of a Note
   * @param node_p a non-null node
   */
  protected boolean isNoteElement(Node node_p) {
    return NON_SEMANTIC_SIRIUS_VIEWTYPES.contains(node_p.getType());
  }
  
  /**
   * Return whether the given element belongs to a viewpoint
   * @param element_p a non-null element
   */
  protected boolean isViewpointElement(EObject element_p) {
    boolean result = false;
    EClass clazz = element_p.eClass();
    if (clazz != null) {
      EPackage pkg = clazz.getEPackage();
      result = SIRIUS_DESCRIPTION_PACKAGES.contains(pkg);
    }
    return result;
  }
  
}
