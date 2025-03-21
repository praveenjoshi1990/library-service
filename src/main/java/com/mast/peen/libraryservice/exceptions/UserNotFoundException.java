package com.mast.peen.libraryservice.exceptions;


public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException() {
    super("User does not found");
  }

}