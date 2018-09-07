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
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import org.eclipse.emf.diffmerge.structures.binary.qualified.IQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.binary.qualified.IRangedQBinaryRelation;
import org.eclipse.emf.diffmerge.structures.endo.IRangedEndorelation;


/**
 * A qualified endorelation whose set of possible qualifiers is finite and known.
 * @see IRangedEndorelation
 * @see IQEndorelation
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public interface IRangedQEndorelation<T, Q> extends IQEndorelation<T, Q>,
IRangedEndorelation<T> {
  
  /**
   * A modifiable qualified ranged endorelation.
   * 
   * @param <T> the type of the elements
   * @param <Q> the type of the qualifiers
   */
  public interface Editable<T, Q> extends IRangedQEndorelation<T, Q>,
  IRangedEndorelation.Editable<T>, IQEndorelation.Editable<T, Q>,
  IRangedQBinaryRelation.Editable<T, T, Q> {
    // Nothing to add
  }
  
  
  /**
   * An invertible qualified ranged endorelation.
   * 
   * @param <T> the type of the elements
   * @param <Q> the type of the qualifiers
   */
  public interface Invertible<T, Q> extends IRangedQEndorelation<T, Q>,
  IRangedEndorelation.Invertible<T>, IQBinaryRelation.Invertible<T, T, Q> {
    // Nothing to add
  }
  
  
  /**
   * A ranged qualified endorelation that supports inverse navigation
   * and that can be edited.
   * 
   * @param <T> the type of the elements
   */
  public interface InvertibleEditable<T, Q> extends Invertible<T, Q>, Editable<T, Q>,
  IRangedEndorelation.InvertibleEditable<T>, IRangedQBinaryRelation.InvertibleEditable<T, T, Q> {
    // Nothing more
  }
  
}
