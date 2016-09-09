package com.epam.javase02.t02.chancellery.items;

import com.epam.javase02.t02.chancellery.Chancellery;

/**
 * Created by Freemind on 2016-09-08.
 */
public class Stepler extends Chancellery {
    private static final double DEFAULT_PRICE=20.0;

    {
        name = "Stepler";
    }

    private int size=10;

    public Stepler(String manufacturerName)
    {
        super(manufacturerName,DEFAULT_PRICE);
    }

    public Stepler(){
       price=DEFAULT_PRICE;
    }

    public Stepler(String manufacturerName,double price){
        super(manufacturerName,price);
    }

    public int getSize()
    {
        return size;
    }




}
