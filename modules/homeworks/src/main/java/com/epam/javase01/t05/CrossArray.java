package com.epam.javase01.t05;

import com.epam.javase01.CommonUtils;

/**
 * Получить матрицу:
 *100...001
 *010...010
 *001...100
 * ........
 *001...100
 *010...010
 *100...001
 */
public class CrossArray {

    public static void main(String[] args) {
        int matrixSize= CommonUtils.tryGetIntFromStringArgs(0,8,args);

        byte[][] matrix=new byte[matrixSize][matrixSize];
        fillMatrix(matrix);
        printMatrix(matrix);


    }

    private static void fillMatrix(byte[][] targetMatrix)
    {
        for(int i=0;i<targetMatrix.length;i++)
        {
            targetMatrix[i][i]=1;
            targetMatrix[i][targetMatrix.length-1-i]=1;
        }
    }

    private  static  void printMatrix(byte[][] targetMatrix)
    {
        for(byte[] column:targetMatrix)
        {
            for(byte columnElement:column)
            {
                System.out.print(columnElement+" ");
            }
            System.out.println();
        }
    }

}
