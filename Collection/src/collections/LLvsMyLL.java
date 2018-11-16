package collections;

import collections.figure.Circle;
import collections.nanoTimeCalc.TimeCalc;
import java.util.LinkedList;


public class LLvsMyLL {

    //MyLinkedList faster then LinkedList  - add(), set()
    public static void main(String[] args) {
        System.out.println("java.util.LinkedList vs. MyLinkedList");
        LinkedList<Circle> linkedList = new LinkedList();
        MyLinkedList<Circle> mylistReal = new MyLinkedList();
        TimeCalc te = new TimeCalc();
        te.timeCalulate(linkedList, mylistReal);

    }
}

