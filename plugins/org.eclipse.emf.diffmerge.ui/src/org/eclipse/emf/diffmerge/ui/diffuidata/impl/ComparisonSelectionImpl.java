/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.diffuidata.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.EMergeableDifference;
import org.eclipse.emf.diffmerge.diffdata.EValuePresence;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.UidiffdataPackage;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.jface.viewers.TreePath;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comparison Selection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl#getDiffNode <em>Diff Node</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl#getSelectedMatches <em>Selected Matches</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl#getSelectedMatchAndFeature <em>Selected Match And Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl#getSelectedTreePath <em>Selected Tree Path</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.ui.diffuidata.impl.ComparisonSelectionImpl#getSelectedValuePresences <em>Selected Value Presences</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComparisonSelectionImpl extends EObjectImpl
    implements ComparisonSelection {

  /**
   * The default value of the '{@link #getDiffNode() <em>Diff Node</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDiffNode()
   * @generated
   * @ordered
   */
  protected static final EMFDiffNode DIFF_NODE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDiffNode() <em>Diff Node</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDiffNode()
   * @generated
   * @ordered
   */
  protected EMFDiffNode diffNode = DIFF_NODE_EDEFAULT;

  /**
   * The cached value of the '{@link #getSelectedMatches() <em>Selected Matches</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectedMatches()
   * @generated
   * @ordered
   */
  protected EList<EMatch> selectedMatches;

  /**
   * The (initially null) set of differences to merge according to this selection
   * @generated NOT
   */
  private EList<EMergeableDifference> _differences;

  /**
   * The (initially null) set of elements concerned with differences to merge
   * @generated NOT
   */
  private EList<EObject> _concernedElements;

  /**
   * The optional role for resolving ambiguities in selected full matches
   * @generated NOT
   */
  private Role _preferredSide;

  /**
   * The cached value of the '{@link #getSelectedMatchAndFeature() <em>Selected Match And Feature</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectedMatchAndFeature()
   * @generated
   * @ordered
   */
  protected MatchAndFeature selectedMatchAndFeature;

  /**
   * The cached value of the '{@link #getSelectedTreePath() <em>Selected Tree Path</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectedTreePath()
   * @generated
   * @ordered
   */
  protected EList<EMatch> selectedTreePath;

  /**
   * The cached value of the '{@link #getSelectedValuePresences() <em>Selected Value Presences</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectedValuePresences()
   * @generated
   * @ordered
   */
  protected EList<EValuePresence> selectedValuePresences;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComparisonSelectionImpl() {
    super();
  }

  /**
   * Constructor
   * @param selected_p a potentially null object
   * @param preferredSide_p an optional role for resolving selection ambiguities in full matches
   * @param diffNode_p a non-null diff node that is the context in which this selection is defined
   * @generated NOT
   */
  public ComparisonSelectionImpl(Object selected_p, Role preferredSide_p,
      EMFDiffNode diffNode_p) {
    _preferredSide = preferredSide_p;
    diffNode = diffNode_p;
    if (selected_p instanceof EMatch) {
      getSelectedMatches().add((EMatch) selected_p);
    } else if (selected_p instanceof MatchAndFeature) {
      selectedMatchAndFeature = (MatchAndFeature) selected_p;
    } else if (selected_p instanceof TreePath) {
      TreePath path = (TreePath) selected_p;
      for (int i = 0; i < path.getSegmentCount(); i++) {
        getSelectedTreePath().add((EMatch) path.getSegment(i));
      }
    } else if (selected_p instanceof EValuePresence) {
      getSelectedValuePresences().add((EValuePresence) selected_p);
    } else if (selected_p instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) selected_p;
      if (!collection.isEmpty()) {
        List<EMatch> matches = new ArrayList<EMatch>();
        List<EValuePresence> presences = new ArrayList<EValuePresence>();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
          Object current = it.next();
          if (current instanceof EValuePresence)
            presences.add((EValuePresence) current);
          else if (current instanceof EMatch)
            matches.add((EMatch) current);
        }
        if (!presences.isEmpty()) {
          getSelectedValuePresences().addAll(presences);
        } else {
          getSelectedMatches().addAll(matches);
        }
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DiffuidataPackage.Literals.COMPARISON_SELECTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMFDiffNode getDiffNode() {
    return diffNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EMatch> getSelectedMatches() {
    if (selectedMatches == null) {
      selectedMatches = new EObjectResolvingEList<EMatch>(EMatch.class, this,
          DiffuidataPackage.COMPARISON_SELECTION__SELECTED_MATCHES);
    }
    return selectedMatches;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MatchAndFeature getSelectedMatchAndFeature() {
    return selectedMatchAndFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public NotificationChain basicSetSelectedMatchAndFeature(
      MatchAndFeature newSelectedMatchAndFeature, NotificationChain msgs_p) {
    NotificationChain msgs = msgs_p;
    MatchAndFeature oldSelectedMatchAndFeature = selectedMatchAndFeature;
    selectedMatchAndFeature = newSelectedMatchAndFeature;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET,
          UidiffdataPackage.COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE,
          oldSelectedMatchAndFeature, newSelectedMatchAndFeature);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EMatch> getSelectedTreePath() {
    if (selectedTreePath == null) {
      selectedTreePath = new EObjectResolvingEList<EMatch>(EMatch.class, this,
          DiffuidataPackage.COMPARISON_SELECTION__SELECTED_TREE_PATH);
    }
    return selectedTreePath;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EValuePresence> getSelectedValuePresences() {
    if (selectedValuePresences == null) {
      selectedValuePresences = new EObjectResolvingEList<EValuePresence>(
          EValuePresence.class, this,
          DiffuidataPackage.COMPARISON_SELECTION__SELECTED_VALUE_PRESENCES);
    }
    return selectedValuePresences;
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
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE:
      return basicSetSelectedMatchAndFeature(null, msgs);
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
    case DiffuidataPackage.COMPARISON_SELECTION__DIFF_NODE:
      return getDiffNode();
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_MATCHES:
      return getSelectedMatches();
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE:
      return getSelectedMatchAndFeature();
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_TREE_PATH:
      return getSelectedTreePath();
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_VALUE_PRESENCES:
      return getSelectedValuePresences();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case DiffuidataPackage.COMPARISON_SELECTION__DIFF_NODE:
      return DIFF_NODE_EDEFAULT == null ? diffNode != null
          : !DIFF_NODE_EDEFAULT.equals(diffNode);
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_MATCHES:
      return selectedMatches != null && !selectedMatches.isEmpty();
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_MATCH_AND_FEATURE:
      return selectedMatchAndFeature != null;
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_TREE_PATH:
      return selectedTreePath != null && !selectedTreePath.isEmpty();
    case DiffuidataPackage.COMPARISON_SELECTION__SELECTED_VALUE_PRESENCES:
      return selectedValuePresences != null
          && !selectedValuePresences.isEmpty();
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
    result.append(" (diffNode: "); //$NON-NLS-1$
    result.append(diffNode);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asDifferencesToMerge()
   * @generated NOT
   */
  public EList<EMergeableDifference> asDifferencesToMerge() {
    if (_differences == null)
      _differences = getDifferencesToMerge();
    return ECollections.unmodifiableEList(_differences);
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asFeature()
   * @generated NOT
   */
  public EStructuralFeature asFeature() {
    EStructuralFeature result = null;
    if (getSelectedMatchAndFeature() != null) {
      result = getSelectedMatchAndFeature().getFeature();
    } else {
      EValuePresence presence = asValuePresence();
      if (presence != null) {
        if (representAsOwnership(presence))
          result = EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature();
        else
          result = presence.getFeature();
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asMatchAndFeature()
   */
  public MatchAndFeature asMatchAndFeature() {
    return selectedMatchAndFeature;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asMatch()
   * @generated NOT
   */
  public EMatch asMatch() {
    EMatch result = null;
    if (!getSelectedMatches().isEmpty()) {
      result = getSelectedMatches().get(0);
    } else if (!getSelectedTreePath().isEmpty()) {
      result = getSelectedTreePath().get(getSelectedTreePath().size() - 1);
    } else if (getSelectedMatchAndFeature() != null) {
      result = getSelectedMatchAndFeature().getMatch();
    } else if (!getSelectedValuePresences().isEmpty()) {
      EValuePresence presence = asValuePresence();
      if (representAsOwnership(presence))
        result = (EMatch) ((IReferenceValuePresence) presence).getValueMatch();
      else
        result = presence.getElementMatch();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asMatches()
   * @generated NOT
   */
  public EList<EMatch> asMatches() {
    EList<EMatch> result = ECollections.emptyEList();
    if (!getSelectedMatches().isEmpty()) {
      result = getSelectedMatches();
    } else if (!getSelectedValuePresences().isEmpty()) {
      result = new FOrderedSet<EMatch>();
      for (EValuePresence valuePresence : getSelectedValuePresences()) {
        EMatch match = valuePresence.getElementMatch();
        if (match != null)
          result.add(match);
      }
    } else if (getSelectedMatchAndFeature() != null
        || getSelectedTreePath() != null) {
      result = new BasicEList<EMatch>(1);
      EMatch match = asMatch();
      if (match != null)
        result.add(match);
    }
    return ECollections.unmodifiableEList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asMatchPath()
   * @generated NOT
   */
  public TreePath asMatchPath() {
    TreePath result = null;
    if (!getSelectedTreePath().isEmpty())
      result = new TreePath(getSelectedTreePath().toArray());
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asValuePresences()
   * @generated NOT
   */
  public EList<EValuePresence> asValuePresences() {
    return getSelectedValuePresences();
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asValuePresence()
   * @generated NOT
   */
  public EValuePresence asValuePresence() {
    EValuePresence result = null;
    if (!getSelectedValuePresences().isEmpty())
      result = getSelectedValuePresences().get(0);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#dispose()
   * @generated NOT
   */
  public void dispose() {
    if (_concernedElements != null)
      _concernedElements.clear();
    if (_differences != null)
      _differences.clear();
    diffNode = null;
    selectedMatchAndFeature = null;
    if (selectedMatches != null)
      selectedMatches.clear();
    if (selectedTreePath != null)
      selectedTreePath.clear();
    if (selectedValuePresences != null)
      selectedValuePresences.clear();
  }

  /**
   * Return the model elements concerned by this selection
   * @return a non-null, potentially empty collection
   * @generated NOT
   */
  private EList<EObject> getConcernedElements() {
    EList<EObject> result = new FOrderedSet<EObject>();
    List<EMatch> matches = asMatches();
    if (matches.isEmpty() || _preferredSide == null) {
      for (EMergeableDifference difference : asDifferencesToMerge()) {
        if (difference instanceof IElementRelativePresence) {
          IElementRelativePresence elementDifference = (IElementRelativePresence) difference;
          IMatch match = elementDifference.getElementMatch();
          if (match != null) {
            EObject element = match.get(elementDifference.getPresenceRole());
            result.add(element);
          }
        }
      }
    } else {
      for (EMatch match : matches) {
        EObject element = match.get(_preferredSide);
        if (element != null)
          result.add(element);
      }
      if (result.isEmpty()) {
        for (EMatch match : matches) {
          EObject element = match.get(_preferredSide.opposite());
          if (element != null)
            result.add(element);
        }
      }
    }
    return result;
  }

  /**
   * Return the differences to merge according to this selection
   * @return a non-null, potentially empty collection
   * @generated NOT
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private EList<EMergeableDifference> getDifferencesToMerge() {
    EList<EMergeableDifference> result = new FArrayList<EMergeableDifference>();
    if (!asValuePresences().isEmpty()) {
      result.addAll(asValuePresences());
    } else if (!getSelectedMatches().isEmpty()) {
      for (EMatch match : getSelectedMatches()) {
        result.addAll((Collection) match.getAllDifferences());
      }
    } else {
      EValuePresence presence = asValuePresence();
      if (presence != null) {
        // Value presence
        result.add(presence);
      } else {
        IMatch match = asMatch();
        EStructuralFeature feature = asFeature();
        if (match != null && feature != null) {
          // All differences of a given feature on a given match
          if (feature instanceof EAttribute)
            result.addAll((Collection) match
                .getAttributeDifferences((EAttribute) feature));
          else
            result.addAll((Collection) match
                .getReferenceDifferences((EReference) feature));
        } else if (match != null) {
          // All differences on a given match
          result.addAll((Collection) match.getAllDifferences());
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredSelection#getFirstElement()
   * @generated NOT
   */
  public EObject getFirstElement() {
    List<EObject> list = toList();
    return list.isEmpty() ? null : list.get(0);
  }

  /**
   * @see org.eclipse.jface.viewers.ISelection#isEmpty()
   * @generated NOT
   */
  public boolean isEmpty() {
    return getSelectedMatches().isEmpty()
        && getSelectedMatchAndFeature() == null
        && getSelectedTreePath().isEmpty()
        && getSelectedValuePresences().isEmpty();
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredSelection#iterator()
   * @generated NOT
   */
  public Iterator<EObject> iterator() {
    return toList().iterator();
  }

  /**
   * Return whether the given difference is represented as a virtual "ownership"
   * (an opposite to a containment value presence)
   * @param difference_p a potentially null difference
   * @generated NOT
   */
  private boolean representAsOwnership(IDifference difference_p) {
    boolean result = false;
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence presence = (IReferenceValuePresence) difference_p;
      result = presence.isOwnership();
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredSelection#size()
   * @generated NOT
   */
  public int size() {
    return toList().size();
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredSelection#toArray()
   * @generated NOT
   */
  public Object[] toArray() {
    return toList().toArray();
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredSelection#toList()
   * @generated NOT
   */
  public List<EObject> toList() {
    if (_concernedElements == null)
      _concernedElements = getConcernedElements();
    return ECollections.unmodifiableEList(_concernedElements);
  }

} //ComparisonSelectionImpl
