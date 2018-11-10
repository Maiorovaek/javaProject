package ProjBall;

import java.util.Objects;

public class Container {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public Container(int x, int y, int width, int height) {
        x1 = x;
        y1 = y;
        x2 = x + width;
        y2 = y - height;
    }

    public int getX() {
        return x1;

    }

    public int getY() {
        return y1;
    }

    public int getWidth() {
        return x2 - x1;

    }

    public int getHeight() {
        return y1 - y2;

    }

    public boolean collides(Ball ball) {
        if (ball.getX() > x1 + ball.getRadius() && ball.getX() < x2 - ball.getRadius() &&
                ball.getY() > y2 + ball.getRadius() && ball.getY() < y1 - ball.getRadius()) {
            return true;
        }
        return false;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof  Container) return false;
        Container container = (Container) o;
        return x1 == container.x1 &&
                x2 == container.x2 &&
                y1 == container.y1 &&
                y2 == container.y2;
    }

    @Override
    public int hashCode() {
        int result =17;
        result =31*result +x1 ;
        result = 31*result + y1;
        result =31*result + x2;
        result = 31*result + y2;
        return result;
    }

    public String toString() {
        return "Container[(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")]";
    }


}
