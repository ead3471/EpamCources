package homeworks.part1;

import javax.xml.transform.sax.SAXSource;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Freemind on 2016-08-27.
 */
public class Ex2 {

    public static void main(String[] args) {
        double eps=0.01;
        int maxSequenceIndex=5;

        if(args.length>0)
            eps=CommonUtils.tryGetDoubleFromString(args[0],0.01);
        if (args.length>1)
            maxSequenceIndex=CommonUtils.tryGetIntFromString(args[1],10);


        double[] array = createSequenceArray(maxSequenceIndex);

        System.out.println("Sequence array: " + Arrays.toString(array));
        int foundIndex = findMinimalIndex(eps,array.length);
        System.out.println("Minimal n for eps=" + eps + " is " + (foundIndex+1)+ " check:"+testFoundedIndex(array,eps,foundIndex));

        randomTests(5000);
        System.out.println(testBiggerThanLast());
        System.out.println(testLessThanFirst());
        System.out.println(testNegative());
        System.out.println(testOneElementSequence());

    }

    private static double[] createSequenceArray(int arraySize)
    {
        double[] resultArray=new double[arraySize];
        for (int i = 0; i <arraySize ; i++) {
            resultArray[i]=1/Math.pow(i+2,2);
        }
        return resultArray;
    }

    private static int findMinimalIndex(double condition, int arrayLength)
    {
        if(condition<=0) return 0;
        int result=(int)(Math.sqrt(1/condition)-1);

        if(result>=arrayLength)
            return -1;

        return  result;
    }

    private static boolean testFoundedIndex(double[] targetArray,double eps,int foundIndex)
    {
        if(foundIndex<0){
            return targetArray[targetArray.length-1]>=eps;
        }
        return foundIndex>0?targetArray[foundIndex]<eps&&targetArray[foundIndex-1]>=0:targetArray[1]<eps;
    }

    private static void randomTests( int testsNumber)
    {


        Random r=new Random();
        double[] array= createSequenceArray(50);
        boolean allPassed=true;

        while(testsNumber>0) {
            double eps = r.nextDouble() / 400;
            int foundIndex = findMinimalIndex(eps, 50);
            if (!testFoundedIndex(array, eps, foundIndex)) {
                System.out.println("eps=" + eps + " fIndex=" + foundIndex + "   FAILED");
                allPassed = false;
            }
            testsNumber--;
        }

        if (allPassed) System.out.println("All random tests passed!");
    }

    private static boolean testNegative()
    {
        double[] d=createSequenceArray(50);
        return findMinimalIndex(-1,50)==0;
    }

    private static boolean testLessThanFirst()
    {
        double[] d=createSequenceArray(50);

        return findMinimalIndex(1,5)==0;
    }

    private static boolean testBiggerThanLast()
    {
        double[] d=createSequenceArray(5);
        return findMinimalIndex(1.0/(2501),5)==-1;
    }

    private static boolean testOneElementSequence()
    {
        double[] d=createSequenceArray(1);

        return findMinimalIndex(1,1)==0&&findMinimalIndex(0.001,1)==-1;
    }


}
