package com.mast.peen.libraryservice.service;

import com.mast.peen.libraryservice.domain.Book;
import com.mast.peen.libraryservice.domain.BookBorrowHistory;
import com.mast.peen.libraryservice.event.BookReturnedEvent;
import com.mast.peen.libraryservice.exceptions.ApplicationErrorCodes;
import com.mast.peen.libraryservice.exceptions.ResourceNotFoundException;
import com.mast.peen.libraryservice.exceptions.ServiceException;
import com.mast.peen.libraryservice.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

  private final BookRepository bookRepository;
  private final BookBorrowHistoryService borrowHistoryService;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public Book addBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book removeBook(Long bookId) {
    Book book = bookRepository.findById(bookId).orElseThrow(ResourceNotFoundException::new);
    bookRepository.delete(book);
    return book;
  }

  @Override
  public Book updateBook(Book book, Long bookId) {
    Book existingBook = bookRepository.findById(bookId).orElseThrow(ResourceNotFoundException::new);
    existingBook.setAuthor(book.getAuthor());
    existingBook.setPrice(book.getPrice());
    existingBook.setTitle(book.getTitle());
    existingBook.setDescription(book.getDescription());
    existingBook.setAvailable(book.isAvailable());
    bookRepository.save(existingBook);
    return existingBook;
  }

  @Override
  public Book getBook(Long bookId) throws RuntimeException {
    Optional<Book> result = bookRepository.findById(bookId);
    if (result.isPresent()) {
      return result.get();
    }
    throw new ServiceException(ApplicationErrorCodes.BOOK_NOT_FOUND);
  }

  @Transactional
  public BookBorrowHistory borrowBook(Long bookId, Long userId)
      throws RuntimeException {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ServiceException(ApplicationErrorCodes.BOOK_NOT_FOUND));
    if (!book.isAvailable()) {
      throw new ServiceException(ApplicationErrorCodes.BOOK_NOT_AVAILABLE);
    }
    book.setAvailable(false);
    return borrowHistoryService.createBookBorrowHistory(bookId, userId);
  }

  @Transactional
  public BookBorrowHistory returnBook(Long bookId, Long userId) throws RuntimeException {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ServiceException(ApplicationErrorCodes.BOOK_NOT_FOUND));
    book.setAvailable(true);
    BookBorrowHistory bookBorrowHistory = borrowHistoryService.updateReturnHistory(bookId, userId);
    BookReturnedEvent bookReturnEvent = new BookReturnedEvent(this, bookId, userId,
        bookBorrowHistory.isLate());
    eventPublisher.publishEvent(bookReturnEvent);
    return bookBorrowHistory;
  }

}
