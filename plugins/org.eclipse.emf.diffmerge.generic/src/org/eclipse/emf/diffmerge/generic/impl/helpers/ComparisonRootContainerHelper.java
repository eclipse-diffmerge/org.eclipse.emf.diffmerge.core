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
