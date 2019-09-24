/*********************************************************************
 * Copyright (c) 2017-2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.model.egraphs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraphsFactory
 * @model kind="package"
 * @generated
 */
public interface EGraphsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "egraphs";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/diffmerge/structures/egraphs/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.emf.diffmerge.structures.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EGraphsPackage eINSTANCE = org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphImpl <em>EGraph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphImpl
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsPackageImpl#getEGraph()
	 * @generated
	 */
	int EGRAPH = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EGRAPH__CONTENTS = 0;

	/**
	 * The number of structural features of the '<em>EGraph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EGRAPH_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>EGraph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EGRAPH_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.ENodeImpl <em>ENode</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.ENodeImpl
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsPackageImpl#getENode()
	 * @generated
	 */
	int ENODE = 1;

	/**
	 * The feature id for the '<em><b>Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENODE__ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENODE__OUTGOING = 1;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENODE__INCOMING = 2;

	/**
	 * The number of structural features of the '<em>ENode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENODE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>ENode</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EHyperEdgeImpl <em>EHyper Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EHyperEdgeImpl
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsPackageImpl#getEHyperEdge()
	 * @generated
	 */
	int EHYPER_EDGE = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EHYPER_EDGE__LABEL = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EHYPER_EDGE__SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Targets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EHYPER_EDGE__TARGETS = 2;

	/**
	 * The number of structural features of the '<em>EHyper Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EHYPER_EDGE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>EHyper Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EHYPER_EDGE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph <em>EGraph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EGraph</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph
	 * @generated
	 */
	EClass getEGraph();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EGraph#getContents()
	 * @see #getEGraph()
	 * @generated
	 */
	EReference getEGraph_Contents();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode <em>ENode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ENode</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.ENode
	 * @generated
	 */
	EClass getENode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getElement()
	 * @see #getENode()
	 * @generated
	 */
	EAttribute getENode_Element();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getOutgoing()
	 * @see #getENode()
	 * @generated
	 */
	EReference getENode_Outgoing();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.ENode#getIncoming()
	 * @see #getENode()
	 * @generated
	 */
	EReference getENode_Incoming();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge <em>EHyper Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EHyper Edge</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge
	 * @generated
	 */
	EClass getEHyperEdge();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getLabel()
	 * @see #getEHyperEdge()
	 * @generated
	 */
	EAttribute getEHyperEdge_Label();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getSource()
	 * @see #getEHyperEdge()
	 * @generated
	 */
	EReference getEHyperEdge_Source();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getTargets <em>Targets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Targets</em>'.
	 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.EHyperEdge#getTargets()
	 * @see #getEHyperEdge()
	 * @generated
	 */
	EReference getEHyperEdge_Targets();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EGraphsFactory getEGraphsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphImpl <em>EGraph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphImpl
		 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsPackageImpl#getEGraph()
		 * @generated
		 */
		EClass EGRAPH = eINSTANCE.getEGraph();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EGRAPH__CONTENTS = eINSTANCE.getEGraph_Contents();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.ENodeImpl <em>ENode</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.ENodeImpl
		 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsPackageImpl#getENode()
		 * @generated
		 */
		EClass ENODE = eINSTANCE.getENode();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENODE__ELEMENT = eINSTANCE.getENode_Element();

		/**
		 * The meta object literal for the '<em><b>Outgoing</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENODE__OUTGOING = eINSTANCE.getENode_Outgoing();

		/**
		 * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENODE__INCOMING = eINSTANCE.getENode_Incoming();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EHyperEdgeImpl <em>EHyper Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EHyperEdgeImpl
		 * @see org.eclipse.emf.diffmerge.structures.model.egraphs.impl.EGraphsPackageImpl#getEHyperEdge()
		 * @generated
		 */
		EClass EHYPER_EDGE = eINSTANCE.getEHyperEdge();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EHYPER_EDGE__LABEL = eINSTANCE.getEHyperEdge_Label();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EHYPER_EDGE__SOURCE = eINSTANCE.getEHyperEdge_Source();

		/**
		 * The meta object literal for the '<em><b>Targets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EHYPER_EDGE__TARGETS = eINSTANCE.getEHyperEdge_Targets();

	}

} //EGraphsPackage
