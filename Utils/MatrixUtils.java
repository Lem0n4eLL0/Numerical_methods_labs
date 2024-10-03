package NumMetods.Utils;

import NumMetods.SystemComponents.Elements.Element;
import NumMetods.SystemComponents.Elements.Ratio;
import NumMetods.SystemComponents.MatrixCoefficientsSystem;
import NumMetods.SystemComponents.VectorSolutionsSystem;

public class MatrixUtils {
    public static MatrixCoefficientsSystem transposition(MatrixCoefficientsSystem matrix)
    {
        int size = matrix.getMatrix().length;
        MatrixCoefficientsSystem tMatrix = new MatrixCoefficientsSystem(matrix.getMatrix().length);
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                tMatrix.setEl(i, j, new Ratio(matrix.getEl(j, i), j));
        return tMatrix;
    }

    public static MatrixCoefficientsSystem multiply(MatrixCoefficientsSystem matrix1, MatrixCoefficientsSystem matrix2)
    {
        int NumColumnsM1 = matrix1.getMatrix()[0].length;
        int NumColumnsM2 = matrix2.getMatrix()[0].length;
        int NumRowsM1 = matrix1.getMatrix().length;
        int NumRowsM2 = matrix2.getMatrix().length;
        if(NumColumnsM1 != NumRowsM2)
            return null;
        MatrixCoefficientsSystem result = new MatrixCoefficientsSystem(matrix1.getMatrix().length);
        for(int i = 0; i < NumRowsM1; i++) {
            for (int j = 0; j < NumColumnsM2; j++) {
                result.setEl(i, j, new Ratio(0, j));
                for (int o = 0; o < NumColumnsM1; o++) {
                    result.setEl(i, j, (Ratio) result.getEl(i, j).add(matrix1.getEl(i, o).mult(matrix2.getEl(o, j))));
                }
            }
        }
        return result;
    }

    public static VectorSolutionsSystem multiply(MatrixCoefficientsSystem matrix, VectorSolutionsSystem vector)
    {
        int NumColumnsM1 = matrix.getMatrix()[0].length;
        int NumRowsM1 = matrix.getMatrix().length;
        int VectorSize = vector.length();
        Element temp = new Element(0);
        if(NumColumnsM1 != VectorSize)
            return null;
        VectorSolutionsSystem vect = new VectorSolutionsSystem(vector);
        for(int i = 0; i < NumRowsM1; i++) {
            temp.setValueC(0);
            for (int j = 0; j < VectorSize; j++) {
                temp = temp.add(vector.getEl(j).mult(matrix.getEl(i, j)));
            }
            vect.setEl(i, new Element(temp));
        }
        return vect;
    }

    public static MatrixCoefficientsSystem swapRows(MatrixCoefficientsSystem matrix, int rowIndex1, int rowIndex2)
    {
        Ratio temp;
        for(int i = 0; i < matrix.size(); i++)
        {
            temp = matrix.getEl(rowIndex1, i);
            matrix.setEl(rowIndex1, i, matrix.getEl(rowIndex2, i));
            matrix.setEl(rowIndex2, i, temp);
        }
        return matrix;
    }

    public static MatrixCoefficientsSystem swapColumns(MatrixCoefficientsSystem matrix, int colIndex1, int colIndex2)
    {
        Ratio temp;
        for(int i = 0; i < matrix.size(); i++)
        {
            temp = matrix.getEl(i, colIndex1);
            matrix.setEl(i, colIndex1, matrix.getEl(i, colIndex2));
            matrix.setEl(i, colIndex2, temp);
        }
        return matrix;
    }

    public static MatrixCoefficientsSystem convertToSymmetrical(MatrixCoefficientsSystem matrix) {
        return multiply(transposition(matrix), matrix);
    }

}
