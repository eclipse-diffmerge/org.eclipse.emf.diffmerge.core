/**
 */
package org.eclipse.emf.diffmerge.tests.elements.Elements.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.diffmerge.tests.elements.Elements.*;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage
 * @generated
 */
public class ElementsAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static ElementsPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ElementsAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = ElementsPackage.eINSTANCE;
    }
  }

	/**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
	@Override
	public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

	/**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ElementsSwitch<Adapter> modelSwitch =
		new ElementsSwitch<Adapter>() {
      @Override
      public Adapter caseIdentifiedElement(IdentifiedElement object) {
        return createIdentifiedElementAdapter();
      }
      @Override
      public Adapter caseNamedElement(NamedElement object) {
        return createNamedElementAdapter();
      }
      @Override
      public Adapter caseRoot(Root object) {
        return createRootAdapter();
      }
      @Override
      public Adapter caseElement(Element object) {
        return createElementAdapter();
      }
      @Override
      public Adapter caseStrictElement(StrictElement object) {
        return createStrictElementAdapter();
      }
      @Override
      public Adapter caseNode(Node object) {
        return createNodeAdapter();
      }
      @Override
      public Adapter caseEdge(Edge object) {
        return createEdgeAdapter();
      }
      @Override
      public Adapter caseReferencingNode(ReferencingNode object) {
        return createReferencingNodeAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

	/**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
	@Override
	public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.IdentifiedElement <em>Identified Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.IdentifiedElement
   * @generated
   */
	public Adapter createIdentifiedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.NamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.NamedElement
   * @generated
   */
	public Adapter createNamedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Root <em>Root</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Root
   * @generated
   */
	public Adapter createRootAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Element
   * @generated
   */
	public Adapter createElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement <em>Strict Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.StrictElement
   * @generated
   */
	public Adapter createStrictElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Node <em>Node</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Node
   * @generated
   */
	public Adapter createNodeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge <em>Edge</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Edge
   * @generated
   */
	public Adapter createEdgeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.ReferencingNode <em>Referencing Node</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ReferencingNode
   * @generated
   */
	public Adapter createReferencingNodeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
	public Adapter createEObjectAdapter() {
    return null;
  }

} //ElementsAdapterFactory
