package figure;

import java.util.Objects;

public class Rectangle {
    private float length = 1.0f;
    private float width = 1.0f;

    public Rectangle() {
    }

    public Rectangle(float length, float width) {
        this.length = length;
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
    public double getArea(){
        return width*length;
    }
    public double getPerimetr(){
        return 2*(width + length);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) obj;
        return  (Float.floatToIntBits(length) == rectangle.length) &&  Float.floatToIntBits(width) == rectangle.width;

    }


    public int hashCode() {
        int result = 17;
        result = 37 * result + Float.floatToIntBits(length);
        result = 37 * result + Float.floatToIntBits(width);
        return result;
    }



}
