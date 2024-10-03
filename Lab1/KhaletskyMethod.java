package NumMetods.Lab1;

import NumMetods.Answer;
import NumMetods.Complex.Complex;
import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.MatrixCoefficientsSystem;
import NumMetods.SystemComponents.VectorSolutionsSystem;
import NumMetods.Systems.LinearEquationSystem;
import NumMetods.Utils.MatrixUtils;
import NumMetods.Utils.SystemUtils;
import NumMetods.interfaces.Solvable;

public class KhaletskyMethod implements Solvable {

    private LinearEquationSystem system;
    private Answer answer;
    public KhaletskyMethod(LinearEquationSystem system) {
        this.system = new LinearEquationSystem(system);
    }

    @Override
    public Answer calculate() {
        answer = new Answer(system.getDimension());
        system = SystemUtils.convertToSymmetrical(system);
        khaletskyDecomposition();
        return SystemUtils.reverseCourse(new LinearEquationSystem(
                MatrixUtils.transposition(system.getTMatrix()),
                new VectorSolutionsSystem(SystemUtils.reverseCourse(system).getResult())));
    }

    private void khaletskyDecomposition() {
        MatrixCoefficientsSystem decMatrix = new MatrixCoefficientsSystem(system.getDimension());
        Element sum = new Element(0);
        for (int i = 0; i < system.getDimension(); i++) {
            for (int j = 0; j < system.getDimension(); j++) {
                sum.setValueC(0);
                if(i == j)
                {
                    for(int p = 0; p < i; p++)
                        sum = sum.add(decMatrix.getEl(i, p).mult(decMatrix.getEl(i, p)));
                    decMatrix.setEl(i, i, new Ratio(system.getTMatrix().getEl(i, i).sub(sum).sqrt(), i));
                } else if(i < j)
                {
                    for(int p = 0; p < i; p++)
                        sum = sum.add(decMatrix.getEl(i, p).mult(decMatrix.getEl(j, p)));
                    decMatrix.setEl(j, i, new Ratio(system.getTMatrix().getEl(j, i).sub(sum).div(decMatrix.getEl(i, i)), i));
                }
            }
        }
        system.setTMatrix(decMatrix);
    }
}
