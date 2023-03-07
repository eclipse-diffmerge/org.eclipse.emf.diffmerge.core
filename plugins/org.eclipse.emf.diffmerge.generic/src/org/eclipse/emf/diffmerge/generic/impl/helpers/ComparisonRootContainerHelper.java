package org.eclipse.emf.diffmerge.generic.impl.helpers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ComparisonRootContainerHelper {

  private Collection<IMapping> mappings = new HashSet<>();

  public ComparisonRootContainerHelper(IComparison comparison) {
    mappings.add(comparison.getMapping());
  }

  private IMatch getMatch(final EObject object, final Role role) {
    return mappings.stream().map(mapping -> mapping.getMatchFor(object, role))
        .filter(Objects::nonNull).findFirst().orElse(null);
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
