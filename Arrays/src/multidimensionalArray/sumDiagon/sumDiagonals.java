package multidimensionalArray.sumDiagon;
import java.util.Random;

public class sumDiagonals {
    int[][] array = new int[8][8];
    Random random = new Random();
    int sumdiag, sumpobdiag;
    int multiDiag = 1, multiPobDiag = 1;

    public void createArray() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(" " + (array[i][j] = random.nextInt(100) + 1));
                if (i == j) {
                    sumdiag = sumdiag + array[i][j];
                    multiDiag = multiDiag * array[i][j];
                }

                if (i == (array.length - j - 1)) {
                    sumpobdiag = sumpobdiag + array[i][j];
                    multiPobDiag *= array[i][j];
                }
            }
            System.out.println();
        }


        System.out.println("Summa main diagonal-->" + sumdiag);
        System.out.println("Multi main diagonal-->" + multiDiag);
        System.out.println("Summa pobochnoy diagonal-->" + sumpobdiag);
        System.out.println("Multi pobochnoy diagonal-->" + multiPobDiag);
    }


}