package com.epam.javase08.t02;

import com.epam.javase08.t02.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-10-17.
 */
public class BookLibraryTest {
    org.apache.logging.log4j.Logger logger= LogManager.getLogger(BookLibraryTest.class);

    @Test
    public void testLibraryFunctionality() throws IOException, SQLException, InterruptedException {
        BookLibrary library=createTestBookLibrary();
        assertThat(library.getAllBooks().size(),is(8));

        library.addBook(new Book("War and Peace","Tolstoy Leo",2001,"very very big book"));

        assertThat(library.getAllBooks().size(),is(9));

        assertThat(library.getBooksByAuthor("Tolstoy Leo").get(0).getAuthorName(),is("Tolstoy Leo"));
        assertThat(library.getBooksByAuthor("Tolstoy Leo").size(),is(1));


        assertThat(library.getBooksByName("War and Peace").get(0).getBookName(),is("War and Peace"));
        assertThat(library.getBooksByName("War and Peace").size(),is(1));

        assertThat(library.getBooksByPublishingYear(2001).get(0).getPublicationYear(),is(2001));
        assertThat(library.getBooksByPublishingYear(2001).size(),is(1));
    }

    private BookLibrary createTestBookLibrary() throws IOException, InterruptedException, SQLException {
        BookLibrary library=new BookLibrary();
        library.initLibraryBaseConnection("src/test/resources/books_db/book_base_prop.txt");
        library.createLibraryBaseFromSqlFile("src/test/resources/books_db/init_books_base.sql");
        return library;
    }



}