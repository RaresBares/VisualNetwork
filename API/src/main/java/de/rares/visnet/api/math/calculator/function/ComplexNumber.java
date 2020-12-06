package de.rares.visnet.api.math.calculator.function;

public class ComplexNumber {


    private double real;


    private double imagine;


    public ComplexNumber(final double real, final double i) {
        this.real = real;
        this.imagine = i;
    }

    public ComplexNumber() {
        real = 0.0;
        imagine = 0.0;
    }

    public static ComplexNumber add(final ComplexNumber a, final ComplexNumber b) {
        final double real = a.real + b.real;
        final double imag = a.imagine + b.imagine;
        return new ComplexNumber(real, imag);
    }

    public static ComplexNumber add(final double real, final ComplexNumber c) {
        return new ComplexNumber(c.real + real, c.imagine);
    }

    public static ComplexNumber sub(final ComplexNumber a, final ComplexNumber b) {
        final double real = a.real - b.real;
        final double imag = a.imagine - b.imagine;
        return new ComplexNumber(real, imag);
    }

    public static ComplexNumber sub(final double real, final ComplexNumber c) {
        return new ComplexNumber(c.real - real, c.imagine);
    }

    public static ComplexNumber mul(final ComplexNumber a, final ComplexNumber b) {
        final double real = (a.real * b.real) - (a.imagine * b.imagine);
        final double imag = (a.imagine * b.real) + (a.real * b.imagine);
        return new ComplexNumber(real, imag);
    }



    public static ComplexNumber div(final ComplexNumber a, final ComplexNumber b) throws IllegalArgumentException {

        if ((b.real == 0) && (b.imagine == 0)) {
            throw new IllegalArgumentException("The complex number b is 0");
        }

        final double c = Math.pow(b.real, 2);
        final double d = Math.pow(b.imagine, 2);

        double real;
        double imag;
        real = (a.real * b.real) + (a.imagine * b.imagine);
        real /= (c + d);
        imag = (a.imagine * b.real) - (a.real * b.imagine);
        imag /= (c + d);

        return new ComplexNumber(real, imag);
    }

    public static double abs(final ComplexNumber z) {
        double x, y, ans, temp;
        x = Math.abs(z.real);
        y = Math.abs(z.imagine);
        if (x == 0.0) {
            ans = y;
        } else if (y == 0.0) {
            ans = x;
        } else if (x > y) {
            temp = y / x;
            ans = x * Math.sqrt(1.0 + (temp * temp));
        } else {
            temp = x / y;
            ans = y * Math.sqrt(1.0 + (temp * temp));
        }
        return ans;
    }



    public static ComplexNumber mul(final double x, final ComplexNumber c) {
        final ComplexNumber result = new ComplexNumber();
        result.real = c.real * x;
        result.imagine = c.imagine * x;
        return result;
    }

    public static ComplexNumber div(final double x, final ComplexNumber c) throws IllegalArgumentException {
        if (x == 0) {
            throw new IllegalArgumentException("scalar is 0");
        }
        final ComplexNumber result = new ComplexNumber();
        result.real = c.real / x;
        result.imagine = c.imagine / x;
        return result;
    }

    public static ComplexNumber pow(final ComplexNumber c, final Double exp) {
        return c.pow(exp);
    }

    public static ComplexNumber pow(final ComplexNumber c, final ComplexNumber exp) {
        return c.pow(exp);
    }

    public static ComplexNumber cbrt(final ComplexNumber a) {
        ComplexNumber z = new ComplexNumber();
        if (a.imagine != 0.0) {
            z.real = Math.cbrt(abs(a)) * Math.cos(a.arg() / 3.0);
            z.imagine = Math.cbrt(abs(a)) * Math.sin(a.arg() / 3.0);
        } else {
            z = new ComplexNumber(Math.cbrt(a.real), 0);
        }
        return z;
    }


    public double getImagine() {

        return imagine;
    }

    public void setImagine(final double imagine) {
        this.imagine = imagine;
    }


    public ComplexNumber inverse() {
        final ComplexNumber result = new ComplexNumber();
        final double a = real * real;
        final double b = imagine * imagine;
        if ((a == 0) && (b == 0)) {
            result.real = 0;
            result.imagine = 0;
        } else {
            result.real = real / (a + b);
            result.imagine = imagine / (a + b);
        }
        return result;

    }

