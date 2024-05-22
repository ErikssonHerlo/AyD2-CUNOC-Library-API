-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.book
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.book (
                                                      ISBN_code VARCHAR(11) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    description TEXT NULL,
    cover_image TEXT NULL,
    publication_date DATETIME NULL,
    editorial VARCHAR(255) NULL,
    status ENUM('available', 'not_available') NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ISBN_code));



-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.career
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.career (
                                                         code VARCHAR(45) NOT NULL,
    name VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (code));



-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.user (
                                                      username VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    career_code VARCHAR(45) NULL,
    role ENUM('librarian', 'student') NOT NULL,
    dob DATE NULL,
    password VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (username),
    INDEX fk_user_career_idx (career_code ASC) VISIBLE,
    CONSTRAINT fk_user_career
    FOREIGN KEY (career_code)
    REFERENCES CUNOC_LIBRARY.career (code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.loan
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.loan (
                                                      id INT NOT NULL AUTO_INCREMENT,
                                                      book_ISBN_code VARCHAR(11) NOT NULL,
    user_username VARCHAR(255) NOT NULL,
    loan_date DATETIME NULL,
    return_date DATETIME NULL,
    status ENUM('lent', 'returned', 'in_default') NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_loan_user1_idx (user_username ASC) VISIBLE,
    INDEX fk_loan_book1_idx (book_ISBN_code ASC) VISIBLE,
    CONSTRAINT fk_loan_user1
    FOREIGN KEY (user_username)
    REFERENCES CUNOC_LIBRARY.user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_loan_book1
    FOREIGN KEY (book_ISBN_code)
    REFERENCES CUNOC_LIBRARY.book (ISBN_code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.reservation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.reservation (
                                                             id INT NOT NULL AUTO_INCREMENT,
                                                             user_username VARCHAR(255) NOT NULL,
    book_ISBN_code VARCHAR(11) NOT NULL,
    reservation_date DATE NOT NULL,
    status ENUM('active', 'expired', 'canceled') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_reservation_user1_idx (user_username ASC) VISIBLE,
    INDEX fk_reservation_book1_idx (book_ISBN_code ASC) VISIBLE,
    CONSTRAINT fk_reservation_user1
    FOREIGN KEY (user_username)
    REFERENCES CUNOC_LIBRARY.user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_reservation_book1
    FOREIGN KEY (book_ISBN_code)
    REFERENCES CUNOC_LIBRARY.book (ISBN_code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.payment (
                                                         id INT NOT NULL AUTO_INCREMENT,
                                                         loan_id INT NOT NULL,
                                                         payment_amount DECIMAL(8,2) NULL,
    payment_date DATETIME NULL,
    default_amount DECIMAL(8,2) NULL,
    register_date DATETIME NULL,
    total_amount VARCHAR(45) NULL,
    status ENUM('pending', 'paid') NULL,
    PRIMARY KEY (id),
    INDEX fk_payment_loan1_idx (loan_id ASC) VISIBLE,
    CONSTRAINT fk_payment_loan1
    FOREIGN KEY (loan_id)
    REFERENCES CUNOC_LIBRARY.loan (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.notification
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.notification (
                                                              id INT NOT NULL AUTO_INCREMENT,
                                                              user_username VARCHAR(255) NOT NULL,
    message VARCHAR(255) NULL,
    status ENUM('read', 'not_read') NULL,
    created_at DATETIME NULL,
    loan_id INT NULL,
    reservation_id INT NULL,
    payment_id INT NULL,
    PRIMARY KEY (id),
    INDEX fk_notification_user1_idx (user_username ASC) VISIBLE,
    INDEX fk_notification_loan1_idx (loan_id ASC) VISIBLE,
    INDEX fk_notification_reservation1_idx (reservation_id ASC) VISIBLE,
    INDEX fk_notification_payment1_idx (payment_id ASC) VISIBLE,
    CONSTRAINT fk_notification_user1
    FOREIGN KEY (user_username)
    REFERENCES CUNOC_LIBRARY.user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_notification_loan1
    FOREIGN KEY (loan_id)
    REFERENCES CUNOC_LIBRARY.loan (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_notification_reservation1
    FOREIGN KEY (reservation_id)
    REFERENCES CUNOC_LIBRARY.reservation (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_notification_payment1
    FOREIGN KEY (payment_id)
    REFERENCES CUNOC_LIBRARY.payment (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
