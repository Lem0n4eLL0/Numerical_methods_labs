package NumMetods.Lab3;

import NumMetods.Answer;
import NumMetods.Lab1.GausMethod;
import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.VectorSolutionsSystem;
import NumMetods.Systems.LinearEquationSystem;
import NumMetods.Utils.MatrixUtils;
import NumMetods.Utils.SystemUtils;
import NumMetods.interfaces.Solvable;

public class RegularizationMethod implements Solvable {
    private LinearEquationSystem syst;
    private Element eps;
    private Element a;
    private VectorSolutionsSystem x;
    public RegularizationMethod(LinearEquationSystem syst, double eps, double a, VectorSolutionsSystem x)
    {
        this.syst = new LinearEquationSystem(syst);
        this.eps = new Element(eps);
        this.a = new Element(a);
        this.x = x;
    }

    private Answer regularizationMethod() {
        Answer answ = new Answer(x.getVector());
        LinearEquationSystem syst1 = new LinearEquationSystem(MatrixUtils.multiply(MatrixUtils.transposition(syst.getTMatrix()), syst.getTMatrix()),
                MatrixUtils.multiply(MatrixUtils.transposition(syst.getTMatrix()), syst.getTVector()));
        VectorSolutionsSystem x1 = new VectorSolutionsSystem(syst.getDimension());
        Element norm = new Element(0), alfa = new Element(0);
        do {
            answ.decrementNumOfOperation();
            alfa = alfa.add(a);
            syst1 = transformedSystem(syst1, alfa, new VectorSolutionsSystem(answ.getResult()));
            x1 = new VectorSolutionsSystem(new GausMethod(syst1).calculate().getResult());
            norm = x1.getEl(0).sub(answ.getRoot(0)).abs();
            for (int i = 0; i < syst.getDimension(); i++)
                if(norm.compareTo(x1.getEl(i).sub(answ.getRoot(i)).abs()) < 0)
                    norm = x1.getEl(i).sub(answ.getRoot(i)).abs();
            answ.setResult(x1.getVector());
        } while(norm.compareTo(eps) > 0);
        return answ;
    }
    private LinearEquationSystem transformedSystem(LinearEquationSystem s, Element alfa, VectorSolutionsSystem v) {
        LinearEquationSystem s1 = new LinearEquationSystem(s);
        for(int i = 0; i < s1.getDimension(); i++) {
            s1.setElTMatrix(i, i, (Ratio) s1.getElTMatrix(i, i).add(alfa));
        }
        for (int i = 0; i < s1.getDimension(); i++) {
            s1.setElTVector(i, s1.getElTVector(i).add(alfa.mult(v.getEl(i))));
        }
        return s1;
    }

    @Override
    public Answer calculate() {
        return regularizationMethod();
    }

    public LinearEquationSystem getSyst() {
        return syst;
    }

    public void setSyst(LinearEquationSystem syst) {
        this.syst = syst;
    }
}
