package NumMetods.Lab2;

import NumMetods.Answer;
import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.VectorSolutionsSystem;
import NumMetods.Systems.LinearEquationSystem;
import NumMetods.interfaces.Solvable;

public class JacobiMethod implements Solvable {

    private LinearEquationSystem syst;
    private Element eps;
    public JacobiMethod(LinearEquationSystem syst, double eps)
    {
        this.syst = new LinearEquationSystem(syst);
        this.eps = new Element(eps);
    }

    private Answer jacobiMethod() {
        VectorSolutionsSystem tempX = new VectorSolutionsSystem(syst.getTVector());
        VectorSolutionsSystem X = new VectorSolutionsSystem(syst.getDimension());
        Answer answ = new Answer(syst.getDimension());
        Element norm;
        do {
            for(int i = 0; i < syst.getDimension(); i++) {
                tempX.setEl(i, new Element(syst.getElTVector(i)));
                for (int j = 0; j < syst.getDimension(); j++) {
                    if(i != j) {
                        tempX.setEl(i, tempX.getEl(i).sub(syst.getElTMatrix(i, j).mult(X.getEl(j))));
                    }
                }
                tempX.setEl(i, tempX.getEl(i).div(syst.getElTMatrix(i, i)));
            }
            norm = X.getEl(0).sub(tempX.getEl(0)).abs();
            for(int i = 0; i < syst.getDimension(); i++) {
                if(X.getEl(i).sub(tempX.getEl(i)).abs().compareTo(norm) > 0) {
                    norm = X.getEl(i).sub(tempX.getEl(i)).abs();
                }
                X.setEl(i, new Element(tempX.getEl(i)));
            }
            answ.decrementNumOfOperation();
        } while(norm.compareTo(eps) > 0);
        answ.setResult(X.getVector());
        return answ;
    }
    @Override
    public Answer calculate() {
        return jacobiMethod();
    }

    public LinearEquationSystem getSyst() {
        return syst;
    }

    public void setSyst(LinearEquationSystem syst) {
        this.syst = syst;
    }
}
