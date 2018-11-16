package collections.figure;


    public class Circle {
        private double radius =1.0;
        private String color = "red";

        public Circle() {
        }

        public Circle(double radius) {
            this.radius = radius;
        }

        public Circle(double radius, String color) {
            this.radius = radius;
            this.color = color;
        }

        public double getRadius() {
            return radius;
        }

        public String getColor() {
            return color;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Circle{" +
                    "r=" + this.radius +
                    ", c='" + this.color + '\'' +
                    '}';
        }
        public double getArea(){
            return Math.PI*this.radius*this.radius;
        }


        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Circle)) return false;
            Circle circle = (Circle) obj;
            return  Double.doubleToLongBits(radius)==circle.radius &&
                    color.equals(circle.color);
        }


        public int hashCode() {
            int result = 17;

            long longBits = Double.doubleToLongBits(radius);
            result = 37 * result + (int)(longBits - (longBits >>> 32));

            result = 31*result + color.hashCode();
            return result;
        }

    }

