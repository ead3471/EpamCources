package com.epam.javase02.t01;

import com.epam.javase02.t02.chancellery.Chancellery;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by Freemind on 2016-09-06.
 */
public class Pen extends Chancellery {

    private Color penPasteColor = Color.BLACK;
    private Color penColor=Color.BLACK;

    private final static double  DEFAULT_PRICE=10.0;

    {
        name="Pen";
    }

    public Pen() {
        price=DEFAULT_PRICE;
    }

    public Pen(String manufacturerName,double price, Color penColor) {
        this(manufacturerName,price,penColor,Color.BLACK);
    }

    public Pen(String manufacturerName,double price,Color penColor, Color penPasteColor) {
        super(manufacturerName,"Pen",price);
        this.penColor = penColor;
        this.penPasteColor=penPasteColor;
    }

    public Pen(double price)
    {
        this.price=price;
    }

    public Color getPenPasteColor() {
        return penPasteColor;
    }

    public Color getPenColor() {
        return penColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pen pen = (Pen) o;

        if (getPenPasteColor() != pen.getPenPasteColor()) return false;
        return getPenColor() == pen.getPenColor();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPenPasteColor().hashCode();
        result = 31 * result + getPenColor().hashCode();
        return result;
    }
}


