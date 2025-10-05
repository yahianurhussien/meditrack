CREATE TABLE patient (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(100),
                         last_name VARCHAR(100),
                         phone_number VARCHAR(100),
                         email VARCHAR(100),
                         date_of_birth DATE
);