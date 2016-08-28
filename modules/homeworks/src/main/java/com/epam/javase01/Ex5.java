package com.epam.javase01;

import java.util.Random;

public class Ex5 {

    public static void main(String[] args) {

        String[][] testingArray=new String[10][10];
        fillArray(testingArray,new StringElementProducer(new IntegerElementProducer()));

        System.out.println(getArrayAsString(testingArray));

        fillArrayCrossed(testingArray,"+");

        System.out.println(getArrayAsString(testingArray));


    }

    private static <T> void fillArray(T[][] targetArray, ElementProducer<T> producer)
    {
        for (int i = 0; i <targetArray.length ; i++) {
            for (int j = 0; j < targetArray[i].length; j++) {
                targetArray[i][j]=producer.getNextElement();
            }
        }
    }

    private static <T> String getArrayAsString(T[][] targetArray)
    {
        StringBuilder stringBuilder=new StringBuilder();

        String format="%1$"+(int)Math.log10(targetArray.length*targetArray[0].length)*2+"s";
        for (int i=0;i<targetArray.length;i++)
        {
            for (int j=0;j<targetArray[i].length;j++)
            {
                stringBuilder.append(String.format(format,targetArray[i][j].toString())).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static<T> void fillArrayCrossed(T[][] targetArray, T fillElement)
    {
        if (targetArray.length==0 || targetArray[0].length==0)
            throw new IllegalArgumentException("Bad Array Form. Must be non 0 length");

        int arrayWidth=targetArray.length;
        int arrayHeight=targetArray[0].length;

        int squareEdge=Math.min(arrayHeight,arrayWidth)-1;
        int startPosition=0;
        int endPosition=squareEdge;

        try {
            while(startPosition<=squareEdge)
            {
                targetArray[startPosition][startPosition]=fillElement;
                targetArray[startPosition++][endPosition--]=fillElement;
            }
        }
        catch(IndexOutOfBoundsException ex)
        {

            throw new IllegalArgumentException("Wrong array format: target array must be square");
        }

    }
}

interface ElementProducer<T>
{
    public T getNextElement();
}

class IntegerElementProducer implements ElementProducer
{
    int element;

    public Integer getNextElement()
    {
        return element++;
    }

}

class RandomFloatElementProducer implements ElementProducer
{
    Random rand=new Random();


    public Float getNextElement()
    {
        return rand.nextFloat();
    }
}

class StringElementProducer implements ElementProducer
{
    ElementProducer filler=null;
    StringElementProducer(ElementProducer filler)
    {
        this.filler=filler;
    }

    public String getNextElement()
    {
        return filler.getNextElement().toString();
    }


}

class FibonacciElementProducer implements ElementProducer
{
    private long element1=0;
    private long element2=1;

    public  Long getNextElement()
    {
        long result=element1+element2;
        element1=element2;
        element2=result;
        return result;
    }
}