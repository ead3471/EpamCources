package com.epam.javase02.t02.chancellery.items;

import com.epam.javase02.t01.Color;
import com.epam.javase02.t02.chancellery.Chancellery;

import java.util.Objects;

/**
 * Created by Freemind on 2016-09-08.
 */
public class ScotchTape extends Chancellery {
    private int widthInMm=25;
    private Color tapeColor=Color.TRANSPARENT;

    @Override
    public boolean equals(Object o) {
        if (o==null)return false;
        if (this == o) return true;

        if (!super.equals(o)) return false;
        ScotchTape that = (ScotchTape) o;
        return getWidthInMm() == that.getWidthInMm() &&
                tapeColor == that.tapeColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWidthInMm(), tapeColor);
    }

    private final static double DEFAULT_PRICE=50.0;
    {
        name="ScotchTape";
    }

    public ScotchTape(String manufacturerName,double price,Color tapeColor,int widthInMm) {
        super(manufacturerName,price);
        this.widthInMm=widthInMm;
        this.tapeColor=tapeColor;
    }

    public ScotchTape(String manufacturerName,double price,Color tapeColor) {
        super(manufacturerName,price);
        this.tapeColor=tapeColor;
    }

    public ScotchTape(String manufacturerName,double price) {
        super(manufacturerName,price);
    }

    public ScotchTape(double price){
        this.price=price;
    }

    public ScotchTape(){
        price=DEFAULT_PRICE;
    }

    public int getWidthInMm()
    {
        return widthInMm;
    }
    public double getWidthInCm()
    {
        return widthInMm/10.0;
    }

    public Color getColor() {
        return tapeColor;
    }
}
