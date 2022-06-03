/*********************************************************************
 * Copyright (c) 2022 Thales Global Services S.A.S.
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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.osgi.framework.Bundle;

public class ProjectHelper {

  /**
   * Create a semantic resource containing the given root element
   */
  public static Resource saveSemanticResource(URI resourceUri, EObject root) {
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(resourceUri.segment(1));
    try {
      if (!project.exists()) {
        project.create(new NullProgressMonitor());
      }
      if (!project.isOpen()) {
        project.open(new NullProgressMonitor());
      }
      XMIResourceImpl e = new XMIResourceImpl(resourceUri);
      e.getContents().add(root);
      e.save(null);
      return e;
    } catch (CoreException | IOException e) {
      throw new AssertionError(NLS.bind("Cannot save resource ''{0}''", resourceUri), e);
    }
  }

  /**
   * This method create and returns a Sirius session based on the current semantic resource
   */
  public static Session createSessionOn(URI semanticResource) {
    try {
      Session session = SessionFactory.INSTANCE.createSession(
          semanticResource.trimFileExtension().appendFileExtension(SiriusUtil.SESSION_RESOURCE_EXTENSION),
          new NullProgressMonitor());
      session.getTransactionalEditingDomain().getCommandStack()
          .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
              session.addSemanticResource(semanticResource, new NullProgressMonitor());
            }
          });
      if (!session.isOpen()) {
        session.open(new NullProgressMonitor());
      }
      session.save(new NullProgressMonitor());
      return session;

    } catch (CoreException e) {
      throw new AssertionError(NLS.bind("Cannot create session ''{0}''", semanticResource), e);
    }
  }

  protected static IProject importProject(String pluginId, String projectPath) {
    try {
      Path sourcePath = getBundleFile(Platform.getBundle(pluginId), projectPath);
      java.net.URI uri = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().toURI();
      Path targetPath = Paths.get(uri).resolve(sourcePath.getFileName().toString());
      copyFolder(sourcePath, targetPath);

      IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(sourcePath.getFileName().toString());
      if (!project.exists()) {
        project.create(new NullProgressMonitor());
      }
      if (!project.isOpen()) {
        project.open(new NullProgressMonitor());
      }
      return project;
    } catch (Exception e) {
      throw new AssertionError(NLS.bind("Cannot import project ''{0}''", projectPath), e);
    }
  }

  protected static void copyFolder(Path sourcePath, Path targetPath) throws IOException {
    Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
        Files.createDirectories(targetPath.resolve(sourcePath.relativize(dir)));
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        Files.copy(file, targetPath.resolve(sourcePath.relativize(file)));
        return FileVisitResult.CONTINUE;
      }
    });
  }

  protected static Path getBundleFile(Bundle bundle, String relativeFilePath) throws URISyntaxException, IOException {
    URL resolvedFileURL = FileLocator.toFileURL(bundle.getEntry(relativeFilePath));
    java.net.URI resolvedURI = new java.net.URI(resolvedFileURL.getProtocol(), resolvedFileURL.getPath(), null);
    return Paths.get(resolvedURI);
  }

}
