/**
 * <copyright>
 * 
 * Copyright (c) 2016-2017 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.structures.endo.qualified;

import org.eclipse.emf.diffmerge.structures.endo.IIterableEndorelation;


/**
 * A qualified iterable endorelation.
 * @see IQEndorelation
 * @see IIterableEndorelation
 * 
 * @param <T> the type of the elements
 * @param <Q> the type of the qualifiers
 * @author Olivier Constant
 */
public interface IIterableQEndorelation<T, Q> extends IQEndorelation<T, Q>,
IIterableEndorelation<T> {
  
  // Nothing more
  
}
