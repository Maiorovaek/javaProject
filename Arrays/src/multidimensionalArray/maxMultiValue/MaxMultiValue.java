package multidimensionalArray.maxMultiValue;

import java.util.Random;

public class MaxMultiValue {

    private int[][]  array= new int[8][5];
    Random random = new Random();

    int proisv=0, maxI =0;
    public void createArray() {
        for(int i = 0; i<array.length;i++){
            int temp=1;
            for (int j=0;j<array[i].length;j++){
                System.out.print(" " + (array[i][j] =random.nextInt(21)-10));
                temp = temp*Math.abs(array[i][j]);


                if(temp>proisv){
                    proisv = temp;
                    maxI = i;
                }

            }

            System.out.println();

        }


        System.out.println(proisv +" max value with index string--> " + maxI);

    }
    }

