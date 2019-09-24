/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.tests.elements.Elements.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsFactory;
import org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage;
import org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StrictElementItemProvider
	extends ElementItemProvider {
	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public StrictElementItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

	/**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
    if (itemPropertyDescriptors == null) {
      super.getPropertyDescriptors(object);

      addSValuePropertyDescriptor(object);
      addSValuesPropertyDescriptor(object);
      addSManyRefPropertyDescriptor(object);
      addSSingleRefPropertyDescriptor(object);
      addSManyFromSingleRefPropertyDescriptor(object);
      addSSingleFromManyRefPropertyDescriptor(object);
      addSManyFromManyRef1PropertyDescriptor(object);
      addSManyFromManyRef2PropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the SValue feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSValuePropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sValue_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sValue_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SVALUE,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the SValues feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSValuesPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sValues_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sValues_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SVALUES,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the SMany Ref feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSManyRefPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sManyRef_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sManyRef_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SMANY_REF,
         true,
         false,
         true,
         null,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the SSingle Ref feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSSingleRefPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sSingleRef_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sSingleRef_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SSINGLE_REF,
         true,
         false,
         true,
         null,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the SMany From Single Ref feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSManyFromSingleRefPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sManyFromSingleRef_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sManyFromSingleRef_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SMANY_FROM_SINGLE_REF,
         true,
         false,
         true,
         null,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the SSingle From Many Ref feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSSingleFromManyRefPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sSingleFromManyRef_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sSingleFromManyRef_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SSINGLE_FROM_MANY_REF,
         true,
         false,
         true,
         null,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the SMany From Many Ref1 feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSManyFromManyRef1PropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sManyFromManyRef1_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sManyFromManyRef1_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SMANY_FROM_MANY_REF1,
         true,
         false,
         true,
         null,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the SMany From Many Ref2 feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addSManyFromManyRef2PropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StrictElement_sManyFromManyRef2_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StrictElement_sManyFromManyRef2_feature", "_UI_StrictElement_type"),
         ElementsPackage.Literals.STRICT_ELEMENT__SMANY_FROM_MANY_REF2,
         true,
         false,
         true,
         null,
         null,
         null));
  }

	/**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
    if (childrenFeatures == null) {
      super.getChildrenFeatures(object);
      childrenFeatures.add(ElementsPackage.Literals.STRICT_ELEMENT__SMANY_CONTENT);
      childrenFeatures.add(ElementsPackage.Literals.STRICT_ELEMENT__SSINGLE_CONTENT);
    }
    return childrenFeatures;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

	/**
   * This returns StrictElement.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/StrictElement"));
  }

	/**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getText(Object object) {
    String label = ((StrictElement)object).getName();
    return label == null || label.length() == 0 ?
      getString("_UI_StrictElement_type") :
      getString("_UI_StrictElement_type") + " " + label;
  }

	/**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(StrictElement.class)) {
      case ElementsPackage.STRICT_ELEMENT__SVALUE:
      case ElementsPackage.STRICT_ELEMENT__SVALUES:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case ElementsPackage.STRICT_ELEMENT__SMANY_CONTENT:
      case ElementsPackage.STRICT_ELEMENT__SSINGLE_CONTENT:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

	/**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (ElementsPackage.Literals.STRICT_ELEMENT__SMANY_CONTENT,
         ElementsFactory.eINSTANCE.createElement()));

    newChildDescriptors.add
      (createChildParameter
        (ElementsPackage.Literals.STRICT_ELEMENT__SMANY_CONTENT,
         ElementsFactory.eINSTANCE.createStrictElement()));

    newChildDescriptors.add
      (createChildParameter
        (ElementsPackage.Literals.STRICT_ELEMENT__SSINGLE_CONTENT,
         ElementsFactory.eINSTANCE.createElement()));

    newChildDescriptors.add
      (createChildParameter
        (ElementsPackage.Literals.STRICT_ELEMENT__SSINGLE_CONTENT,
         ElementsFactory.eINSTANCE.createStrictElement()));
  }

	/**
   * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
    Object childFeature = feature;
    Object childObject = child;

    boolean qualify =
      childFeature == ElementsPackage.Literals.ELEMENT__MANY_CONTENT ||
      childFeature == ElementsPackage.Literals.ELEMENT__SINGLE_CONTENT ||
      childFeature == ElementsPackage.Literals.ELEMENT__MANY_CONTENT_WITH_UP ||
      childFeature == ElementsPackage.Literals.ELEMENT__SINGLE_CONTENT_WITH_UP ||
      childFeature == ElementsPackage.Literals.STRICT_ELEMENT__SMANY_CONTENT ||
      childFeature == ElementsPackage.Literals.STRICT_ELEMENT__SSINGLE_CONTENT;

    if (qualify) {
      return getString
        ("_UI_CreateChild_text2",
         new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
    }
    return super.getCreateChildText(owner, feature, child, selection);
  }

}
