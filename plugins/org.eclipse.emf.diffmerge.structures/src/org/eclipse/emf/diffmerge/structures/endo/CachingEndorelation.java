/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.endo;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;
import org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;


/**
 * An implementation of a recursively-defined endorelation that has a
 * known exploration state that evolves incrementally.
 * 
 * @param <T> the type of the elements
 * @author Olivier Constant
 */
public class CachingEndorelation<T> extends RecursivelyDefinedEndorelation<T>
implements ICachingEndorelation<T> {
  
  /** The non-null exploration iterator */
  private IGraphIterator<T> _explorationIterator;
  
  /** The non-null (source, target) explored subset */
  private final IRangedEndorelation.Editable<T> _exploredSubset;
  
  /** The non-null, potentially empty set of elements covered (as source)
   *  by the explored subset */
  protected final Set<T> _coveredElements;
  
  
  /**
   * Constructor (enforces no particular constraint)
   * @param origins_p the non-null set of origin elements for the base case
   * @param rule_p the non-null endorelation for the recursion step
   */
  public CachingEndorelation(Collection<? extends T> origins_p,
      IEndorelation<T> rule_p) {
    this(origins_p, rule_p, false);
  }
  
  /**
   * Constructor
   * @param origins_p the non-null set of origin elements for the base case
   * @param rule_p the non-null endorelation for the recursion step
   * @param noMultipleInverseOrCycles_p whether elements may not have several
   *          inverse elements in the relation and there cannot be cycles; this
   *          is used for optimization. True means that the endorelation is
   *          injective and its transitive closure is antisymmetric.
   */
  public CachingEndorelation(Collection<? extends T> origins_p,
      IEndorelation<T> rule_p, boolean noMultipleInverseOrCycles_p) {
    super(origins_p, rule_p, noMultipleInverseOrCycles_p);
    setExplorationIterator(newExplorationIterator());
    _exploredSubset = newExploredSubset();
    _coveredElements = new FHashSet<T>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#explore()
   */
  public long explore() {
    IGraphIterator<T> explorationIterator = getExplorationIterator();
    while (explorationIterator.hasNext()) {
      explorationIterator.next();
    }
    return explorationIterator.maxDepth();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#exploreNext(long)
   */
  public long exploreNext(long depthSteps_p) {
    long targetDepth = getExplorationIterator().lastDepth() + depthSteps_p;
    return exploreUntil(targetDepth);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#exploreUntil(long)
   */
  public long exploreUntil(long depth_p) {
    IGraphIterator<T> explorationIterator = getExplorationIterator();
    long initialDepth = explorationIterator.lastDepth();
    while (explorationIterator.hasNext() && explorationIterator.nextDepth() <= depth_p) {
      explorationIterator.next();
    }
    return explorationIterator.lastDepth() - initialDepth;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.RecursivelyDefinedEndorelation#get(java.lang.Object)
   */
  @Override
  public Collection<T> get(T element_p) {
    Collection<T> result;
    IRangedBinaryRelation.Editable<T, T> explored = getEditableExploredSubset();
    if (_coveredElements.contains(element_p)) {
      result = explored.get(element_p);
    } else {
      result = super.get(element_p);
      explored.addAll(element_p, result);
      _coveredElements.add(element_p);
    }
    return result;
  }
  
  /**
   * Return the explored subset for modification purposes
   * @return a non-null object
   */
  protected IRangedBinaryRelation.Editable<T, T> getEditableExploredSubset() {
    return _exploredSubset;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.IRangedEndorelation#getElements()
   */
  public Collection<T> getElements() {
    return getExploredSubset().getElements();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#getExplorationDepth()
   */
  public long getExplorationDepth() {
    return getExplorationIterator().lastDepth();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#getExploredSubset()
   */
  public IRangedEndorelation<T> getExploredSubset() {
    return _exploredSubset;
  }
  
  /**
   * Return the iterator that is used for exploration
   * @return a non-null iterator
   */
  public IGraphIterator<T> getExplorationIterator() {
    return _explorationIterator;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractIterableEndorelation#getMinimalElements()
   */
  @Override
  public IPropertyValue<Collection<T>> getMinimalElements() {
    IPropertyValue<Collection<T>> result;
    if (isExplored()) {
      Set<T> mins = new FHashSet<T>(getOrigins(), getEqualityTester());
      mins.removeAll(getExploredSubset().getTargets());
      result = new PropertyValue<Collection<T>>(Collections.unmodifiableCollection(mins));
    } else {
      result = super.getMinimalElements();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation#getSources()
   */
  public Collection<T> getSources() {
    return getExploredSubset().getSources();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IRangedBinaryRelation#getTargets()
   */
  public Collection<T> getTargets() {
    return getExploredSubset().getTargets();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#isExplored()
   */
  public boolean isExplored() {
    return !getExplorationIterator().hasNext();
  }
  
  /**
   * Create and return a new exploration iterator
   * @return a non-null iterator
   */
  protected IGraphIterator<T> newExplorationIterator() {
    return new BreadthFirstIterator<T>(this) {
      /**
       * @see org.eclipse.emf.diffmerge.structures.endo.BreadthFirstIterator#update()
       */
      @Override
      protected void update() {
        super.update();
        if (_next == null && _coveredElements != null) // Can be null during object init
          _coveredElements.clear(); // Clear when exploration is complete
      }
    };
  }
  
  /**
   * Create and return the explored subset structure
   * @return a non-null object
   */
  protected IRangedEndorelation.Editable<T> newExploredSubset() {
    return new EditableEndorelation<T>(getEqualityTester());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICachingEndorelation#resetExploration()
   */
  public void resetExploration() {
    getEditableExploredSubset().clear();
    _coveredElements.clear();
    setExplorationIterator(newExplorationIterator());
  }
  
  /**
   * Set the exploration iterator
   * @param iterator_p a non-null iterator
   */
  protected void setExplorationIterator(IGraphIterator<T> iterator_p) {
    _explorationIterator = iterator_p;
  }
  
}
