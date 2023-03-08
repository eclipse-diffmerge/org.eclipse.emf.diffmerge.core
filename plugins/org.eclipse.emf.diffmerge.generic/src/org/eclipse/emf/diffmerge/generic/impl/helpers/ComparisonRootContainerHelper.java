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
package org.eclipse.emf.diffmerge.generic.impl.helpers;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ComparisonRootContainerHelper {

  private IMapping mapping = null;

  public ComparisonRootContainerHelper(IComparison comparison) {
    mapping = comparison.getMapping();
  }

  private IMatch getMatch(final EObject object, final Role role) {
    if (mapping != null)
      return mapping.getMatchFor(object, role);
    return null;
  }

  public EObject getRootFromScope(EObject object, Role role) {
    EObject root = object;
    IMatch match = getMatch(root, role);

    if (match != null) {
      while (root != null && match.getElementPresenceDifference() != null) {
        match = match.getElementPresenceDifference().getOwnerMatch();
        root = (EObject) match.get(role);
      }
    }

    return EcoreUtil.getRootContainer(root);
  }

}
