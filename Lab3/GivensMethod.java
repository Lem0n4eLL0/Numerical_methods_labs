package NumMetods.Lab3;

import NumMetods.Answer;
import NumMetods.Lab1.GausMethod;
import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.Systems.ExtendedLinearSystem;
import NumMetods.Systems.LinearEquationSystem;
import NumMetods.interfaces.Solvable;

public class GivensMethod implements Solvable {
    private LinearEquationSystem syst;
    public GivensMethod(LinearEquationSystem syst)
    {
        this.syst = new LinearEquationSystem(syst);
    }

    private Answer givensMethod() {
        Answer answ = new Answer();
        ExtendedLinearSystem syst1 = new ExtendedLinearSystem(syst);
        Ratio temp = new Ratio(0, 0);
        Element c = new Element(0), s = new Element(0), r = new Element(0);
        for (int i = 0; i < syst1.getDimension() - 1; i++) {
            for (int k = i + 1; k < syst1.getDimension(); k++) {
                r = new Element(
                        syst1.getElTMatrix(i, i).mult(syst1.getElTMatrix(i, i))
                                .add(syst1.getElTMatrix(k, i).mult(syst1.getElTMatrix(k, i))).sqrt());
                if(!r.isZerro()) {
                    c = new Element(syst1.getElTMatrix(i, i).div(r));
                    s = new Element(syst1.getElTMatrix(k, i).div(r));

                    for (int j = i; j < syst1.getDimension() + 1; j++) {
                        temp = new Ratio(
                                c.mult(syst1.getElTMatrix(i, j))
                                        .add(s.mult(syst1.getElTMatrix(k, j))), j);
                        syst1.setElTMatrix(k, j, (Ratio) new Ratio(-1, j).mult(s).mult(syst1.getElTMatrix(i, j)
                                .add(c.mult(syst1.getElTMatrix(k, j)))));
                        syst1.setElTMatrix(i, j, temp);
                    }
                }
            }
        }
        LinearEquationSystem ss = new LinearEquationSystem(syst1.getTMatrix(), syst1.getTVector());
        answ.setResult(new GausMethod(ss).calculate().getResult());
        answ.decrementNumOfOperation();
        return answ;
    }
    @Override
    public Answer calculate() {
        return givensMethod();
    }

    public LinearEquationSystem getSyst() {
        return syst;
    }

    public void setSyst(LinearEquationSystem syst) {
        this.syst = syst;
    }
}
