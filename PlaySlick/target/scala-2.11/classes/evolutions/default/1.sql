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