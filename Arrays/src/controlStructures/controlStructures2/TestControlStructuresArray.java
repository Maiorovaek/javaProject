package controlStructures.controlStructures2;

import java.util.Random;

public class TestControlStructuresArray {


    public static void main(String[] args) {
        int[] arr = new int[50];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(11);
        }
        ControlStructuresArray controlStructuresArray = new ControlStructuresArray();

        long startTime = System.nanoTime();
        controlStructuresArray.switchArray(arr);
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println();
        System.out.println("Switch -->" + estimatedTime);


        long startTime2 = System.nanoTime();
        controlStructuresArray.ifElseIf(arr);

        long estimatedTime2 = System.nanoTime() - startTime2;
        System.out.println();
        System.out.println("If-Else-if -->" + estimatedTime2);



    }


}
