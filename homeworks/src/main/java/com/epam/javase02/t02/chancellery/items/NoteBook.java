package com.epam.javase02.t02.chancellery.items;

import com.epam.javase02.t02.chancellery.Chancellery;

/**
 * Created by Freemind on 2016-09-08.
 */
public class NoteBook extends Chancellery
{
    private int numberOfPages =96;
    private  PaperFormat format=PaperFormat.A4;
    private static final double DEFAULT_PRICE=15.0;

    {
        name="NoteBook";
    }

    public NoteBook()
    {
       price=DEFAULT_PRICE;
    }


    public NoteBook(String manufacturerName, double price, PaperFormat paperFormat,int numberOfPages)
    {
        super(manufacturerName,price);
        this.numberOfPages =numberOfPages;
        this.format=paperFormat;
    }

    public NoteBook(String manufacturerName, double price, PaperFormat paperFormat)
    {
        super(manufacturerName,price);
        this.format=paperFormat;
    }

   public NoteBook(String manufacturerName, double price)
    {
        super(manufacturerName,price);
    }

    public NoteBook(double price)
    {
        super(price);
    }

    public PaperFormat getPaperFormat()
    {
        return format;
    }

    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NoteBook noteBook = (NoteBook) o;

        if (getNumberOfPages() != noteBook.getNumberOfPages()) return false;
        return format == noteBook.format;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getNumberOfPages();
        result = 31 * result + format.hashCode();
        return result;
    }
}


