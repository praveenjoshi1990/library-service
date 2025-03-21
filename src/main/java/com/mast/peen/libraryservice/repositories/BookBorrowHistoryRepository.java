package com.mast.peen.libraryservice.repositories;

import com.mast.peen.libraryservice.domain.BookBorrowHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowHistoryRepository extends JpaRepository<BookBorrowHistory, Long> {

  Optional<BookBorrowHistory> findByBookIdAndUserId(Long bookId, Long userId);

}
