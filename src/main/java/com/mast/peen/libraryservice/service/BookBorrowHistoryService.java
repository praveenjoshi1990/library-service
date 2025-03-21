package com.mast.peen.libraryservice.service;

import com.mast.peen.libraryservice.domain.BookBorrowHistory;
import com.mast.peen.libraryservice.exceptions.ApplicationErrorCodes;
import com.mast.peen.libraryservice.exceptions.ServiceException;
import com.mast.peen.libraryservice.repositories.BookBorrowHistoryRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookBorrowHistoryService {

  @Value("${library.noOfDaysAllowed}")
  private int noOfDaysAllowed;

  private final BookBorrowHistoryRepository borrowHistoryRepository;

  public BookBorrowHistory getBorrowHistory(Long orderId) {
    Optional<BookBorrowHistory> result = borrowHistoryRepository.findById(orderId);
    if (result.isPresent()) {
      return result.get();
    }
    throw new ServiceException(ApplicationErrorCodes.BORROW_HISTORY_NOT_FOUND);
  }


  public BookBorrowHistory createBookBorrowHistory(Long bookId, Long userId) {
    BookBorrowHistory borrowingHistory = new BookBorrowHistory();
    borrowingHistory.setBookId(bookId);
    borrowingHistory.setUserId(userId);
    borrowingHistory.setBorrowedAt(LocalDateTime.now());
    borrowingHistory.setReturnedAt(null);
    borrowingHistory.setLate(false);
    return borrowHistoryRepository.save(borrowingHistory);
  }

  public BookBorrowHistory updateReturnHistory(Long bookId, Long userId) {
    BookBorrowHistory borrowingHistory = borrowHistoryRepository
        .findByBookIdAndUserId(bookId, userId)
        .orElseThrow(
            () -> new ServiceException(ApplicationErrorCodes.BORROW_HISTORY_NOT_AVAILABLE));
    borrowingHistory.setReturnedAt(LocalDateTime.now());
    borrowingHistory.setLate(isLate(borrowingHistory.getBorrowedAt()));
    return borrowHistoryRepository.save(borrowingHistory);
  }

  private boolean isLate(LocalDateTime borrowedAt) {
    return LocalDateTime.now().isAfter(borrowedAt.plusDays(noOfDaysAllowed));
  }

}
