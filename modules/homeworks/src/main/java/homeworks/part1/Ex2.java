package homeworks.part1;

import java.util.Arrays;

/**
 * Created by Freemind on 2016-08-27.
 */
public class Ex2 {

    public static void main(String[] args) {


        double[] array = createSequenceToArray(10);
        double eps = 0.01;
        System.out.println("Sequence array: " + Arrays.toString(array));
        int minimalIndex = findMinimalIndex(eps);
        System.out.println("Minimal n for eps=" + eps + " is " + minimalIndex);
        System.out.println("Check:" + (array[minimalIndex] < eps && array[minimalIndex - 1] >= eps));
    }

    private static double[] createSequenceToArray(int arraySize)
    {
        double[] resultArray=new double[arraySize];
        for (int i = 0; i <arraySize ; i++) {
            resultArray[i]=1/Math.pow(i+2,2);
        }
        return resultArray;
    }

    private static int findMinimalIndex(double condition)
    {
        //a[n]=1/(n+1)**2<eps
        //

        if(condition<=0) return 0;
        return (int)(Math.sqrt(1/condition)-1);
    }

    private boolean testFoundedIndex(double[] targetArray,double eps,int foundedIndex)
    {

    }
}
