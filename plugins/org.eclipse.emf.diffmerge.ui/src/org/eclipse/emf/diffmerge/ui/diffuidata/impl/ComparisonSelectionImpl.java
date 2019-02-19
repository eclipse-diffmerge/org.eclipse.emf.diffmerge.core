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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection;
import org.eclipse.emf.diffmerge.ui.diffuidata.DiffuidataPackage;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.UidiffdataPackage;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
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
  protected EList<IMatch<?>> selectedMatches;

  /**
   * The (initially null) set of differences to merge according to this selection
   * @generated NOT
   */
  protected EList<IDifference<?>> _differences;

  /**
   * The (initially null) set of elements concerned with differences to merge
   * @generated NOT
   */
  protected EList<Object> _concernedElements;

  /**
   * The optional role for resolving ambiguities in selected full matches
   * @generated NOT
   */
  protected Role _preferredSide;

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
  protected EList<IMatch<?>> selectedTreePath;

  /**
   * The cached value of the '{@link #getSelectedValuePresences() <em>Selected Value Presences</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectedValuePresences()
   * @generated
   * @ordered
   */
  protected EList<IValuePresence<?>> selectedValuePresences;

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
    if (selected_p instanceof IMatch) {
      getSelectedMatches().add((IMatch<?>) selected_p);
    } else if (selected_p instanceof MatchAndFeature) {
      selectedMatchAndFeature = (MatchAndFeature) selected_p;
    } else if (selected_p instanceof TreePath) {
      TreePath path = (TreePath) selected_p;
      for (int i = 0; i < path.getSegmentCount(); i++) {
        getSelectedTreePath().add((IMatch<?>) path.getSegment(i));
      }
    } else if (selected_p instanceof IValuePresence) {
      getSelectedValuePresences().add((IValuePresence<?>) selected_p);
    } else if (selected_p instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) selected_p;
      if (!collection.isEmpty()) {
        List<IMatch<?>> matches = new ArrayList<IMatch<?>>();
        List<IValuePresence<?>> presences = new ArrayList<IValuePresence<?>>();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
          Object current = it.next();
          if (current instanceof IValuePresence) {
            presences.add((IValuePresence<?>) current);
          } else if (current instanceof IMatch) {
            matches.add((IMatch<?>) current);
          }
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
  public EList<IMatch<?>> getSelectedMatches() {
    if (selectedMatches == null) {
      selectedMatches = new EObjectResolvingEList<IMatch<?>>(IMatch.class, this,
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
  public EList<IMatch<?>> getSelectedTreePath() {
    if (selectedTreePath == null) {
      selectedTreePath = new EObjectResolvingEList<IMatch<?>>(IMatch.class,
          this, DiffuidataPackage.COMPARISON_SELECTION__SELECTED_TREE_PATH);
    }
    return selectedTreePath;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IValuePresence<?>> getSelectedValuePresences() {
    if (selectedValuePresences == null) {
      selectedValuePresences = new EObjectResolvingEList<IValuePresence<?>>(
          IValuePresence.class, this,
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
  public EList<IDifference<?>> asDifferencesToMerge() {
    if (_differences == null) {
      _differences = getDifferencesToMerge();
    }
    return ECollections.unmodifiableEList(_differences);
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asFeature()
   * @generated NOT
   */
  public Object asFeature() {
    Object result = null;
    if (getSelectedMatchAndFeature() != null) {
      result = getSelectedMatchAndFeature().getFeature();
    } else {
      IValuePresence<?> presence = asValuePresence();
      if (presence != null) {
        if (representAsOwnership(presence)) {
          result = EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature();
        } else {
          result = presence.getFeature();
        }
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
  public IMatch<?> asMatch() {
    IMatch<?> result = null;
    if (!getSelectedMatches().isEmpty()) {
      result = getSelectedMatches().get(0);
    } else if (!getSelectedTreePath().isEmpty()) {
      result = getSelectedTreePath().get(getSelectedTreePath().size() - 1);
    } else if (getSelectedMatchAndFeature() != null) {
      result = getSelectedMatchAndFeature().getMatch();
    } else if (!getSelectedValuePresences().isEmpty()) {
      IValuePresence<?> presence = asValuePresence();
      if (representAsOwnership(presence)) {
        result = ((IReferenceValuePresence<?>) presence).getValueMatch();
      } else {
        result = presence.getElementMatch();
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asMatches()
   * @generated NOT
   */
  public EList<IMatch<?>> asMatches() {
    EList<IMatch<?>> result = ECollections.emptyEList();
    if (!getSelectedMatches().isEmpty()) {
      result = getSelectedMatches();
    } else if (!getSelectedValuePresences().isEmpty()) {
      result = new FOrderedSet<IMatch<?>>();
      for (IValuePresence<?> valuePresence : getSelectedValuePresences()) {
        IMatch<?> match = valuePresence.getElementMatch();
        if (match != null) {
          result.add(match);
        }
      }
    } else if (getSelectedMatchAndFeature() != null
        || getSelectedTreePath() != null) {
      result = new BasicEList<IMatch<?>>(1);
      IMatch<?> match = asMatch();
      if (match != null) {
        result.add(match);
      }
    }
    return ECollections.unmodifiableEList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asMatchPath()
   * @generated NOT
   */
  public TreePath asMatchPath() {
    TreePath result = null;
    if (!getSelectedTreePath().isEmpty()) {
      result = new TreePath(getSelectedTreePath().toArray());
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asValuePresences()
   * @generated NOT
   */
  public EList<IValuePresence<?>> asValuePresences() {
    return getSelectedValuePresences();
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#asValuePresence()
   * @generated NOT
   */
  public IValuePresence<?> asValuePresence() {
    IValuePresence<?> result = null;
    if (!getSelectedValuePresences().isEmpty()) {
      result = getSelectedValuePresences().get(0);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.ui.diffuidata.ComparisonSelection#dispose()
   * @generated NOT
   */
  public void dispose() {
    if (_concernedElements != null) {
      _concernedElements.clear();
    }
    if (_differences != null) {
      _differences.clear();
    }
    diffNode = null;
    selectedMatchAndFeature = null;
    if (selectedMatches != null) {
      selectedMatches.clear();
    }
    if (selectedTreePath != null) {
      selectedTreePath.clear();
    }
    if (selectedValuePresences != null) {
      selectedValuePresences.clear();
    }
  }

  /**
   * Return the model elements concerned by this selection
   * @return a non-null, potentially empty collection
   * @generated NOT
   */
  protected EList<Object> getConcernedElements() {
    EList<Object> result = new FOrderedSet<Object>();
    List<IMatch<?>> matches = asMatches();
    if (matches.isEmpty()) {
      for (IDifference<?> difference : asDifferencesToMerge()) {
        if (difference instanceof IElementRelativePresence) {
          IElementRelativePresence<?> elementDifference = (IElementRelativePresence<?>) difference;
          IMatch<?> match = elementDifference.getElementMatch();
          if (match != null) {
            Object element = match.get(elementDifference.getPresenceRole());
            result.add(element);
          }
        }
      }
    } else {
      Role firstSide = getPreferredSide();
      for (IMatch<?> match : matches) {
        Object element = match.get(firstSide);
        if (element != null) {
          result.add(element);
        }
      }
      if (result.isEmpty()) {
        for (IMatch<?> match : matches) {
          Object element = match.get(firstSide.opposite());
          if (element != null) {
            result.add(element);
          }
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
  protected EList<IDifference<?>> getDifferencesToMerge() {
    EList<IDifference<?>> result = new FArrayList<IDifference<?>>();
    if (!asValuePresences().isEmpty()) {
      result.addAll(asValuePresences());
    } else if (!getSelectedMatches().isEmpty()) {
      for (IMatch<?> match : getSelectedMatches()) {
        result.addAll(match.getAllDifferences());
      }
    } else {
      IValuePresence<?> presence = asValuePresence();
      if (presence != null) {
        // Value presence
        result.add(presence);
      } else {
        IMatch<?> match = asMatch();
        Object feature = asFeature();
        if (match != null && feature != null) {
          // All differences of a given feature on a given match
          if (feature instanceof EAttribute) {
            result.addAll(match.getAttributeDifferences(feature));
          } else {
            result.addAll(match.getReferenceDifferences(feature));
          }
        } else if (match != null) {
          // All differences on a given match
          result.addAll(match.getAllDifferences());
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredSelection#getFirstElement()
   * @generated NOT
   */
  public Object getFirstElement() {
    List<Object> list = toList();
    return list.isEmpty() ? null : list.get(0);
  }

  /**
   * Return the preferred role when interpreting matches as elements
   * @return a non-null role
   * @generated NOT
   */
  protected Role getPreferredSide() {
    Role firstSide = _preferredSide;
    if (firstSide == null) {
      firstSide = getDiffNode().getTargetRole();
    }
    if (firstSide == null) {
      firstSide = getDiffNode().getDrivingRole();
    }
    return firstSide;
  }

  /**
   * @see org.eclipse.jface.viewers.ISelection#isEmpty()
   * @generated NOT
   */
  public boolean isEmpty() {
    return toList().isEmpty();
  }

  /**
   * @see org.eclipse.jface.viewers.IStructuredSelection#iterator()
   * @generated NOT
   */
  public Iterator<Object> iterator() {
    return toList().iterator();
  }

  /**
   * Return whether the given difference is represented as a virtual "ownership"
   * (an opposite to a containment value presence)
   * @param difference_p a potentially null difference
   * @generated NOT
   */
  protected boolean representAsOwnership(IMergeableDifference<?> difference_p) {
    boolean result = false;
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence<?> presence = (IReferenceValuePresence<?>) difference_p;
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
  public List<Object> toList() {
    if (_concernedElements == null) {
      _concernedElements = getConcernedElements();
    }
    return ECollections.unmodifiableEList(_concernedElements);
  }

  /**
   * Return a variant of the given element-based selection as a
   * list of matches through the given diff node
   * @param selection_p a non-null, potentially empty selection
   * @param diffNode_p a non-null diff node
   * @return a non-null, potentially empty selection
   */
  public static List<IMatch<?>> selectionToMatches(
      IStructuredSelection selection_p, EMFDiffNode diffNode_p) {
    return selectionToMatches(selection_p, diffNode_p,
        diffNode_p.getDrivingRole());
  }

  /**
   * Return a variant of the given element-based selection as a
   * list of matches. Elements from the given side are converted
   * to their corresponding matches through the given diff node.
   * Other elements are converted from the opposite side if possible.
   * @param selection_p a non-null, potentially empty selection
   * @param diffNode_p a non-null diff node
   * @param role_p a non-null role
   * @return a non-null, potentially empty selection
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static List<IMatch<?>> selectionToMatches(
      IStructuredSelection selection_p, EMFDiffNode diffNode_p, Role role_p) {
    List<IMatch<?>> result = new FArrayList<IMatch<?>>();
    IComparison<?> comparison = diffNode_p.getActualComparison();
    if (comparison != null) {
      IMapping mapping = comparison.getMapping();
      for (Object selectedElement : selection_p.toArray()) {
        IMatch<?> match = mapping.getMatchFor(selectedElement, role_p);
        if (match == null) {
          match = mapping.getMatchFor(selectedElement, role_p.opposite());
        }
        if (match != null) {
          result.add(match);
        }
      }
    }
    return result;
  }

  /**
   * Return a variant of the given element-based selection as a match-based
   * tree path. The first element of the selection is converted to its
   * corresponding match and parent matches according to the scope hierarchy
   * through the given diff node. Other elements are ignored.
   * @param selection_p a non-null selection
   * @param diffNode_p a non-null diff node
   * @return a non-null, potentially empty selection
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static TreePath selectionToTreePath(IStructuredSelection selection_p,
      EMFDiffNode diffNode_p) {
    TreePath result = null;
    if (!selection_p.isEmpty()) {
      Role role = diffNode_p.getDrivingRole(); // Necessarily
      IStructuredSelection singletonSelection = selection_p.size() == 1
          ? selection_p
          : new StructuredSelection(selection_p.getFirstElement());
      List<IMatch<?>> convertedSelection = selectionToMatches(
          singletonSelection, diffNode_p, role);
      if (!convertedSelection.isEmpty()) {
        IMatch<?> selectedMatch = convertedSelection.get(0);
        IComparison<?> comparison = diffNode_p.getActualComparison();
        ITreeDataScope<?> scope = comparison.getScope(role);
        IMapping<?> mapping = comparison.getMapping();
        LinkedList<IMatch<?>> treePathContents = new LinkedList<IMatch<?>>();
        IMatch<?> treeNode = selectedMatch;
        while (treeNode != null) {
          treePathContents.addFirst(treeNode);
          Object element = treeNode.get(role);
          Object parent = (element == null) ? null
              : ((ITreeDataScope)scope).getContainer(element);
          treeNode = (parent == null) ? null
              : ((IMapping)mapping).getMatchFor(parent, role);
        }
        result = UIUtil.toTreePath(treePathContents);
      }
    }
    return result;
  }

} //ComparisonSelectionImpl
