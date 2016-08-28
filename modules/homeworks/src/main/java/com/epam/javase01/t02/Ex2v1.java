package com.epam.javase01.t02;

import com.epam.javase01.CommonUtils;

/**
 *Найти наименьший номер элемента последовательности, для которого выполняется условие M. Вывести на экран этот номер и все элементы ai  где i = 1, 2, ..., n.
 *ai=1/(1+n)^2
 * M: an<eps
 */
public class Ex2v1 {

    public static void main(String[] args) {
        double eps=0.001;

        if(args.length>0)
            eps= CommonUtils.tryGetDoubleFromString(args[0],0.01);

        int sequenceIndex=1;
        double sequenceElement=getSequenceElement(sequenceIndex);

        while(sequenceElement>=eps)
        {
            System.out.println(sequenceIndex+":"+sequenceElement);
            sequenceElement=getSequenceElement(++sequenceIndex);
        }

        System.out.println("First less than eps="+eps+" "+sequenceIndex+":"+sequenceElement);
    }

    private static double getSequenceElement(int sequenceIndex)
    {
        return 1.0/Math.pow(sequenceIndex+1,2);
    }
}
