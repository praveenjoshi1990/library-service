package com.mast.peen.libraryservice.controller;


import com.mast.peen.libraryservice.domain.Book;
import com.mast.peen.libraryservice.helpers.ApiResponseMapper;
import com.mast.peen.libraryservice.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Api(tags = "Book Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;
  private final ApiResponseMapper apiResponseMapper;

  @ApiOperation(value = "Get all books")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @ApiOperation(value = "Get book with bookId")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @GetMapping("/{bookId}")
  public ResponseEntity getBook(
      @ApiParam(value = "Book Id", required = true)
      @PathVariable("bookId") final Long bookId) throws RuntimeException {
    return apiResponseMapper.mapServiceOkResponse(bookService.getBook(bookId));
  }

  @ApiOperation(value = "Add a new book")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @PostMapping("/manage")
  public ResponseEntity addBook(@RequestBody Book book,
      HttpServletRequest request
  ) throws RuntimeException {
    Book addedBook = bookService.addBook(book);
    URI newResource = UriComponentsBuilder.newInstance()
        .path(request.getAttribute(
            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) +
            "/" + addedBook.getId()).build().toUri();
    return apiResponseMapper.mapServiceCreateResponse(newResource, book);
  }

  @ApiOperation(value = "Remove book with bookId")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @DeleteMapping("/manage/{bookId}")
  public ResponseEntity deleteBook(
      @ApiParam(value = "Book Id", required = true)
      @PathVariable("bookId") final Long bookId) throws RuntimeException {
    return apiResponseMapper.mapServiceResponse(bookService.removeBook(bookId),
        HttpStatus.NO_CONTENT);
  }

  @ApiOperation(value = "Update book with bookId")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorised"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 500, message = "Internal server error")
      }
  )
  @PutMapping("/manage/{bookId}")
  public ResponseEntity deleteBook(
      @ApiParam(value = "Book Id", required = true)
      @PathVariable("bookId") final Long bookId,
      @RequestBody Book book) throws RuntimeException {
    return apiResponseMapper.mapServiceOkResponse(bookService.updateBook(book,bookId));
  }

}
