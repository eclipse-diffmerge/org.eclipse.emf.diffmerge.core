/**
 */
package org.eclipse.emf.diffmerge.diffdata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EElement Relative Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.diffmerge.diffdata.DiffdataPackage#getEElementRelativePresence()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EElementRelativePresence extends
    org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence<EObject, EAttribute, EReference>,
    EMergeableDifference {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation" required="true"
   * @generated
   */
  EMatch getElementMatch();

} // EElementRelativePresence
