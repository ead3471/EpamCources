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


    public Chancellery( double price)
    {
        this.price=price;
    }


    public Chancellery(String manufacturerName,double price)
    {
        this.manufacturerName=manufacturerName;
        this.price=price;
    }

    public Chancellery(String manufacturerName,String name,double price) {
        if(price<0 || Double.isNaN(price))
            throw new IllegalArgumentException("Price must be >0 and not NaN");
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



    public String getManufacturerName() {
        return manufacturerName;
    }


    @Override
    public String toString() {
        return name+"{manufacturer:"+getManufacturerName()+" price="+getPrice()+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chancellery that = (Chancellery) o;

        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        if (!getName().equals(that.getName())) return false;
        return getManufacturerName().equals(that.getManufacturerName());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getPrice());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getManufacturerName().hashCode();
        return result;
    }
}
