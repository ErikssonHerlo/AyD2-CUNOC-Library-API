-- -----------------------------------------------------
-- Migration to alter ISBN_code size in CUNOC_LIBRARY.book
-- -----------------------------------------------------
ALTER TABLE CUNOC_LIBRARY.book
    MODIFY ISBN_code VARCHAR(13) NOT NULL;

-- -----------------------------------------------------
-- Migration to update foreign key references in CUNOC_LIBRARY.loan
-- -----------------------------------------------------
ALTER TABLE CUNOC_LIBRARY.loan
DROP FOREIGN KEY fk_loan_book1;

ALTER TABLE CUNOC_LIBRARY.loan
    MODIFY book_ISBN_code VARCHAR(13) NOT NULL;

ALTER TABLE CUNOC_LIBRARY.loan
    ADD CONSTRAINT fk_loan_book1
        FOREIGN KEY (book_ISBN_code)
            REFERENCES CUNOC_LIBRARY.book (ISBN_code)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Migration to update foreign key references in CUNOC_LIBRARY.reservation
-- -----------------------------------------------------
ALTER TABLE CUNOC_LIBRARY.reservation
DROP FOREIGN KEY fk_reservation_book1;

ALTER TABLE CUNOC_LIBRARY.reservation
    MODIFY book_ISBN_code VARCHAR(13) NOT NULL;

ALTER TABLE CUNOC_LIBRARY.reservation
    ADD CONSTRAINT fk_reservation_book1
        FOREIGN KEY (book_ISBN_code)
            REFERENCES CUNOC_LIBRARY.book (ISBN_code)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;
