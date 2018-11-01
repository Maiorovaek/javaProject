package ProjBall;

public class Ball {
    private float x;
    private float y;
    private int radius;
    private float xDelte;
    private float yDelte;

    public Ball(float x, float y, int radius, int speed, int direction){
        this.x = x;
        this.y = y;
        this.radius = radius;
        xDelte = (float) (speed * Math.cos(Math.toRadians(direction)));
        yDelte = (float) ((-1) * speed * Math.sin(Math.toRadians(direction)));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getxDelte() {
        return xDelte;
    }

    public void setxDelte(float xDelte) {
        this.xDelte = xDelte;
    }

    public float getyDelte() {
        return yDelte;
    }

    public void setyDelte(float yDelte) {
        this.yDelte = yDelte;
    }


    public void move() {
        x += xDelte;
        y += yDelte;

    }

    public void reflectHorizontal() {
        xDelte = (-1)*xDelte;

    }

    public void reflectVertical() {
        yDelte = (-1)*yDelte;
    }

    @Override
    public String toString() {
        return "Ball{" + "(" + x +"," + y +") speed=(" + xDelte + ", " + yDelte + ")}";
    }
}
