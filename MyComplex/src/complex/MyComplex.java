package complex;

import java.util.Objects;

public class MyComplex {
    private double real = 0;
    private double imag = 0;

    public MyComplex() {
    }

    public MyComplex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public void setValue(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    @Override
    public String toString() {
        return "(" + real +
                " + " + imag + "i)";
    }

    public boolean isReal() {
        if (real != 0) {
            return true;
        }
        return false;
    }

    public boolean isImaginary() {
        if (imag != 0) {
            return true;
        }
        return false;
    }



    public boolean equals(double real, double imag) {
       if (this.real ==real && this.imag == imag){
           return  true;
       }
        return false;
    }


    public boolean equals(MyComplex another){
     if (this.real == another.real && this.imag == another.imag){
         return true;
        }
        return false;
    }

    //модуль комплексного числа
    public double magnitude() {
        return Math.sqrt(real * real + imag * imag);
    }

    //в радианах
    public double argument() {
        if(this.real>0){
            return Math.atan(imag/ real);
        }
     if(real <0 && imag>=0){
            return Math.PI + Math.atan(imag/real);
     }

     if(real<0 && imag<0){
            return Math.atan(imag/real) - Math.PI;
     }
     if(real==0 && imag<0){
            return(-1)* Math.PI /2;
     }
     if(real==0 && imag>0){
            return Math.PI/2;
     }
     return 0;
    }

    public MyComplex add(MyComplex right) {
        this.real = this.real + right.getReal();
        this.imag = this.imag + right.getImag();
        return this;
    }


    public MyComplex addNew(MyComplex right) {
        //(a + bi) + (c + di) = (a+c) + (b+d)i
        right.setReal(this.real + right.getReal());
        right.setImag(this.imag + right.getImag());
        return this;
    }

    public MyComplex subtract(MyComplex right) {
        this.real = this.real - right.getReal();
        this.real = this.imag - right.getImag();
        return this;
    }

    public MyComplex subtractNew(MyComplex right) {
        //(a + bi) - (c + di) = (a-c) + (b-d)i
        right.setReal(this.real - right.getReal());
        right.setImag(this.imag - right.getImag());
        return right;
    }

    // (a + bi)(c + si)=(a*c -b*s) + (a*s + b*c)i
    public MyComplex multiply(MyComplex right) {
        this.real = this.real * right.getReal() - this.imag * right.getImag();
        this.imag = (this.real * right.getReal()) + (this.imag * right.getImag());
        return this;
    }


    //(a + bi) / (c + di) = [(a + bi) * (c – di)] / (c*c + d*d)
    public MyComplex divide(MyComplex right) {
        double del =  this.real*right.getReal() + this.imag* right.getImag();
        this.real = (this.real*right.getReal() + this.imag* right.getImag()) / del;
        this.imag = (this.imag*right.getReal() - this.real* right.getImag()) / del;
        return this;
    }

    public MyComplex conjugate() {
        this.imag = (-1)*this.imag;
        return  this;
    }

}