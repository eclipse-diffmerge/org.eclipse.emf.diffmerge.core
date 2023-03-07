/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.gdiffdata.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.generic.Messages;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergeSelector;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.diffmerge.generic.impl.helpers.DiffOperation;
import org.eclipse.emf.diffmerge.generic.impl.helpers.MatchOperation;
import org.eclipse.emf.diffmerge.generic.impl.helpers.MergeOperation;
import org.eclipse.emf.diffmerge.generic.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.generic.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.diffmerge.generic.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GComparison</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getAncestorScope <em>Ancestor Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getReferenceScope <em>Reference Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getLastMatchPolicy <em>Last Match Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getLastDiffPolicy <em>Last Diff Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getLastMergePolicy <em>Last Merge Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GComparisonImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class GComparisonImpl<E, A, R> extends GIdentifiedImpl
    implements GComparison<E, A, R> {
  /**
   * The cached value of the '{@link #getAncestorScope() <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestorScope()
   * @generated
   * @ordered
   */
  protected IEditableTreeDataScope<E> ancestorScope;

  /**
   * The cached value of the '{@link #getReferenceScope() <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceScope()
   * @generated
   * @ordered
   */
  protected IEditableTreeDataScope<E> referenceScope;

  /**
   * The cached value of the '{@link #getTargetScope() <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetScope()
   * @generated
   * @ordered
   */
  protected IEditableTreeDataScope<E> targetScope;

  /**
   * The cached value of the '{@link #getLastMatchPolicy() <em>Last Match Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMatchPolicy()
   * @generated
   * @ordered
   */
  protected IMatchPolicy<E> lastMatchPolicy;

  /**
   * The cached value of the '{@link #getLastDiffPolicy() <em>Last Diff Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastDiffPolicy()
   * @generated
   * @ordered
   */
  protected IDiffPolicy<E> lastDiffPolicy;

  /**
   * The cached value of the '{@link #getLastMergePolicy() <em>Last Merge Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMergePolicy()
   * @generated
   * @ordered
   */
  protected IMergePolicy<E> lastMergePolicy;

  /**
   * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMapping()
   * @generated
   * @ordered
   */
  protected GMapping<E, A, R> mapping;

  /**
   * The non-null sets of duplicate match IDs per role
   * @generated NOT
   */
  private final Map<Role, Set<Object>> _duplicateIDs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected GComparisonImpl() {
    super();
    Map<Role, Set<Object>> duplicateIDs = new HashMap<Role, Set<Object>>(3);
    for (Role role : Role.values()) {
      duplicateIDs.put(role, new HashSet<Object>(0));
    }
    _duplicateIDs = Collections.unmodifiableMap(duplicateIDs);
  }

  /**
   * Simplified constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @generated NOT
   */
  public GComparisonImpl(IEditableTreeDataScope<E> targetScope_p,
      IEditableTreeDataScope<E> referenceScope_p) {
    this(targetScope_p, referenceScope_p, null);
  }

  /**
   * Full constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @param ancestorScope_p the optional model scope playing the ANCESTOR comparison role
   * @generated NOT
   */
  public GComparisonImpl(IEditableTreeDataScope<E> targetScope_p,
      IEditableTreeDataScope<E> referenceScope_p,
      IEditableTreeDataScope<E> ancestorScope_p) {
    this();
    setTargetScope(targetScope_p);
    setReferenceScope(referenceScope_p);
    setAncestorScope(ancestorScope_p);
    setMapping(newMapping());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.GCOMPARISON;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableTreeDataScope<E> getAncestorScope() {
    return ancestorScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAncestorScope(IEditableTreeDataScope<E> newAncestorScope) {
    IEditableTreeDataScope<E> oldAncestorScope = ancestorScope;
    ancestorScope = newAncestorScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.GCOMPARISON__ANCESTOR_SCOPE, oldAncestorScope,
          ancestorScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableTreeDataScope<E> getReferenceScope() {
    return referenceScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferenceScope(IEditableTreeDataScope<E> newReferenceScope) {
    IEditableTreeDataScope<E> oldReferenceScope = referenceScope;
    referenceScope = newReferenceScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.GCOMPARISON__REFERENCE_SCOPE, oldReferenceScope,
          referenceScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableTreeDataScope<E> getTargetScope() {
    return targetScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetScope(IEditableTreeDataScope<E> newTargetScope) {
    IEditableTreeDataScope<E> oldTargetScope = targetScope;
    targetScope = newTargetScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.GCOMPARISON__TARGET_SCOPE, oldTargetScope,
          targetScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMatchPolicy<E> getLastMatchPolicy() {
    return lastMatchPolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastMatchPolicy(IMatchPolicy<E> newLastMatchPolicy) {
    IMatchPolicy<E> oldLastMatchPolicy = lastMatchPolicy;
    lastMatchPolicy = newLastMatchPolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.GCOMPARISON__LAST_MATCH_POLICY, oldLastMatchPolicy,
          lastMatchPolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IDiffPolicy<E> getLastDiffPolicy() {
    return lastDiffPolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastDiffPolicy(IDiffPolicy<E> newLastDiffPolicy) {
    IDiffPolicy<E> oldLastDiffPolicy = lastDiffPolicy;
    lastDiffPolicy = newLastDiffPolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.GCOMPARISON__LAST_DIFF_POLICY, oldLastDiffPolicy,
          lastDiffPolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMergePolicy<E> getLastMergePolicy() {
    return lastMergePolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastMergePolicy(IMergePolicy<E> newLastMergePolicy) {
    IMergePolicy<E> oldLastMergePolicy = lastMergePolicy;
    lastMergePolicy = newLastMergePolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.GCOMPARISON__LAST_MERGE_POLICY, oldLastMergePolicy,
          lastMergePolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GMapping<E, A, R> getMapping() {
    return mapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public NotificationChain basicSetMapping(GMapping<E, A, R> newMapping,
      NotificationChain msgs) {
    NotificationChain result = msgs;
    GMapping<E, A, R> oldMapping = mapping;
    mapping = newMapping;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET, GdiffdataPackage.GCOMPARISON__MAPPING, oldMapping,
          newMapping);
      if (result == null) {
        result = notification;
      } else {
        result.add(notification);
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMapping(GMapping<E, A, R> newMapping) {
    if (newMapping != mapping) {
      NotificationChain msgs = null;
      if (mapping != null)
        msgs = ((InternalEObject) mapping).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - GdiffdataPackage.GCOMPARISON__MAPPING,
            null, msgs);
      if (newMapping != null)
        msgs = ((InternalEObject) newMapping).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - GdiffdataPackage.GCOMPARISON__MAPPING,
            null, msgs);
      msgs = basicSetMapping(newMapping, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.GCOMPARISON__MAPPING, newMapping, newMapping));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd,
      int featureID, NotificationChain msgs) {
    switch (featureID) {
    case GdiffdataPackage.GCOMPARISON__MAPPING:
      return basicSetMapping(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case GdiffdataPackage.GCOMPARISON__ANCESTOR_SCOPE:
      return getAncestorScope();
    case GdiffdataPackage.GCOMPARISON__REFERENCE_SCOPE:
      return getReferenceScope();
    case GdiffdataPackage.GCOMPARISON__TARGET_SCOPE:
      return getTargetScope();
    case GdiffdataPackage.GCOMPARISON__LAST_MATCH_POLICY:
      return getLastMatchPolicy();
    case GdiffdataPackage.GCOMPARISON__LAST_DIFF_POLICY:
      return getLastDiffPolicy();
    case GdiffdataPackage.GCOMPARISON__LAST_MERGE_POLICY:
      return getLastMergePolicy();
    case GdiffdataPackage.GCOMPARISON__MAPPING:
      return getMapping();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case GdiffdataPackage.GCOMPARISON__ANCESTOR_SCOPE:
      setAncestorScope((IEditableTreeDataScope<E>) newValue);
      return;
    case GdiffdataPackage.GCOMPARISON__REFERENCE_SCOPE:
      setReferenceScope((IEditableTreeDataScope<E>) newValue);
      return;
    case GdiffdataPackage.GCOMPARISON__TARGET_SCOPE:
      setTargetScope((IEditableTreeDataScope<E>) newValue);
      return;
    case GdiffdataPackage.GCOMPARISON__LAST_MATCH_POLICY:
      setLastMatchPolicy((IMatchPolicy<E>) newValue);
      return;
    case GdiffdataPackage.GCOMPARISON__LAST_DIFF_POLICY:
      setLastDiffPolicy((IDiffPolicy<E>) newValue);
      return;
    case GdiffdataPackage.GCOMPARISON__LAST_MERGE_POLICY:
      setLastMergePolicy((IMergePolicy<E>) newValue);
      return;
    case GdiffdataPackage.GCOMPARISON__MAPPING:
      setMapping((GMapping<E, A, R>) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case GdiffdataPackage.GCOMPARISON__ANCESTOR_SCOPE:
      setAncestorScope((IEditableTreeDataScope<E>) null);
      return;
    case GdiffdataPackage.GCOMPARISON__REFERENCE_SCOPE:
      setReferenceScope((IEditableTreeDataScope<E>) null);
      return;
    case GdiffdataPackage.GCOMPARISON__TARGET_SCOPE:
      setTargetScope((IEditableTreeDataScope<E>) null);
      return;
    case GdiffdataPackage.GCOMPARISON__LAST_MATCH_POLICY:
      setLastMatchPolicy((IMatchPolicy<E>) null);
      return;
    case GdiffdataPackage.GCOMPARISON__LAST_DIFF_POLICY:
      setLastDiffPolicy((IDiffPolicy<E>) null);
      return;
    case GdiffdataPackage.GCOMPARISON__LAST_MERGE_POLICY:
      setLastMergePolicy((IMergePolicy<E>) null);
      return;
    case GdiffdataPackage.GCOMPARISON__MAPPING:
      setMapping((GMapping<E, A, R>) null);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case GdiffdataPackage.GCOMPARISON__ANCESTOR_SCOPE:
      return ancestorScope != null;
    case GdiffdataPackage.GCOMPARISON__REFERENCE_SCOPE:
      return referenceScope != null;
    case GdiffdataPackage.GCOMPARISON__TARGET_SCOPE:
      return targetScope != null;
    case GdiffdataPackage.GCOMPARISON__LAST_MATCH_POLICY:
      return lastMatchPolicy != null;
    case GdiffdataPackage.GCOMPARISON__LAST_DIFF_POLICY:
      return lastDiffPolicy != null;
    case GdiffdataPackage.GCOMPARISON__LAST_MERGE_POLICY:
      return lastMergePolicy != null;
    case GdiffdataPackage.GCOMPARISON__MAPPING:
      return mapping != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (ancestorScope: "); //$NON-NLS-1$
    result.append(ancestorScope);
    result.append(", referenceScope: "); //$NON-NLS-1$
    result.append(referenceScope);
    result.append(", targetScope: "); //$NON-NLS-1$
    result.append(targetScope);
    result.append(", lastMatchPolicy: "); //$NON-NLS-1$
    result.append(lastMatchPolicy);
    result.append(", lastDiffPolicy: "); //$NON-NLS-1$
    result.append(lastDiffPolicy);
    result.append(", lastMergePolicy: "); //$NON-NLS-1$
    result.append(lastMergePolicy);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#clear()
   * @generated NOT
   */
  public void clear() {
    ((IMapping.Editable<E>) getMapping()).clear();
    setLastMatchPolicy(null);
    setLastDiffPolicy(null);
    setLastMergePolicy(null);
    for (Role role : Role.values()) {
      _duplicateIDs.get(role).clear();
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#compute(org.eclipse.emf.diffmerge.generic.api.IMatchPolicy, org.eclipse.emf.diffmerge.generic.api.IDiffPolicy, org.eclipse.emf.diffmerge.generic.api.IMergePolicy, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public IStatus compute(IMatchPolicy<E> matchPolicy_p,
      IDiffPolicy<E> diffPolicy_p, IMergePolicy<E> mergePolicy_p,
      IProgressMonitor monitor_p) {
    // Monitor
    IProgressMonitor nonNullMonitor = monitor_p != null ? monitor_p
        : new NullProgressMonitor();
    SubMonitor subMonitor = SubMonitor.convert(nonNullMonitor,
        Messages.Comparison_Task_Main, 2);
    // Policies
    setLastMatchPolicy(
        matchPolicy_p != null ? matchPolicy_p : getDefaultMatchPolicy());
    setLastDiffPolicy(
        diffPolicy_p != null ? diffPolicy_p : getDefaultDiffPolicy());
    setLastMergePolicy(
        mergePolicy_p != null ? mergePolicy_p : getDefaultMergePolicy());
    // Behavior
    IStatus result = computeMatch(getLastMatchPolicy(), subMonitor.newChild(1));
    if (result.isOK()) {
      result = computeDiff(getLastDiffPolicy(), getLastMergePolicy(),
          subMonitor.newChild(1));
    }
    return result;
  }

  /**
   * Execute the Match phase of the comparison process
   * @param matchPolicy_p a non-null match policy
   * @param monitor_p a non-null progress monitor
   * @return a non-null status of the execution
   * @generated NOT
   */
  protected IStatus computeMatch(IMatchPolicy<E> matchPolicy_p,
      IProgressMonitor monitor_p) {
    IExpensiveOperation matchOperation = getMatchOperation(matchPolicy_p,
        _duplicateIDs);
    IStatus result = matchOperation.run(monitor_p);
    return result;
  }

  /**
   * Execute the Diff phase of the comparison process
   * @param diffPolicy_p a non-null diff policy
   * @param mergePolicy_p a non-null merge policy
   * @param monitor_p a non-null progress monitor
   * @return a non-null status of the execution
   * @generated NOT
   */
  protected IStatus computeDiff(IDiffPolicy<E> diffPolicy_p,
      IMergePolicy<E> mergePolicy_p, IProgressMonitor monitor_p) {
    IExpensiveOperation diffOperation = getDiffOperation(diffPolicy_p,
        mergePolicy_p);
    IStatus result = diffOperation.run(monitor_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getAllContents(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  @SuppressWarnings("serial")
  public TreeIterator<IMatch<E>> getAllContents(final Role role_p) {
    return new AbstractTreeIterator<IMatch<E>>(this, false) {
      /**
       * @see org.eclipse.emf.common.util.AbstractTreeIterator#getChildren(Object)
       */
      @SuppressWarnings({ "rawtypes", "unchecked" })
      @Override
      protected Iterator<? extends IMatch<E>> getChildren(Object object_p) {
        Iterator<? extends IMatch<E>> result;
        if (object_p instanceof IComparison) {
          result = ((IComparison) object_p).getContents(role_p).iterator();
        } else {
          result = getContentsOf((IMatch) object_p, role_p).iterator();
        }
        return result;
      }
    };
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContainerOf(org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IMatch<E> getContainerOf(IMatch<E> match_p, Role role_p) {
    IMatch<E> result = null;
    E child = match_p.get(role_p);
    if (child != null) {
      ITreeDataScope<E> scope = getScope(role_p);
      E container = scope.getContainer(child);
      if (container != null) {
        result = getMapping().getMatchFor(container, role_p);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContents()
   * @generated NOT
   */
  public List<IMatch<E>> getContents() {
    List<IMatch<E>> targetMatches = getContents(Role.TARGET);
    List<IMatch<E>> referenceMatches = getContents(Role.REFERENCE);
    List<IMatch<E>> result = new FArrayList<IMatch<E>>(referenceMatches, null);
    for (IMatch<E> targetMatch : targetMatches) {
      if (!result.contains(targetMatch))
        result.add(targetMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContents(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public List<IMatch<E>> getContents(Role role_p) {
    List<IMatch<E>> result = new FArrayList<IMatch<E>>();
    // Defining 'contents': list of elements whose matches must be retrieved
    Iterable<E> contents = getScope(role_p).getRoots();
    // Retrieving matches
    for (E child : contents) {
      IMatch<E> childMatch = getMapping().getMatchFor(child, role_p);
      if (childMatch != null)
        result.add(childMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContentsOf(org.eclipse.emf.diffmerge.generic.api.IMatch)
   * @generated NOT
   */
  public List<IMatch<E>> getContentsOf(IMatch<E> match_p) {
    List<IMatch<E>> targetMatches = getContentsOf(match_p, Role.TARGET);
    List<IMatch<E>> referenceMatches = getContentsOf(match_p, Role.REFERENCE);
    List<IMatch<E>> result = new FOrderedSet<IMatch<E>>(referenceMatches, null);
    for (IMatch<E> targetMatch : targetMatches) {
      result.add(targetMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContentsOf(org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public List<IMatch<E>> getContentsOf(IMatch<E> match_p, Role role_p) {
    List<IMatch<E>> result = new FArrayList<IMatch<E>>();
    // Defining 'contents': list of elements whose matches must be retrieved
    Iterable<E> contents = null;
    E container = match_p.get(role_p);
    if (container != null) {
      ITreeDataScope<E> scope = getScope(role_p);
      contents = scope.getContents(container);
    }
    // Retrieving matches
    if (contents != null) {
      for (E child : contents) {
        IMatch<E> childMatch = getMapping().getMatchFor(child, role_p);
        if (childMatch != null)
          result.add(childMatch);
      }
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * Return a default diff policy
   * @return a non-null object
   * @generated NOT
   */
  protected IDiffPolicy<E> getDefaultDiffPolicy() {
    return new DefaultDiffPolicy<E>();
  }

  /**
   * Return a default match policy
   * @return a non-null object
   * @generated NOT
   */
  protected IMatchPolicy<E> getDefaultMatchPolicy() {
    return new ConfigurableMatchPolicy<E>();
  }

  /**
   * Return a default merge policy
   * @return a non-null object
   * @generated NOT
   */
  protected IMergePolicy<E> getDefaultMergePolicy() {
    return new DefaultMergePolicy<E>();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getDifferences(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public Collection<IDifference<E>> getDifferences(Role role_p) {
    Collection<IDifference<E>> result = new FHashSet<IDifference<E>>(IEqualityTester.BY_EQUALS);
    for (IMatch<E> match : getMapping().getContents()) {
      result.addAll(match.getPresenceDifferencesIn(role_p));
    }
    return Collections.unmodifiableCollection(result);
  }

  /**
   * Return an operation for executing the Diff phase
   * @param diffPolicy_p an optional diff policy
   * @param mergePolicy_p an optional merge policy
   * @return a non-null operation which is configured to be applied on the given comparison data
   * @generated NOT
   */
  protected IExpensiveOperation getDiffOperation(IDiffPolicy<E> diffPolicy_p,
      IMergePolicy<E> mergePolicy_p) {
    return new DiffOperation<E>(this, diffPolicy_p, mergePolicy_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getDuplicateMatchIDs(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public Collection<Object> getDuplicateMatchIDs(Role role_p) {
    return _duplicateIDs.get(role_p);
  }

  /**
   * Return an operation for executing the Match phase
   * @param policy_p an optional match policy
   * @param duplicateIDs_p an optional map that associates each role with an empty, modifiable set of duplicate match IDs 
   * @return a non-null operation which is configured to be applied on the given comparison data
   * @generated NOT
   */
  protected IExpensiveOperation getMatchOperation(IMatchPolicy<E> policy_p,
      Map<Role, Set<Object>> duplicateIDs_p) {
    return new MatchOperation<E>(this, policy_p, duplicateIDs_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getNbDifferences()
   * @generated NOT
   */
  public int getNbDifferences() {
    int result = 0;
    for (IMatch<E> match : getMapping().getContents()) {
      result += match.getRelatedDifferences().size();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getNbNoContainmentDifferences()
   * @generated NOT
   */
  public int getNbNoContainmentDifferences() {
    int result = 0;
    for (IMatch<E> match : getMapping().getContents()) {
      result += match.getNbNoContainmentDifferences();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getRemainingDifferences()
   * @generated NOT
   */
  public Collection<IDifference<E>> getRemainingDifferences() {
    Collection<IDifference<E>> result = new FHashSet<IDifference<E>>(
        IEqualityTester.BY_EQUALS);
    for (IMatch<E> match : getMapping().getContents()) {
      for (IDifference<E> difference : match.getAllDifferences()) {
        if (!difference.isMerged())
          result.add(difference);
      }
    }
    return Collections.unmodifiableCollection(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#getScope(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IEditableTreeDataScope<E> getScope(Role role) {
    IEditableTreeDataScope<E> result;
    switch (role) {
    case TARGET:
      result = getTargetScope();
      break;
    case REFERENCE:
      result = getReferenceScope();
      break;
    default:
      result = getAncestorScope();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#hasRemainingDifferences()
   * @generated NOT
   */
  public boolean hasRemainingDifferences() {
    for (IMatch<E> match : getMapping().getContents()) {
      for (IDifference<E> difference : match.getAllDifferences()) {
        if (!difference.isMerged())
          return true;
      }
    }
    return false;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#isConsistent()
   * @generated NOT
   */
  public boolean isConsistent() {
    for (Role role : Role.values()) {
      if (!getDuplicateMatchIDs(role).isEmpty())
        return false;
    }
    return true;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#isThreeWay()
   * @generated NOT
   */
  public boolean isThreeWay() {
    return getAncestorScope() != null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#merge(org.eclipse.emf.diffmerge.generic.api.Role, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated not
   */
  public Collection<IDifference<E>> merge(final Role destination_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    Collection<IDifference<E>> result = merge(new IMergeSelector<E>() {
      /**
       * @see org.eclipse.emf.diffmerge.generic.api.IMergeSelector#getMergeDirection(org.eclipse.emf.diffmerge.generic.api.diff.IDifference)
       */
      public Role getMergeDirection(IDifference<E> difference_p) {
        return destination_p;
      }
    }, updateReferences_p, monitor_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#merge(java.util.Collection, org.eclipse.emf.diffmerge.generic.api.Role, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public Collection<IDifference<E>> merge(
      Collection<? extends IDifference<E>> differences_p, Role destination_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    MergeOperation<E> operation = new MergeOperation<E>(this, differences_p,
        destination_p, updateReferences_p);
    operation.run(monitor_p);
    return operation.getOutput();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#merge(org.eclipse.emf.diffmerge.generic.api.IMergeSelector, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public Collection<IDifference<E>> merge(IMergeSelector<E> merger_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    MergeOperation<E> operation = new MergeOperation<E>(this, merger_p,
        updateReferences_p);
    operation.run(monitor_p);
    return operation.getOutput();
  }

  /**
   * Create and return a mapping for this comparison
   * @return a non-null mapping
   * @generated NOT
   */
  protected abstract GMapping<E, A, R> newMapping();

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#swapScopes()
   * @generated NOT
   */
  public boolean swapScopes() {
    boolean isEmpty = getMapping().isEmpty();
    if (isEmpty) {
      IEditableTreeDataScope<E> formerTarget = targetScope;
      targetScope = referenceScope;
      referenceScope = formerTarget;
    }
    return isEmpty;
  }

} //GComparisonImpl
