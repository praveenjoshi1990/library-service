DROP TABLE if exists book_borrow_history;

CREATE TABLE book_borrow_history (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     book_id BIGINT NOT NULL,
                                     user_id BIGINT NOT NULL,
                                     borrowed_at TIMESTAMP NOT NULL,
                                     returned_at TIMESTAMP,
                                     late BOOLEAN DEFAULT FALSE,
                                     FOREIGN KEY (book_id) REFERENCES book(id),
                                     FOREIGN KEY (user_id) REFERENCES user(id)
);