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
  
  /**
   * Whether to uses diff-decorated icons to represent differences on elements
   */
  Identifier<Boolean> P_CUSTOM_ICONS = new Identifier<Boolean>(
      "PROPERTY_CUSTOM_ICONS");
  
  /**
   * Whether to uses diff-decorated labels to represent differences on elements
   */
  Identifier<Boolean> P_CUSTOM_LABELS = new Identifier<Boolean>(
      "PROPERTY_CUSTOM_LABELS");
  
  /**
   * The default value for the "cover children" property as proposed to the user when merging
   */
  Identifier<Boolean> P_DEFAULT_COVER_CHILDREN = new Identifier<Boolean>(
      "PROPERTY_DEFAULT_COVER_CHILDREN");
  
  /**
   * The default value for the "incremental mode" property as proposed to the user when merging
   */
  Identifier<Boolean> P_DEFAULT_INCREMENTAL_MODE = new Identifier<Boolean>(
      "PROPERTY_DEFAULT_INCREMENTAL_MODE");
  
  /**
   * The default value for "show merge impact" property as proposed to the user when merging
   */
  Identifier<Boolean> P_DEFAULT_SHOW_MERGE_IMPACT = new Identifier<Boolean>(
      "PROPERTY_DEFAULT_SHOW_MERGE_IMPACT");
  
  /**
   * Whether events must be logged
   */
  Identifier<Boolean> P_LOG_EVENTS = new Identifier<Boolean>(
      "PROPERTY_LOG_EVENTS");
  
  /**
   * Whether an impact dialog must be shown at merge time
   */
  Identifier<Boolean> P_SHOW_DIFFERENCE_NUMBERS = new Identifier<Boolean>(
      "PROPERTY_SHOW_DIFFERENCE_NUMBERS");
  
  /**
   * Whether an impact dialog must be shown at merge time
   */
  Identifier<Boolean> P_SHOW_MERGE_IMPACT = new Identifier<Boolean>(
      "PROPERTY_SHOW_MERGE_IMPACT");
  
  /**
   * Whether the left and right sides may be shown
   */
  Identifier<Boolean> P_SHOW_SIDES_POSSIBLE = new Identifier<Boolean>(
      "PROPERTY_SHOW_SIDES_POSSIBLE");
  
  /**
   * Whether to support undo/redo (cost in memory usage and response time)
   */
  Identifier<Boolean> P_SUPPORT_UNDO_REDO = new Identifier<Boolean>(
      "PROPERTY_SUPPORT_UNDO_REDO");
  
  /**
   * Whether undo/redo support is optional, see above
   */
  Identifier<Boolean> P_SUPPORT_UNDO_REDO_OPTIONAL = new Identifier<Boolean>(
      "PROPERTY_SUPPORT_UNDO_REDO_OPTIONAL");
  
  /**
   * Whether to synchronize the left/right model viewers and the synthesis viewer
   */
  Identifier<Boolean> P_SYNC_EXTERNAL = new Identifier<Boolean>(
      "PROPERTY_SYNC_EXTERNAL");
  
  /**
   * Whether to synchronize the left/right model viewers and the synthesis viewer
   */
  Identifier<Boolean> P_SYNC_SYNTHESIS_AND_SIDES = new Identifier<Boolean>(
      "PROPERTY_SYNC_SYNTHESIS_AND_SIDES");
  
  /**
   * Whether to use technical (vs. simplified) labels to represent, 
   * in particular, meta elements
   */
  Identifier<Boolean> P_TECHNICAL_LABELS = new Identifier<Boolean>(
      "PROPERTY_TECHNICAL_LABELS");
  
}