package NumMetods.SystemComponents.Elements;

import NumMetods.Complex.Complex;
import NumMetods.SystemComponents.Elements.Element;

public class Ratio extends Element {
    private int xid;
    public Ratio(double value, int xid) {
        super(value);
        this.xid = xid;
    }
    public Ratio(Complex c, int xid) {
        super(c);
        this.xid = xid;
    }

    public Ratio(Element el, int xid) {
        super(el);
        this.xid = xid;
    }

    public Ratio(Ratio el) {
        super(el);
        this.xid = el.xid;
    }

    @Override
    public Element add(Element el) {
        return new Ratio(super.add(el), xid);
    }

    @Override
    public Element add(double el) {
        return new Ratio(super.add(el), xid);
    }

    @Override
    public Element sub(Element el) {
        return new Ratio(super.sub(el), xid);
    }

    @Override
    public Element sub(double el) {
        return new Ratio(super.sub(el), xid);
    }

    @Override
    public Element mult(Element el) {
        return new Ratio(super.mult(el), xid);
    }

    @Override
    public Element mult(double el) {
        return new Ratio(super.mult(el), xid);
    }

    @Override
    public Element div(Element el) {
        return new Ratio(super.div(el), xid);
    }

    @Override
    public Element div(double el) {
        return new Ratio(super.div(el), xid);
    }
    @Override
    public Element abs() {
        return new Ratio(super.abs(), xid);
    }
    public void setXid(int xid) {
        this.xid = xid;
    }
    public int getXid() {
        return xid;
    }

    @Override
    public String toString() {
        return super.toString() + " xid: " + (xid + 1);
    }
}
