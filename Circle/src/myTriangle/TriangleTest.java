package myTriangle;

public class TriangleTest {
    public static void main(String[] args) {
        MyPoint a = new MyPoint(0, 0);
        MyPoint b = new MyPoint(2, 2);
        MyPoint c = new MyPoint(0, 4);
        MyTriangle triangle1 = new MyTriangle(a,b,c);
        System.out.println(triangle1.toString());
        System.out.println("Perimetr " + triangle1.getPerimetr());
        System.out.println("Type " + triangle1.getTrianleType());

    }
}
