CREATE TABLE if exists user;

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(100) NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      role VARCHAR(20) NOT NULL,
                      email VARCHAR(255)
);