package collections.Tests;

import collections.figure.Circle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestList {
   private int n = 50000;


    public static void main(String[] args) {
        List <Circle> listarr = new ArrayList<>();
        List <Circle> linkedList = new LinkedList<>();
        TestList myTestList = new TestList();
        System.out.println("Add element");
        myTestList.timeCalc(myTestList.addEnd(linkedList) , myTestList.addEnd(listarr));
        System.out.println("Add element in middle");
        myTestList.timeCalc(myTestList.addMiddle(linkedList), myTestList.addMiddle(listarr));
        System.out.println("Remove last element");
        myTestList.timeCalc(myTestList.removeElement(linkedList), myTestList.removeElement(listarr));
    }


    void createList(List<Circle> list){
        for(int i = 0; i<n; i++){
            list.add(new Circle());
        }
    }


    public long addEnd(List<Circle> list) {
        TestList test = new TestList();
        test.createList(list);
        long start = System.nanoTime();
        list.add(new Circle(5, "red"));
        long end = System.nanoTime() - start;
        return end;
    }

    public long addMiddle(List <Circle> list){
        long start = System.nanoTime();
        list.add(n/2,new Circle());
        long end = System.nanoTime() - start;
        return end;
    }

    public long removeElement(List <Circle> list){
        long start = System.nanoTime();
        list.remove(n/2);
        long end = System.nanoTime() - start;
        return end;

    }


public void timeCalc(long time1, long time2){
        if(time1<time2){
            System.out.println("LinkedList faster then ArrayList, time2-time1 = " + (time2-time1) );
        } else {
            System.out.println("ArrayList faster then LinkedList, time1-time2 = " +(time1 - time2));

        }

}
}
