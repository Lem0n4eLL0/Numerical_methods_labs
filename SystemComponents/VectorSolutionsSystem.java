package NumMetods.SystemComponents;

import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;

import java.util.Arrays;

public class VectorSolutionsSystem {

    private Element[] vector;

    public VectorSolutionsSystem(Element[] vector) {
        int dimension = vector.length;
        this.vector = new Element[dimension];
        for (int i = 0; i < dimension; i++)
            this.vector[i] = new Element(vector[i]);
    }

    public VectorSolutionsSystem(double[] vector) {
        int dimension = vector.length;
        this.vector = new Element[dimension];
        for (int i = 0; i < dimension; i++)
            this.vector[i] = new Element(vector[i]);
    }

    public VectorSolutionsSystem(VectorSolutionsSystem vector) {
        this(vector.getVector());
    }

    public VectorSolutionsSystem(int dimension) {
        this(new double[dimension]);
    }
    public void display() {
        System.out.println(Arrays.toString(vector));
    }

    public Element getEl(int index) {
        return vector[index];
    }

    public void setEl(int index, Element value) {
        vector[index] = value;
    }

    public Element[] getVector() {
        return vector;
    }

    public void setVector(Element[] vector) {
        this.vector = vector;
    }

    public int length() {
        return vector.length;
    }
}
