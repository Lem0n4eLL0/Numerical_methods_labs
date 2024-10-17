package NumMetods.SystemComponents.Elements;

import NumMetods.Complex.Complex;
import NumMetods.interfaces.Calculable;

public class Element implements Comparable<Element>, Calculable {

    private Complex valueC;
    public Element(double value) {
        this(new Complex(value, 0));
    }
    public Element(Complex c) {
        valueC = new Complex(c);
    }
    public Element(Element c) {
        this(c.getValueC());
    }

    public void display() {
        valueC.display();
    }
    public boolean isZerro() {
        return valueC.isZerro();
    }
    public Complex getValueC() {
        return valueC;
    }
    public void setValueC(Complex valueC) {
        this.valueC = valueC;
    }
    public void setValueC(double valueC) {
        this.valueC = new Complex(valueC, 0);
    }



    @Override
    public int compareTo(Element o) {
        return this.valueC.compareTo(o.valueC);
    }

    @Override
    public String toString() {
        return "value: " + valueC.toString();
    }


    @Override
    public Element add(Element el) {
        return new Element(valueC.add(el.valueC));
    }

    @Override
    public Element add(double el) {
        return new Element(valueC.add(new Complex(el, 0)));
    }

    @Override
    public Element sub(Element el) {
        return new Element(valueC.sub(el.valueC));
    }

    @Override
    public Element sub(double el) {
        return new Element(valueC.sub(new Complex(el, 0)));
    }

    @Override
    public Element mult(Element el) {
        return new Element(valueC.mult(el.valueC));
    }

    @Override
    public Element mult(double el) {
        return new Element(valueC.mult(new Complex(el, 0)));
    }

    @Override
    public Element div(Element el) {
        return new Element(valueC.div(el.valueC));
    }

    @Override
    public Element div(double el) {
        return new Element(valueC.div(new Complex(el, 0)));
    }

    @Override
    public Element sqrt() {
        return new Element(valueC.sqrt());
    }

    public Element abs() {
        return new Element(valueC.abs());
    }
}
