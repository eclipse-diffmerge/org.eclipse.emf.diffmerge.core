/*********************************************************************
 * Copyright (c) 2016-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.endo;

import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedGet;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.PropertyValue;
import org.eclipse.emf.diffmerge.structures.Relations;
import org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation;
import org.eclipse.emf.diffmerge.structures.common.FHashMap;
import org.eclipse.emf.diffmerge.structures.common.FLinkedList;


/**
 * An implementation of composite endorelations.
 * It supports couples that solely belong to this relation and not to a sub-relation,
 * using the relation itself as qualifier.
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the sub-relations
 * @author Olivier Constant
 */
public class CompositeEndorelation<T, Q extends IBinaryRelation<?,?>>
extends AbstractEndorelation<T>
implements ICompositeEndorelation.Extensible<T, Q>, IEndorelation.RuntimeTyped<T> {
  
  /** The runtime type of the endorelation */
  private final Class<T> _type;
  
  /** The non-null, potentially empty, ordered bijection between the sub-relations
   * and their associated adaptations as endorelations over T */
  protected final EMap<Q, IEndorelation<T>> _subRelations;
  
  
  /**
   * Constructor
   * @param type_p the non-null runtime type of the elements of the endorelation
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  public CompositeEndorelation(Class<T> type_p, IEqualityTester tester_p) {
    super(tester_p);
    _type = type_p;
    _subRelations = new FHashMap<Q, IEndorelation<T>>(tester_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICompositeEndorelation.Extensible#addSubRelation(org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation)
   */
  public IEndorelation<T> addSubRelation(Q subRelation_p) {
    IEndorelation<T> adaptation =
        new TypeAdaptedEndorelation<T>(subRelation_p, getSourceType());
    _subRelations.put(subRelation_p, adaptation);
    return adaptation;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#defaultQualifier()
   */
  public Q defaultQualifier() {
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
   */
  public Collection<T> get(T element_p) {
    return qualifiedGet(this, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#get(java.lang.Object, java.lang.Object)
   */
  public Collection<T> get(T element_p, Q qualifier_p) {
    Collection<T> result;
    IEndorelation<T> adapted = getSubRelations().get(qualifier_p);
    if (adapted != null)
      result = adapted.get(element_p);
    else
      result = Collections.emptySet();
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object)
   */
  public Collection<Q> getQualifiers(T element_p) {
    return Relations.qualifiedGetQualifiers(this, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object, java.lang.Object)
   */
  public Collection<Q> getQualifiers(T source_p, T target_p) {
    List<Q> result = new FLinkedList<Q>(getEqualityTester());
    for (Map.Entry<Q, IEndorelation<T>> entry : getSubRelations().entrySet()) {
      if (entry.getValue().maps(source_p, target_p))
        result.add(entry.getKey());
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.RuntimeTyped#getSourceType()
   */
  public Class<T> getSourceType() {
    return _type;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.ICompositeEndorelation#getSubRelations()
   */
  public Map<Q, IEndorelation<T>> getSubRelations() {
    return Collections.unmodifiableMap(_subRelations.map());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.RuntimeTyped#getTargetType()
   */
  public Class<T> getTargetType() {
    return _type;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getWithDetails(java.lang.Object)
   */
  public Map<Q, Collection<T>> getWithDetails(T element_p) {
    EMap<Q, Collection<T>> result = new FHashMap<Q, Collection<T>>();
    for (Map.Entry<Q, IEndorelation<T>> entry : getSubRelations().entrySet()) {
      Collection<T> targets = entry.getValue().get(element_p);
      if (!targets.isEmpty())
        result.put(entry.getKey(), targets);
    }
    return Collections.unmodifiableMap(result.map());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.endo.AbstractEndorelation#isIrreflexive()
   */
  @Override
  public IPropertyValue<Boolean> isIrreflexive() {
    return PropertyValue.all(getSubRelations().values(), propertyIsIrreflexive());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#maps(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean maps(T source_p, T target_p, Q qualifier_p) {
    boolean result = false;
    IEndorelation<T> adapted = getSubRelations().get(qualifier_p);
    if (adapted != null)
      result = adapted.maps(source_p, target_p);
    return result;
  }
  
}
