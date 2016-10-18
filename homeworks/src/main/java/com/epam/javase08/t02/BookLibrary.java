package com.epam.javase08.t02;

import com.epam.javase08.t01.JDBCTester;
import com.epam.javase08.t02.dao.BookDao;
import com.epam.javase08.t02.dao.BookDaoSql;
import com.epam.javase08.t02.model.Book;
import com.epam.javase08.t02.pool.ConnectionPool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by Freemind on 2016-10-16.
 */
public class BookLibrary {
    private ConnectionPool connectionPool;
    private BookDao bookDao;
    public  void createLibraryBaseFromSqlFile(String fileName) throws FileNotFoundException, SQLException, InterruptedException {
        try(Connection con=connectionPool.takeConnection()){
            JDBCTester.executeSQLFromFile(fileName,con);
        }
    }

    public void initLibraryBaseConnection(String initConnectionFile) throws IOException, SQLException {
        Properties setup=new Properties();
        setup.load(new FileInputStream(initConnectionFile));
        connectionPool=ConnectionPool.getInstance(setup);
        bookDao=new BookDaoSql(connectionPool);
    }

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public Optional<Book> getBookById(int id){
        return bookDao.getBookById(id);
    }

    public void addBook(Book book){
        bookDao.addBookToBase(book);
    }
    public void addBooks(List<Book> books){
        bookDao.addBooks(books);
    }
    public void removeBook(Book book){bookDao.removeBook(book);}
    public List<Book> getBooksByAuthor(String author){
        return bookDao.getBooksByAuthor(author);
    }
    public List<Book> getBooksByName(String name){
        return bookDao.getBooksByName(name);
    }

    public List<Book> getBooksByPublishingYear(int year){
        return bookDao.getBooksByPublishingYear(year);
    }






}
