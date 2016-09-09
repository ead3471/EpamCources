package com.epam.javase02.t02.chancellery.items;

import com.epam.javase02.t02.chancellery.Chancellery;

/**
 * Created by Freemind on 2016-09-08.
 */
public class NoteBook extends Chancellery
{
    private int numberOfPapers =96;
    private  PaperFormat format=PaperFormat.A4;
    private static final double DEFAULT_PRICE=15.0;

    {
        name="NoteBook";
    }

    public NoteBook()
    {
       price=DEFAULT_PRICE;
    }


    public NoteBook(String manufacturerName, PaperFormat paperFormat, double price)
    {
        super(manufacturerName,price);
        this.format=paperFormat;
    }

    public PaperFormat getFormat()
    {
        return format;
    }

    public int getNumberOfPapers()
    {
        return numberOfPapers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteBook)) return false;

        NoteBook noteBook = (NoteBook) o;


        if(this.manufacturerName!= noteBook.manufacturerName) return false;
        if(this.format!= noteBook.format) return false;

        if(this.price!= noteBook.price) return false;
        if (getNumberOfPapers() != noteBook.getNumberOfPapers()) return false;
        return getFormat() == noteBook.getFormat();

    }

    @Override
    public int hashCode() {
        int result = getNumberOfPapers();
        result = 31 * result + getFormat().hashCode();
        return result;
    }
}


