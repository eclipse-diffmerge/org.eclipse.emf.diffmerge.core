/**
 * <copyright>
 * 
 * Copyright (c) 2013-2016 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.impl.policies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;


/**
 * A multi-criteria match policy that supports the computation of the match ID
 * of certain elements according to the match ID of other elements.
 * @author Olivier Constant
 */
public class ConfigurableMatchPolicy extends CachingMatchPolicy implements Cloneable {
  
  /**
   * A predefined set of criteria for matching.
   */
  public static enum MatchCriterionKind {
    /**
     * The 'semantics' criterion kind: support for semantical criteria.
     */
    SEMANTICS,
    /**
     * The 'structure' criterion kind: support for structure-based criteria.
     */
    STRUCTURE,
    /**
     * The 'name' criterion kind: support for qualified names.
     */
    NAME,
    /**
     * The 'intrinsic ID' criterion kind: support for Ecore unique IDs.
     */
    INTRINSIC_ID,
    /**
     * The 'extrinsic ID' criterion kind: support for persistence-related IDs such as XMI IDs.
     */
    EXTRINSIC_ID
  }
  
  
  /**
   * A criterion for finer-grained tuning of matching
   */
  public static class FineGrainedMatchCriterion {
    /** The non-null main criterion to which this criterion is relative */
    private final MatchCriterionKind _category;
    /** A non-null label for the criterion */
    private final String _label;
    /** An optional description for the criterion */
    private final String _description;
    /**
     * Constructor
     * @param category_p a non-null main criterion
     * @param label_p a non-null label
     * @param description_p a potentially null description
     */
    public FineGrainedMatchCriterion(MatchCriterionKind category_p, String label_p,
        String description_p) {
      _category = category_p;
      _label = label_p;
      _description = description_p;
    }
    /**
     * Return the main criterion to which this criterion is relative
     * @return a non-null object
     */
    public MatchCriterionKind getCategory() {
      return _category;
    }
    /**
     * Return the description of this criterion, if any
     * @return a potentially null object
     */
    public String getDescription() {
      return _description;
    }
    /**
     * Return the label of this criterion
     * @return a non-null object
     */
    public String getLabel() {
      return _label;
    }
  }
  
  /** A criterion for structural matching of roots */
  public static final FineGrainedMatchCriterion CRITERION_STRUCTURE_ROOTS =
      new FineGrainedMatchCriterion(MatchCriterionKind.STRUCTURE,
          "Match unambiguous roots",
          "Match root elements that cannot be confused with others.");
  
  /** A criterion for structural matching by containments */
  public static final FineGrainedMatchCriterion CRITERION_STRUCTURE_CONTAINMENTS =
      new FineGrainedMatchCriterion(MatchCriterionKind.STRUCTURE,
          "Match unambiguous children",
          "Match elements that have the same container and cannot be confused with other siblings (more general than 'Match unique children').");
  
  /** A criterion for structural matching by containments */
  public static final FineGrainedMatchCriterion CRITERION_STRUCTURE_UNIQUECHILDREN =
      new FineGrainedMatchCriterion(MatchCriterionKind.STRUCTURE,
          "Match unique children",
          "Match elements that have the same container and play the same discriminating role within it.");
  
  /** A criterion for semantic matching of project structure */
  public static final FineGrainedMatchCriterion CRITERION_SEMANTICS_DEFAULTCONTENTS =
      new FineGrainedMatchCriterion(MatchCriterionKind.SEMANTICS,
          "Match default model contents",
          "When a new model is being created, it is usually filled with default model elements: match these default elements if present.\nIt may only work if the top-level elements of the models match (see 'Structure - Match unambiguous roots').");
  
  
  /** Whether the cache must be used */
  private boolean _useCache;
  
  /** The set of match criteria to use */
  private final Set<MatchCriterionKind> _selectedCriteria;
  
  /** The set of fine-grained match criteria to use */
  private final Set<FineGrainedMatchCriterion> _selectedFineGrainedCriteria;
  
  
  /**
   * Constructor
   */
  public ConfigurableMatchPolicy() {
    // Cache
    _useCache = false;
    // Selected criteria
    _selectedCriteria = new HashSet<MatchCriterionKind>(
        MatchCriterionKind.values().length);
    _selectedCriteria.addAll(getDefaultCriteria());
    _selectedFineGrainedCriteria = new HashSet<FineGrainedMatchCriterion>();
  }
  