    public double module() {
        return Math.sqrt((real * real) + (imagine * imagine));
    }

    public double arg() {

        double angle = Math.atan2(imagine, real);
        if (angle < 0) {
            angle = (2 * Math.PI) + angle;
        }
        return (angle * 180) / Math.PI;

    }

    public ComplexNumber negate() {
        return new ComplexNumber(-real, -imagine);
    }

    public ComplexNumber exp() {
        final double exp_x = Math.exp(real);
        return new ComplexNumber(exp_x * Math.cos(imagine), exp_x * Math.sin(imagine));
    }

    public ComplexNumber log10() {

        final double rpart = Math.sqrt((real * real) + (imagine * imagine));
        double ipart = Math.atan2(imagine, real);
        if (ipart > Math.PI) {
            ipart = ipart - (2.0 * Math.PI);
        }
        return new ComplexNumber(Math.log10(rpart), (1 / Math.log(10)) * ipart);

    }

    public ComplexNumber log() {
        return new ComplexNumber(Math.log(abs(this)), Math.atan2(imagine, real));

    }

    public ComplexNumber sqrt() {
        final double r = Math.sqrt((this.real * this.real) + (imagine * imagine));
        final double rpart = Math.sqrt(0.5 * (r + this.real));
        double ipart = Math.sqrt(0.5 * (r - this.real));
        if (imagine < 0.0) {
            ipart = -ipart;
        }
        return new ComplexNumber(rpart, ipart);
    }

    public ComplexNumber pow(final ComplexNumber exp) {
        ComplexNumber a = this.log();
        a = mul(exp, a);
        return a.exp();
    }


    public ComplexNumber pow(final double exp) {
        ComplexNumber a = this.log();
        a = mul(exp, a);
        return a.exp();
    }


    public ComplexNumber sin() {
        return new ComplexNumber(Math.sin(real) * Math.cosh(imagine), Math.cos(real) * Math.sinh(imagine));
    }


    public ComplexNumber cos() {
        return new ComplexNumber(Math.cos(real) * Math.cosh(imagine), -StrictMath.sin(real) * Math.sinh(imagine));
    }


    public ComplexNumber tan() throws IllegalArgumentException {
        return div(this.sin(), this.cos());
    }


    public ComplexNumber asin() {
        final ComplexNumber IM = new ComplexNumber(0.0, -1.0);
        final ComplexNumber ZP = mul(this, IM);
        final ComplexNumber ZM = add((sub(new ComplexNumber(1.0, 0.0), mul(this, this))).sqrt(), ZP);
        return mul(ZM.log(), new ComplexNumber(0.0, 1.0));
    }


    public ComplexNumber acos() {
        final ComplexNumber IM = new ComplexNumber(0.0, -1.0);
        final ComplexNumber ZM = add(mul((sub(new ComplexNumber(1.0, 0.0), mul(this, this))).sqrt(), IM), this);
        return mul(ZM.log(), new ComplexNumber(0.0, 1.0));
    }


    public ComplexNumber atan() throws IllegalArgumentException {
        final ComplexNumber IM = new ComplexNumber(0.0, -1.0);
        final ComplexNumber ZP = new ComplexNumber(real, imagine - 1.0);
        final ComplexNumber ZM = new ComplexNumber(-real, -imagine - 1.0);
        return div(2.0, mul(IM, (div(ZP, ZM).log())));
    }


    public ComplexNumber sinh() {
        return new ComplexNumber(Math.sinh(real) * Math.cos(imagine), Math.cosh(real) * Math.sin(imagine));
    }

    public ComplexNumber cosh() {
        return new ComplexNumber(Math.cosh(real) * Math.cos(imagine), Math.sinh(real) * Math.sin(imagine));
    }


    public ComplexNumber tanh() throws IllegalArgumentException {
        return div(this.sinh(), this.cosh());
    }

    public ComplexNumber atanh() throws IllegalArgumentException {
        return sub((add(1.0, this)).log(), div(2.0, ((sub(1.0, this)).negate()).log()));
    }

}
