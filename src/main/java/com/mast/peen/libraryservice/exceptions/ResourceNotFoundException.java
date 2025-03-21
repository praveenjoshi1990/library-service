package com.mast.peen.libraryservice.exceptions;


public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException() {
    super("Resource does not found");
  }

}