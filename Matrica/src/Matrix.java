import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Matrix {
    private int[][] matrixA;


    public Matrix(int n, int m) {

       /* Scanner sc = new Scanner(System.in);
        System.out.println("Enter size matrix m x n ");
        int m = sc.nextInt();
        int n = sc.nextInt();*/

        if ((n < 1) || (m < 1)) {
            System.out.println("m and n must be >1");
        }
        matrixA = new int[n][m];

    }

    public int getVerticalSize() {
        return matrixA.length;
    }

    public int getHorizontalSqze() {
        return matrixA[0].length;
    }

    public int getElement(int i, int j) throws Exception{
        if(checkRange(i,j)){
            return matrixA[i][j];}
            throw new Exception();
    }

    public void setElements(int i, int j, int value)  {
        if (checkRange(i, j)) { // проверка i и j
            matrixA[i][j] = value;}



    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : " + matrixA.length + "x\n" + matrixA[0].length);
        System.out.println();
        for (int[] row : matrixA) {
            for (int value : row) {
                s.append(value + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private boolean checkRange(int i, int j){

        if ( i >= 0 && i < matrixA.length && j >= 0 && j < matrixA[0].length ) {

            return true;
        } else {
        return false;}
    }


}
