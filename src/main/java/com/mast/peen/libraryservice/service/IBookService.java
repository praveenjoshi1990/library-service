package com.mast.peen.libraryservice.service;

import com.mast.peen.libraryservice.domain.Book;
import java.util.List;

public interface IBookService {

  public Book addBook(Book book);

  public Book removeBook(Long bookId);

  public Book updateBook(Book book, Long bookId);

  public Book getBook(Long bookId);

  public List<Book> getAllBooks();

}
