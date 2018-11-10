package sort;
import java.util.Arrays;
import java.util.Random;
public class ArraySort {
  private int n=10;
  private static Random random = new Random(47);
   int[] array = new int[n] ;

    public ArraySort(int n) {
        this.n = n;
        this.array = new int[n];

        for(int i=0; i<array.length;i++ ) {
            array[i] = random.nextInt(200);
        }
    }

    public String bubbleSort() {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return   Arrays.toString(array);
    }

    public String selectionSort(){


        for(int i =0; i <array.length;i++){
            int  min = array[i];
            int min_i = i;

            for(int j = i+1; j<array.length; j++){
                if(array[j] < min){
                    min = array[j];
                    min_i = j;
                }
            }

            if(i != min_i){
                int tmp = array[i];
                array[i] = array[min_i];
                array[min_i] = tmp;
            }
        }


        return  Arrays.toString(array);
    }

    public String printArraysSort(){
        Arrays.sort(array);
        return  Arrays.toString(array);
    }

    @Override
    public String toString() {
        return "ArraySort{"
                +" array=" + Arrays.toString(array) +
                '}';
    }
}
