/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
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

import java.util.Map;

import org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation;
import org.eclipse.emf.diffmerge.structures.endo.qualified.IQEndorelation;


/**
 * An endorelation defined as the union of other relations.
 * The "sub-relations" are visible as qualifiers of the contents of the
 * composite endorelation. 
 * The graph of a composite endorelation is thus a directed multigraph whose edges
 * are typed by the sub-relations.
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the sub-relations
 * @author Olivier Constant
 */
public interface ICompositeEndorelation<T, Q extends IBinaryRelation<?,?>>
extends IQEndorelation<T, Q> {
  
  /**
   * Return the bijection between the sub-relations and their adaptations
   * as endorelations over T.
   * Note that the set of sub-relations can be obtained through getQualifiers().
   * @return a non-null, potentially empty, unmodifiable map
   */
  Map<Q, IEndorelation<T>> getSubRelations();
  
  
  /**
   * A composite endorelation that can be extended with sub-relations.
   *
   * @param <T> the type of the elements
   */
  public interface Extensible<T, Q extends IBinaryRelation<?,?>>
  extends ICompositeEndorelation<T, Q> {
    /**
     * Add the given binary relation to this composite endorelation and return its
     * adaptation as an endorelation over T that will be used within this endorelation
     * @param subRelation_p a non-null binary relation
     * @return a non-null endorelation over T
     */
    IEndorelation<T> addSubRelation(Q subRelation_p);
  }
  
}
