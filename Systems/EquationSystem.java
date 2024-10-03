package NumMetods.Systems;

import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.MatrixCoefficientsSystem;
import NumMetods.SystemComponents.VectorSolutionsSystem;

public abstract class EquationSystem {
    private MatrixCoefficientsSystem TMatrix;
    private VectorSolutionsSystem TVector;
    int dimension;
    private StringBuilder view;
    public EquationSystem(MatrixCoefficientsSystem TMatrix, VectorSolutionsSystem TVector) {
        dimension = TVector.length();
        this.TMatrix = new MatrixCoefficientsSystem(TMatrix);
        this.TVector = new VectorSolutionsSystem(TVector);
    }
    public EquationSystem(EquationSystem system) {
        this(system.getTMatrix(), system.getTVector());
    }

    public void display(boolean pretty) {
        if(pretty)
            System.out.println(view + "\n");
        else
            display();
    }
    public void display() {
        StringBuilder view = new StringBuilder();
        for(int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                view.append(TMatrix.getEl(i, j) + " ");
            }
            view.append("| " + TVector.getEl(i) + "\n");
        }
        System.out.println(view + "\n");
    }

    public MatrixCoefficientsSystem getTMatrix() {
        return TMatrix;
    }
    public VectorSolutionsSystem getTVector() {
        return TVector;
    }
    public int getDimension() {
        return dimension;
    }
    public StringBuilder getView() {
        return view;
    }

    public void setElTMatrix(int rowIndex, int colIndex, Ratio value)
    {
        TMatrix.setEl(rowIndex, colIndex, value);
    }
    public void setElTVector(int index, Element value)
    {
        TVector.setEl(index, value);
    }

    public void setTMatrix(MatrixCoefficientsSystem TMatrix) {
        this.TMatrix = TMatrix;
    }

    public void setTVector(VectorSolutionsSystem TVector) {
        this.TVector = TVector;
    }

    public Ratio getElTMatrix(int rowIndex, int colIndex)
    {
       return TMatrix.getEl(rowIndex, colIndex);
    }
    public Element getElTVector(int index)
    {
        return TVector.getEl(index);
    }

    public void setView(StringBuilder view) {
        this.view = view;
    }
}
