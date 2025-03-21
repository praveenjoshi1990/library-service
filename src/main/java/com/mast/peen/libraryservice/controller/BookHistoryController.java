package com.mast.peen.libraryservice.controller;

import com.mast.peen.libraryservice.domain.BookBorrowHistory;
import com.mast.peen.libraryservice.exceptions.ResourceNotFoundException;
import com.mast.peen.libraryservice.helpers.ApiResponseMapper;
import com.mast.peen.libraryservice.service.BookBorrowHistoryService;
import com.mast.peen.libraryservice.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Api(tags = "Book borrow history Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class BookHistoryController {

  private final BookService bookService;
  private final BookBorrowHistoryService bookBorrowHistoryService;
  private final ApiResponseMapper apiResponseMapper;


  @ApiOperation(value = "Get book borrow history")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @GetMapping("/{orderId}")
  public ResponseEntity getBook(
      @ApiParam(value = "Order Id", required = true)
      @PathVariable("orderId") final Long orderId) throws RuntimeException {
    return apiResponseMapper
        .mapServiceOkResponse(bookBorrowHistoryService.getBorrowHistory(orderId));
  }

  @ApiOperation(value = "Borrow book with bookId on a userId")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @PostMapping("/borrow/{bookId}")
  public ResponseEntity borrowBook(@ApiParam(value = "Book Id", required = true)
  @PathVariable("bookId") final Long bookId,
      @RequestParam Long userId,
      HttpServletRequest request) throws RuntimeException {
    BookBorrowHistory bookBorrowOrder = bookService.borrowBook(bookId, userId);
    URI newResource = UriComponentsBuilder.newInstance()
        .path(request.getAttribute(
            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) +
            "/" + bookBorrowOrder.getId()).build().toUri();
    return apiResponseMapper.mapServiceCreateResponse(newResource, bookBorrowOrder);
  }

  @ApiOperation(value = "Return a book with bookId on a userId")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @PostMapping("/return/{bookId}")
  public ResponseEntity returnBook(@ApiParam(value = "Book Id", required = true)
  @PathVariable("bookId") final Long bookId,
      @RequestParam Long userId,
      HttpServletRequest request) throws ResourceNotFoundException {
    BookBorrowHistory bookReturnOrder = bookService.returnBook(bookId, userId);
    URI newResource = UriComponentsBuilder.newInstance()
        .path(request.getAttribute(
            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) +
            "/" + bookReturnOrder.getId()).build().toUri();
    return apiResponseMapper.mapServiceCreateResponse(newResource, bookReturnOrder);
  }

}
