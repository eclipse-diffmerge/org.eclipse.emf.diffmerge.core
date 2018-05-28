/**
 * <copyright>
 * 
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.binary.qualified;

import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedCopyInto;
import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedGet;
import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedGetQualifiers;
import static org.eclipse.emf.diffmerge.structures.Relations.qualifiedMaps;

import java.util.Collection;

import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation;


/**
 * A base implementation of modifiable finite, qualified binary relations.
 * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation
 *
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public abstract class AbstractQBinaryRelation<T, U, Q> extends AbstractBinaryRelation<T, U>
implements IRangedQBinaryRelation.Editable<T, U, Q> {
  
  /**
   * Constructor for the default equality tester (by reference)
   */
  public AbstractQBinaryRelation() {
    this((IEqualityTester)null);
  }
  
  /**
   * Constructor
   * @param tester_p a potentially null equality tester for comparing elements
   *        (null means default)
   */
  public AbstractQBinaryRelation(IEqualityTester tester_p) {
    super(tester_p);
  }
  
  /**
   * Constructor
   * @param initialContents_p a non-null ranged qualified binary relation defining the
   *        initial contents of this relation
   */
  public AbstractQBinaryRelation(
      IRangedQBinaryRelation<T, U, Q> initialContents_p) {
    super(initialContents_p.getEqualityTester());
    qualifiedCopyInto(initialContents_p, this);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#add(java.lang.Object, java.lang.Object)
   */
  public boolean add(T source_p, U target_p) {
    Q qualifier = defaultQualifier();
    if (qualifier == null)
      throw new UnsupportedOperationException(
          "Operation is not supported because the default qualifier is null"); //$NON-NLS-1$
    return add(source_p, target_p, qualifier);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.Editable#addAll(java.lang.Object, java.util.Collection)
   */
  public boolean addAll(T source_p, Collection<? extends U> targets_p) {
    return addAll(source_p, targets_p, defaultQualifier());
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
  public Collection<U> get(T element_p) {
    return qualifiedGet(this, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#get(java.lang.Object, java.lang.Object)
   */
  public Collection<U> get(T element_p, Q qualifier_p) {
    return qualifiedGet(this, element_p, qualifier_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation#getQualifiers(java.lang.Object)
   */
  public Collection<Q> getQualifiers(T element_p) {
    return qualifiedGetQualifiers(this, element_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#getQualifiers(java.lang.Object, java.lang.Object)
   */
  public Collection<Q> getQualifiers(T source_p, U target_p) {
    return qualifiedGetQualifiers(this, source_p, target_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation#maps(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  public boolean maps(T source_p, U target_p, Q qualifier_p) {
    return qualifiedMaps(this, source_p, target_p, qualifier_p);
  }
  
}
