/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.diffdata.impl;

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
import org.eclipse.emf.diffmerge.Messages;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.IMapping;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.api.IMergePolicy;
import org.eclipse.emf.diffmerge.api.IMergeSelector;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.api.scopes.IModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMapping;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.impl.helpers.DiffOperation;
import org.eclipse.emf.diffmerge.impl.helpers.MatchOperation;
import org.eclipse.emf.diffmerge.impl.helpers.MergeOperation;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.diffmerge.util.IExpensiveOperation;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.FHashSet;
import org.eclipse.emf.diffmerge.util.structures.FOrderedSet;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EComparison</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl#getAncestorScope <em>Ancestor Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl#getReferenceScope <em>Reference Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl#getLastMatchPolicy <em>Last Match Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl#getLastDiffPolicy <em>Last Diff Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl#getLastMergePolicy <em>Last Merge Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EComparisonImpl extends EObjectImpl implements EComparison {
  /**
   * The default value of the '{@link #getAncestorScope() <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestorScope()
   * @generated
   * @ordered
   */
  protected static final IEditableModelScope ANCESTOR_SCOPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAncestorScope() <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestorScope()
   * @generated
   * @ordered
   */
  protected IEditableModelScope ancestorScope = ANCESTOR_SCOPE_EDEFAULT;

  /**
   * The default value of the '{@link #getReferenceScope() <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceScope()
   * @generated
   * @ordered
   */
  protected static final IEditableModelScope REFERENCE_SCOPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReferenceScope() <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceScope()
   * @generated
   * @ordered
   */
  protected IEditableModelScope referenceScope = REFERENCE_SCOPE_EDEFAULT;

  /**
   * The default value of the '{@link #getTargetScope() <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetScope()
   * @generated
   * @ordered
   */
  protected static final IEditableModelScope TARGET_SCOPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTargetScope() <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetScope()
   * @generated
   * @ordered
   */
  protected IEditableModelScope targetScope = TARGET_SCOPE_EDEFAULT;

  /**
   * The default value of the '{@link #getLastMatchPolicy() <em>Last Match Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMatchPolicy()
   * @generated
   * @ordered
   */
  protected static final IMatchPolicy LAST_MATCH_POLICY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLastMatchPolicy() <em>Last Match Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMatchPolicy()
   * @generated
   * @ordered
   */
  protected IMatchPolicy lastMatchPolicy = LAST_MATCH_POLICY_EDEFAULT;

  /**
   * The default value of the '{@link #getLastDiffPolicy() <em>Last Diff Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastDiffPolicy()
   * @generated
   * @ordered
   */
  protected static final IDiffPolicy LAST_DIFF_POLICY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLastDiffPolicy() <em>Last Diff Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastDiffPolicy()
   * @generated
   * @ordered
   */
  protected IDiffPolicy lastDiffPolicy = LAST_DIFF_POLICY_EDEFAULT;

  /**
   * The default value of the '{@link #getLastMergePolicy() <em>Last Merge Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMergePolicy()
   * @generated
   * @ordered
   */
  protected static final IMergePolicy LAST_MERGE_POLICY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLastMergePolicy() <em>Last Merge Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMergePolicy()
   * @generated
   * @ordered
   */
  protected IMergePolicy lastMergePolicy = LAST_MERGE_POLICY_EDEFAULT;

  /**
   * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMapping()
   * @generated
   * @ordered
   */
  protected EMapping mapping;

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
  protected EComparisonImpl() {
    super();
    Map<Role, Set<Object>> duplicateIDs = new HashMap<Role, Set<Object>>(3);
    duplicateIDs.put(Role.ANCESTOR, new HashSet<Object>(0));
    duplicateIDs.put(Role.TARGET, new HashSet<Object>(0));
    duplicateIDs.put(Role.REFERENCE, new HashSet<Object>(0));
    _duplicateIDs = Collections.unmodifiableMap(duplicateIDs);
  }

  /**
   * Simplified constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableModelScope targetScope_p,
      IEditableModelScope referenceScope_p) {
    this(targetScope_p, referenceScope_p, null);
  }

  /**
   * Full constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @param ancestorScope_p the optional model scope playing the ANCESTOR comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableModelScope targetScope_p,
      IEditableModelScope referenceScope_p, IEditableModelScope ancestorScope_p) {
    this();
    targetScope = targetScope_p;
    referenceScope = referenceScope_p;
    ancestorScope = ancestorScope_p;
    setMapping(new EMappingImpl());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffdataPackage.Literals.ECOMPARISON;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableModelScope getAncestorScope() {
    return ancestorScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAncestorScope(IEditableModelScope newAncestorScope) {
    IEditableModelScope oldAncestorScope = ancestorScope;
    ancestorScope = newAncestorScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE, oldAncestorScope,
          ancestorScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableModelScope getReferenceScope() {
    return referenceScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferenceScope(IEditableModelScope newReferenceScope) {
    IEditableModelScope oldReferenceScope = referenceScope;
    referenceScope = newReferenceScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.ECOMPARISON__REFERENCE_SCOPE, oldReferenceScope,
          referenceScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableModelScope getTargetScope() {
    return targetScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetScope(IEditableModelScope newTargetScope) {
    IEditableModelScope oldTargetScope = targetScope;
    targetScope = newTargetScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.ECOMPARISON__TARGET_SCOPE, oldTargetScope,
          targetScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMatchPolicy getLastMatchPolicy() {
    return lastMatchPolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastMatchPolicy(IMatchPolicy newLastMatchPolicy) {
    IMatchPolicy oldLastMatchPolicy = lastMatchPolicy;
    lastMatchPolicy = newLastMatchPolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY, oldLastMatchPolicy,
          lastMatchPolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IDiffPolicy getLastDiffPolicy() {
    return lastDiffPolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastDiffPolicy(IDiffPolicy newLastDiffPolicy) {
    IDiffPolicy oldLastDiffPolicy = lastDiffPolicy;
    lastDiffPolicy = newLastDiffPolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY, oldLastDiffPolicy,
          lastDiffPolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMergePolicy getLastMergePolicy() {
    return lastMergePolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastMergePolicy(IMergePolicy newLastMergePolicy) {
    IMergePolicy oldLastMergePolicy = lastMergePolicy;
    lastMergePolicy = newLastMergePolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY, oldLastMergePolicy,
          lastMergePolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMapping getMapping() {
    return mapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public NotificationChain basicSetMapping(EMapping newMapping,
      NotificationChain msgs) {
    NotificationChain result = msgs;
    EMapping oldMapping = mapping;
    mapping = newMapping;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET, DiffdataPackage.ECOMPARISON__MAPPING, oldMapping,
          newMapping);
      if (result == null)
        result = notification;
      else
        result.add(notification);
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMapping(EMapping newMapping) {
    if (newMapping != mapping) {
      NotificationChain msgs = null;
      if (mapping != null)
        msgs = ((InternalEObject) mapping).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - DiffdataPackage.ECOMPARISON__MAPPING,
            null, msgs);
      if (newMapping != null)
        msgs = ((InternalEObject) newMapping).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - DiffdataPackage.ECOMPARISON__MAPPING,
            null, msgs);
      msgs = basicSetMapping(newMapping, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          DiffdataPackage.ECOMPARISON__MAPPING, newMapping, newMapping));
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
    case DiffdataPackage.ECOMPARISON__MAPPING:
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
    case DiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      return getAncestorScope();
    case DiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      return getReferenceScope();
    case DiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      return getTargetScope();
    case DiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      return getLastMatchPolicy();
    case DiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      return getLastDiffPolicy();
    case DiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      return getLastMergePolicy();
    case DiffdataPackage.ECOMPARISON__MAPPING:
      return getMapping();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case DiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      setAncestorScope((IEditableModelScope) newValue);
      return;
    case DiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      setReferenceScope((IEditableModelScope) newValue);
      return;
    case DiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      setTargetScope((IEditableModelScope) newValue);
      return;
    case DiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      setLastMatchPolicy((IMatchPolicy) newValue);
      return;
    case DiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      setLastDiffPolicy((IDiffPolicy) newValue);
      return;
    case DiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      setLastMergePolicy((IMergePolicy) newValue);
      return;
    case DiffdataPackage.ECOMPARISON__MAPPING:
      setMapping((EMapping) newValue);
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
    case DiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      setAncestorScope(ANCESTOR_SCOPE_EDEFAULT);
      return;
    case DiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      setReferenceScope(REFERENCE_SCOPE_EDEFAULT);
      return;
    case DiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      setTargetScope(TARGET_SCOPE_EDEFAULT);
      return;
    case DiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      setLastMatchPolicy(LAST_MATCH_POLICY_EDEFAULT);
      return;
    case DiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      setLastDiffPolicy(LAST_DIFF_POLICY_EDEFAULT);
      return;
    case DiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      setLastMergePolicy(LAST_MERGE_POLICY_EDEFAULT);
      return;
    case DiffdataPackage.ECOMPARISON__MAPPING:
      setMapping((EMapping) null);
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
    case DiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      return ANCESTOR_SCOPE_EDEFAULT == null ? ancestorScope != null
          : !ANCESTOR_SCOPE_EDEFAULT.equals(ancestorScope);
    case DiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      return REFERENCE_SCOPE_EDEFAULT == null ? referenceScope != null
          : !REFERENCE_SCOPE_EDEFAULT.equals(referenceScope);
    case DiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      return TARGET_SCOPE_EDEFAULT == null ? targetScope != null
          : !TARGET_SCOPE_EDEFAULT.equals(targetScope);
    case DiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      return LAST_MATCH_POLICY_EDEFAULT == null ? lastMatchPolicy != null
          : !LAST_MATCH_POLICY_EDEFAULT.equals(lastMatchPolicy);
    case DiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      return LAST_DIFF_POLICY_EDEFAULT == null ? lastDiffPolicy != null
          : !LAST_DIFF_POLICY_EDEFAULT.equals(lastDiffPolicy);
    case DiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      return LAST_MERGE_POLICY_EDEFAULT == null ? lastMergePolicy != null
          : !LAST_MERGE_POLICY_EDEFAULT.equals(lastMergePolicy);
    case DiffdataPackage.ECOMPARISON__MAPPING:
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

    StringBuffer result = new StringBuffer(super.toString());
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
   * @see org.eclipse.emf.diffmerge.api.IComparison#clear()
   * @generated NOT
   */
  public void clear() {
    ((IMapping.Editable) getMapping()).clear();
    setLastMatchPolicy(null);
    setLastDiffPolicy(null);
    setLastMergePolicy(null);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#compute(org.eclipse.emf.diffmerge.api.IMatchPolicy, org.eclipse.emf.diffmerge.api.IDiffPolicy, org.eclipse.emf.diffmerge.api.IMergePolicy, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public IStatus compute(IMatchPolicy matchPolicy_p, IDiffPolicy diffPolicy_p,
      IMergePolicy mergePolicy_p, IProgressMonitor monitor_p) {
    // Monitor
    IProgressMonitor nonNullMonitor = monitor_p != null ? monitor_p
        : new NullProgressMonitor();
    SubMonitor subMonitor = SubMonitor.convert(nonNullMonitor,
        Messages.Comparison_Task_Main, 2);
    // Policies
    setLastMatchPolicy(matchPolicy_p != null ? matchPolicy_p
        : new DefaultMatchPolicy());
    setLastDiffPolicy(diffPolicy_p != null ? diffPolicy_p
        : new DefaultDiffPolicy());
    setLastMergePolicy(mergePolicy_p != null ? mergePolicy_p
        : new DefaultMergePolicy());
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
  protected IStatus computeMatch(IMatchPolicy matchPolicy_p,
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
  protected IStatus computeDiff(IDiffPolicy diffPolicy_p,
      IMergePolicy mergePolicy_p, IProgressMonitor monitor_p) {
    IExpensiveOperation diffOperation = getDiffOperation(diffPolicy_p,
        mergePolicy_p);
    IStatus result = diffOperation.run(monitor_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getAllContents(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  @SuppressWarnings("serial")
  public TreeIterator<IMatch> getAllContents(final Role role_p) {
    return new AbstractTreeIterator<IMatch>(this, false) {
      /**
       * @see org.eclipse.emf.common.util.AbstractTreeIterator#getChildren(Object)
       */
      @Override
      protected Iterator<? extends IMatch> getChildren(Object object_p) {
        Iterator<? extends IMatch> result;
        if (object_p instanceof IComparison)
          result = ((IComparison) object_p).getContents(role_p).iterator();
        else
          result = getContentsOf((IMatch) object_p, role_p).iterator();
        return result;
      }
    };
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getContainerOf(org.eclipse.emf.diffmerge.api.IMatch, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IMatch getContainerOf(IMatch match_p, Role role_p) {
    IMatch result = null;
    EObject child = match_p.get(role_p);
    if (child != null) {
      IModelScope scope = getScope(role_p);
      EObject container = scope.getContainer(child);
      if (container != null) {
        result = getMapping().getMatchFor(container, role_p);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getContents()
   * @generated NOT
   */
  public List<IMatch> getContents() {
    List<IMatch> targetMatches = getContents(Role.TARGET);
    List<IMatch> referenceMatches = getContents(Role.REFERENCE);
    List<IMatch> result = new FArrayList<IMatch>(referenceMatches, null);
    for (IMatch targetMatch : targetMatches) {
      if (!result.contains(targetMatch))
        result.add(targetMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getContents(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public List<IMatch> getContents(Role role_p) {
    List<IMatch> result = new FArrayList<IMatch>();
    // Defining 'contents': list of elements whose matches must be retrieved
    List<EObject> contents = getScope(role_p).getContents();
    // Retrieving matches
    for (EObject child : contents) {
      IMatch childMatch = getMapping().getMatchFor(child, role_p);
      if (childMatch != null)
        result.add(childMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getContentsOf(org.eclipse.emf.diffmerge.api.IMatch)
   * @generated NOT
   */
  public List<IMatch> getContentsOf(IMatch match_p) {
    List<IMatch> targetMatches = getContentsOf(match_p, Role.TARGET);
    List<IMatch> referenceMatches = getContentsOf(match_p, Role.REFERENCE);
    List<IMatch> result = new FOrderedSet<IMatch>(referenceMatches, null);
    for (IMatch targetMatch : targetMatches) {
      result.add(targetMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getContentsOf(org.eclipse.emf.diffmerge.api.IMatch, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public List<IMatch> getContentsOf(IMatch match_p, Role role_p) {
    List<IMatch> result = new FArrayList<IMatch>();
    // Defining 'contents': list of elements whose matches must be retrieved
    List<EObject> contents = null;
    EObject container = match_p.get(role_p);
    if (container != null) {
      IModelScope scope = getScope(role_p);
      contents = scope.getContents(container);
    }
    // Retrieving matches
    if (contents != null) {
      for (EObject child : contents) {
        IMatch childMatch = getMapping().getMatchFor(child, role_p);
        if (childMatch != null)
          result.add(childMatch);
      }
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getDifferences(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public List<IDifference> getDifferences(Role role_p) {
    List<IDifference> result = new FArrayList<IDifference>();
    Iterator<IMatch> it = getAllContents(role_p);
    while (it.hasNext()) {
      IMatch current = it.next();
      result.addAll(current.getPresenceDifferencesIn(role_p));
    }
    return result;
  }

  /**
   * Return an operation for executing the Diff phase
   * @param diffPolicy_p an optional diff policy
   * @param mergePolicy_p an optional merge policy
   * @return a non-null operation which is configured to be applied on the given comparison data
   * @generated NOT
   */
  protected IExpensiveOperation getDiffOperation(IDiffPolicy diffPolicy_p,
      IMergePolicy mergePolicy_p) {
    return new DiffOperation(this, diffPolicy_p, mergePolicy_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getDuplicateMatchIDs(org.eclipse.emf.diffmerge.api.Role)
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
  protected IExpensiveOperation getMatchOperation(IMatchPolicy policy_p,
      Map<Role, Set<Object>> duplicateIDs_p) {
    return new MatchOperation(this, policy_p, duplicateIDs_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getNbDifferences()
   * @generated NOT
   */
  public int getNbDifferences() {
    int result = 0;
    for (IMatch match : getMapping().getContents())
      result += match.getRelatedDifferences().size();
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getNbProperElementDifferences()
   * @generated NOT
   */
  public int getNbProperElementDifferences() {
    int result = 0;
    for (IMatch match : getMapping().getContents())
      result += match.getNbProperElementDifferences();
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getRemainingDifferences()
   * @generated NOT
   */
  public Collection<IDifference> getRemainingDifferences() {
    Collection<IDifference> result = new FHashSet<IDifference>(
        IEqualityTester.BY_EQUALS);
    for (IMatch match : getMapping().getContents()) {
      for (IDifference difference : match.getAllDifferences()) {
        if (!difference.isMerged())
          result.add(difference);
      }
    }
    return Collections.unmodifiableCollection(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#getScope(org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IEditableModelScope getScope(Role role_p) {
    IEditableModelScope result;
    switch (role_p) {
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
   * @see org.eclipse.emf.diffmerge.api.IComparison#hasRemainingDifferences()
   * @generated NOT
   */
  public boolean hasRemainingDifferences() {
    for (IMatch match : getMapping().getContents()) {
      for (IDifference difference : match.getAllDifferences()) {
        if (!difference.isMerged())
          return true;
      }
    }
    return false;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#isConsistent()
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
   * @see org.eclipse.emf.diffmerge.api.IComparison#isThreeWay()
   * @generated NOT
   */
  public boolean isThreeWay() {
    return getAncestorScope() != null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#merge(org.eclipse.emf.diffmerge.api.Role, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated not
   */
  public Collection<IDifference> merge(final Role destination_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    Collection<IDifference> result = merge(new IMergeSelector() {
      /**
       * @see org.eclipse.emf.diffmerge.api.IMergeSelector#getMergeDirection(org.eclipse.emf.diffmerge.api.diff.IDifference)
       */
      public Role getMergeDirection(IDifference difference_p) {
        return destination_p;
      }
    }, updateReferences_p, monitor_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#merge(java.util.Collection, org.eclipse.emf.diffmerge.api.Role, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public Collection<IDifference> merge(
      Collection<? extends IDifference> differences_p, Role destination_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    MergeOperation operation = new MergeOperation(this, differences_p,
        destination_p, updateReferences_p);
    operation.run(monitor_p);
    return operation.getOutput();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison#merge(org.eclipse.emf.diffmerge.api.IMergeSelector, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public Collection<IDifference> merge(IMergeSelector merger_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    MergeOperation operation = new MergeOperation(this, merger_p,
        updateReferences_p);
    operation.run(monitor_p);
    return operation.getOutput();
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison.Editable#newAttributeValuePresence(org.eclipse.emf.diffmerge.api.IMatch, org.eclipse.emf.ecore.EAttribute, java.lang.Object, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IAttributeValuePresence newAttributeValuePresence(
      IMatch elementMatch_p, EAttribute attribute_p, Object value_p,
      Role presenceRole_p, boolean isOrder_p) {
    EAttributeValuePresence result = new EAttributeValuePresenceImpl(this,
        (EMatch) elementMatch_p, attribute_p, value_p, presenceRole_p,
        isOrder_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison.Editable#newElementPresence(org.eclipse.emf.diffmerge.api.IMatch, org.eclipse.emf.diffmerge.api.IMatch)
   * @generated NOT
   */
  public IElementPresence newElementPresence(IMatch elementMatch_p,
      IMatch ownerMatch_p) {
    return new EElementPresenceImpl(this, (EMatch) elementMatch_p,
        (EMatch) ownerMatch_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison.Editable#newMatch(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   * @generated NOT
   */
  public EMatch newMatch(EObject targetElement_p, EObject referenceElement_p,
      EObject ancestorElement_p) {
    return new EMatchImpl(targetElement_p, referenceElement_p,
        ancestorElement_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.api.IComparison.Editable#newReferenceValuePresence(org.eclipse.emf.diffmerge.api.IMatch, org.eclipse.emf.ecore.EReference, org.eclipse.emf.diffmerge.api.IMatch, org.eclipse.emf.diffmerge.api.Role)
   * @generated NOT
   */
  public IReferenceValuePresence newReferenceValuePresence(
      IMatch elementMatch_p, EReference reference_p, IMatch valueMatch_p,
      Role presenceRole_p, boolean isOrder_p) {
    EReferenceValuePresence result = new EReferenceValuePresenceImpl(this,
        (EMatch) elementMatch_p, reference_p, (EMatch) valueMatch_p,
        presenceRole_p, isOrder_p);
    return result;
  }

} //EComparisonImpl
