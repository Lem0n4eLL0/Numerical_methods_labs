package NumMetods.Systems;

import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.MatrixCoefficientsSystem;
import NumMetods.SystemComponents.VectorSolutionsSystem;

public class ExtendedLinearSystem extends EquationSystem {

    public ExtendedLinearSystem(MatrixCoefficientsSystem TMatrix, VectorSolutionsSystem TVector) {
        super(TMatrix, TVector);
    }
    public ExtendedLinearSystem(EquationSystem system) {
        super(system);
    }

    @Override
    public void setElTMatrix(int rowIndex, int colIndex, Ratio value)
    {
        if(colIndex == super.getDimension())
            super.setElTVector(rowIndex, value);
        else
            super.setElTMatrix(rowIndex, colIndex, value);
    }
    @Override
    public Ratio getElTMatrix(int rowIndex, int colIndex)
    {
        if(colIndex == super.getDimension())
            return new Ratio(super.getElTVector(rowIndex), rowIndex);
        else
            return super.getElTMatrix(rowIndex, colIndex);
    }

}
