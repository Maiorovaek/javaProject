package collections.nanoTimeCalc;

import collections.MyLinkedList;
import collections.figure.Circle;

import java.util.LinkedList;

public class TimeCalc {
    public    void compareLinkedList(long end, long endL){
        if (end<endL){
            System.out.println("MyLinkedList faster then LinkedList"  + " " + (endL-end));
        } else{
            System.out.println("LinkedList faster then MyLinkedList");
        }
    }

    public  void timeCalulate(LinkedList<Circle> elem, MyLinkedList<Circle> myElem){

        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            elem.add(new Circle());
        }
        long end = System.nanoTime() - start;

        long startL = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            myElem.add(new Circle());
        }
        long endL = System.nanoTime() - startL;
        compareLinkedList(endL, end);


        long start1 = System.nanoTime();
        for (int i = 0; i < elem.size(); i++) {
            elem.set(i, new Circle(5, "green"));
        }
        long end1 = System.nanoTime() - start1;

        long startL1 = System.nanoTime();
        for (int i = 0; i < elem.size(); i++) {
            myElem.set(i, new Circle(5, "green"));
        }
        long endL1 = System.nanoTime() - startL1;
        compareLinkedList(end1, endL1);


        long start2 = System.nanoTime();
        for (int i = 0; i < elem.size(); i++) {
            elem.remove(25);
        }
        long end2 = System.nanoTime() - start2;

        long start2L = System.nanoTime();
        for (int i = 0; i < elem.size(); i++) {
            elem.remove(25);
        }
        long end2L = System.nanoTime() - start2L;

        compareLinkedList(end2 , end2L);
    }


}
