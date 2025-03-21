package com.mast.peen.libraryservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@ToString
@EqualsAndHashCode
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private String title;
  @NotEmpty
  private String author;
  private boolean available;
  @JsonIgnore
  @NotEmpty
  private double price; // In case book is lost
  private String description;

}
