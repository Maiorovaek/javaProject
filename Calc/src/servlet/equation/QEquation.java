package servlet.equation;

public class QEquation {

    private double a;
    private double b;
    private double c;

    private Double x1;
    private Double x2;
    private  String res = "";

    public QEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        qresult();
    }

    public void qresult() {
        if (a == 0) {
            x1 = c / b;
            this.res = "x: " + x1 ;
        } else {
            double discr = Math.pow(b, 2) - 4 * a * c;
            if (discr > 0) {
                x1 = (-b + Math.sqrt(discr)) / 2 * a;
                x2 = (-b - Math.sqrt(discr)) / 2 * a;
                this.res = "x1: " + x1 + ", x2: " + x2;
            } else if (discr == 0) {
                x1 = -b / (2 * a);
                this.res = "x: " + x1 ;
            }
            else if(discr<0){
                this.res = "There are no real roots." ;
            }
        }
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public String getRes() {
        return res;
    }

}
