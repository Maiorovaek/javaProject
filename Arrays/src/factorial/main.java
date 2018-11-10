package factorial;

public class main {
    public static void main(String[] args) {
Factorial f = new Factorial(5);



        long startTime = System.nanoTime();
        System.out.println("" + f.mulFactor());
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);
        long startTime2 = System.nanoTime();
        System.out.println(" " + f.rekursFactor(5)); //быстрее чем цикл
        long estimatedTime2 = System.nanoTime() - startTime2;
        System.out.println(estimatedTime2);
    }
}
