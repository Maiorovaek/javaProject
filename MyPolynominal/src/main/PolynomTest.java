package main;

import polynom.MyPolynominal;

public class PolynomTest {
    public static void main(String[] args) {
        MyPolynominal mp = new MyPolynominal(1,0,5,2);
        MyPolynominal a = new MyPolynominal(3,4,2);
        System.out.println(mp);
        System.out.println(a);
        System.out.println("Add-> " + mp.add(a) );
        System.out.println("Multiply-> " + mp.multiply(a) );
        System.out.println("Evaluate->" + mp.evaluate(5));
        System.out.println("Degree->" + mp.getDegree());
    }
}
