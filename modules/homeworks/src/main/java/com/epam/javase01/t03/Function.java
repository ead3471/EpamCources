package com.epam.javase01.t03;

import com.epam.javase01.CommonUtils;

/**
 * Составить программу для вычисления значений функции F(x) на отрезке [а, b] с шагом h.
 * Результат представить в виде таблицы, первый столбец которой – значения аргумента,
 * второй - соответствующие значения функции:F(x)=tg(2x)-3
 */
public class Function {

    /**
     *
     * @param args массив входных параметров
     *              args[0] - a
     *              args[1] - b
     *              args[2] - h
     */
    public static void main(String[] args) {

        double a= CommonUtils.tryGetDoubleFromStringArgs(0,0.0,args);
        double b=CommonUtils.tryGetDoubleFromStringArgs(1,2*Math.PI,args);
        double step=CommonUtils.tryGetDoubleFromStringArgs(2,0.1,args);

        if(a>b) {
            System.out.println("Bad input args: a must be less or equal b");
            return;
        }

        if(step<=0.0){
            System.out.println("Bad input args: step must be greater than zero");
            return;
        }

        for(;a<=b;a+=step)
        {
            System.out.println(a+" : "+getFunctionValue(a));
        }
   }

   private static double getFunctionValue(double argument)
   {
       return Math.tan(argument*2)-3;
   }



}
