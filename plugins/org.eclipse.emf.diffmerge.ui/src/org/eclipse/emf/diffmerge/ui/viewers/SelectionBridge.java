/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;


/**
 * A selection provider and listener which can be used as a bridge between a set of selection
 * providers and the selection service of the workbench site. Its usefulness, in addition to
 * being capable of forwarding the selection of multiple sources, comes from the fact that it
 * can be instantiated earlier than the sources, so it can be registered as a selection
 * provider for the workbench site as soon as the workbench part is activated. Listeners from
 * other workbench parts that react to part activation, such as the PropertySheet, are thus
 * able to register this selection provider.
 * @author Olivier Constant
 */
public class SelectionBridge implements ISelectionChangedListener, ISelectionProvider {
  
  /** The current, potentially null selection */
  private  ISelection _selection;
  
  /** The non-null, potentially empty set of listeners */
  private final Set<ISelectionChangedListener> _selectionListeners;
  
  
  /**
   * Constructor
   */
  public SelectionBridge() {
    _selection = StructuredSelection.EMPTY;
    _selectionListeners = new HashSet<ISelectionChangedListener>();
  }
  
  /**
   * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
   */
  public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
    _selectionListeners.add(listener_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
   */
  public ISelection getSelection() {
    return _selection;
  }
  
  /**
   * Notify listeners of the current selection
   */
  protected void notifyListeners() {
    SelectionChangedEvent event = new SelectionChangedEvent(this, _selection);
    for (ISelectionChangedListener listener :
        new ArrayList<ISelectionChangedListener>(_selectionListeners)) {
      listener.selectionChanged(event);
    }
  }
  
  /**
   * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
   */
  public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
    _selectionListeners.remove(listener_p);
  }
  
  /**
   * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
   */
  public void setSelection(ISelection selection_p) {
    _selection = selection_p;
    notifyListeners();
  }
  
  /**
   * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
   */
  public void selectionChanged(SelectionChangedEvent event_p) {
    setSelection(event_p.getSelection());
  }
  
  
  /**
   * A selection bridge which is able to manage a single selection provider as source.
   * It can still be used as a classical SelectionBridge.
   */
  public static class SingleSource extends SelectionBridge {
    
    /** The potentially null selection provider source */
    private ISelectionProvider _source;
    
    /**
     * Constructor
     */
    public SingleSource() {
      _source = null;
    }
    
    /**
     * Return the source selection provider
     * @return a potentially null object
     */
    public ISelectionProvider getSource() {
      return _source;
    }
    
    /**
     * Set the source selection provider
     * @param source_p a potentially null object
     */
    public void setSource(ISelectionProvider source_p) {
      if (_source != source_p) {
        if (_source != null)
          _source.removeSelectionChangedListener(this);
        _source = source_p;
        if (source_p != null) {
          source_p.addSelectionChangedListener(this);
          ISelection newSelection = source_p.getSelection();
          if (newSelection != null)
            setSelection(newSelection);
        }
      }
    }
    
  }
  
}