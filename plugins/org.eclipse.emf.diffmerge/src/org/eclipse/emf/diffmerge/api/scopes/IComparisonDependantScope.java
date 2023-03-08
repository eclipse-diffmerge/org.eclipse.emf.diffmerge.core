/*********************************************************************
 * Copyright (c) 2023 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.api.scopes;

import org.eclipse.emf.diffmerge.generic.api.IComparison;

/*
 * A scope which contains the comparison object that uses it.
 */
public interface IComparisonDependantScope {
  void setComparison(IComparison comparison);
}
