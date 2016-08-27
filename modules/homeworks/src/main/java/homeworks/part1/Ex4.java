package homeworks.part1;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Freemind on 2016-08-27.
 */
public class Ex4 {

    public static void main(String[] args) {

        double[] d= createRandomarray(3);
        System.out.println(Arrays.toString(d));

        System.out.println(findMax(d));

    }

    private static double[] createRandomarray(int n)
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
        double maxSumm=targetArray[0]+targetArray[targetArray.length-1];

        
        for(int i=1;i<targetArray.length/2-1;i++)
        {
            double nextSumm=targetArray[i]+targetArray[targetArray.length-1-i];
            if (nextSumm>maxSumm)
            {
                maxSumm=nextSumm;
            }
        }

        return  maxSumm;
    }
}
