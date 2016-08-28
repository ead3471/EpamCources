package com.epam.javase01.t02;

import com.epam.javase01.CommonUtils;

/**
 *Найти наименьший номер элемента последовательности, для которого выполняется условие M. Вывести на экран этот номер и все элементы ai  где i = 1, 2, ..., n.
 *ai=1/(1+n)^2
 * M: an<eps
 */
public class Sequence {

    /**
     *
     * @param args массив входных параметров
     *              args[0] - eps
     */
    public static void main(String[] args) {
        double eps=CommonUtils.tryGetDoubleFromStringArgs(0,0.01,args);

        int sequenceIndex=1;
        double sequenceElement=getSequenceElement(sequenceIndex);

        while(sequenceElement>=eps)
        {
            System.out.print(sequenceElement+" ");
            sequenceElement=getSequenceElement(++sequenceIndex);
        }
        System.out.println("\nFirst sequence element less than "+eps+" is "+sequenceElement+" Index="+sequenceIndex);

    }

    private static double getSequenceElement(int sequenceIndex) {
        return 1.0/Math.pow(sequenceIndex+1,2);
    }
}
