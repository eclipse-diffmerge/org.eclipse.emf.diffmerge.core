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
package org.eclipse.emf.diffmerge.tests.ju;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.sirius.SiriusDiffPolicy;
import org.eclipse.emf.diffmerge.sirius.SiriusMatchPolicy;
import org.eclipse.emf.diffmerge.sirius.SiriusMergePolicy;
import org.eclipse.emf.diffmerge.sirius.SiriusScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.junit.Before;
import org.junit.Test;

public abstract class ImageDependencyDiffMergeTest {
	private SiriusScope sourceScope;
	private SiriusScope destinationScope;
	private EComparison comparison;

	private int passedAsserts = 0;

	@Before
	public void setupProjects() {
		URI semanticDestination = URI.createPlatformResourceURI("target project/target project.elements", false);
		ProjectHelper.saveSemanticResource(semanticDestination, getTargetRoot());
		Session destinationSession = ProjectHelper.createSessionOn(semanticDestination);
		destinationScope = new SiriusScope(destinationSession.getSessionResource().getURI(),
				destinationSession.getTransactionalEditingDomain(), true);

		URI semanticSource = URI.createPlatformResourceURI("source project/source project.elements", false);
		ProjectHelper.saveSemanticResource(semanticSource, getSourceRoot());
		Session sourceSession = ProjectHelper.createSessionOn(semanticSource);
		sourceScope = new SiriusScope(sourceSession.getSessionResource().getURI(),
				sourceSession.getTransactionalEditingDomain(), true);

		comparison = new EComparisonImpl(destinationScope, sourceScope);
		destinationScope.setComparison(comparison);
		sourceScope.setComparison(comparison);
	}

	@Test
	public void runTest() {
		TransactionalEditingDomain domain = (TransactionalEditingDomain) destinationScope.getEditingDomain();
		domain.getCommandStack().execute(new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				comparison.compute(new SiriusMatchPolicy(), new SiriusDiffPolicy(), new SiriusMergePolicy(),
						new NullProgressMonitor());
				Collection<IDifference<EObject>> diffs = comparison.getDifferences(Role.REFERENCE);

				comparison.merge(Role.TARGET, true, new NullProgressMonitor());

				diffs.stream().forEach(diff -> {
					assertDifferences(diff);
				});

				assertEquals("More or less differences have been found than required.", getRequiredDifferences(),
						passedAsserts);
			}

		});
	}
	
	protected void increasePassedAsserts() {
		passedAsserts++;
	}

	protected abstract int getRequiredDifferences();

	protected abstract void assertDifferences(IDifference diff);

	protected abstract EObject getSourceRoot();

	protected abstract EObject getTargetRoot();
}
