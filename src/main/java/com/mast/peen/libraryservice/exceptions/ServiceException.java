package com.mast.peen.libraryservice.exceptions;

public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = 7718828512143293558L;

  private final ApplicationErrorCodes code;

  public ServiceException(ApplicationErrorCodes code) {
    super();
    this.code = code;
  }

  public ServiceException(String message, Throwable cause, ApplicationErrorCodes code) {
    super(message, cause);
    this.code = code;
  }

  public ServiceException(String message, ApplicationErrorCodes code) {
    super(message);
    this.code = code;
  }

  public ServiceException(Throwable cause, ApplicationErrorCodes code) {
    super(cause);
    this.code = code;
  }

  public ApplicationErrorCodes getCode() {
    return this.code;
  }

}
