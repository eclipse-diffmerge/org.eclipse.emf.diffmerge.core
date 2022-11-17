/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.eclipse.emf.diffmerge.ui.viewers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.core.text.StringMatcher;
import org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.ui.util.DiffDecoratingLabelProvider;
import org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory;

/**
 * 
 * @author Erwann Traisnel Textual Category filter that filters elements based
 *         on their label
 *
 */
public class TextualCategoryFilter extends AbstractDifferenceCategory {

  /**
   * The viewer
   */
  protected ComparisonTreeViewer treeViewer;

  /**
   * The string pattern matcher used for this pattern filter.
   */
  private StringMatcher matcher;

  /**
   * The Pattern representing a non word
   */
  private static final Pattern NON_WORD = Pattern.compile("\\W+", //$NON-NLS-1$
      Pattern.UNICODE_CHARACTER_CLASS);

  /**
   * The match string from the viewer's filter text
   */
  String matchString = ""; //$NON-NLS-1$

  /**
   * Visibility cache to avoid recomputing state
   */
  Map<Object, Boolean> visibilityCache = new HashMap<Object, Boolean>();

  /**
   * isActive is true when the filter text is active, false otherwise
   */
  boolean isActive = true;

  /**
   * 
   * @param treeViewer_p
   *          : the treeViewer on which the filter is created, used to get the
   *          labelProvider
   */
  public TextualCategoryFilter(ComparisonTreeViewer treeViewer_p) {
    this.treeViewer = treeViewer_p;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#copy()
   */
  public IDifferenceCategory copy() {
    TextualCategoryFilter copy = new TextualCategoryFilter(treeViewer);
    copy.copyState(this);
    return copy;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#copyState(org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory)
   */
  @Override
  public void copyState(IDifferenceCategory peer_p) {
    TextualCategoryFilter toCopy = (TextualCategoryFilter) peer_p;
    toCopy.matchString = matchString;
    toCopy.matcher = matcher;
    toCopy.isActive = isActive;
    super.copyState(peer_p);
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#covers(org.eclipse.emf.diffmerge.generic.api.diff.IDifference,
   *      org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode) This category only
   *      covers ElementPresence, based on their matching their label with
   *      matchString
   */
  @Override
  public boolean covers(IDifference<?> difference_p, EMFDiffNode node_p) {
    if (difference_p instanceof EElementRelativePresence) {
      Boolean visible = visibilityCache.get(difference_p);
      if (visible == null) {
        boolean textMatches = false;
        EElementRelativePresence eElementPresence = (EElementRelativePresence) difference_p;
        EMatch ownerMatch = eElementPresence.getElementMatch();
        String label = ((DiffDecoratingLabelProvider) treeViewer
            .getLabelProvider()).getUndecoratedText(ownerMatch);

        textMatches = wordMatches(label);
        visible = Boolean.valueOf(textMatches);
        visibilityCache.put(difference_p, visible);

      }
      return !visible.booleanValue();
    }
    return false;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#getID()
   */
  @Override
  public String getID() {
    return "textualFilter";//$NON-NLS-1$
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategoryItem#getText(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public String getText(EMFDiffNode node_p) {
    return "Textual Category Filter";//$NON-NLS-1$
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#isActive()
   */
  @Override
  public boolean isActive() {
    return isActive;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#setActive(boolean)
   */
  @Override
  public void setActive(boolean active_p) {
    isActive = active_p;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#isApplicable(org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode)
   */
  @Override
  public boolean isApplicable(EMFDiffNode node_p) {
    // Always true
    return true;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#isVisible()
   */
  @Override
  public boolean isVisible() {
    // Always false, as the category filter shall not appear in the category
    // dialog
    return false;
  }

  /**
   * 
   * @see org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory#isModifiable()
   */
  @Override
  public boolean isModifiable() {
    // Always false, as the user shouldn't be able to disable it
    return false;
  }

  /**
   * 
   * @param inputString
   *          : new text
   * 
   *          Ensures the text actually has changed, that cache is cleared, and
   *          that the matcher is updated with new text
   */
  public void textChanged(String inputString) {
    if (inputString == null || !inputString.equals(matchString)) {
      clearCache();
      matchString = inputString;
      if (inputString == null || inputString.isEmpty()) {
        matcher = null;
        setActive(false);
      } else {
        setActive(true);
        String pattern = "*" + inputString + "*"; //$NON-NLS-1$ //$NON-NLS-2$
        matcher = new StringMatcher(pattern, true, false);
      }
    }
  }

  /**
   * Clear visibility cache
   */
  protected void clearCache() {
    visibilityCache.clear();
  }

  /**
   * 
   * @param text
   *          : the text to extract words from
   * @return array of words
   */
  private String[] getWords(String text) {
    return NON_WORD.split(text, 0);
  }

  /**
   * Return whether or not if any of the words in text satisfy the match
   * criteria.
   *
   * @param text
   *          the text to match
   * @return boolean <code>true</code> if one of the words in text satisfies the
   *         match criteria.
   */
  protected boolean wordMatches(String text) {
    if (text == null) {
      return false;
    }

    // If the whole text matches we are all set
    if (match(text)) {
      return true;
    }

    // Otherwise check if any of the words of the text matches
    String[] words = getWords(text);
    for (String word : words) {
      if (match(word)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Answers whether the given String matches the pattern.
   *
   * @param string
   *          the String to test
   *
   * @return whether the string matches the pattern
   */
  private boolean match(String string) {
    if (matcher == null) {
      return true;
    }
    return matcher.match(string);
  }
}