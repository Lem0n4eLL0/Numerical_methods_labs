package NumMetods;

import NumMetods.Lab1.GausMethod;
import NumMetods.Lab1.KhaletskyMethod;
import NumMetods.Lab2.JacobiMethod;
import NumMetods.Lab2.SORMethod;
import NumMetods.Lab2.SeidelMethod;
import NumMetods.Lab3.GivensMethod;
import NumMetods.Lab3.RegularizationMethod;
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

        LinearEquationSystem syst2 = new LinearEquationSystem(new MatrixCoefficientsSystem(new double[][]{
                {31.6, 0.1332, 0.1595, 0.1859},
                {0.1019, 30.7, 0.1545, 0.1809},
                {0.0969, 0.1232, 29.8, 0.1759},
                {0.0919, 0.1182, 0.1445, 28.9}}),
                new VectorSolutionsSystem(new double[]{86.8143, 90.3811, 93.5793, 96.4090})); // [2.7, 2.9, 3.1, 3.3]

        LinearEquationSystem syst3 = new LinearEquationSystem(new MatrixCoefficientsSystem(new double[][]{{1.03, 0.2973}, {0.991, 0.2829}}),
                new VectorSolutionsSystem(new double[]{2.50, 2.46})); //[7.44694, -17.39103]
        LinearEquationSystem systTest = new LinearEquationSystem(new MatrixCoefficientsSystem(new double[][]{{1.03, 0.991}, {0.991, 0.943}}),
                new VectorSolutionsSystem(new double[]{0.515, 0.415}));
        Computed c = new Computed() {
            @Override
            public Answer systemSolve(Solvable solvable) {
                return solvable.calculate();
            }
        };
        //Lab1
//        c.systemSolve(new GausMethod(syst)).display();
//        System.out.println();
//        c.systemSolve(new KhaletskyMethod(syst)).display();
        //Lab2
//        c.systemSolve(new JacobiMethod(syst2, 0.01)).display();
//        c.systemSolve(new SeidelMethod(syst2, 0.01)).display();
//        c.systemSolve(new SORMethod(syst2, 0.01, 1.8)).display();

        //Lab3
        //c.systemSolve(new RegularizationMethod(syst3, 5e-3, 1e-8, new VectorSolutionsSystem(new double[]{0, 0}))).display();
        c.systemSolve(new GausMethod(syst3)).display();
        c.systemSolve(new GivensMethod(syst3)).display();

    }

}

