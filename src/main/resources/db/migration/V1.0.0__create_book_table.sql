CREATE TABLE if exists book;

CREATE TABLE book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      author VARCHAR(100) NOT NULL,
                      available BOOLEAN DEFAULT TRUE,
                      price DOUBLE NOT NULL,
                      description TEXT
);