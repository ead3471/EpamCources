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

        Pen pen = (Pen) o;

        if (!manufacturerName.equals(pen.manufacturerName)) return false;
        if (penPasteColor != pen.penPasteColor) return false;
        return penColor == pen.penColor;
    }

    @Override
    public int hashCode() {
        int result = manufacturerName.hashCode();
        result = 43 * result + penPasteColor.hashCode();
        result = 43 * result + penColor.hashCode();
        return result;
    }
}


