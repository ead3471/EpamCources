package com.epam.javase01;

/**
 * Created by Freemind on 2016-08-27.
 */
public class Ex3 {

    public static void main(String[] args) {
        double a=0;
        double b=2*Math.PI;
        double step=0.001;
        int stepsNumber=(int)((b-a)/step+1);

        double[][] resultArray=new double[2][stepsNumber];

        for (int i = 0; i < stepsNumber; i++) {
            resultArray[0][i]=a;
            resultArray[1][i]=Math.tan(a*2)-3;
            System.out.println(resultArray[0][i]+"="+resultArray[1][i]);
            a+=step;
        }
   }
}
