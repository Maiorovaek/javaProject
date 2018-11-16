package collections;

import collections.figure.Circle;

import java.util.LinkedList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        Circle cr = new Circle();
        MyLinkedList<Circle> mylistReal = new MyLinkedList();
        for (int i = 0; i < 2; i++) {
            mylistReal.add(new Circle(5, "green"));
        }
        System.out.println(mylistReal);
        System.out.println("--Add (index, element)--");
        mylistReal.add(0, new Circle(8, "yellow"));

        System.out.println( mylistReal);
        System.out.println("--Get(element)--");
        System.out.println(mylistReal.get(2));
        System.out.println("--Add(element)--");
        Circle elem = new Circle(1, "red");
        mylistReal.add(elem);
        System.out.println(  mylistReal);
        System.out.println("--IndexOf()--");
        System.out.println( mylistReal.indexOf(elem));
        System.out.println("--Remove(index)--");
        mylistReal.remove(1);
        System.out.println( mylistReal);
        mylistReal.set(1, new Circle(2, "yellow"));
        System.out.println("--Set(index , element)--");
        System.out.println( mylistReal);
        System.out.println("--Clear --");
        mylistReal.clear();
        System.out.println( mylistReal);
        System.out.println(mylistReal.size());


    }

}
