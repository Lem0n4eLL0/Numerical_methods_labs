package NumMetods;

import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.VectorSolutionsSystem;

import java.util.Arrays;

public class Answer {
    private Ratio[] result;
    private long numOfOperation;
    private boolean hasSolution;

    public Answer(int systDimension) {
        result = new Ratio[systDimension];
        for(int i = 0; i < systDimension; i++)
            result[i] = new Ratio(0, i);
        numOfOperation = 0;
        hasSolution = true;
    }
    public Answer(Element[] el) {
        result = new Ratio[el.length];
        for(int i = 0; i < el.length; i++)
            result[i] = new Ratio(el[i], i);
        numOfOperation = 0;
        hasSolution = true;
    }
    public Answer() {
        this(2);
    }
    public void display() {
        if(!hasSolution)
        {
            System.out.println("Система не имеет решений");
        } else
        {
            System.out.println("Количество Вычислительных операций: " + numOfOperation);
            System.out.println("Корни уравнения: " + Arrays.toString(result));
        }
    }

    public Element[] getResult() {
        return result;
    }

    public void setResult(Ratio[] result) {
        this.result = result;
    }
    public void setResult(Element[] result) {
        this.result = new Ratio[result.length];
        for (int i = 0; i < result.length; i++)
            this.result[i] = new Ratio(result[i], i);
    }
    public void setRoot(int index, Ratio value)
    {
        this.result[index] = value;
    }

    public Element getRoot(int index)
    {
        return this.result[index];
    }

    public long getNumOfOperation() {
        return numOfOperation;
    }

    public void setNumOfOperation(long numOfOperation) {
        this.numOfOperation = numOfOperation;
    }

    public void decrementNumOfOperation() {
        numOfOperation++;
    }

    public boolean isHasSolution() {
        return hasSolution;
    }

    public void setHasSolution(boolean hasSolution) {
        this.hasSolution = hasSolution;
    }
}