  /**
   * @see java.lang.Object#clone()
   */
  @Override
  public ConfigurableMatchPolicy clone() throws CloneNotSupportedException {
    // Increasing visibility and down-casting
    return (ConfigurableMatchPolicy)super.clone();
  }
  
  /**
   * Configure this policy according to the given one
   * @param policy_p a non-null policy
   */
  public void configureAccordingTo(ConfigurableMatchPolicy policy_p) {
    // Common properties
    setKeepMatchIDs(policy_p.keepMatchIDs());
    setUseCache(policy_p.useCache());
    // Match criteria
    for (MatchCriterionKind criterion : policy_p.getApplicableCriteria()) {
      setUseMatchCriterion(criterion, policy_p.useMatchCriterion(criterion));
    }
    // Fine-grained match criteria
    for (FineGrainedMatchCriterion criterion : policy_p.getAvailableFineGrainedCriteria()) {
      setUseFineGrainedMatchCriterion(criterion, policy_p.useFineGrainedMatchCriterion(criterion));
    }
  }
  
  /**
   * Return the set of applicable match criteria in decreasing priority
   * @return a non-null collection
   */
  public Collection<MatchCriterionKind> getApplicableCriteria() {
    return Arrays.asList(
        MatchCriterionKind.STRUCTURE,
        MatchCriterionKind.NAME,
        MatchCriterionKind.INTRINSIC_ID,
        MatchCriterionKind.EXTRINSIC_ID);
  }
  
  /**
   * Return the set of available fine-grained match criteria, independently of the
   * fact that their category is applicable or not
   * @return a non-null, modifiable list
   */
  public List<FineGrainedMatchCriterion> getAvailableFineGrainedCriteria() {
    List<FineGrainedMatchCriterion> result = new ArrayList<FineGrainedMatchCriterion>();
    result.add(CRITERION_STRUCTURE_ROOTS);
    result.add(CRITERION_STRUCTURE_UNIQUECHILDREN);
    result.add(CRITERION_STRUCTURE_CONTAINMENTS);
    return result;
  }
  
  /**
   * Return the container of the given element within the given scope
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a potentially null reference
   */
  protected EObject getContainer(EObject element_p, IModelScope scope_p) {
    EObject result;
    if (isScopeOnly()) {
      if (scope_p instanceof IFeaturedModelScope)
        result = ((IFeaturedModelScope)scope_p).getContainer(element_p);
      else
        result = null;
    } else {
      result = element_p.eContainer();
    }
    return result;
  }
  
  /**
   * Return a match ID for the given element from the given scope
   * based on the ID of its container and the given ID suffix
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers the element
   * @param qualifier_p a non-null suffix for the ID that identifies the element within its container
   * @param separator_p an optional string to use as qualification separator
   * @return a potentially null object
   */
  protected String getContainerRelativeID(EObject element_p,
      IModelScope scope_p, String qualifier_p, String separator_p) {
    String result = null;
    EObject container = getContainer(element_p, scope_p);
    if (container != null) {
      String containerID = getMatchID(container, scope_p);
      if (containerID != null) {
        String separator = separator_p == null? getQualificationSeparatorDefault():
          separator_p;
        result = containerID + separator + qualifier_p;
      }
    } else {
      // Root
      result = qualifier_p;
    }
    return result;
  }
  
  /**
   * Return the containment reference of the given element within the given scope
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a potentially null reference
   */
  protected EReference getContainment(EObject element_p, IModelScope scope_p) {
    EReference result;
    if (isScopeOnly()) {
      if (scope_p instanceof IFeaturedModelScope)
        result = ((IFeaturedModelScope)scope_p).getContainment(element_p);
      else
        result = null;
    } else {
      result = element_p.eContainmentFeature();
    }
    return result;
  }
  
