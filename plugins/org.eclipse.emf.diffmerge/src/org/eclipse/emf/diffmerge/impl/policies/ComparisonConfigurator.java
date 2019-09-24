/*********************************************************************
 * Copyright (c) 2017-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfiguration;
import org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfigurator;
import org.eclipse.emf.diffmerge.generic.impl.policies.AbstractConfigurationElement;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.FineGrainedMatchCriterion;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind;


/**
 * An implementation of IComparisonConfigurator for predefined configurations.
 * @author Olivier Constant
 */
public class ComparisonConfigurator extends AbstractConfigurationElement
implements IComparisonConfigurator {
  
  /** The non-null, potentially empty set of selected criteria */
  protected final Collection<MatchCriterionKind> _selectedCriteria;
  
  /** The non-null, potentially empty set of selected fine-grained criteria */
  protected final Collection<FineGrainedMatchCriterion> _selectedFineGrainedCriteria;
  
  
  /**
   * Constructor
   * @param label_p a non-null label
   * @param description_p a potentially null description
   * @param selectedCriteria_p a non-null, potentially empty set of selected criteria
   * @param selectedFineGrainedCriteria_p a non-null, potentially empty set of selected
   *        fine-grained criteria
   */
  public ComparisonConfigurator(String label_p,
      String description_p, Collection<MatchCriterionKind> selectedCriteria_p,
      Collection<FineGrainedMatchCriterion> selectedFineGrainedCriteria_p) {
    super(label_p, description_p);
    _selectedCriteria = new HashSet<MatchCriterionKind>(selectedCriteria_p);
    _selectedFineGrainedCriteria =
        new HashSet<FineGrainedMatchCriterion>(selectedFineGrainedCriteria_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfigurator#apply(org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfiguration)
   */
  public boolean apply(IComparisonConfiguration<?> configuration_p) {
    boolean result = true;
    IMatchPolicy<?> matchPolicy = configuration_p.getMatchPolicy();
    if (matchPolicy instanceof ConfigurableMatchPolicy) {
      result = applyMatchCriteria((ConfigurableMatchPolicy)matchPolicy);
    }
    return result;
  }
  
  /**
   * Apply the match criteria of this configurator to the given configurable match policy
   * @param policy_p a non-null match policy
   * @return whether the operation fully succeeded
   */
  protected boolean applyMatchCriteria(ConfigurableMatchPolicy policy_p) {
    policy_p.setAllUsedCriteria(_selectedCriteria);
    policy_p.setAllUsedFineGrainedCriteria(_selectedFineGrainedCriteria);
    policy_p.setUseCache(mustUseCache());
    return true;
  }
  
  /**
   * Return whether the match criteria of the given policy comply with those
   * of this configurator
   * @param policy_p a non-null match policy
   */
  protected boolean checkMatchCriteria(ConfigurableMatchPolicy policy_p) {
    for (MatchCriterionKind criterion : MatchCriterionKind.values()) {
      boolean mustBeSelected = _selectedCriteria.contains(criterion);
      if (policy_p.useCriterion(criterion) != mustBeSelected)
        return false;
    }
    for (FineGrainedMatchCriterion criterion : policy_p.getAvailableFineGrainedCriteria()) {
      boolean mustBeSelected = _selectedFineGrainedCriteria.contains(criterion);
      if (_selectedCriteria.contains(criterion.getParentCriterion()) &&
          policy_p.useFineGrainedCriterion(criterion) != mustBeSelected)
        return false;
    }
    return mustUseCache() == policy_p.useCache();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfigurator#isCompliant(org.eclipse.emf.diffmerge.generic.api.config.IComparisonConfiguration)
   */
  public boolean isCompliant(IComparisonConfiguration<?> configuration_p) {
    IMatchPolicy<?> rawMatchPolicy = configuration_p.getMatchPolicy();
    if (!(rawMatchPolicy instanceof ConfigurableMatchPolicy)) {
      return false;
    }
    ConfigurableMatchPolicy matchPolicy = (ConfigurableMatchPolicy)rawMatchPolicy;
    return checkMatchCriteria(matchPolicy);
  }
  
  /**
   * Return whether configurations must use a cache according to this configurator
   */
  protected boolean mustUseCache() {
    // Keep match IDs if relative criteria are being used
    return !Arrays.asList(
        MatchCriterionKind.EXTRINSIC_ID,
        MatchCriterionKind.INTRINSIC_ID).containsAll(_selectedCriteria);
  }
  
}