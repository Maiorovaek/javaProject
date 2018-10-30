package figure;

public class RectangleTest {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle();
        Rectangle rectangle2 = new Rectangle(20, 10);
        System.out.println(rectangle1);
        System.out.println("Area rectangle 1  ----> " + rectangle1.getArea());
        System.out.println("Perimetr rectangle 1  ----> " + rectangle1.getPerimetr());
        System.out.println(rectangle2);
        System.out.println("Area rectangle 2  ----> " + rectangle2.getArea());
        System.out.println("Perimetr rectangle 2 ----> " + rectangle2.getPerimetr());
    }
}
