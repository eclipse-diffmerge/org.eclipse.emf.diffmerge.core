/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.ui.util;


/**
 * User-level kinds of differences.
 * @author Olivier Constant
 */
public enum DifferenceKind {
  
  /** Modification from the left */
  FROM_LEFT,
  /** Addition from the left */
  FROM_LEFT_ADD,
  /** Deletion from the left */
  FROM_LEFT_DEL,
  /** Modification from the right */
  FROM_RIGHT,
  /** Addition from the right */
  FROM_RIGHT_ADD,
  /** Deletion from the right */
  FROM_RIGHT_DEL,
  /** Modification from both sides */
  FROM_BOTH,
  /** Non-directed modification */
  MODIFIED,
  /** Conflict */
  CONFLICT,
  /** No direct difference but differences in contents */
  COUNTED,
  /** No difference */
  NONE;
  
  
  /**
   * Return whether the receiver is a pure addition
   */
  public boolean isAddition() {
    boolean result;
    switch (this) {
      case FROM_LEFT_ADD:
      case FROM_RIGHT_ADD:
        result = true; break;
      default:
        result = false; break;
    }
    return result;
  }
  
  /**
   * Return whether the receiver is a pure deletion
   */
  public boolean isDeletion() {
    boolean result;
    switch (this) {
      case FROM_LEFT_DEL:
      case FROM_RIGHT_DEL:
        result = true; break;
      default:
        result = false; break;
    }
    return result;
  }
  
  /**
   * Return whether the receiver is concerned with the left-hand side
   * @param considerOrigin_p whether to consider origin or presence as a criterion
   */
  public boolean isLeft(boolean considerOrigin_p) {
    boolean result;
    switch (this) {
      case FROM_BOTH:
      case FROM_LEFT:
      case FROM_LEFT_ADD:
      case MODIFIED:
      case CONFLICT:
        result = true; break;
      case FROM_LEFT_DEL:
        result = considerOrigin_p; break;
      case FROM_RIGHT_DEL:
        result = !considerOrigin_p; break;
      default:
        result = false; break;
    }
    return result;
  }
  
  /**
   * Return whether the receiver is not concerned by any side
   */
  public boolean isNeutral() {
    return this == COUNTED || this == NONE;
  }
  
  /**
   * Return whether the receiver is concerned with the right-hand side
   * @param considerOrigin_p whether to consider origin or presence as a criterion
   */
  public boolean isRight(boolean considerOrigin_p) {
    boolean result;
    switch (this) {
      case FROM_BOTH:
      case FROM_RIGHT:
      case FROM_RIGHT_ADD:
      case MODIFIED:
      case CONFLICT:
        result = true; break;
      case FROM_RIGHT_DEL:
        result = considerOrigin_p; break;
      case FROM_LEFT_DEL:
        result = !considerOrigin_p; break;
      default:
        result = false; break;
    }
    return result;
  }
  
  /**
   * Remove the "add/del" characteristic of the receiver
   * @param considerOrigin_p whether to consider origin or presence as a criterion
   * @return a non-null kind
   */
  public DifferenceKind keepOnlyDirection(boolean considerOrigin_p) {
    DifferenceKind result;
    switch (this) {
      case FROM_LEFT_ADD: case FROM_LEFT_DEL: case FROM_LEFT:
        result = considerOrigin_p? FROM_LEFT: MODIFIED;
        break;
      case FROM_RIGHT_ADD: case FROM_RIGHT_DEL: case FROM_RIGHT:
        result = considerOrigin_p? FROM_RIGHT: MODIFIED;
        break;
      default:
        result = this;
        break;
    }
    return result;
  }
  
  /**
   * Return the combination of the receiver with the given difference kind
   * @param peer_p a potentially null kind
   * @param considerOrigin_p whether to consider origin or presence as a criterion
   * @return a non-null kind
   */
  public DifferenceKind with(DifferenceKind peer_p, boolean considerOrigin_p) {
    DifferenceKind result = this;
    if (peer_p != null) {
      if (peer_p == this)
        result = this;
      else if (this == CONFLICT || peer_p == CONFLICT)
        result = CONFLICT;
      else if (this == MODIFIED || peer_p == MODIFIED)
        result = MODIFIED;
      else if (this == FROM_BOTH || peer_p == FROM_BOTH ||
          isLeft(considerOrigin_p) && peer_p.isRight(considerOrigin_p) ||
          isRight(considerOrigin_p) && peer_p.isLeft(considerOrigin_p))
        result = FROM_BOTH;
      else if (isLeft(considerOrigin_p) && peer_p.isLeft(considerOrigin_p))
        result = FROM_LEFT;
      else if (isRight(considerOrigin_p) && peer_p.isRight(considerOrigin_p))
        result = FROM_RIGHT;
      else if (isLeft(considerOrigin_p) || isRight(considerOrigin_p))
        result = this;
      else if (peer_p.isLeft(considerOrigin_p) || peer_p.isRight(considerOrigin_p))
        result = peer_p;
      else if (this == COUNTED || peer_p == COUNTED)
        result = COUNTED;
      else
        result = this;
    }
    return result;
  }
  
}
