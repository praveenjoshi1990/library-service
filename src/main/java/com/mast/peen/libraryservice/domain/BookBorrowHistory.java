package com.mast.peen.libraryservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@ToString
@EqualsAndHashCode
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class BookBorrowHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private Long bookId;
  @NotEmpty
  private Long userId;
  @CreatedDate
  @Column(name = "borrowed_at", nullable = false, updatable = false)
  private LocalDateTime borrowedAt;
  @LastModifiedDate
  @Column(name = "returned_at")
  private LocalDateTime returnedAt;
  private boolean late;

}
