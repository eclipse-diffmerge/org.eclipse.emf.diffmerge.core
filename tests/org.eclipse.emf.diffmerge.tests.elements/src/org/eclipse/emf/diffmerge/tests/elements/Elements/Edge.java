/**
 */
package org.eclipse.emf.diffmerge.tests.elements.Elements;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getEdge()
 * @model
 * @generated
 */
public interface Edge extends NamedElement {
	/**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Node#getIncoming <em>Incoming</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(Node)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getEdge_Target()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Node#getIncoming
   * @model opposite="incoming" required="true"
   * @generated
   */
	Node getTarget();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
	void setTarget(Node value);

	/**
   * Returns the value of the '<em><b>Source</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Node#getOutgoing <em>Outgoing</em>}'.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(Node)
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.ElementsPackage#getEdge_Source()
   * @see org.eclipse.emf.diffmerge.tests.elements.Elements.Node#getOutgoing
   * @model opposite="outgoing" required="true"
   * @generated
   */
	Node getSource();

	/**
   * Sets the value of the '{@link org.eclipse.emf.diffmerge.tests.elements.Elements.Edge#getSource <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */
	void setSource(Node value);

} // Edge
