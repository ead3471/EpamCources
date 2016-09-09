package com.epam.javase02.t02.chancellery;

/**
 * Created by Freemind on 2016-09-06.
 */
public abstract class Chancellery {
    protected double price=0.0;
    protected String name="Noname";
    protected String manufacturerName="Noname";

    public Chancellery()
    {

    }

    public Chancellery(String manufacturerName,double price)
    {
        this.manufacturerName=manufacturerName;
        this.price=price;
    }

    public Chancellery(String manufacturerName,String name,double price) {
        if(price<0.0)
            throw new IllegalArgumentException("Price must be >0");
        this.price = price;
        this.name = name;
        this.manufacturerName = manufacturerName;

    }

    public double getPrice() {
     return price;
    }

    public String getName(){
        return name;
    }



    String getManufacturerName() {
        return manufacturerName;
    }


    @Override
    public String toString() {
        return "SimpleChancellery{" +
                "price=" + price +
                ", name='" + name + '\''+
                '}';
    }



}
