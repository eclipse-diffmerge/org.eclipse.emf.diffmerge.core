/*********************************************************************
 * Copyright (c) 2022 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.tests.ju;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.diffmerge.sirius.SiriusMatchPolicy;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.junit.Test;

public class SiriusMatchPolicyAnnotationTest {

  @Test
  public void dAnnotation() {
    DView view = ViewpointFactory.eINSTANCE.createDView();
    DRepresentationDescriptor descriptor = createDescriptor(view);
    descriptor.setUid("desc1");
    
    SiriusMatchPolicy sm = new SiriusMatchPolicy();
    sm.setUseCriterion(MatchCriterionKind.SEMANTICS, true);
    RootedModelScope scope = new RootedModelScope(Arrays.asList(view));
    
    DAnnotation annotation1 = createAnnotation("aaaa", descriptor);
    annotation1.setUid("annotation1");
    String id1 = sm.getMatchID(annotation1, scope);
    assertTrue("Annotation index is computed based on the id, not custom id", id1.equals("annotation1"));
  }

  @Test
  public void dAnnotationEntry() {
    DView view = ViewpointFactory.eINSTANCE.createDView();
    DRepresentation descriptor = createRepresentation(view);
    descriptor.setUid("desc1");
    
    SiriusMatchPolicy sm = new SiriusMatchPolicy();
    RootedModelScope scope = new RootedModelScope(Arrays.asList(view));
    
    AnnotationEntry annotation1 = createAnnotationEntry("aaaa", descriptor);
    annotation1.setUid("annotation1");
    annotation1.setSource("GMF_DIAGRAMS");
    
    sm.setUseCriterion(MatchCriterionKind.SEMANTICS, true);
    String id1 = sm.getMatchID(annotation1, scope);
    assertTrue("Annotation index is computed based on the source", id1.endsWith("GMF_DIAGRAMS"));
    
    sm.setUseCriterion(MatchCriterionKind.SEMANTICS, false);
    String id2 = sm.getMatchID(annotation1, scope);
    assertTrue("Annotation index is computed based on the id", id2.equals("annotation1"));
  }
  
  protected DAnnotation createAnnotation(String source, DRepresentationDescriptor container) {
    DAnnotation annotation3 = DescriptionFactory.eINSTANCE.createDAnnotation();
    annotation3.setSource(source);
    container.getEAnnotations().add(annotation3);
    return annotation3;
  }
  
  protected AnnotationEntry createAnnotationEntry(String source, DRepresentation container) {
    AnnotationEntry annotation3 = DescriptionFactory.eINSTANCE.createAnnotationEntry();
    annotation3.setSource(source);
    container.getOwnedAnnotationEntries().add(annotation3);
    return annotation3;
  }

  protected DRepresentationDescriptor createDescriptor(DView view) {
    DRepresentationDescriptor descriptor = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
    view.getOwnedRepresentationDescriptors().add(descriptor);
    return descriptor;
  }

  protected DRepresentation createRepresentation(DView view) {
    DRepresentation descriptor = DiagramFactory.eINSTANCE.createDSemanticDiagram();
    return descriptor;
  }

}
