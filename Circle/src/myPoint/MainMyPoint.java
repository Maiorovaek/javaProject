package myPoint;

public class MainMyPoint {
    public static void main(String[] args) {
        MyPoint m1 = new MyPoint(2,5);
        System.out.println(m1);
        System.out.println(m1.distance(8,5));

        MyPoint a = new MyPoint(2,8);
        System.out.println(m1.distance(a));

        System.out.println(m1.distance());

    }

}