  /**
   * Return the set of default match criteria among the applicable ones
   * @return a non-null collection
   */
  public Collection<MatchCriterionKind> getDefaultCriteria() {
    return Arrays.asList(MatchCriterionKind.INTRINSIC_ID, MatchCriterionKind.EXTRINSIC_ID);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy#getExtrinsicID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getExtrinsicID(EObject element_p, IModelScope scope_p) {
    String result = null;
    Comparable<?> superID = super.getExtrinsicID(element_p, scope_p);
    if (superID instanceof String)
      result = (String)superID;
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.CachingMatchPolicy#getMatchID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  public String getMatchID(EObject element_p, IModelScope scope_p) {
    return (String)super.getMatchID(element_p, scope_p);
  }
  
  /**
   * Return a match ID for the given element from the given scope according
   * to the given criterion
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @param criterion_p a non-null criterion
   * @return a potentially null object
   */
  protected String getMatchID(EObject element_p, IModelScope scope_p,
      MatchCriterionKind criterion_p) {
    String result;
    switch (criterion_p) {
    case EXTRINSIC_ID:
      result = getExtrinsicID(element_p, scope_p); break;
    case INTRINSIC_ID:
      result = getIntrinsicID(element_p); break;
    case NAME:
      result = getQualifiedName(element_p, scope_p); break;
    case STRUCTURE:
      result = getStructureBasedID(element_p, scope_p); break;
    default:
      result = getSemanticID(element_p, scope_p); break;
    }
    return result;
  }
  
  /**
   * Return the symbol to use by default for separating qualifiers in qualified IDs,
   * for example qualified names
   * @return a non-null string
   */
  protected String getQualificationSeparatorDefault() {
    return getQualificationSeparatorSemantics();
  }
  
  /**
   * Return the symbol to use by default for separating qualifiers in, e.g., qualified names
   * @return a non-null string
   */
  protected String getQualificationSeparatorNames() {
    return "::"; //$NON-NLS-1$
  }
  
  /**
   * Return the symbol to use by default for separating qualifiers in qualified IDs,
   * for example qualified names
   * @return a non-null string
   */
  protected String getQualificationSeparatorSemantics() {
    return "~"; //$NON-NLS-1$
  }
  
  /**
   * Return the symbol to use by default for separating qualifiers in, e.g., qualified names
   * @return a non-null string
   */
  protected String getQualificationSeparatorStructure() {
    return "@"; //$NON-NLS-1$
  }
  
  /**
   * Return the qualified name of the given element
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   * @return a potentially null string
   */
  protected String getQualifiedName(EObject element_p, IModelScope scope_p) {
    String result = null;
    String name = getUniqueName(element_p, scope_p);
    if (isSignificant(name))
      result = getContainerRelativeID(
          element_p, scope_p, name, getQualificationSeparatorNames());
    return result;
  }
  
  /**
   * Return a semantic-based match ID for the given element from the given scope
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers the element
   * @return a potentially null object
   */
  protected String getSemanticID(EObject element_p, IModelScope scope_p) {
    return null;
  }
  
  /**
   * Return the siblings of the given element from the given scope, including the element itself.
   * Siblings are defined as elements owned by the same setting if the element has a container,
   * as roots of the same scope/resource otherwise.
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected List<EObject> getSiblings(EObject element_p, IModelScope scope_p) {
    List<EObject> result;
    EReference containment = getContainment(element_p, scope_p);
    if (containment == null) {
      Resource resource = element_p.eResource();
      if (isScopeOnly() || resource == null)
        result = scope_p.getContents();
      else
        result = resource.getContents();
    } else if (scope_p instanceof IFeaturedModelScope) {
      EObject container = getContainer(element_p, scope_p);
      result = ((IFeaturedModelScope)scope_p).get(container, containment);
    } else {
      result = Collections.emptyList();
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return an object that represents the type of the given element
   * for structure-based discrimination of elements.
   * Two elements have the same 'structural type' if and only if their types
   * are equal in the sense of {@link Object#equals(Object)}.
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a non-null object
   */
  protected Object getStructuralType(EObject element_p, IModelScope scope_p) {
    return element_p.eClass();
  }
  
