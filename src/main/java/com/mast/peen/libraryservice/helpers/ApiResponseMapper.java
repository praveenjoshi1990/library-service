package com.mast.peen.libraryservice.helpers;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseMapper {

  /**
   * This method maps a POST  response with resource location and response status
   *
   * @param <T> response body type
   * @return 201 Created with location header, 500 Internal Server Error
   */
  public <T> ResponseEntity mapServiceCreateResponse(URI resource, T response) {
    if (response == null) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(resource);
      return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }
  }

  /**
   * This method maps a POST  response  with resource location
   *
   * @param <T> response body type
   * @return 201 Created with location header, 500 Internal Server Error
   */
  public <T> ResponseEntity mapServiceCreateResponse(URI resource) {
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(resource);
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  /**
   * This method maps a POST  response
   *
   * @param <T> response body type
   * @return 201 Created with location header, 500 Internal Server Error
   */
  public <T> ResponseEntity mapServiceOkResponse(T responseBody) {
    return new ResponseEntity<>(responseBody, HttpStatus.OK);
  }

  public <T> ResponseEntity mapServiceResponse(T responseBody, HttpStatus statusCode) {
    return new ResponseEntity<>(responseBody, statusCode);
  }
}
