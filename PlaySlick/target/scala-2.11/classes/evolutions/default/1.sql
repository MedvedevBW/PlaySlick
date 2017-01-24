CREATE TABLE address (
                address_id INT AUTO_INCREMENT NOT NULL,
                street VARCHAR(100) NOT NULL,
                PRIMARY KEY (address_id)
);

CREATE TABLE person (
                person_id INT AUTO_INCREMENT NOT NULL,
                name VARCHAR(50) NOT NULL,
                PRIMARY KEY (person_id)
);

CREATE TABLE phone (
                phone_id INT AUTO_INCREMENT NOT NULL,
                person_id INT NOT NULL,
                number VARCHAR(50) NOT NULL,
                PRIMARY KEY (phone_id, person_id)
);

CREATE TABLE person_address (
                person_id INT NOT NULL,
                address_id INT NOT NULL,
                PRIMARY KEY (person_id, address_id)
);

CREATE TABLE author (
                author_id INT AUTO_INCREMENT NOT NULL,
                name VARCHAR(50) NOT NULL,
                surname VARCHAR(50) NOT NULL,
                middlename VARCHAR(50),
                birthday DATE
                PRIMARY KEY (author_id)
);

CREATE TABLE book (
                book_id INT AUTO_INCREMENT NOT NULL,
                title VARCHAR(50) NOT NULL,
                subtitle VARCHAR(50),
                pubDate DATE,
                pubHouse VARCHAR(50) NOT NULL,
                PRIMARY KEY (book_id)
);

CREATE TABLE author_book (
                author_id INT NOT NULL,
                book_id INT NOT NULL,
                PRIMARY KEY (author_id, book_id)
);


ALTER TABLE author_book ADD CONSTRAINT author_author_book_fk
FOREIGN KEY (author_id)
REFERENCES author (author_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE author_book ADD CONSTRAINT book_author_book_fk
FOREIGN KEY (book_id)
REFERENCES book (book_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE person_address ADD CONSTRAINT address_person_address_fk
FOREIGN KEY (address_id)
REFERENCES address (address_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE person_address ADD CONSTRAINT person_person_address_fk
FOREIGN KEY (person_id)
REFERENCES person (person_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE phone ADD CONSTRAINT person_phone_fk
FOREIGN KEY (person_id)
REFERENCES person (person_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;