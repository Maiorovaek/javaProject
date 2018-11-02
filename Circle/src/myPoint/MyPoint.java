package myPoint;

import java.util.Objects;

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

    public int[] getXY(){
       int[] MyPoint =  new int[2];
        MyPoint[0] = x;
        MyPoint[1] = y;
        return MyPoint;


    }
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ')';
    }

    public double distance(int x, int y){
        return Math.sqrt(Math.pow(x-this.x,2) + Math.pow(y-this.y,2));
    }
    public double distance(MyPoint another){
        return Math.sqrt(Math.pow(another.getX()-this.x,2) + Math.pow(another.getY()-this.y,2));
    }
    public double distance(){
        return Math.sqrt(Math.pow(x-this.x,2) + Math.pow(y-this.y,2));
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
