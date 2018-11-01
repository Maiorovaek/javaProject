package main;

import complex.MyComplex;

public class TestMyComplex {
    public static void main(String[] args) {
        MyComplex x1 = new MyComplex(1.1, 2.2);
        MyComplex x2 = new MyComplex(3.3, 4.4);
        System.out.println("Number 1 is: " + x1);
        System.out.println("Number 2 is: " + x2);
        System.out.println(x1.equals(x2));
        System.out.println("AddNew -->" +  x1.addNew(x2));
        System.out.println("Add -->" +  x1.add(x2));
        System.out.println( "Subtract --> " +x1.subtract(x2));
        System.out.println( "SubtractNew --> " +x1.subtractNew(x2));
        System.out.println( "Multiply-->" + x1.multiply(x2));
        System.out.println( "Devide-->" + x1.divide(x2));


        System.out.println("Module complex number ---->" + x1.magnitude());
        System.out.println("inn radian ---->" + x1.argument());
    }
}
