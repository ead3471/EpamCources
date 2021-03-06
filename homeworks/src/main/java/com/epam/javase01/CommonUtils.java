package com.epam.javase01;

/**
 * Created by Freemind on 2016-08-27.
 */
public class CommonUtils {

    public static double tryGetDoubleFromString(String doubleString, double defaultValue)
    {
        try {
            return Double.parseDouble(doubleString);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Bad String to double conversion:"+doubleString);
            return defaultValue;
        }
    }

    public static int tryGetIntFromString(String intString, int defaultValue)
    {
        try {
            return Integer.parseInt(intString);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Bad String to double conversion:"+intString);
            return defaultValue;
        }
    }

    public static double tryGetDoubleFromStringArgs(int argumentIndex,double defaultValue,String... arguments)
    {
        if (arguments.length-1<argumentIndex)
            return defaultValue;

        try {
            return Double.parseDouble(arguments[argumentIndex]);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Bad argument format:"+arguments[argumentIndex]);
            return defaultValue;
        }
    }

    public static int tryGetIntFromStringArgs(int argumentIndex,int defaultValue,String... arguments)
    {
        if (arguments.length-1<argumentIndex)
            return defaultValue;

        try {
            return Integer.parseInt(arguments[argumentIndex]);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Bad argument format:"+arguments[argumentIndex]);
            return defaultValue;
        }

    }



}
