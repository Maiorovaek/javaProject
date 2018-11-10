package arrayOneDementional;

import java.util.Random;

public class ArrayOneDementional {
    Random random = new Random();

    public void createArray() {
        int[] array = new int[100];
        for (int i = 0; i <= array.length; i++) {
            if (i % 2 != 0) {
                array[i] = i;
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
        for (int j = array.length; j >= 1; j--) {
            if (j % 2 != 0) {
                array[j] = j;
                System.out.print(array[j] + " ");
            }
        }
        System.out.println();
    }


    public void evenOrUneven() {
        int[] arr = new int[20];
        System.out.println("-----evenOrUneven------");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(11);
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int count = 0, countTwo = 0;
        int[] even = new int[20];
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] % 2) != 0) {
                System.out.print(arr[i] + " ");
                countTwo++;
            } else {
                even[i] = arr[i];
                count++;
            }
        }
        System.out.println();
        for (int i = 0; i < count; i++) {
            System.out.print(even[i] + " ");
        }
        System.out.println();
        System.out.println("Count even ----- " + count);
        System.out.println("Count uneven -----" + countTwo);
    }


    public void replaceElementByZero() {
        int[] arr = new int[10];

        System.out.println("-----replaceElementByZero------");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100) + 1;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 != 0) {
                arr[i] = 0;
                System.out.print(arr[i] + " ");
            } else {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }


    public void maxMin() {
        int[] arr = new int[15];

        System.out.println("-----maxMin------");

        for (int i = 0; i < arr.length; i++) {

            arr[i] = random.nextInt(100) - 50;
            System.out.print(arr[i] + " ");
        }
        int max = 0, min = 0, index = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }

            if (arr[i] < min) {
                min = arr[i];

            }
        }
        System.out.println();
        System.out.println("Max -- > " + max + " index =" + index);
        System.out.println("Min -- > " + min);
    }


    public void averageValueArrays() {
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
        System.out.println("-----average Value Arrays------");

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt(10);
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = random.nextInt(11);
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
        double average1 = 0, average2 = 0;
        if (arr1.length > 0) {
            double sum = 0;
            for (int i = 0; i < arr1.length; i++) {
                sum += arr1[i];
            }
            average1 = sum / arr1.length;
            System.out.println("average value for arr1-->" + average1);
        }

        if (arr2.length > 0) {
            double sum = 0;
            for (int i = 0; i < arr2.length; i++) {
                sum += arr2[i];
            }
            average2 = sum / arr2.length;
            System.out.println("average value for arr2-->" + average2);
            if (average1 > average2) {
                System.out.println("average value for arr1 > average value arr2");
            } else if (average1 == average2) {
                System.out.println("average value for arr1 = average value arr2");

            } else {
                System.out.println("average value for arr1 < average value arr2");
            }
        }
    }


    public void oftenUsedOrEqualNumber() {
        int[] arr1 = new int[20];

        int negativ = 0, zero = 0, pozitiv = 0;
        System.out.println("-----average Value Arrays------");

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt(3) - 1;
            System.out.print(arr1[i] + " ");


        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] < 0) {
                negativ++;
            } else if (arr1[i] == 0) {
                zero++;
            } else {
                pozitiv++;
            }
        }
        System.out.println();
        System.out.println(negativ + "     -----     " + zero + " ----- " + pozitiv);


        if(zero<negativ && negativ>pozitiv){
            System.out.println("Often used -1 -->"  +negativ);
        }
        if(zero<pozitiv && pozitiv>negativ){
            System.out.println("Often used 1 -->" + pozitiv);}

        if(negativ<zero && zero>pozitiv){
            System.out.println("Often used 0 -->" + zero);
        }
        if(zero == pozitiv && zero>negativ){
            System.out.println("Often used 0 and 1 -->");
        }
        if(negativ == pozitiv && negativ>zero){
            System.out.println("Often used -1 and 1 -->");
        }
        if(zero== negativ && negativ>pozitiv){
            System.out.println("Often used -1 and 0");
        }


        System.out.println();

    }
}