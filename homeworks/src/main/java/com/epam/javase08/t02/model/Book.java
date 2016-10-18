package com.epam.javase08.t02.model;

import java.time.Year;

/**
 * Created by Freemind on 2016-10-13.
 */
public class Book {
    private String bookName;
    private String authorName;
    private final int publicationYear;
    private String annotation;

    public Book(String bookName, String authorName, int publicationYear, String annotation) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.publicationYear = publicationYear;
        this.annotation = annotation;
    }

    public Book(String bookName, String authorName, int publicationYear) {
        this(bookName,authorName,publicationYear,"");
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPublicationYear() {return publicationYear;}

    public String getAnnotation() {
        return annotation;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", publicationYear=" + publicationYear +
                ", annotation='" + annotation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getPublicationYear() != book.getPublicationYear()) return false;
        if (!getBookName().equals(book.getBookName())) return false;
        if (!getAuthorName().equals(book.getAuthorName())) return false;
        return getAnnotation() != null ? getAnnotation().equals(book.getAnnotation()) : book.getAnnotation() == null;

    }

    @Override
    public int hashCode() {
        int result = getBookName().hashCode();
        result = 31 * result + getAuthorName().hashCode();
        result = 31 * result + getPublicationYear();
        result = 31 * result + (getAnnotation() != null ? getAnnotation().hashCode() : 0);
        return result;
    }
}
