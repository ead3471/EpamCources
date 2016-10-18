DROP ALL OBJECTS;
CREATE TABLE  IF NOT EXISTS Books (id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    book_name VARCHAR(100) NOT NULL,
                    book_author  VARCHAR(100) NOT NULL,
                    comment VARCHAR(200),
                    UNIQUE (book_name));
INSERT INTO Books (book_name, book_author) VALUES ('Clean Code: A Handbook of Agile Software Craftsmanship', 'Robert C. Martin');
INSERT  INTO Books (book_name, book_author) VALUES ('Design Patterns Elements of Reusable Object-Oriented Software', 'Richard Helm, Ralph Johnson, and John Vlissides');
INSERT INTO Books (book_name, book_author) VALUES ('Domain Driven Design: Tacking the Complexity in the Heart of Software', 'Eric Evans');
INSERT INTO Books (book_name, book_author) VALUES ('Effective Java', 'Joshua Bloch');
INSERT INTO Books (book_name, book_author) VALUES ('Java Puzzlers: Traps, Pitfalls, and Corner Cases', 'Joshua Bloch');
INSERT INTO Books (book_name, book_author) VALUES ('Java Concurrency in Practice', 'Joshua Bloch');
INSERT INTO Books (book_name, book_author) VALUES ('Effective Unit Testing: A guide for Java developers', 'Lasse Koskela');
INSERT INTO Books (book_name, book_author) VALUES ('Patterns of Enterprise Application Architecture', 'Martin Fowler');