  /**
   * Return a qualifier that characterizes the structural type of the given element
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a non-null string
   */
  protected String getStructuralTypeQualifier(EObject element_p, IModelScope scope_p) {
    Object type = getStructuralType(element_p, scope_p);
    String result;
    if (type instanceof EClass)
      result = ((EClass)type).getName();
    else
      result = type.toString();
    return result;
  }
  
  /**
   * Return a structural match ID for the given element based on its containment
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @param checkContainment_p whether the containment reference must be checked for its discriminating nature
   * @return a potentially null object
   */
  protected String getStructureBasedContainmentID(EObject element_p, IModelScope scope_p,
      boolean checkContainment_p) {
    String result = null;
    String lastIDPart = getStructureBasedContainmentQualifier(element_p, scope_p, checkContainment_p);
    if (isSignificant(lastIDPart))
      result = getContainerRelativeID(
          element_p, scope_p, lastIDPart, getQualificationSeparatorStructure());
    return result;
  }
  
  /**
   * Return a structural match ID qualifier for the given element based on its containment
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @param checkContainment_p whether the containment reference must be checked for its discriminating nature
   * @return a potentially null object
   */
  protected String getStructureBasedContainmentQualifier(EObject element_p, IModelScope scope_p,
      boolean checkContainment_p) {
    String result = null;
    EReference containment = getContainment(element_p, scope_p);
    if (containment != null &&
        (!checkContainment_p || isDiscriminatingContainment(element_p, containment)) &&
        isUniqueSiblingOfItsType(element_p, scope_p))
      result = containment.getName() + '[' + getStructuralTypeQualifier(element_p, scope_p) + ']';
    return result;
  }
  
  /**
   * Return a match ID for the given element from the given scope
   * based on the role played by the element in the model structure
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers the element
   * @return a potentially null object
   */
  protected String getStructureBasedID(EObject element_p, IModelScope scope_p) {
    String result = null;
    EReference containment = getContainment(element_p, scope_p);
    if (containment == null && useFineGrainedMatchCriterion(CRITERION_STRUCTURE_ROOTS)) {
      result = getStructureBasedRootQualifier(element_p, scope_p);
    } else if (containment != null &&
        (useFineGrainedMatchCriterion(CRITERION_STRUCTURE_CONTAINMENTS) ||
            useFineGrainedMatchCriterion(CRITERION_STRUCTURE_UNIQUECHILDREN))) {
      result = getStructureBasedContainmentID(element_p, scope_p,
          !useFineGrainedMatchCriterion(CRITERION_STRUCTURE_CONTAINMENTS));
    }
    return result;
  }
  
