package sort;

public class main {
    public static void main(String[] args) {
        ArraySort as = new ArraySort(500);
        System.out.println(as);


        long startTime1 = System.nanoTime();
        System.out.println(" " + as.selectionSort());
        long estimatedTime2 = System.nanoTime() - startTime1;
        System.out.println(estimatedTime2);

        long startTime3 = System.nanoTime();
        System.out.println(" "  + as.bubbleSort());
        long estimatedTime3 = System.nanoTime() - startTime3;
        System.out.println(estimatedTime3);


        long startTime = System.nanoTime();
        System.out.println(" "  + as.printArraysSort());
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);
    }
}
