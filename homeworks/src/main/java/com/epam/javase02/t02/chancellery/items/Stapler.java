package com.epam.javase02.t02.chancellery.items;

import com.epam.javase02.t02.chancellery.Chancellery;

/**
 * Created by Freemind on 2016-09-08.
 */
public class Stapler extends Chancellery {
    private static final double DEFAULT_PRICE=20.0;

    {
        name = "Stapler";
    }

    private int clipsSize =10;

    public Stapler(String manufacturerName)
    {
        super(manufacturerName,DEFAULT_PRICE);
    }

    public Stapler(){
       price=DEFAULT_PRICE;
    }

    public Stapler(double staplerPrice)
    {
        price=staplerPrice;
    }
    public Stapler(String manufacturerName, double price){
        super(manufacturerName,price);
    }

    public int getClipsSize()
    {
        return clipsSize;
    }




}
