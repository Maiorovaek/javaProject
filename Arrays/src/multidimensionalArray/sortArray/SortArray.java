package multidimensionalArray.sortArray;

import java.util.Random;

public class SortArray {
    public int[][] arr = new int[10][7];
    Random random = new Random();


    public void createSortArray() {
        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(101);
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();

        }

        System.out.println();
        System.out.println("отсортированный массив");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = j + 1; k < arr[i].length; k++) {

                    if (arr[i][j] < arr[i][k]) {
                        int tmp = arr[i][j];
                        arr[i][j] = arr[i][k];
                        arr[i][k] = tmp;
                    }
                }
            }
        }

        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
