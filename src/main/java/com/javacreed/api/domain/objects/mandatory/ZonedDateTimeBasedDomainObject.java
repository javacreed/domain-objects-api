package com.javacreed.api.domain.objects.mandatory;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.concurrent.Immutable;

import com.google.common.base.Preconditions;

@Immutable
public class ZonedDateTimeBasedDomainObject extends ObjectBasedDomainObject<ZonedDateTime>
    implements Comparable<ZonedDateTimeBasedDomainObject> {

  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

  protected ZonedDateTimeBasedDomainObject(final ZonedDateTime value) throws NullPointerException {
    super(value);
  }

  @Override
  public int compareTo(final ZonedDateTimeBasedDomainObject other) {
    return compareTo(other, ZonedDateTime::compareTo);
  }

  public String format(final DateTimeFormatter formatter) throws NullPointerException {
    Preconditions.checkNotNull(formatter);
    return map(formatter::format);
  }

  /**
   *
   * @param pattern
   * @return
   * @throws NullPointerException
   * @throws IllegalArgumentException
   *           if the pattern is invalid
   */
  public String format(final String pattern) throws NullPointerException, IllegalArgumentException {
    Preconditions.checkNotNull(pattern);
    return format(DateTimeFormatter.ofPattern(pattern));
  }

  public String toFormattedString() {
    return format(ZonedDateTimeBasedDomainObject.DEFAULT_FORMATTER);
  }

  public LocalDateTime toLocalDateTime() {
    return value.toLocalDateTime();
  }
}
