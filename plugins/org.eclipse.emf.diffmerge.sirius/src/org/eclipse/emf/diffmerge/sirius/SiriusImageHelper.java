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
package org.eclipse.emf.diffmerge.sirius;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.impl.helpers.ComparisonRootContainerHelper;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;

/**
 * Helper to adapt SiriusScope results
 */
public class SiriusImageHelper {

  /** @see org.eclipse.emf.diffmerge.sirius.SiriusImageHelper getMainProjectName */
  private HashMap<EObject, String> _mainProjectNames = new HashMap<>();

  private ComparisonRootContainerHelper comparisonRootContainerHelper;

  public SiriusImageHelper(IComparison comparison) {
    comparisonRootContainerHelper = new ComparisonRootContainerHelper(comparison);
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#get(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EAttribute)
   */
  public List<Object> adaptGetValue(EObject source_p, EAttribute attribute_p, List<Object> objects_p) {
    List<Object> objects = objects_p;
    
    if (RichTextAttributeRegistry.INSTANCE.getEAttributes().contains(attribute_p)) {
      objects = objects.stream().filter(String.class::isInstance).map((e) -> {
        return replaceRichtextToRelative(source_p, (String) e);
      }).collect(Collectors.toList());
  
    } else if (attribute_p == DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH) {
      objects = objects.stream().filter(String.class::isInstance).map((e) -> {
        return replaceWorkspaceImageToRelative(source_p, (String) e);
      }).collect(Collectors.toList());
    }

    return objects;
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#add(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EAttribute, java.lang.Object)
   */
  public Object adaptAddValue(EObject source_p, EAttribute attribute_p, Object value_p) {
    if (value_p instanceof String) {
      if (RichTextAttributeRegistry.INSTANCE.getEAttributes().contains(attribute_p)) {
        return replaceRichtextToHardcodedProject(source_p, (String) value_p);
  
      } else if (attribute_p == DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH) {
        return replaceWorkspaceImageToHardcodedProject(source_p, (String) value_p);
      }
    }
    return value_p;
  }

  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#remove(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EAttribute, java.lang.Object)
   */
  public Object adaptRemoveValue(EObject source_p, EAttribute attribute_p, Object value_p) {
    if (value_p instanceof String) {
      if (RichTextAttributeRegistry.INSTANCE.getEAttributes().contains(attribute_p)) {
        return replaceRichtextToHardcodedProject(source_p, (String) value_p);
  
      } else if (attribute_p == DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH) {
        return replaceWorkspaceImageToHardcodedProject(source_p, (String) value_p);
      }
    }
    return value_p;
  }
  

  /**
   * Replace paths of images located in the root project by a relative path to avoid detecting differences on models
   * with different names
   * 
   * @param source_p  the element
   * @param value_p the richtext description that can contain image paths
   * @implNote Notice the 'cdo:/'. Sirius will change urls to cdo:/ if located on remote repository
   * @return a richtext description containing relative paths on images located in the root project
   */
  protected String replaceRichtextToRelative(EObject source_p, String value_p) {
    String projectName = getMainProjectName(source_p);
    if (projectName == null) {
      return value_p;
    }
    return value_p.replaceAll("src=\"(cdo:/)?" + projectName + "/", "src=\"$1./"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  /**
   * Replace relative paths of images by an absolute path toward the root project of the source_p element
   * 
   * @param source_p the element
   * @param value_p the richtext description that can contain image paths
   * @implNote Notice the 'cdo:/'. Sirius will change urls to cdo:/ if located on remote repository
   * @return a richtext description containing relative paths on images located in the root project
   */
  protected String replaceRichtextToHardcodedProject(EObject source_p, String value_p) {
    String projectName = getMainProjectName(source_p);
    if (projectName == null) {
      return value_p;
    }
    return value_p.replaceAll("src=\"(cdo:/)?\\./", "src=\"$1" + projectName + "/"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  /**
   * Replace paths of {@link WorkspaceImage} located in the root project by a relative path to avoid detecting
   * differences on models with different names
   * 
   * @param source_p
   *          the element
   * @param value_p
   *          the {@link WorkspaceImage} workspace path
   * @implNote Notice the 'cdo:/'. Sirius will change urls to cdo:/ if located on remote repository
   * @return a workspace path containing relative path if image was located in the root project
   */
  protected String replaceWorkspaceImageToRelative(EObject source_p, String value_p) {
    String projectName = getMainProjectName(source_p);
    if (projectName == null) {
      return value_p;
    }
    return value_p.replaceAll("^(cdo:/)?" + projectName + "/", "$1./"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  /**
   * Replace relative paths of {@link WorkspaceImage} by an absolute path toward the root project of the source_p
   * element
   * 
   * @param source_p the element
   * @param value_p the {@link WorkspaceImage} workspace path
   * @implNote Notice the 'cdo:/'. Sirius will change urls to cdo:/ if located on remote repository
   * @return a workspace path containing absolute path if image was located in the root project
   */
  protected String replaceWorkspaceImageToHardcodedProject(EObject source_p, String value_p) {
    String projectName = getMainProjectName(source_p);
    if (projectName == null) {
      return value_p;
    }
    return value_p.replaceAll("^(cdo:/)?\\./", "$1" + projectName + "/"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  /**
   * For a given object, retrieve its project
   * 
   * @implSpec Note that the project name containing the element source_p might be different than the IProject containing the
   *           Sirius session. Here we lookup for the project name owning the resource containing the element
   */
  protected String getProjectName(EObject source_p) {
    EObject root = EcoreUtil.getRootContainer(source_p, true);
    if (root != null) {
      Resource resource = root.eResource();
      if (resource != null) {
        ResourceSet set = resource.getResourceSet();
        return getProjectFromUri(resource.getURI(), set != null ? set.getURIConverter() : null);
      }
    }
    return null;
  }

  /**
   * For a given resource URI, retrieve the root segment describing the root project name
   */
  protected String getProjectFromUri(URI uri_p, URIConverter converter) {

    // if platform, we retrieve the second segment (platform:/resource/projectName)
    if (uri_p.isPlatform()) {
      return uri_p.segment(1);
    }

    // When the model is directly stored under git, we cannot retrieve a project name as there is none
    if (uri_p.segmentCount() == 1) {
      return uri_p.trimFileExtension().segment(uri_p.segmentCount() - 1);
    }

    // otherwise we look for the parent containing a .project
    if (converter != null) {
      URI uri = uri_p;
      while (uri.segmentCount() > 1) {
        uri = uri.trimSegments(1);
        URI project = uri.appendSegment(IProjectDescription.DESCRIPTION_FILE_NAME);
        if (converter.exists(project, null)) {
          return getNameFromDotProject(project, converter);
        }
      }
    }

    // otherwise we retrieve the first segment
    return uri_p.segment(0);
  }

  /**
   * Retrieve the name of a .project. On an unexpected error, retrieve the folder of the project
   * 
   * @apiNote the .project file shall exists.
   */
  protected String getNameFromDotProject(URI projectUri, URIConverter converter) {
    try (InputStream stream = converter.createInputStream(projectUri)) {
      IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(stream);
      return description.getName();
      
    } catch (Exception e) {
      return projectUri.segment(projectUri.segmentCount() - 2);
    }
  }
  
  /**
   * For an element, retrieve it's project name
   */
  protected String getMainProjectName(EObject source_p) {
    EObject root = comparisonRootContainerHelper.getRootFromScope(source_p, Role.TARGET);
    root = comparisonRootContainerHelper.getRootFromScope(root, Role.REFERENCE);
    
    if (!_mainProjectNames.containsKey(root)) {
      String string = getProjectName(root);
      _mainProjectNames.put(root, string);
    }
    return _mainProjectNames.get(root);
  }
  
}
