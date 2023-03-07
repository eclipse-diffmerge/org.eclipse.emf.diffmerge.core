package org.eclipse.emf.diffmerge.api.scopes;

import org.eclipse.emf.diffmerge.generic.api.IComparison;

/*
 * A scope which contains the comparison object that uses it.
 */
public interface IComparisonDependantScope {
  void setComparison(IComparison comparison);
}
