package com.javacreed.api.domain.primitives.array;

import java.util.Arrays;
import java.util.Iterator;

import com.google.common.base.Preconditions;

public class ReadOnlyCharArray implements Iterable<Character> {

  public static ReadOnlyCharArray of(final char[] data) throws NullPointerException {
    Preconditions.checkNotNull(data);
    return new ReadOnlyCharArray(Arrays.copyOf(data, data.length));
  }

  private final char[] data;
  /* Compute the hash code when requested */
  private transient int lazyHashCode;
  private transient boolean lazyHashCodeComputed = false;

  private ReadOnlyCharArray(final char[] data) {
    this.data = data;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    final ReadOnlyCharArray other = (ReadOnlyCharArray) object;
    return Arrays.equals(data, other.data);
  }

  @Override
  public int hashCode() {
    if (false == lazyHashCodeComputed) {
      lazyHashCode = Arrays.hashCode(data);
      lazyHashCodeComputed = true;
    }
    return lazyHashCode;
  }

  @Override
  public Iterator<Character> iterator() {
    return CharArrayIterator.create(data);
  }

  public int length() {
    return data.length;
  }

  public boolean sameAs(final char[] other) throws NullPointerException {
    Preconditions.checkNotNull(other);
    return Arrays.equals(data, other);
  }

  public char valueAt(final int index) {
    return data[index];
  }
}