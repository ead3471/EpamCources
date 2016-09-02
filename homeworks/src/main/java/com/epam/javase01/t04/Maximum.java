package com.epam.javase01.t04;

import com.epam.javase01.CommonUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * Даны действительные числа a1, a2 … a2n.  Найти max(a[1]+a[2n],a[2]+a[2n-1],....a[n]+a[n+1])
 */
public class Maximum {
    /**
     *
     * @param args массив входных параметров
     *              args[0] - количество элементов входного массива. Должно быть четным
     */
    public static void main(String[] args) {

        int arraySize= CommonUtils.tryGetIntFromStringArgs(0,6,args);

        double[] sourceArray= createRandomDoubleArray(arraySize);
        System.out.println(Arrays.toString(sourceArray));
        System.out.println(findMax(sourceArray));

    }

    private static double[] createRandomDoubleArray(int n)
    {
        Random r=new Random();
        double[] resultArray=new double[n];
        for (int i = 0; i <n ; i++) {
            resultArray[i]=r.nextDouble()*100;
        }
        return resultArray;
    }
    
    private static double findMax(double[] targetArray)
    {
        double maxSum=targetArray[0]+targetArray[targetArray.length-1];

        for(int i=1;i<targetArray.length/2-1;i++)
        {
            double nextSum=targetArray[i]+targetArray[targetArray.length-1-i];
            if (nextSum>maxSum)
            {
                maxSum=nextSum;
            }
        }
        return  maxSum;
    }
}
