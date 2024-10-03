package NumMetods.Lab1;

import NumMetods.Answer;
import NumMetods.Cords;
import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.Systems.LinearEquationSystem;
import NumMetods.Utils.SystemUtils;
import NumMetods.interfaces.Solvable;

public class GausMethod implements Solvable {
    private LinearEquationSystem system;
    private Answer answer;
    public GausMethod(LinearEquationSystem system) {
        this.system = new LinearEquationSystem(system);
    }

    @Override
    public Answer calculate() {
        if(answer == null)
        {
            answer = new Answer(system.getDimension());
            uConversion(system);
            answer = SystemUtils.reverseCourse(system);
        }
        return answer;
    }

    private LinearEquationSystem uConversion(LinearEquationSystem system) {
        for(int i = 0; i < system.getDimension() - 1; i++) {
            if (system.getElTMatrix(i, i).isZerro())
                answer.setHasSolution(false);
            systemReductionUConversion(system, new Cords(i, i));
        }
        return system;
    }
    private void systemReductionUConversion(LinearEquationSystem system, Cords refElCords) {
        int currentSize = system.getDimension() - refElCords.getY();
        Element[] temp = new Element[currentSize];
        Element divider = system.getElTMatrix(refElCords.getX(), refElCords.getY());
        for(int i = refElCords.getY(); i < system.getDimension(); i++)
        {
            system.setElTMatrix(refElCords.getX(), i, (Ratio) system.getElTMatrix(refElCords.getX(), i).div(divider));
        }
        system.setElTVector(refElCords.getX(), system.getElTVector(refElCords.getX()).div(divider));
        for(int i = 1; i < currentSize; i++)
        {
            Element multiplier = system.getElTMatrix(i + refElCords.getX(), refElCords.getY());
            for(int j = 0; j < currentSize; j++)
            {
                temp[j] = system.getElTMatrix(refElCords.getX(), j + refElCords.getY()).mult(multiplier);
                system.setElTMatrix(i + refElCords.getX(),
                        j + refElCords.getX(),
                        (Ratio) system.getElTMatrix(i + refElCords.getX(), j + refElCords.getY()).sub(temp[j]));
            }
            temp[0] = system.getElTVector(refElCords.getX()).mult(multiplier);
            system.setElTVector(i + refElCords.getX(), system.getElTVector(i + refElCords.getX()).sub(temp[0]));
        }
        if(currentSize <= 2)
        {
            temp[0] = system.getElTMatrix(system.getDimension() - 1, system.getDimension() - 1);
            system.setElTMatrix(system.getDimension() - 1, system.getDimension() - 1, new Ratio(1, system.getDimension() - 1));
            system.setElTVector(system.getDimension() - 1, system.getElTVector(system.getDimension() - 1).div(temp[0]));
        }
    }
}
