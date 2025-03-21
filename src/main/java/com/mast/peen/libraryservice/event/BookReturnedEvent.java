package com.mast.peen.libraryservice.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookReturnedEvent extends ApplicationEvent {

  private final Long bookId;
  private final Long userId;
  private final boolean late;

  public BookReturnedEvent(Object source, Long bookId, Long userId, boolean late) {
    super(source);
    this.bookId = bookId;
    this.userId = userId;
    this.late = late;
  }
}
