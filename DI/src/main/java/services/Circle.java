package services;

import org.springframework.beans.factory.annotation.Autowired;

public class Circle implements Shape {

    private Point center;

    public Circle() {
    }

    public void draw() {
        System.out.println("Drawing circle\n");
        System.out.println("Point is: (" + center.getX() + ", " + center.getY() + ")");
    }

    public Circle(Point center) {
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }

    @Autowired
    public void setCenter(Point center) {
        this.center = center;
    }
}
