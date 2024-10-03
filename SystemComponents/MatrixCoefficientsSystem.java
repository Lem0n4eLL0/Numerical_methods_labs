package NumMetods.SystemComponents;

import NumMetods.SystemComponents.Elements.Ratio;

import java.util.Arrays;

public class MatrixCoefficientsSystem {
    private Ratio[][] matrix;
    public MatrixCoefficientsSystem(Ratio[][] matrix) {
        int dimension = matrix.length;
        this.matrix = new Ratio[dimension][dimension];
        for(int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                this.matrix[i][j] = new Ratio(matrix[i][j], j);
    }

    public MatrixCoefficientsSystem(double[][] matrix) {
        int dimension = matrix.length;
        this.matrix = new Ratio[dimension][dimension];
        for(int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                this.matrix[i][j] = new Ratio(matrix[i][j], j);
    }

    public MatrixCoefficientsSystem(int dimension) {
        this.matrix = new Ratio[dimension][dimension];
        for(int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                matrix[i][j] = new Ratio(0, j);
    }

    public MatrixCoefficientsSystem(MatrixCoefficientsSystem matrix)
    {
        this(matrix.getMatrix());
    }

    public void display() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
    public Ratio getEl(int rowIndex, int colIndex) {
        return matrix[rowIndex][colIndex];
    }

    public void setEl(int rowIndex, int colIndex, Ratio value) {
        matrix[rowIndex][colIndex] = value;
    }

    public Ratio[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Ratio[][] matrix) {
        this.matrix = matrix;
    }

    public int size() {
        return matrix.length;
    }
}
