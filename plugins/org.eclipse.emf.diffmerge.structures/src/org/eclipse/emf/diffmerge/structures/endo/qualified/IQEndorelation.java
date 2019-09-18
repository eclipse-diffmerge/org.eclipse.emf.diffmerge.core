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
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.endo.IEndorelation;


/**
 * A qualified binary relation that is an endorelation.
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public interface IQEndorelation<T, Q> extends IEndorelation<T>,
IQBinaryRelation<T, T, Q> {
  
  /**
   * A modifiable endorelation with qualifiers.
   * The order in which couples are introduced is assumed to be preserved (add/get consistency).
   * 
   * @param <T> the type of the domain elements
   * @param <Q> the type of the qualifiers
   */
  public interface Editable<T, Q> extends IQEndorelation<T, Q>,
  IQBinaryRelation.Editable<T, T, Q> {
    // Nothing more
  }
  
  
  /**
   * A qualified endorelation which supports inverse navigation.
   * 
   * @param <T> the type of the elements
   * @param <Q> the type of the qualifiers
   */
  public interface Invertible<T, Q> extends IQEndorelation<T, Q>,
  IQBinaryRelation.Invertible<T, T, Q>, IEndorelation.Invertible<T> {
    // Nothing more
  }
  
}
