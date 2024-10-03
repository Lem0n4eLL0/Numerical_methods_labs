package NumMetods.Systems;

import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.MatrixCoefficientsSystem;
import NumMetods.SystemComponents.VectorSolutionsSystem;

public class LinearEquationSystem extends EquationSystem {

    public LinearEquationSystem(MatrixCoefficientsSystem TMatrix, VectorSolutionsSystem TVector) {
        super(TMatrix, TVector);
    }
    public LinearEquationSystem(LinearEquationSystem system) {
        super(system);
    }

    @Override
    public void display(boolean pretty) {
        if(getView() == null)
        {
            StringBuilder view = new StringBuilder();
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    view.append(getElTMatrix(i, j).compareTo(new Ratio(0, getElTMatrix(i, j).getXid())) < 0 ? (j == 0) ?
                            getElTMatrix(i, j) + "x" + (j + 1) :
                            " - " + getElTMatrix(i, j).mult( -1) + "x" + (j + 1) :
                            (j == 0)?getElTMatrix(i, j) + "x" + (j + 1):
                                    " + " + getElTMatrix(i, j) + "x" + (j + 1));
                }
                view.append(" = " + getElTVector(i) + "\n");
            }
            setView(view);
        }
        super.display(pretty);
    }


}
