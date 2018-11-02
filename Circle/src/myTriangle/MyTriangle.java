package myTriangle;

import java.util.Objects;

public class MyTriangle extends MyPoint {
    private MyPoint v1;
    private MyPoint v2;
    private MyPoint v3;
    private double minLength, midLength, hepLength;

    public MyTriangle() {

    }

    public MyTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        v1 = new MyPoint(x1, y1);
        v2 = new MyPoint(x2, y2);
        v3 = new MyPoint(x3, y3);
    }

    public MyTriangle(MyPoint v1, MyPoint v2, MyPoint v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        getMinMax();
    }

    @Override
    public String toString() {
        return "MyTriangle{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                ", v3=" + v3 +
                '}';
    }

    public double getPerimetr() {
        return v1.distance(v2) + v2.distance(v3) + v3.distance(v1);
    }

    private static double min(double a, double b) {
        if (a < b)
            return a;
        else
            return b;

    }

    private static double max(double a, double b) {
        if (a > b)
            return a;
        else
            return b;
    }

    public void getMinMax() {
        minLength = min(min(v1.distance(v2), v2.distance(v3)), v3.distance(v1));

        midLength = min(max(v1.distance(v2), v2.distance(v3)), v3.distance(v1));

        hepLength = max(max(v1.distance(v2), v2.distance(v3)), v3.distance(v1));
    }

    public TypeTriangle getTrianleType() {
        int choise = 0;
        getMinMax();


        if (Math.pow(hepLength, 2) == Math.pow(midLength, 2) + Math.pow(minLength, 2))
            choise = 1;
        else if (Math.pow(hepLength, 2) < Math.pow(midLength, 2) + Math.pow(minLength, 2)) {
            choise = 2;
        } else if (Math.pow(hepLength, 2) > Math.pow(midLength, 2) + Math.pow(minLength, 2)) {
            choise = 3;

        }

        if (hepLength == 0 | midLength == 0 | minLength == 0) {
            return TypeTriangle.DOESNOTEXIST;
        }
        switch (choise) {
            case 1:
                return TypeTriangle.ISOSCALES;
            case 2:
                return TypeTriangle.EQUILATERAL;
            case 3:
                return TypeTriangle.SCALENE;
            default:
                return TypeTriangle.DOESNOTEXIST;

        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyTriangle)) return false;
        MyTriangle trianglemy = (MyTriangle) obj;
        return v1.equals(trianglemy.v1) && v2.equals(trianglemy.v2) && v3.equals(trianglemy.v3);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + v1.hashCode();
        result = 31 * result + v2.hashCode();
        result = 31 * result + v3.hashCode();
        return result;
    }



}
