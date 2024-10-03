package NumMetods;

import NumMetods.Lab1.GausMethod;
import NumMetods.Lab1.KhaletskyMethod;
import NumMetods.SystemComponents.MatrixCoefficientsSystem;
import NumMetods.SystemComponents.VectorSolutionsSystem;
import NumMetods.Systems.LinearEquationSystem;
import NumMetods.Utils.MatrixUtils;
import NumMetods.Utils.SystemUtils;
import NumMetods.interfaces.Computed;
import NumMetods.interfaces.Solvable;

import java.util.Arrays;

import static NumMetods.Utils.MatrixUtils.multiply;

public class Main {
    public static void main(String[] args) {

        LinearEquationSystem syst = new LinearEquationSystem(new MatrixCoefficientsSystem(new double[][]{{3.74, 3.36, 2.94}, {4.02, 3.51, 3.04}, {4.18, 3.61, 3.09}}),
                new VectorSolutionsSystem(new double[] {63.26, 67.51, 70.03})); //[12.34, 5.92, -0.95]

        Computed c = new Computed() {
            @Override
            public Answer systemSolve(Solvable solvable) {
                return solvable.calculate();
            }
        };

        c.systemSolve(new GausMethod(syst)).display();
        System.out.println();
        c.systemSolve(new KhaletskyMethod(syst)).display();

    }

}

