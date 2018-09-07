/*********************************************************************
 * Copyright (c) 2017 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.model.integration;

import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedCopyInto;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.binary.qualified.AbstractQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FHashMap;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.diffmerge.structures.common.FLinkedList;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsFactory;
import org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge;
import org.eclipse.emf.diffmerge.structures.model.egraphs.ENode;


/**
 * An implementation of modifiable finite, qualified binary relations whose
 * internal state is defined as a graph.
 * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation
 *
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public class GraphQBinaryRelation<T, U, Q> extends AbstractQBinaryRelation<T, U, Q>
implements IQBinaryRelation.Invertible<T, U, Q> {
  
  /** The non-null internal state */
  protected final EGraph _state;
  
  /** The non-null (element -> node) cache. */
  protected final EMap<Object, ENode> _cache;
  
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public GraphQBinaryRelation() {
    this((IEqualityTester)null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  public GraphQBinaryRelation(IEqualityTester tester_p) {
    this(null, tester_p);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   * @param initialContents_p a potentially null initial state
   */
  public GraphQBinaryRelation(EGraph initialContents_p, IEqualityTester tester_p) {
    super(tester_p);
    _state = initialContents_p != null? initialContents_p:
      EGraphsFactory.eINSTANCE.createEGraph();
    _cache = new FHashMap<Object, ENode>(getEqualityTester());
    if (initialContents_p != null) {
      for (ENode node : initialContents_p.getContents()) {
        _cache.put(node.getElement(), node);
      }
    }
  }
  
  /**
   * Constructor
   * @param initialContents_p a non-null ranged qualified binary relation defining the
   *        initial contents of this relation
   */
  public GraphQBinaryRelation(
      IRangedQBinaryRelation<T, U, Q> initialContents_p) {
    this(initialContents_p.getEqualityTester());
    qualifiedCopyInto(initialContents_p, this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Editable#add(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean add(T source_p, U target_p, Q qualifier_p) {
    EHyperEdge edge = getCreateEdge(source_p, qualifier_p);
    ENode targetNode = getCreateNode(target_p);
    boolean result = edge.getTargets().add(targetNode);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Editable#addAll(java.lang.Object, java.util.Collection, java.lang.Object)
   */
  public boolean addAll(T source_p, Collection<? extends U> targets_p, Q qualifier_p) {
    EHyperEdge edge = getCreateEdge(source_p, qualifier_p);
    boolean result = false;
    for (U target : targets_p) {
      ENode targetNode = getCreateNode(target);
      boolean actuallyAdded = edge.getTargets().add(targetNode);
      result = result || actuallyAdded;
    }
    return result;
  }
  
  /**
   * Check whether the given edge must be removed after the removal
   * of at least one of its targets
   * @param node_p a non-null node
   * @return whether it was removed
   */
  protected boolean checkGarbage(EHyperEdge edge_p) {
    boolean result = false;
    if (edge_p.getTargets().isEmpty()) {
      result = true;
      ENode sourceNode = edge_p.getSource();
      edge_p.setSource(null);
      if (sourceNode != null)
        checkGarbage(sourceNode);
    }
    return result;
  }
  
  /**
   * Check whether the given node must be removed
   * @param node_p a non-null node
   * @return whether it was removed
   */
  protected boolean checkGarbage(ENode node_p) {
    boolean result = false;
    if (node_p.getIncoming().isEmpty() && node_p.getOutgoing().isEmpty()) {
      result = _state.getContents().remove(node_p);
      _cache.removeKey(node_p.getElement());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.IBinaryRelation.Editable#clear()
   */
  public void clear() {
    _state.getContents().clear();
    _cache.clear();
  }
  
  /**
   * Return, and create if needed, the outgoing edge for the given element through
   * the given qualifier
   * @param source_p a non-null element
   * @param qualifier_p a non-null qualifier
   * @return a non-null, potentially empty, modifiable collection for storing targets
   */
  protected EHyperEdge getCreateEdge(T source_p, Q qualifier_p) {
    ENode node = getCreateNode(source_p);
    EHyperEdge edge = getOutgoingEdge(node, qualifier_p);
    if (edge == null) {
      edge = EGraphsFactory.eINSTANCE.createEHyperEdge();
      edge.setLabel(qualifier_p.toString());
      edge.setSource(node);
    }
    return edge;
  }
  
  /**
   * Return, and create if needed, the node for the given element
   * @param element_p a non-null element
   * @return a non-null, potentially empty, modifiable collection for storing targets
   */
  protected ENode getCreateNode(Object element_p) {
    ENode result = _cache.get(element_p);
    if (result == null) {
      result = EGraphsFactory.eINSTANCE.createENode();
      result.setElement(element_p.toString());
      _state.getContents().add(result);
      _cache.put(element_p, result);
    }
    return result;
  }
  
  /**
   * Return the edge for the given qualifier on the given node, if any
   * @param node_p a non-null node
   * @param qualifier_p a potentially null qualifier
   * @return a potentially null edge
   */
  protected EHyperEdge getOutgoingEdge(ENode node_p, Q qualifier_p) {
    IEqualityTester tester = getEqualityTester();
    for (EHyperEdge edge : node_p.getOutgoing()) {
      if (tester.areEqual(qualifier_p, edge.getLabel()))
        return edge;
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Invertible#getInverse(java.lang.Object)
   */
  public Collection<T> getInverse(U element_p) {
    Collection<T> result;
    ENode node = _cache.get(element_p);
    if (node != null) {
      result = new FOrderedSet<T>(getEqualityTester());
      for (EHyperEdge edge : node.getIncoming()) {
        result.add((T)edge.getSource().getElement());
      }
      result = Collections.unmodifiableCollection(result);
    } else {
      result = Collections.emptySet();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Invertible#getInverse(java.lang.Object, java.lang.Object)
   */
  public Collection<T> getInverse(U element_p, Q qualifier_p) {
    Collection<T> result;
    IEqualityTester tester = getEqualityTester();
    ENode node = _cache.get(element_p);
    if (node != null) {
      result = new FOrderedSet<T>(tester);
      for (EHyperEdge edge : node.getIncoming()) {
        if (tester.areEqual(qualifier_p, edge.getLabel()))
          result.add((T)edge.getSource().getElement());
      }
      result = Collections.unmodifiableCollection(result);
    } else {
      result = Collections.emptySet();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Invertible#getInverseQualifiers(java.lang.Object)
   */
  public Collection<Q> getInverseQualifiers(U element_p) {
    Collection<Q> result;
    ENode node = _cache.get(element_p);
    if (node != null) {
      result = new FOrderedSet<Q>(getEqualityTester());
      for (EHyperEdge edge : node.getIncoming()) {
        result.add((Q)edge.getLabel());
      }
      result = Collections.unmodifiableCollection(result);
    } else {
      result = Collections.emptySet();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Invertible#getInverseWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<T>> getInverseWithDetails(U element_p) {
    IEqualityTester tester = getEqualityTester();
    EMap<Q, Collection<T>> result = new FHashMap<Q, Collection<T>>(tester);
    ENode node = _cache.get(element_p);
    if (node != null) {
      for (EHyperEdge edge : node.getIncoming()) {
        Q qualifier = (Q)edge.getLabel();
        Collection<T> sources = result.get(qualifier);
        if (sources == null) {
          sources = new FArrayList<T>(tester);
          result.put(qualifier, sources);
        }
        T source = (T)edge.getSource().getElement();
        sources.add(source);
      }
    }
    return Collections.unmodifiableMap(result.map());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IRangedQBinaryRelation#getQualifiers()
   */
  public Collection<Q> getQualifiers() {
    Set<Q> result = new FHashSet<Q>(getEqualityTester());
    for (ENode node : _state.getContents()) {
      result.addAll(getQualifiers((T)node.getElement()));
    }
    return Collections.unmodifiableSet(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.IRangedBinaryRelation#getSources()
   */
  public Collection<T> getSources() {
    Collection<T> result = new FLinkedList<T>(getEqualityTester());
    for (ENode node : _state.getContents()) {
      if (!node.getOutgoing().isEmpty())
        result.add((T)node.getElement());
    }
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.IRangedBinaryRelation#getTargets()
   */
  public Collection<U> getTargets() {
    Collection<U> result = new FLinkedList<U>(getEqualityTester());
    for (ENode node : _state.getContents()) {
      if (!node.getIncoming().isEmpty())
        result.add((U)node.getElement());
    }
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation#getWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<U>> getWithDetails(T element_p) {
    EMap<Q, Collection<U>> result = new FHashMap<Q, Collection<U>>(getEqualityTester());
    ENode node = _cache.get(element_p);
    if (node != null) {
      for (EHyperEdge edge : node.getOutgoing()) {
        Collection<U> targets = (Collection)Collections.unmodifiableCollection(
            edge.getTargets());
        result.put((Q)edge.getLabel(), targets);
      }
    }
    return Collections.unmodifiableMap(result.map());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.IBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, U target_p) {
    boolean result = false;
    ENode sourceNode = _cache.get(source_p);
    ENode targetNode = _cache.get(target_p);
    if (sourceNode != null && targetNode != null) {
      for (EHyperEdge edge : sourceNode.getOutgoing()) {
        boolean actuallyRemoved = edge.getTargets().remove(targetNode);
        result = result || actuallyRemoved;
        if (actuallyRemoved)
          checkGarbage(edge);
      }
      if (result) {
        checkGarbage(sourceNode);
        checkGarbage(targetNode);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Editable#remove(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean remove(T source_p, U target_p, Q qualifier_p) {
    boolean result = false;
    ENode sourceNode = _cache.get(source_p);
    ENode targetNode = _cache.get(target_p);
    if (sourceNode != null && targetNode != null) {
      EHyperEdge edge = getOutgoingEdge(sourceNode, qualifier_p);
      if (edge != null) {
        result = edge.getTargets().remove(targetNode);
        if (result) {
          checkGarbage(edge);
          checkGarbage(sourceNode);
          checkGarbage(targetNode);
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IRangedQBinaryRelation.Editable#removeQualifier(java.lang.Object)
   */
  public boolean removeQualifier(Q qualifier_p) {
    boolean result = false;
    for (ENode node : new FLinkedList<ENode>(_state.getContents(), null)) {
      boolean actuallyRemoved = removeQualifier(node, qualifier_p);
      result = result || actuallyRemoved;
    }
    return result;
  }
  
  /**
   * Remove the edge corresponding to the given qualifier on the given node
   * @param source_p a non-null node
   * @param qualifier_p a non-null qualifier
   * @return whether the operation had an actual effect
   */
  protected boolean removeQualifier(ENode sourceNode_p, Q qualifier_p) {
    boolean result = false;
    EHyperEdge edge = getOutgoingEdge(sourceNode_p, qualifier_p);
    if (edge != null) {
      result = true;
      Collection<ENode> targetNodes = new FLinkedList<ENode>(edge.getTargets(), null);
      edge.getTargets().clear();
      for (ENode targetNode : targetNodes) {
        checkGarbage(targetNode);
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.qualified.IQBinaryRelation.Editable#removeQualifier(java.lang.Object, java.lang.Object)
   */
  public boolean removeQualifier(T source_p, Q qualifier_p) {
    boolean result = false;
    ENode sourceNode = _cache.get(source_p);
    if (sourceNode != null)
      result = removeQualifier(sourceNode, qualifier_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.IRangedBinaryRelation.Editable#removeSource(java.lang.Object)
   */
  public boolean removeSource(T source_p) {
    boolean result = false;
    ENode node = _cache.get(source_p);
    if (node != null) {
      result = !node.getOutgoing().isEmpty();
      for (EHyperEdge edge : node.getOutgoing()) {
        Collection<ENode> targetNodes = new FLinkedList<ENode>(edge.getTargets(), null);
        edge.getTargets().clear();
        for (ENode targetNode : targetNodes) {
          checkGarbage(targetNode);
        }
      }
      node.getOutgoing().clear();
      checkGarbage(node);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.util.relations.binary.IRangedBinaryRelation.Editable#removeTarget(java.lang.Object)
   */
  public boolean removeTarget(U target_p) {
    boolean result = false;
    ENode node = _cache.get(target_p);
    if (node != null) {
      for (EHyperEdge edge : new FLinkedList<EHyperEdge>(node.getIncoming(), null)) {
        boolean actuallyRemoved = edge.getTargets().remove(node);
        result = result || actuallyRemoved;
        checkGarbage(edge);
      }
      if (result)
        checkGarbage(node);
    }
    return result;
  }
  
}
