package myTriangle;

public class MyPoint {
    private int x =0;
    private int y =0;

    public MyPoint() {
    }

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
public double distance(int x, int y){
        return  Math.sqrt(Math.pow(this.x-x,2) + Math.pow(this.y - y,2));
}
    public double distance(MyPoint anotherPoint){
        return  Math.sqrt(Math.pow(anotherPoint.getX()-this.getX(),2) + Math.pow(anotherPoint.getY() - this.getY(),2));
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyPoint)) return false;
        MyPoint myPoint = (MyPoint) obj;
        return x == myPoint.x &&
                y == myPoint.y;
    }


    public int hashCode() {
        int result =17;
        result =31*result +x ;
        result = 31*result + y;
        return result;
    }
}
