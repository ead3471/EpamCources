package com.epam.javase02.t02.chancellery.items;

import com.epam.javase02.t02.chancellery.Chancellery;

/**
 * Created by Freemind on 2016-09-06.
 */
public class SimpleChancellery extends Chancellery {
    private double price=0.0;

    public SimpleChancellery(String manufacturerName, String name, double price) {

        super(manufacturerName, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleChancellery that = (SimpleChancellery) o;


        if (Double.compare(that.price, price) != 0) return false;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
