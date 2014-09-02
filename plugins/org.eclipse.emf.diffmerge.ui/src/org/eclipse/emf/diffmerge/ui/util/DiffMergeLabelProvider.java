/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.impl.scopes.AbstractModelScope;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * A label provider for UI elements related to model Diff/Merge.
 * @author Olivier Constant
 */
public class DiffMergeLabelProvider extends LabelProvider {
  
  /** The instance of this class (singleton pattern) */
  private static DiffMergeLabelProvider __instance = null;
  
  /**
   * Return the instance of this class (singleton pattern)
   * @return a non-null object
   */
  public static DiffMergeLabelProvider getInstance() {
    if (__instance == null)
      __instance = new DiffMergeLabelProvider();
    return __instance;
  }
  
  
  /**
   * Constructor
   */
  protected DiffMergeLabelProvider() {
    // Nothing needed
  }
  
  /**
   * Return an image for the given difference and the given merge destination
   * @param difference_p a non-null difference
   * @param destination_p a non-null role which is TARGET or REFERENCE
   * @return a potentially null image
   */
  public Image getDifferenceImage(IDifference difference_p, Role destination_p) {
    Image result = null;
    if (difference_p instanceof IPresenceDifference) {
      ImageID imageId;
      if (difference_p instanceof IValuePresence && ((IValuePresence)difference_p).isOrder()) {
        imageId = ImageID.SORT;
      } else {
        Role presenceSide = ((IPresenceDifference)difference_p).getPresenceRole();
        if (presenceSide == destination_p)
          imageId = ImageID.DELETE;
        else
          imageId = ImageID.PLUS;
      }
      result = EMFDiffMergeUIPlugin.getDefault().getImage(imageId);
    }
    return result;
  }
  
  /**
   * Return a label for the given difference and the given merge destination
   * @param difference_p a non-null difference
   * @param destination_p a non-null role which is TARGET or REFERENCE
   * @param domain_p an optional editing domain for retrieving labels on deleted elements
   * @return a potentially null string
   */
  public String getDifferenceText(IDifference difference_p, Role destination_p,
      EditingDomain domain_p) {
    String result = difference_p.toString();
    if (difference_p instanceof IPresenceDifference) {
      Role presenceSide = ((IPresenceDifference)difference_p).getPresenceRole();
      boolean added = presenceSide != destination_p;
      if (difference_p instanceof IElementPresence) {
        if (added) {
          IElementPresence presence = (IElementPresence)difference_p;
          IMatch ownerMatch = presence.getOwnerMatch();
          if (ownerMatch != null) {
            String featureName = presence.getElement().eContainmentFeature().getName();
            String containerText = getMatchText(ownerMatch, destination_p, domain_p);
            result = String.format(
                Messages.EMFDiffMergeLabelProvider_AdditionInto, containerText, featureName);
          } else {
            result = Messages.EMFDiffMergeLabelProvider_Addition;
          }
        } else {
          result = Messages.EMFDiffMergeLabelProvider_Deletion;
        }
      } else if (difference_p instanceof IValuePresence) {
        IValuePresence valuePresence = (IValuePresence)difference_p;
        EStructuralFeature feature = valuePresence.getFeature();
        final String QUOTE = "'"; //$NON-NLS-1$
        final String SPACE = " "; //$NON-NLS-1$
        String featureName = feature != null? QUOTE + feature.getName() + QUOTE:
          Messages.EMFDiffMergeLabelProvider_RootContainment;
        if (feature instanceof EReference && ((EReference)feature).isContainment() &&
            !valuePresence.isOrder()) {
          String containerText =
            getMatchText(valuePresence.getElementMatch(), destination_p, domain_p);
          if (added)
            result = String.format(
                Messages.EMFDiffMergeLabelProvider_MoveInto, containerText, featureName);
          else
            result = String.format(
                Messages.EMFDiffMergeLabelProvider_MoveFrom, containerText, featureName);
        } else {
          String featureKind = feature instanceof EAttribute?
              Messages.EMFDiffMergeLabelProvider_Attribute:
                Messages.EMFDiffMergeLabelProvider_Reference;
          String operationKind;
          if (valuePresence.isOrder()) {
            if (added)
              operationKind = Messages.EMFDiffMergeLabelProvider_OrderAdd;
            else
              operationKind = Messages.EMFDiffMergeLabelProvider_OrderDel;
          } else {
            Object value = valuePresence.getValue();
            String valueText = value instanceof IMatch?
                getMatchText((IMatch)value, destination_p, domain_p): getText(value);
            operationKind = (added? Messages.EMFDiffMergeLabelProvider_ValueAddition:
              Messages.EMFDiffMergeLabelProvider_ValueDeletion) + SPACE + valueText;
          }
          result = featureKind + SPACE + featureName + ": " + operationKind; //$NON-NLS-1$
        }
      }
    }
    return result;
  }
  
  /**
   * Return the element to represent for the given match and the given destination
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFERENCE
   * @return a non-null element
   */
  private EObject getElementToRepresent(IMatch match_p, Role destination_p) {
    EObject result;
    if (match_p.getUncoveredRole() == destination_p)
      result = match_p.get(destination_p.opposite());
    else
      result = match_p.get(destination_p);
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element_p) {
    Object element = element_p;
    if (element instanceof AbstractModelScope)
      element = ((AbstractModelScope)element).getOriginator();
    Image result = UIUtil.getEMFImage(element);
    if (result == null)
      result = EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.EMPTY);
    return result;
  }
  
  /**
   * Return a label for the given match and the given merge destination
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFERENCE
   * @param domain_p an optional editing domain for retrieving labels on deleted elements
   * @return a potentially null string
   */
  public String getMatchText(IMatch match_p, Role destination_p, EditingDomain domain_p) {
    EObject toRepresent = getElementToRepresent(match_p, destination_p);
    return getText(toRepresent, domain_p);
  }
  
  /**
   * Return an image for the given match and the given merge destination
   * @param match_p a non-null match
   * @param destination_p a non-null role which is TARGET or REFERENCE
   * @return a potentially null image
   */
  public Image getMatchImage(IMatch match_p, Role destination_p) {
    return getImage(getElementToRepresent(match_p, destination_p));
  }
  
  /**
   * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    Object element = element_p;
    String result;
    if (element instanceof AbstractModelScope)
      element = ((AbstractModelScope)element).getOriginator();
    if (element instanceof EObject)
      result = UIUtil.getEMFText(element);
    else if (element instanceof Resource)
      result = UIUtil.simplifyURI(((Resource)element).getURI());
    else if (element instanceof IFile)
      result = ((IFile)element).getFullPath().toPortableString();
    else {
      result = super.getText(element);
      if (element_p instanceof String)
        result = "\"" + result + "\""; //$NON-NLS-1$ //$NON-NLS-2$
    }
    if (result == null && element_p != null)
      result = element_p.toString();
    return result;
  }
  
  /**
   * Return a label for the given element based on the editing domain if possible
   * @param element_p a non-null element
   * @param domain_p an optional editing domain
   * @return a non-null label
   */
  protected String getText(EObject element_p, EditingDomain domain_p) {
    String result = null;
    if (domain_p instanceof AdapterFactoryEditingDomain) {
      AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain)domain_p;
      IItemLabelProvider provider = (IItemLabelProvider)domain.getAdapterFactory().adapt(
          element_p, IItemLabelProvider.class);
      if (provider != null)
        result = provider.getText(element_p);
    }
    if (result == null)
      result = getText(element_p);
    return result;
  }
  
}