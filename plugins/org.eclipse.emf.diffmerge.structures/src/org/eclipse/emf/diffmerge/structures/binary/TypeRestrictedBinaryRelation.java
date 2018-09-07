/*********************************************************************
 * Copyright (c) 2016-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.structures.binary;

import static org.eclipse.emf.diffmerge.structures.PropertyValue.unknownValue;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.structures.IProperty;
import org.eclipse.emf.diffmerge.structures.IPropertyValue;
import org.eclipse.emf.diffmerge.structures.common.FLinkedList;


/**
 * A wrapper around a binary relation that changes the types it operates on.
 * Target elements outside the specified target (codomain) type are filtered out.
 *
 * @param <T> the type of the domain elements
 * @param <U> the type of the codomain elements
 * @author Olivier Constant
 */
public class TypeRestrictedBinaryRelation<T, U> extends AbstractBinaryRelation<T, U>
implements IBinaryRelation.RuntimeTyped<T, U>{
  
  /** The non-null binary relation to adapt */
  private final IBinaryRelation<?,?> _adapted;
  
  /** The non-null runtime type of the source elements */
  private final Class<T> _sourceType;
  
  /** The non-null runtime type of the target elements */
  private final Class<U> _targetType;
  
  /** Whether source elements require filtering */
  protected final boolean _sourcesRequireFiltering;
  
  /** Whether target elements require filtering */
  protected final boolean _targetsRequireFiltering;
  
  /** The source type of the adapted relation, or null if unknown */
  protected final Class<?> _originalSourceType;
  
  
  /**
   * Constructor
   * @param toAdapt_p the non-null binary relation to adapt
   * @param sourceType_p the new non-null runtime type of the source elements
   * @param targetType_p the new non-null runtime type of the target elements
   */
  public TypeRestrictedBinaryRelation(IBinaryRelation<?,?> toAdapt_p,
      Class<T> sourceType_p, Class<U> targetType_p) {
    super(toAdapt_p.getEqualityTester());
    _adapted = toAdapt_p;
    _sourceType = sourceType_p;
    _targetType = targetType_p;
    if (toAdapt_p instanceof IBinaryRelation.RuntimeTyped<?,?>) {
      IBinaryRelation.RuntimeTyped<?,?> typedOriginalRelation =
          (IBinaryRelation.RuntimeTyped<?,?>)toAdapt_p;
      _originalSourceType = typedOriginalRelation.getSourceType();
      _sourcesRequireFiltering = !_originalSourceType.isAssignableFrom(_sourceType);
      _targetsRequireFiltering =
          !_targetType.isAssignableFrom(typedOriginalRelation.getTargetType());
    } else {
      _sourcesRequireFiltering = true;
      _targetsRequireFiltering = true;
      _originalSourceType = null;
    }
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation#get(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public Collection<U> get(T element_p) {
    Collection<U> result;
    Collection<?> originalResult = getRaw(element_p);
    if (!_targetsRequireFiltering || originalResult.isEmpty()) {
      result = (Collection<U>)originalResult;
    } else {
      result = new FLinkedList<U>(getEqualityTester());
      Class<U> targetType = getTargetType();
      for (Object candidate : originalResult) {
        if (targetType.isInstance(candidate))
          result.add((U)candidate);
      }
      result = Collections.unmodifiableCollection(result);
    }
    return result;
  }
  
  /**
   * Return the non-filtered target elements for the given source element
   * @param element_p a non-null element
   * @return a non-null, potentially empty, unmodifiable collection
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected Collection<?> getRaw(T element_p) {
    IBinaryRelation adaptedRelation = getAdaptedRelation();
    Collection<?> result;
    if (_sourcesRequireFiltering && _originalSourceType != null) {
      // Filtering is needed and can be done
      if (_originalSourceType.isInstance(element_p)) {
        // Element is OK for the adapted relation
        result = adaptedRelation.get(element_p);
      } else {
        // Element is KO for the adapted relation
        result = Collections.emptySet();
      }
    } else {
      // No filtering needed or it cannot be done
      try {
        result = adaptedRelation.get(element_p);
      } catch (ClassCastException e) {
        // Failed due to lack of filtering
        result = Collections.emptySet();
      }
    }
    return result;
  }
  
  /**
   * Return the binary relation that is adapted
   * @return a non-null binary relation
   */
  public IBinaryRelation<?,?> getAdaptedRelation() {
    return _adapted;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.RuntimeTyped#getSourceType()
   */
  public Class<T> getSourceType() {
    return _sourceType;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.IBinaryRelation.RuntimeTyped#getTargetType()
   */
  public Class<U> getTargetType() {
    return _targetType;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isFunctional()
   */
  @Override
  public IPropertyValue<Boolean> isFunctional() {
    return trueForAdaptedOrUnknown(propertyIsFunctional());
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.structures.binary.AbstractBinaryRelation#isInjective()
   */
  @Override
  public IPropertyValue<Boolean> isInjective() {
    return trueForAdaptedOrUnknown(propertyIsInjective());
  }
  
  /**
   * Return a property value that is true if the given property is true for the
   * adapted relation, otherwise unknown. It reflects the fact that the given property
   * could be true for this relation even though it is not true for the adapted one.
   * @param property_p a non-null boolean property
   * @return a non-null boolean property value
   */
  protected IPropertyValue<Boolean> trueForAdaptedOrUnknown(
      IProperty<Boolean> property_p) {
    IPropertyValue<Boolean> result = unknownValue();
    IBinaryRelation<?,?> adaptedRelation = getAdaptedRelation();
    if (adaptedRelation instanceof IBinaryRelation.WithProperties<?,?>) {
      IPropertyValue<Boolean> originalResult = 
          ((IBinaryRelation.WithProperties<?,?>)adaptedRelation).getProperty(property_p);
      if (originalResult.is(Boolean.TRUE))
        result = originalResult;
    }
    return result;
  }
  
}
