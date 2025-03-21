package com.mast.peen.libraryservice.exceptions;

import org.springframework.http.HttpStatus;

public enum ApplicationErrorCodes {
  UNKNOWN_ERROR("10001", "Unknown error occured. Please try again.",
      HttpStatus.INTERNAL_SERVER_ERROR),
  INVALID_PASSWORD("10002", "Invalid user password.", HttpStatus.UNAUTHORIZED),
  USER_NOT_FOUND("20001", "Requested User not found.", HttpStatus.NOT_FOUND),
  USERNAME_NOT_AVAILABLE("20002", "Username already taken.", HttpStatus.CONFLICT),
  BOOK_NOT_FOUND("30001", "Requested book not found.", HttpStatus.NOT_FOUND),
  BOOK_NOT_AVAILABLE("30002", "Book is not available for borrowing.", HttpStatus.CONFLICT),
  BORROW_HISTORY_NOT_AVAILABLE("40001", "Borrowing history not found for the user.",
      HttpStatus.NOT_FOUND),
  BORROW_HISTORY_NOT_FOUND("40002", "Borrowing history not found.",
      HttpStatus.NOT_FOUND);

  private final String msg;
  private final String code;
  private final HttpStatus httpStatus;


  ApplicationErrorCodes(String code, String msg, HttpStatus httpStatus) {
    this.code = code;
    this.msg = msg;
    this.httpStatus = httpStatus;
  }

  public String getCode() {
    return this.code;
  }

  public String getMsg() {
    return this.msg;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}


