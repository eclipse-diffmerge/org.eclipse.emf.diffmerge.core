/**
 * <copyright>
 * 
 * Copyright (c) 2018 Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.ui.viewers;

import org.eclipse.emf.diffmerge.ui.util.UserProperty.Identifier;


/**
 * Identifiers for the UI properties supported by the default comparison viewer.
 * @see ComparisonViewer
 * @author Olivier Constant
 */
@SuppressWarnings("nls")
public interface DefaultUserProperties {
  
  /** Whether to use technical (vs. simplified) labels to represent, 
   * in particular, meta elements */
  Identifier<Boolean> TECHNICAL_LABELS = new Identifier<Boolean>("PROPERTY_TECHNICAL_LABELS");
  
}