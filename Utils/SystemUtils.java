package NumMetods.Utils;

import NumMetods.Answer;
import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.VectorSolutionsSystem;
import NumMetods.Systems.LinearEquationSystem;

public class SystemUtils {

    public static LinearEquationSystem convertToSymmetrical(LinearEquationSystem system) {
        return new LinearEquationSystem(MatrixUtils.convertToSymmetrical(system.getTMatrix()),
                MatrixUtils.multiply(MatrixUtils.transposition(system.getTMatrix()), system.getTVector()));
    }
    public static Answer reverseCourse(LinearEquationSystem system) {
        LinearEquationSystem tempSyst = new LinearEquationSystem(system);
        Answer result = new Answer(tempSyst.getDimension());
        convertToTriangular(tempSyst);
        Element tempSum = new Element(0);
        int currentRoot = 0;
        for (int i = tempSyst.getDimension() - 1; i >= 0; i--) {
            tempSum.setValueC(0);
            currentRoot = 0;
            for (int j = 0; j < tempSyst.getDimension(); j++) {
                if(tempSyst.getElTMatrix(i, j).isZerro())
                    continue;
                else {
                    if(result.getRoot(tempSyst.getElTMatrix(i, j).getXid()).isZerro())
                        currentRoot = tempSyst.getElTMatrix(i, j).getXid();
                    else
                        tempSum = tempSum.add(tempSyst.getElTMatrix(i, j).mult(result.getRoot(tempSyst.getElTMatrix(i, j).getXid())));
                }
            }
            Ratio el = new Ratio(tempSyst.getElTVector(i).sub(tempSum).div(tempSyst.getElTMatrix(i, currentRoot)), currentRoot);
            result.setRoot(currentRoot, el);
        }
        return result;
    }
    private static LinearEquationSystem convertToTriangular(LinearEquationSystem system)
    {
        int[] zeros = new int[system.getDimension()];
        for(int i = 0; i < system.getDimension(); i++)
        {
            for(int j = 0; j < system.getDimension(); j++)
                if(system.getElTMatrix(i, j).isZerro())
                    zeros[i]++;
        }
        Element te;
        int temp = 0;
        for(int i = 1, j = 0; i < zeros.length; i++)
        {
            temp = zeros[i];
            j = i;
            while(j > 0 && temp < zeros[j - 1])
            {
                zeros[j] = zeros[j-1];
                MatrixUtils.swapRows(system.getTMatrix(), j, j-1);
                te = system.getTVector().getEl(j);
                system.getTVector().setEl(j, system.getTVector().getEl(j-1));
                system.getTVector().setEl(j-1, te);
                --j;
            }
            zeros[j] = temp;
        }
        return system;
    }
}
