package com.javacreed.api.domain.primitives.mandatory;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;
import com.javacreed.api.domain.primitives.utils.ComparatorUtils;

@Immutable
public class ObjectBasedDomainPrimitive<T> {

  protected final T value;

  protected ObjectBasedDomainPrimitive(final T value) throws NullPointerException {
    this.value = Preconditions.checkNotNull(value, "Value of " + getClass().getSimpleName() + " cannot be null");
  }

  protected int compareTo(final ObjectBasedDomainPrimitive<T> other, final Comparator<T> comparator) {
    return ComparatorUtils.compare(getValue(), other.getValue(), comparator);
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    @SuppressWarnings("rawtypes")
    final ObjectBasedDomainPrimitive other = (ObjectBasedDomainPrimitive) object;
    return value.equals(other.value);
  }

  public T getValue() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  /**
   * If a value is present, apply the provided mapping function to it, and if the result is non-null, return an
   * {@link Optional} describing the result. Otherwise return an empty {@link Optional}.
   *
   * @param <U>
   *          The type of the result of the mapping function
   * @param mapper
   *          a mapping function to apply to the value, if present (which cannot be <code>null</code>)
   * @return an {@link Optional} describing the result of applying a mapping function to the value of this
   *         {@link Optional}, if a value is present, otherwise an empty {@link Optional}
   * @throws NullPointerException
   *           if the mapping function is <code>null</code>
   */
  public <U> U map(final Function<? super T, ? extends U> mapper) throws NullPointerException {
    Preconditions.checkNotNull(mapper);
    return mapper.apply(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
