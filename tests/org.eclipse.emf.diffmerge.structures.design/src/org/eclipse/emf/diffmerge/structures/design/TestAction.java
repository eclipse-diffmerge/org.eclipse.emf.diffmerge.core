/*********************************************************************
 * Copyright (c) 2017-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.design;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.endo.qualified.IRangedQEndorelation;
import org.eclipse.emf.diffmerge.structures.impacts.IImpactRule;
import org.eclipse.emf.diffmerge.structures.impacts.ImpactRule;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph;
import org.eclipse.emf.diffmerge.structures.model.integration.EImpact;
import org.eclipse.emf.diffmerge.structures.model.integration.EImpactExploredSubset;
import org.eclipse.emf.diffmerge.structures.model.integration.GraphQBinaryRelation;
import org.eclipse.emf.ecore.EObject;


/**
 * An action for testing.
 * 
 * @author Olivier COnstant
 */
public class TestAction extends AbstractSiriusAction {
  
  /**
   * Constructor
   */
  public TestAction() {
    // Nothing needed
  }
  
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  @Override
  public void execute(Collection<? extends EObject> selection_p,
      Map<String, Object> parameters_p) {
    EGraph graph = getGraph(selection_p);
    checkImpact(graph);
    IMPACT.exploreNext(2l);
  }
  
  /**
   * Check that the shared impact is correctly initialized
   * @param graph_p a potentially null graph
   */
  protected void checkImpact(final EGraph graph_p) {
    // Declarations
    final Collection<?> origins = getOrigins();
    final Collection<IImpactRule> rules = getRules();
    // Initialization
    if (graph_p != null) {
      if (IMPACT == null) {
        IMPACT = new EImpact(origins, rules, IEqualityTester.BY_EQUALS) {
          @Override
          protected IRangedQEndorelation.Editable<Object, IImpactRule> newExploredSubset() {
            return new EImpactExploredSubset(defaultQualifier(), getEqualityTester()) {
              @Override
              protected IRangedQBinaryRelation.Editable<Object, Object, IImpactRule> newContents(
                  IEqualityTester tester_p) {
                return new GraphQBinaryRelation<Object, Object, IImpactRule>(graph_p, tester_p);
              }
            };
          }
        };
      }
    }
  }
  
  /**
   * Return the set of origin elements
   * @return a non-nul, potentially empty set
   */
  protected Collection<String> getOrigins() {
    return Arrays.asList(
        "1", "3");  //$NON-NLS-1$//$NON-NLS-2$
  }
  
  /**
   * Return the set of rules for this impact
   * @return a non-null, potentially empty set
   */
  protected Collection<IImpactRule> getRules() {
    return Arrays.<IImpactRule>asList(
        new ImpactRule("+3", IEqualityTester.BY_EQUALS) { //$NON-NLS-1$
          /**
           * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
           */
          public Collection<Object> get(Object element_p) {
            int sourceElement = Integer.parseInt(element_p.toString());
            int targetElement = sourceElement + 3;
            return Arrays.<Object>asList(Integer.valueOf(targetElement).toString());
          }
        },
        new ImpactRule("+4", IEqualityTester.BY_EQUALS) { //$NON-NLS-1$
          /**
           * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
           */
          public Collection<Object> get(Object element_p) {
            int sourceElement = Integer.parseInt(element_p.toString());
            int targetElement = sourceElement + 4;
            return Arrays.<Object>asList(Integer.valueOf(targetElement).toString());
          }
        }
    );
  }
  
}
