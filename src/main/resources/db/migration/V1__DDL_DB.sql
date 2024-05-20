-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.BOOK
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.BOOK (
                                                      ISBN_code VARCHAR(11) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    description TEXT NULL,
    cover_image BLOB NULL,
    publication_date DATETIME NULL,
    editorial VARCHAR(255) NULL,
    status ENUM('available', 'not_available') NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ISBN_code));
    


-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.CARREER
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.CARREER (
                                                         code VARCHAR(45) NOT NULL,
    name VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (code));
    


-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.USER
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.USER (
                                                      username VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    CARREER_code VARCHAR(45) NULL,
    role ENUM('librarian', 'student') NOT NULL,
    dob DATE NULL,
    password VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (username),
    INDEX fk_USER_CARREER_idx (CARREER_code ASC) VISIBLE,
    CONSTRAINT fk_USER_CARREER
    FOREIGN KEY (CARREER_code)
    REFERENCES CUNOC_LIBRARY.CARREER (code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    


-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.LOAN
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.LOAN (
                                                      id INT NOT NULL AUTO_INCREMENT,
                                                      BOOK_ISBN_code VARCHAR(11) NOT NULL,
    USER_username VARCHAR(255) NOT NULL,
    loan_date DATETIME NULL,
    return_date DATETIME NULL,
    status ENUM('lended', 'returned', 'in_default') NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_LOAN_USER1_idx (USER_username ASC) VISIBLE,
    INDEX fk_LOAN_BOOK1_idx (BOOK_ISBN_code ASC) VISIBLE,
    CONSTRAINT fk_LOAN_USER1
    FOREIGN KEY (USER_username)
    REFERENCES CUNOC_LIBRARY.USER (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_LOAN_BOOK1
    FOREIGN KEY (BOOK_ISBN_code)
    REFERENCES CUNOC_LIBRARY.BOOK (ISBN_code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    


-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.RESERVATION
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.RESERVATION (
                                                             id INT NOT NULL AUTO_INCREMENT,
                                                             USER_username VARCHAR(255) NOT NULL,
    BOOK_ISBN_code VARCHAR(11) NOT NULL,
    reservation_date DATE NOT NULL,
    status ENUM('active', 'expired') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_RESERVATION_USER1_idx (USER_username ASC) VISIBLE,
    INDEX fk_RESERVATION_BOOK1_idx (BOOK_ISBN_code ASC) VISIBLE,
    CONSTRAINT fk_RESERVATION_USER1
    FOREIGN KEY (USER_username)
    REFERENCES CUNOC_LIBRARY.USER (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_RESERVATION_BOOK1
    FOREIGN KEY (BOOK_ISBN_code)
    REFERENCES CUNOC_LIBRARY.BOOK (ISBN_code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    


-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.PAYMENT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.PAYMENT (
                                                         id INT NOT NULL AUTO_INCREMENT,
                                                         LOAN_id INT NOT NULL,
                                                         payment_amount DECIMAL(8,2) NULL,
    payment_date DATETIME NULL,
    default_amount DECIMAL(8,2) NULL,
    register_date DATETIME NULL,
    total_amount VARCHAR(45) NULL,
    status ENUM('paid', 'pending') NULL,
    PRIMARY KEY (id),
    INDEX fk_PAYMENT_LOAN1_idx (LOAN_id ASC) VISIBLE,
    CONSTRAINT fk_PAYMENT_LOAN1
    FOREIGN KEY (LOAN_id)
    REFERENCES CUNOC_LIBRARY.LOAN (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    

-- -----------------------------------------------------
-- Table CUNOC_LIBRARY.NOTIFICATION
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUNOC_LIBRARY.NOTIFICATION (
                                                              id INT NOT NULL AUTO_INCREMENT,
                                                              USER_username VARCHAR(255) NOT NULL,
    message VARCHAR(255) NULL,
    status ENUM('read', 'not_read') NULL,
    created_at DATETIME NULL,
    LOAN_id INT NULL,
    RESERVATION_id INT NULL,
    PAYMENT_id INT NULL,
    PRIMARY KEY (id),
    INDEX fk_NOTIFICATION_USER1_idx (USER_username ASC) VISIBLE,
    INDEX fk_NOTIFICATION_LOAN1_idx (LOAN_id ASC) VISIBLE,
    INDEX fk_NOTIFICATION_RESERVATION1_idx (RESERVATION_id ASC) VISIBLE,
    INDEX fk_NOTIFICATION_PAYMENT1_idx (PAYMENT_id ASC) VISIBLE,
    CONSTRAINT fk_NOTIFICATION_USER1
    FOREIGN KEY (USER_username)
    REFERENCES CUNOC_LIBRARY.USER (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_NOTIFICATION_LOAN1
    FOREIGN KEY (LOAN_id)
    REFERENCES CUNOC_LIBRARY.LOAN (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_NOTIFICATION_RESERVATION1
    FOREIGN KEY (RESERVATION_id)
    REFERENCES CUNOC_LIBRARY.RESERVATION (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_NOTIFICATION_PAYMENT1
    FOREIGN KEY (PAYMENT_id)
    REFERENCES CUNOC_LIBRARY.PAYMENT (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
