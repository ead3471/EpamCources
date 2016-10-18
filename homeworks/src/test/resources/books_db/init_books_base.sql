DROP ALL OBJECTS;
CREATE TABLE  IF NOT EXISTS Books (id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    author  VARCHAR(100) NOT NULL,
                    publicationYear INT NOT NULL,
                    annotation VARCHAR(200),
                    UNIQUE (name));
INSERT INTO Books (name, author,publicationYear) VALUES ('Clean Code: A Handbook of Agile Software Craftsmanship', 'Robert C. Martin','2011');
INSERT  INTO Books (name, author,publicationYear) VALUES ('Design Patterns Elements of Reusable Object-Oriented Software', 'Richard Helm, Ralph Johnson, and John Vlissides','2011');
INSERT INTO Books (name, author,publicationYear) VALUES ('Domain Driven Design: Tacking the Complexity in the Heart of Software', 'Eric Evans','2011');
INSERT INTO Books (name, author,publicationYear) VALUES ('Effective Java', 'Joshua Bloch','2011');
INSERT INTO Books (name, author,publicationYear) VALUES ('Java Puzzlers: Traps, Pitfalls, and Corner Cases', 'Joshua Bloch','2011');
INSERT INTO Books (name, author,publicationYear) VALUES ('Java Concurrency in Practice', 'Joshua Bloch','2011');
INSERT INTO Books (name, author,publicationYear) VALUES ('Effective Unit Testing: A guide for Java developers', 'Lasse Koskela','2011');
INSERT INTO Books (name, author,publicationYear) VALUES ('Patterns of Enterprise Application Architecture', 'Martin Fowler','2011');

