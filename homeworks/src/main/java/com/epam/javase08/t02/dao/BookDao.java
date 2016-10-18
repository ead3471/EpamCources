package com.epam.javase08.t02.dao;

import com.epam.javase08.t02.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Created by Freemind on 2016-10-16.
 */
public abstract class BookDao {

    protected final static Logger logger= LogManager.getLogger(BookDaoSql.class);

    abstract public Optional<Book> getBookById(int id);
    abstract public List<Book> getAllBooks();
    abstract public void addBooks(List<Book> books);
    abstract public void addBookToBase(Book book);
    abstract public int removeBook(Book bookForRemoving);
    abstract public List<Book> getBooksByAuthor(String authorName);
    abstract public List<Book> getBooksByPublishingYear(int publishingYear);
    abstract public List<Book> getBooksByName(String bookName);
}
