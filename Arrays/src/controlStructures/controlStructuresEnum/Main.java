package controlStructures.controlStructuresEnum;


public class Main {
    public static void main(String[] args) {
        RandomEnum<Birds> r = new RandomEnum<>(Birds.class);
        ControlStructures controlStructures = new ControlStructures();


        long startTime = System.nanoTime();
        for(int i=0; i<1000; i++){
            Birds bir = r.random();
        controlStructures.ifElseIf(bir);
        }
        long estimatedTime = System.nanoTime() - startTime;

        long startTime2 = System.nanoTime();
        for(int i=0; i<1000; i++){
            Birds bir = r.random();
            controlStructures.printBird(bir);
        }

        long estimatedTime2 = System.nanoTime() - startTime2;
        System.out.println("IfElseIf -->" + estimatedTime);
        System.out.println("Switch -->" + estimatedTime2);


    }
}
