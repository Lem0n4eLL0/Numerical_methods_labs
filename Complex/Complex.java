package NumMetods.Complex;

public class Complex implements Comparable<Complex>{
    private double re; // Действительная часть
    private double im; // мнимая часть
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex(Complex c) {
        this.re = c.re;
        this.im = c.im;
    }

    public Complex add(Complex el) {
        return new Complex(re + el.re, im + el.im);
    }
    public Complex sub(Complex el) {
        return new Complex(re - el.re, im - el.im);
    }
    public Complex mult(Complex el) {
        return new Complex(re * el.re - im * el.im, im * el.re + re * el.im);
    }
    public Complex div(Complex el) {
        return new Complex((re * el.re + im * el.im) / (el.im * el.im + el.re * el.re), (im * el.re - re * el.im) / (el.im * el.im + el.re * el.re));
    }
    public Complex abs() {
        return new Complex(Math.abs(re), Math.abs(im));
    }
    public Complex sqrt() {
        if(isComplex())
            return new Complex(0, 0);
        else if(re >= 0)
            return new Complex(Math.sqrt(re), 0);
        else
            return new Complex(0, Math.sqrt(Math.abs(re)));
    }

    public void display() {
        System.out.println(this.toString());
    }
    public double getRe() {
        return re;
    }
    public double getIm() {
        return im;
    }
    public void setComplex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public boolean isComplex() {
        return im != 0;
    }
    public boolean isZerro() {
        return re == 0 && im == 0;
    }

    @Override
    public int compareTo(Complex o) {
        return Double.compare(this.re, o.re) < 0?-1:Double.compare(this.re, o.re) == 0?Double.compare(this.im, o.im):1;
    }

    @Override
    public String toString() {
        return String.valueOf(re) + " + " + im + "i";
    }
}
