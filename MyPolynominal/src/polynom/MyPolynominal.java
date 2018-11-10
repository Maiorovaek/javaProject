package polynom;

import java.util.Arrays;

public class MyPolynominal {
    private double[] coeffs;

    public MyPolynominal(double... coeffs) {
        this.coeffs = coeffs;
    }

    public int getDegree() {
        return coeffs.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int n = coeffs.length - 1; n > 0; n--) {
            s.append((coeffs[n] != 0) ? (coeffs[n] + "x" + ((n > 1) ? "" + n : " ") + " + ") : "");
        }
        if (coeffs.length > 0) {
            s.append(coeffs[0]);
        }
        return s.toString();
    }


    public double evaluate(double x) {
        int n = 0;
        double result = 0;
        for (double t : coeffs) {
            result = result + t * Math.pow(x, n);
            n++;
        }
        return result;
    }

    public MyPolynominal add(MyPolynominal right) {
        double[] addresult;
        if (this.coeffs.length > right.coeffs.length) {
            addresult = new double[this.coeffs.length];
        } else {
            addresult = new double[right.coeffs.length];
        }

        for (int i = 0; (i < this.coeffs.length) || (i < right.coeffs.length); i++) {
            if (i < this.coeffs.length) addresult[i] = addresult[i] + this.coeffs[i];
            if (i < right.coeffs.length) addresult[i] = addresult[i] + right.coeffs[i];
        }
        return new MyPolynominal(addresult);
    }

    public MyPolynominal multiply(MyPolynominal right) {
        double[] mulresult = new double[this.coeffs.length + right.coeffs.length - 1];

        for (int i = 0; i < coeffs.length; i++) {
            for (int j = 0; j < right.coeffs.length; j++) {
                mulresult[i + j] = mulresult[i + j] + coeffs[i] * right.coeffs[j];
            }
        }
        return new MyPolynominal(mulresult);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o  instanceof MyPolynominal) return false;
        MyPolynominal pol = (MyPolynominal) o;
        return Arrays.equals(coeffs, pol.coeffs);
    }


    public int hashCode() {
        return Arrays.hashCode(coeffs);
    }
}
