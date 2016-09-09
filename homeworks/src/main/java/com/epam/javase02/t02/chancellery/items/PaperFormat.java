package com.epam.javase02.t02.chancellery.items;

/**
 * Created by Freemind on 2016-09-08.
 */
public enum PaperFormat{
    A0(841,1189),
    A1(594,841),
    A2(420,594),
    A3(297,420),
    A4(210,297),
    A5(148,210),
    A6(105,148);

    private double height=0.0;
    private double width=0.0;

    PaperFormat(double width, double height)
    {
        this.height=height;
        this.width=width;
    }

    public double getHeightInCm() {
        return height/10;
    }
    public double getHeightInMm()
    {
        return height;
    }


    public double getWidthInCm()
    {
        return width/10;
    }
    public double getWidthInMm()
    {
        return width;
    }





}
