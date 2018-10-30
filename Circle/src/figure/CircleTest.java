package figure;

public class CircleTest {
    public static void main(String[] args) {
        Circle circle = new Circle();
        Circle circle1 = new Circle(2, "green");
        circle1.setColor("yellow");
        System.out.println(circle);
        System.out.println(circle1);
        String t = String.format("Area circle = %(.2f ", circle1.getArea());
        System.out.println(t);
    }
}