  /**
   * Return a structural match ID qualifier for the given root element
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   * @return a potentially null object
   */
  protected String getStructureBasedRootQualifier(EObject element_p, IModelScope scope_p) {
    String result = null;
    if (isUniqueSiblingOfItsType(element_p, scope_p))
      result = getQualificationSeparatorStructure() + getStructuralTypeQualifier(element_p, scope_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.CachingMatchPolicy#getUncachedMatchID(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IModelScope)
   */
  @Override
  protected String getUncachedMatchID(EObject element_p, IModelScope scope_p) {
    String result = null;
    Iterator<MatchCriterionKind> it = getApplicableCriteria().iterator();
    while (result == null && it.hasNext()) {
      MatchCriterionKind criterion = it.next();
      if (useMatchCriterion(criterion))
        result = getMatchID(element_p, scope_p, criterion);
    }
    return result;
  }
  
  /**
   * Return the name of the given element, unique within the container if possible
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers the element
   * @return a potentially null object
   */
  protected String getUniqueName(EObject element_p, IModelScope scope_p) {
    String result = null;
    if (element_p instanceof ENamedElement)
      result = ((ENamedElement)element_p).getName();
    return result;
  }
  
  /**
   * Return whether the given containment reference is discriminating enough to uniquely
   * identify the given element as a child
   * @param element_p a non-null element
   * @param containment_p a non-null containment reference
   */
  protected boolean isDiscriminatingContainment(EObject element_p, EReference containment_p) {
    return !containment_p.isMany();
  }
  
  /**
   * Return whether the given element is owned via a discriminating containment in the given scope
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isInDiscriminatingContainment(EObject element_p, IModelScope scope_p) {
    EReference containment = getContainment(element_p, scope_p);
    return containment != null && isDiscriminatingContainment(element_p, containment);
  }
  
  /**
   * Return whether the given element is an instance of one of the given types
   * @param element_p a non-null element
   * @param types_p a non-null, potentially empty collection
   */
  protected boolean isInstanceOf(EObject element_p, Collection<? extends EClass> types_p) {
    Iterator<? extends EClass> typesIterator = types_p.iterator();
    while (typesIterator.hasNext()) {
      EClass type = typesIterator.next();
      if (type.isInstance(element_p))
        return true;
    }
    return false;
  }
  
  /**
   * Return whether only the model scope should be considered for building match IDs,
   * or the whole underlying EMF model
   */
  protected boolean isScopeOnly() {
    return true;
  }
  
  /**
   * Return whether the given string can be considered as significant for contributing to
   * the identification of an element
   * @param string_p a potentially null string
   */
  protected boolean isSignificant(String string_p) {
    return string_p != null && string_p.length() > 0;
  }
  
  /**
   * Return whether the given element is the only one of its type among those in the given collection
   * @param element_p a non-null element
   * @param collection_p a non-null, potentially empty collection
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isUniqueOfItsTypeAmong(EObject element_p,
      Collection<? extends EObject> collection_p, IModelScope scope_p) {
    Iterator<? extends EObject> it = collection_p.iterator();
    boolean result = false;
    Object type = getStructuralType(element_p, scope_p);
    if (type != null) {
      boolean isPresent = false, sameType = false;
      while (it.hasNext() && !sameType) {
        EObject root = it.next();
        if (root == element_p) {
          isPresent = true;
        } else {
          if (type.equals(getStructuralType(root, scope_p)))
            sameType = true;
        }
      }
      result = isPresent && !sameType;
    }
    return result;
  }
  
  /**
   * Return whether the given element is the only one of its type among its siblings
   * @param element_p a non-null element
   * @param scope_p a non-null scope that covers element_p
   */
  protected boolean isUniqueSiblingOfItsType(EObject element_p, IModelScope scope_p) {
    Collection<EObject> siblings = getSiblings(element_p, scope_p);
    return isUniqueOfItsTypeAmong(element_p, siblings, scope_p);
  }
  
  /**
   * Set whether the cache must be used
   * @param useCache_p whether it must be used
   */
  public void setUseCache(boolean useCache_p) {
    _useCache = useCache_p;
  }
  
  /**
   * Set whether the given fine-grained match criterion must be used
   * @param criterion_p a non-null criterion
   * @param use_p whether it must be used
   */
  public void setUseFineGrainedMatchCriterion(FineGrainedMatchCriterion criterion_p, boolean use_p) {
    if (use_p)
      _selectedFineGrainedCriteria.add(criterion_p);
    else
      _selectedFineGrainedCriteria.remove(criterion_p);
  }
  
  /**
   * Set whether the given match criterion must be used
   * @param criterion_p a non-null criterion
   * @param use_p whether it must be used
   */
  public void setUseMatchCriterion(MatchCriterionKind criterion_p, boolean use_p) {
    if (use_p)
      _selectedCriteria.add(criterion_p);
    else
      _selectedCriteria.remove(criterion_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.policies.CachingMatchPolicy#useCache()
   */
  @Override
  public boolean useCache() {
    return _useCache;
  }
  
  /**
   * Return whether the given fine-grained match criterion is selected for being used
   * by this match policy, independently of the fact that its category is used or not
   * @param criterion_p a non-null criterion
   */
  public boolean useFineGrainedMatchCriterion(FineGrainedMatchCriterion criterion_p) {
    return _selectedFineGrainedCriteria.contains(criterion_p);
  }
  
  /**
   * Return whether the given match criterion is used by this match policy
   * @param criterion_p a non-null criterion
   */
  public boolean useMatchCriterion(MatchCriterionKind criterion_p) {
    return _selectedCriteria.contains(criterion_p);
  }
  
}
