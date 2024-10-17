package NumMetods.Lab2;

import NumMetods.Answer;
import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.VectorSolutionsSystem;
import NumMetods.Systems.LinearEquationSystem;
import NumMetods.interfaces.Solvable;

public class SeidelMethod implements Solvable {
    private LinearEquationSystem syst;
    private Element eps;
    public SeidelMethod(LinearEquationSystem syst, double eps)
    {
        this.syst = new LinearEquationSystem(syst);
        this.eps = new Element(eps);
    }

    private Answer seidelMethod() {
        VectorSolutionsSystem x = new VectorSolutionsSystem(syst.getDimension());
        VectorSolutionsSystem tempx = new VectorSolutionsSystem(syst.getDimension());
        Answer answ = new Answer(syst.getDimension());
        new Element(0);
        Element norm;
        do {
            for(int i = 0; i < syst.getDimension(); i++) {
                x.setEl(i, syst.getElTVector(i));
                for (int j = 0; j < syst.getDimension(); j++) {
                    if(i != j) {
                        x.setEl(i, x.getEl(i).sub(syst.getElTMatrix(i, j).mult(x.getEl(j))));
                    }
                }
                x.setEl(i, x.getEl(i).div(syst.getElTMatrix(i, i)));
            }
            norm = x.getEl(0).sub(tempx.getEl(0)).abs();
            for(int i = 0; i < syst.getDimension(); i++) {
                if(x.getEl(i).sub(tempx.getEl(i)).abs().compareTo(norm) > 0) {
                    norm = x.getEl(i).sub(tempx.getEl(i)).abs();
                }
                tempx.setEl(i, new Element(x.getEl(i)));
            }
            answ.decrementNumOfOperation();
        } while(norm.compareTo(eps) > 0 && answ.getNumOfOperation() < 10);
        answ.setResult(x.getVector());
        return answ;
    }
    @Override
    public Answer calculate() {
        return seidelMethod();
    }

    public LinearEquationSystem getSyst() {
        return syst;
    }

    public void setSyst(LinearEquationSystem syst) {
        this.syst = syst;
    }
}
