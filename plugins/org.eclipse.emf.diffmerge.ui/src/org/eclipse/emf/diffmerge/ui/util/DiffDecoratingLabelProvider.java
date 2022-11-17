/*********************************************************************
 * Copyright (c) 2018-2022 Thales Global Services S.A.S.
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

import java.util.regex.Matcher;

import org.eclipse.emf.common.ui.viewer.IUndecoratingLabelProvider;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IPureMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.Messages;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.viewers.CategoryManager;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;


/**
 * A delegating label provider which uses a diff label decorator to decorate
 * the representation of elements based on their diff status.
 * Elements that are supported by default are: matches (IMatch), differences
 * (IDifference), MatchAndFeature, EObject (elements of the models compared).
 * @author Olivier Constant
 */
public abstract class DiffDecoratingLabelProvider extends DelegatingLabelProvider
implements IUndecoratingLabelProvider, ITreePathLabelProvider, IDiffLabelDecorator.Provider {
  
  /**
   * Data that is in a cache to improve performance.
   */
  protected static class CacheEntry {
    /** The element concerned */
    private final Object _element;
    /** The element to represent */
    private final Object _elementToRepresent;
    /** The diff status of the element concerned */
    private final DifferenceKind _differenceKind;
    /**
     * Constructor covering the concerned element, its represented counterpart
     * and its diff status
     * @param element_p a non-null object
     * @param elementToRepresent a non-null object
     * @param differenceKind_p FROM_LEFT_*, FROM_RIGHT_*, CONFLICT, MODIFIED or NONE
     */
    public CacheEntry(Object element_p, Object elementToRepresent, DifferenceKind differenceKind_p) {
      _element = element_p;
      _elementToRepresent = elementToRepresent;
      _differenceKind = differenceKind_p;
    }
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object_p) {
      boolean result = false;
      if (object_p instanceof CacheEntry) {
        CacheEntry peer = (CacheEntry)object_p;
        result =
            getDifferenceKind().equals(peer.getDifferenceKind()) &&
            getElement().equals(peer.getElement()) &&
            getElementToRepresent().equals(peer.getElementToRepresent());
      }
      return result;
    }
    /**
     * Return the diff status of the element concerned
     * @return FROM_LEFT_*, FROM_RIGHT_*, CONFLICT, MODIFIED or NONE
     */
    public DifferenceKind getDifferenceKind() {
      return _differenceKind;
    }
    /**
     * Return the element concerned
     * @return a non-null element
     */
    public Object getElement() {
      return _element;
    }
    /**
     * Return the element to represented, derived from the element concerned
     * @return a non-null element
     */
    public Object getElementToRepresent() {
      return _elementToRepresent;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return getDifferenceKind().hashCode() + getElement().hashCode() +
          getElementToRepresent().hashCode();
    }
  }
  
  /**
   * If true, indicates that the comparison is left-to-right or right-to-left
   */
  private boolean isDirectedComparison = false;
  
  /** The non-null diff label decorator */
  private IDiffLabelDecorator _diffDecorator;
  
  /** The potentially null cache that associates a given object with pre-computed data.
      It is not synchronized since it is supposed to be solely used through the UI thread. */
  private CacheEntry _diffCache;
  
  
  /**
   * Default constructor
   */
  public DiffDecoratingLabelProvider() {
    super();
    _diffDecorator = getDefaultDiffLabelDecorator();
    _diffCache = null;
  }
  
  /**
   * Constructor
   * @param delegate_p the optional label provider to which behavior is delegated
   */
  public DiffDecoratingLabelProvider(ILabelProvider delegate_p) {
    super(delegate_p);
    _diffDecorator = getDefaultDiffLabelDecorator();
    _diffCache = null;
  }
  
  /**
   * Clear the (element, diff status) cache
   */
  protected void cacheClear() {
    _diffCache = null;
  }
  
  /**
   * Return the cached data for the given element, if any
   * @param element_p a potentially null object
   * @return a potentially null object, where null stands for "not in cache"
   */
  protected CacheEntry cacheGet(Object element_p) {
    CacheEntry result = null;
    if (_diffCache != null && element_p != null &&
        element_p == _diffCache.getElement() /* == is intentional */) {
      result = _diffCache;
    }
    return result;
  }
  
  /**
   * Put data for the given element into the cache
   * @param element_p a potentially null object, where null clears the cache
   */
  protected void cachePut(Object element_p) {
    if (element_p == null) {
      cacheClear();
    } else {
      DifferenceKind diffKind = doGetDiffStatus(element_p);
      Object representedElement = doGetElementToRepresent(element_p);
      _diffCache = new CacheEntry(element_p, representedElement, diffKind);
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.StyledCellLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    cacheClear();
    setDiffLabelDecorator(null);
  }
  
  /**
   * Return the difference kind of the given object.
   * Override to handle difference kinds on unexpected objects.
   * @param element_p a potentially null object, where null implies NONE is returned
   * @return FROM_LEFT_*, FROM_RIGHT_*, CONFLICT, MODIFIED or NONE
    */
  protected DifferenceKind doGetDiffStatus(Object element_p) {
    DifferenceKind result = DifferenceKind.NONE;
    if (element_p != null) {
      EMFDiffNode node = getDiffNode();
      if (node != null) {
        CategoryManager catManager = node.getCategoryManager(); // Non-null
        if (element_p instanceof IMatch &&
            catManager.isComparisonPart((IMatch<?>)element_p)) {
          // Match
          result = catManager.getDifferenceKind((IMatch<?>)element_p);
        } else if (element_p instanceof MatchAndFeature &&
            catManager.isComparisonPart(((MatchAndFeature)element_p).getMatch())) {
          // MatchAndFeature
          result = catManager.getDifferenceKind((MatchAndFeature)element_p);
        } else if (element_p instanceof IDifference &&
            catManager.isComparisonPart((IDifference<?>)element_p)) {
          // Difference
          result = catManager.getDifferenceKind((IDifference<?>)element_p);
        } else if (getSide() != null) {
          // Element compared
          result = catManager.getDifferenceKind(element_p, getSide());
        }
      }
    }
    return result;
  }
  
  /**
   * Return the element to actually represent from the given one
   * @param element_p a potentially null object
   * @return a potentially null object
   */
  protected Object doGetElementToRepresent(Object element_p) {
    Object result = element_p;
    if (element_p instanceof IMatch) {
      result = getElementToRepresentFromMatch((IMatch<?>)element_p);
    } else if (element_p instanceof MatchAndFeature) {
      result = ((MatchAndFeature)element_p).getFeature();
    } else if (element_p instanceof IValuePresence) {
      IValuePresence<?> valuePresence = (IValuePresence<?>)element_p;
      result = getElementToRepresent(
          isFromValue(valuePresence)? valuePresence.getElementMatch():
            valuePresence.getValue());
    } else if (element_p instanceof TreePath) {
      result = ((TreePath)element_p).getLastSegment();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element_p) {
    Color result = getDiffLabelDecorator().getBackground(
        element_p,
        getUndecoratedBackground(element_p),
        getDifferenceKind(element_p),
        getSide(),
        getDiffNode());
    return result;
  }
  
  /**
   * Return the container of the given element on the given side
   * @param element_p a non-null element
   * @param side_p a non-null role
   * @return a potentially null object
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected Object getContainer(Object element_p, Role side_p) {
    Object result = null;
    EMFDiffNode node = getDiffNode();
    if (node != null) {
      ITreeDataScope<?> scope =
          node.getActualComparison().getScope(side_p);
      if (scope != null) {
        result = ((ITreeDataScope)scope).getContainer(element_p);
      }
    }
    return result;
  }
  
  /**
   * Return the default diff label decorator for this label provider
   * @return a non-null object
   */
  protected IDiffLabelDecorator getDefaultDiffLabelDecorator() {
    if(isDirectedComparison) {
      return GitLikeDiffLabelDecorator.getInstance();
    }
    return DefaultDiffLabelDecorator.getInstance();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.IDiffLabelDecorator.Provider#getDiffLabelDecorator()
   */
  public IDiffLabelDecorator getDiffLabelDecorator() {
    return _diffDecorator;
  }
  
  /**
   * Return the difference kind of the given object
   * @param element_p a potentially null object, where null implies NONE is returned
   * @return FROM_LEFT_*, FROM_RIGHT_*, CONFLICT, MODIFIED or NONE
    */
  protected DifferenceKind getDifferenceKind(Object element_p) {
    DifferenceKind result;
    CacheEntry data = cacheGet(element_p);
    if (data != null) {
      result = data.getDifferenceKind();
    } else {
      result = doGetDiffStatus(element_p);
      // Do not cache automatically
    }
    return result;
  }
  
  /**
   * Return the diff node that governs the representation of differences, or null if none.
   * If null is returned then diff diff decorations may not be applicable.
   * @return a potentially null object
   */
  protected abstract EMFDiffNode getDiffNode();
  
  /**
   * Return the element to actually represent from the given one
   * @param element_p a potentially null object
   * @return a potentially null object
   */
  protected Object getElementToRepresent(Object element_p) {
    Object result;
    CacheEntry data = cacheGet(element_p);
    if (data != null) {
      result = data.getElementToRepresent();
    } else {
      result = doGetElementToRepresent(element_p);
      // Do not cache automatically
    }
    return result;
  }
  
  /**
   * Return the element to actually represent from the given match
   * @param match_p a non-null match
   * @return a non-null object
   */
  protected Object getElementToRepresentFromMatch(IMatch<?> match_p) {
    Role sideRole = getSide();
    if (sideRole == null) {
      EMFDiffNode node = getDiffNode();
      if (node != null) {
        sideRole = node.getDrivingRole();
      }
    }
    if (sideRole == null) {
      sideRole = Role.TARGET; // By default
    }
    Object result;
    if (match_p.getUncoveredRole() == sideRole) {
      result = match_p.get(sideRole.opposite());
    } else {
      result = match_p.get(sideRole);
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element_p) {
    Font result = getDiffLabelDecorator().getFont(
        element_p,
        getUndecoratedFont(element_p),
        getDifferenceKind(element_p),
        getSide(),
        getDiffNode());
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(Object element_p) {
    Color result = getDiffLabelDecorator().getForeground(
        element_p,
        getUndecoratedForeground(element_p),
        getDifferenceKind(element_p),
        getSide(),
        getDiffNode());
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element_p) {
    Image result = getDiffLabelDecorator().getImage(
        element_p,
        getUndecoratedImage(element_p),
        getDifferenceKind(element_p),
        getSide(),
        getDiffNode());
    return result;
  }
  
  /**
   * Return a label representing the match ID associated to the given object, if any
   * @param element_p a potentially null element
   * @return a potentially null string
   */
  protected String getMatchIDText(Object element_p) {
    String result = null;
    if (element_p instanceof IPureMatch) {
      IMatchPolicy<?> policy = getMatchPolicy();
      Object matchID = ((IPureMatch<?>)element_p).getMatchID();
      String matchIDText = null;
      if (matchID != null) {
        matchIDText = matchID.toString();
        // Trying to refine based on separators
        if (policy instanceof ConfigurableMatchPolicy) {
          ConfigurableMatchPolicy cPolicy = (ConfigurableMatchPolicy)policy;
          for (String separator : cPolicy.getSeparators()) {
            final String outPattern = Matcher.quoteReplacement('\n' + separator + ' ');
            matchIDText = matchIDText.replaceAll(separator, outPattern);
          }
        }
      } else if (policy != null && policy.keepMatchIDs()) {
          matchIDText = Messages.ComparisonTreeViewer_NoMatchID;
      }
      if (matchIDText != null) {
        result = Messages.ComparisonTreeViewer_MatchIDTooltip + matchIDText;
      }
    }
    return result;
  }
  
  /**
   * Return the match policy that was used for the current comparison, if any
   * @return a potentially null match policy
   */
  protected IMatchPolicy<?> getMatchPolicy() {
    IMatchPolicy<?> result = null;
    EMFDiffNode node = getDiffNode();
    if (node != null) {
      IComparison<?> comparison = node.getActualComparison();
      if (comparison != null) {
        result = comparison.getLastMatchPolicy();
      }
    }
    return result;
  }
  
  /**
   * Return an image that represents an ordering difference
   * @return a potentially null image
   */
  protected Image getOrderDifferenceImage() {
    return EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.SORT);
  }
  
  /**
   * Return an image that represents the virtual "ownership" feature
   * @return a potentially null image
   */
  protected Image getOwnershipFeatureImage() {
    return EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.TREE);
  }
  
  /**
   * Return the comparison side all objects to represent belong to, or null
   * if unknown or not applicable
   * @return a potentially null object
   */
  protected abstract Role getSide();
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getStyledText(java.lang.Object)
   */
  @Override
  public StyledString getStyledText(Object element_p) {
    CharSequence base = getUndecoratedStyledText(element_p);
    if (base == null) {
      base = getUndecoratedText(element_p);
    }
    CharSequence label = getDiffLabelDecorator().getText(
        element_p,
        base,
        getDifferenceKind(element_p),
        getSide(),
        getDiffNode());
    StyledString result;
    if (label instanceof StyledString) {
      result = (StyledString)label;
    } else {
      result = new StyledString(label.toString());
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object element_p) {
    CharSequence label = getDiffLabelDecorator().getText(
        element_p,
        getUndecoratedText(element_p),
        getDifferenceKind(element_p),
        getSide(),
        getDiffNode());
    String result;
    if (label instanceof StyledString || label == null) {
      result = null;
    } else {
      result = label.toString();
    }
    return result;
  }
  
  /**
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
   */
  @Override
  public String getToolTipText(Object element_p) {
    String result = getDiffLabelDecorator().getToolTipText(
        element_p,
        getUndecoratedToolTipText(element_p),
        getDifferenceKind(element_p),
        getSide(),
        getDiffNode());
    return result;
  }
  
  /**
   * Return the undecorated background color
   * @see DiffDecoratingLabelProvider#getBackground(Object)
   */
  public Color getUndecoratedBackground(Object element_p) {
    Object elementToRepresent = getElementToRepresent(element_p);
    return elementToRepresent == element_p?
        super.getBackground(elementToRepresent):
          getUndecoratedBackground(elementToRepresent);
  }
  
  /**
   * Return the undecorated font
   * @see DiffDecoratingLabelProvider#getFont(Object)
   */
  public Font getUndecoratedFont(Object element_p) {
    Object elementToRepresent = getElementToRepresent(element_p);
    return elementToRepresent == element_p?
        super.getFont(elementToRepresent):
          getUndecoratedFont(elementToRepresent);
  }
  
  /**
   * Return the undecorated foreground color
   * @see DiffDecoratingLabelProvider#getForeground(Object)
   */
  public Color getUndecoratedForeground(Object element_p) {
    Object elementToRepresent = getElementToRepresent(element_p);
    return elementToRepresent == element_p?
        super.getForeground(elementToRepresent):
          getUndecoratedForeground(elementToRepresent);
  }
  
  /**
   * @see org.eclipse.emf.common.ui.viewer.IUndecoratingLabelProvider#getUndecoratedImage(java.lang.Object)
   */
  public Image getUndecoratedImage(Object element_p) {
    Image result;
    if (isOwnershipFeature(element_p)) {
      result = getOwnershipFeatureImage();
    } else if (isOrderDifference(element_p)) {
      result = getOrderDifferenceImage();
    } else {
      Object elementToRepresent = getElementToRepresent(element_p);
      result = (elementToRepresent == element_p)?
          super.getImage(elementToRepresent):
            getUndecoratedImage(elementToRepresent);
    }
    return result;
  }
  
  /**
   * Return the undecorated styled text
   * @see DiffDecoratingLabelProvider#getStyledText(Object)
   */
  public StyledString getUndecoratedStyledText(Object element_p) {
    StyledString result;
    if (element_p instanceof IValuePresence) {
      result = getUndecoratedStyledTextFromValuePresence((IValuePresence<?>)element_p);
    } else {
      Object elementToRepresent = getElementToRepresent(element_p);
      result = (elementToRepresent == element_p)?
          super.getStyledText(elementToRepresent):
            getUndecoratedStyledText(elementToRepresent);
    }
    return result;
  }
  
  /**
   * Return an undecorated styled label for the given value presence representing
   * the value from the point of view of the holder
   * @param valueLabel_p a potentially null string
   * @param valuePresence_p a non-null value presence
   * @return a potentially null styled string
   */
  protected StyledString getUndecoratedStyledTextFromHolder(String valueLabel_p,
      IValuePresence<?> valuePresence_p) {
    StyledString result = new StyledString(valueLabel_p);
    if (valuePresence_p instanceof IReferenceValuePresence) {
      Object value = ((IReferenceValuePresence<?>)valuePresence_p).getValue();
      Object container = getContainer(value, valuePresence_p.getPresenceRole());
      String containerLabel = (container == null)? null:
        getUndecoratedText(container);
      if (containerLabel != null) {
        containerLabel = String.format(Messages.ValuesViewer_ContainerLabel, containerLabel);
        result.append(' ');
        result.append(containerLabel, StyledString.QUALIFIER_STYLER);
      }
    }
    return result;
  }
  
  /**
   * Return an undecorated styled label for the given value presence representing
   * the holder from the point of view of the value
   * @param holderLabel_p a potentially null string
   * @param valuePresence_p a non-null value presence
   * @return a potentially null styled string
   */
  protected StyledString getUndecoratedStyledTextFromValue(String holderLabel_p,
      IValuePresence<?> valuePresence_p) {
    StyledString result = new StyledString(holderLabel_p);
    Object feature = valuePresence_p.getFeature();
    if (feature != null) {
      String featureText = getUndecoratedText(feature);
      if (featureText != null) {
        featureText = String.format(Messages.ValuesViewer_FeatureLabel, featureText);
        result.append(' ');
        result.append(featureText, StyledString.QUALIFIER_STYLER);
      }
    }
    return result;
  }
  
  /**
   * Return an undecorated styled label for the given value presence
   * @param valuePresence_p a non-null object
   * @return a potentially null styled string
   */
  protected StyledString getUndecoratedStyledTextFromValuePresence(
      IValuePresence<?> valuePresence_p) {
    StyledString result;
    String base = getUndecoratedText(valuePresence_p);
    result = isFromValue(valuePresence_p)?
        getUndecoratedStyledTextFromValue(base, valuePresence_p):
          getUndecoratedStyledTextFromHolder(base, valuePresence_p);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.common.ui.viewer.IUndecoratingLabelProvider#getUndecoratedText(java.lang.Object)
   */
  public String getUndecoratedText(Object element_p) {
    String result;
    if (isOrderDifference(element_p)) {
      result = Messages.ValuesViewer_OrderLabel;
    } else if (element_p instanceof EStructuralFeature && !isTextTechnicalForMeta()) {
      result = UIUtil.getFormattedFeatureText((EStructuralFeature)element_p);
    } else {
      Object elementToRepresent = getElementToRepresent(element_p);
      result = (elementToRepresent == element_p)?
          super.getText(elementToRepresent):
            getUndecoratedText(elementToRepresent);
    }
    return result;
  }

  /**
   * Return the undecorated tool tip text
   * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
   */
  public String getUndecoratedToolTipText(Object element_p) {
    Object elementToRepresent = getElementToRepresent(element_p);
    return elementToRepresent == element_p?
        super.getToolTipText(elementToRepresent):
          getUndecoratedToolTipText(elementToRepresent);
  }
  
  /**
   * Return whether the given value presence must be represented from
   * the point of view of the target value, i.e., the holder is represented
   * rather than the value
   * @param valuePresence_p a non-null object
   */
  protected boolean isFromValue(IValuePresence<?> valuePresence_p) {
    // Override if needed
    return false;
  }
  
  /**
   * Return whether the given object is an ordering difference
   * @param element_p a potentially null object
   */
  protected boolean isOrderDifference(Object element_p) {
    return element_p instanceof IValuePresence && ((IValuePresence<?>)element_p).isOrder();
  }
  
  /**
   * Return whether the given object represents the virtual ownership feature
   * @param object_p a potentially null object
   */
  protected boolean isOwnershipFeature(Object object_p) {
    return EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature().equals(object_p);
  }
  
  /**
   * Return whether meta-elements (instances of EClass, EStructuralFeature, etc.)
   * must have technical vs. user-friendly labels
   */
  protected boolean isTextTechnicalForMeta() {
    // Override if needed
    return false;
  }
  
  /**
   * Set the diff label decorator of this label provider
   * @param diffDecorator_p a potentially null object, where null stands for default
   */
  public void setDiffLabelDecorator(IDiffLabelDecorator diffDecorator_p) {
    IDiffLabelDecorator old = _diffDecorator;
    _diffDecorator = diffDecorator_p;
    if (_diffDecorator == null) {
      _diffDecorator = getDefaultDiffLabelDecorator();
    }
    if (_diffDecorator != old) {
      fireLabelProviderChanged(new LabelProviderChangedEvent(this));
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.util.DelegatingLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
   */
  @Override
  public void update(ViewerCell cell_p) {
    Object element = cell_p.getElement();
    cachePut(element);
    super.update(cell_p);
    cacheClear();
  }
  
  /**
   * @see org.eclipse.jface.viewers.ITreePathLabelProvider#updateLabel(org.eclipse.jface.viewers.ViewerLabel, org.eclipse.jface.viewers.TreePath)
   */
  public void updateLabel(ViewerLabel label_p, TreePath elementPath_p) {
    // Override if needed, e.g., to distinguish between different occurrences
    // of the same element
    Object element = elementPath_p.getLastSegment();
    cachePut(element);
    label_p.setText(getText(element));
    label_p.setImage(getImage(element));
    label_p.setBackground(getBackground(element));
    label_p.setForeground(getForeground(element));
    _defaultFont = (label_p.getFont() != null)?
        UIUtil.getBase(label_p.getFont()): null;
    label_p.setFont(getFont(element));
    _defaultFont = null;
    cacheClear();
  }
  

  /**
   * 
   * @param value
   *          : true if left-to-right or right-to-left When true, the decorators
   *          are changed to git decorators
   */
  public void setDirected(boolean value) {
    if(value != isDirectedComparison) {
      isDirectedComparison = value;
      if(isDirectedComparison) {
        setDiffLabelDecorator(GitLikeDiffLabelDecorator.getInstance());
      } else {
        setDiffLabelDecorator(DefaultDiffLabelDecorator.getInstance());
      }
    }
    
  }
}
