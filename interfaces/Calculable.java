package NumMetods.interfaces;

import NumMetods.Complex.Complex;
import NumMetods.SystemComponents.Elements.Element;

public interface Calculable {
    Element add(Element el);
    Element add(double el);
    Element sub(Element el);
    Element sub(double el);
    Element mult(Element el1);
    Element mult(double el);
    Element div(Element el);
    Element div(double el);

    Element sqrt();
}
