package multidimensionalArray.maxElement;

import java.util.Random;

public class MaxElement {
   private int[][] arrayt = new int[8][5];
    Random random = new Random();

int max, maxI, maxJ;
    public void createArray() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(" " + (arrayt[i][j] = random.nextInt(199) -99));

                if(arrayt[i][j]>max){
                    max = arrayt[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
            System.out.println();
        }
        System.out.println(max + " [" +  maxI +" ," +maxJ  + "]");
    }
}
