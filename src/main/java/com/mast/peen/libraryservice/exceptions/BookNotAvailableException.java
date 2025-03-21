package com.mast.peen.libraryservice.exceptions;

public class BookNotAvailableException extends RuntimeException {

  public BookNotAvailableException(String message) {
    super(message);
  }

  public BookNotAvailableException() {
    super("Books is not available to borrow");
  }

}