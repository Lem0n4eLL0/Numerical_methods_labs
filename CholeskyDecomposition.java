package NumMetods;

public class CholeskyDecomposition {
    // Метод для вычисления разложения Холецкого
    public static double[][] choleskyDecomposition(double[][] A) {
        int n = A.length;
        double[][] L = new double[n][n];

        // Выполняем разложение Холецкого
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0.0;

                // Вычисляем сумму для элементов L[i][j]
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * L[j][k];
                }

                if (i == j) {
                    // Диагональные элементы L
                    L[i][i] = Math.sqrt(A[i][i] - sum);
                } else {
                    // Внедиагональные элементы L
                    L[i][j] = (A[i][j] - sum) / L[j][j];
                }
            }
        }
        return L;
    }
    public static double[][] transpose(double[][] A) {
        int n = A.length;
        double[][] AT = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AT[i][j] = A[j][i]; // Транспонирование матрицы
            }
        }
        return AT;
    }

    public static double[][] multiplyMatrices(double[][] A, double[][] B) {
        int n = A.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += A[i][k] * B[k][j]; // Умножение матриц
                }
            }
        }
        return result;
    }

    public static double[] multiplyMatrixVector(double[][] A, double[] b) {
        int n = A.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += A[i][j] * b[j]; // Умножение матрицы на вектор
            }
        }
        return result;
    }
    public static void matrixPrint(double[][] a, int n){
        for (int i = 0; i < n; i++) {
            System.out.printf("Строка %d: ", i + 1);
            for (int j = 0; j < n; j++) {
                System.out.printf("%.2f ", a[i][j]);
            }
            System.out.println();
        }
    }
    public static void symmetrizeSystem(double[][] A, double[] b) {
        double[][] AT = transpose(A); // Транспонируем матрицу A
        double[][] newA = multiplyMatrices(AT, A); // Получаем симметричную матрицу A^T * A
        double[] newB = multiplyMatrixVector(AT, b); // Преобразуем вектор правой части A^T * b

        // Замещаем исходные A и b на новые симметричные
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = newA[i][j];
            }
            b[i] = newB[i];
        }
    }
    public static void vectorPrint(double[] b,int n){
        for(int i=0;i<n;i++){
            System.out.println(b[i]);
        }
    }


    // Метод для решения системы уравнений на основе разложения Холецкого
    public static double[] solveWithCholesky(double[][] L, double[] b) {
        int n = L.length;
        double[] y = new double[n];
        double[] x = new double[n];

        // Шаг 1: Решаем систему L * y = b (прямой ход)
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * y[j];
            }
            y[i] = (b[i] - sum) / L[i][i];
        }

        // Шаг 2: Решаем систему L^T * x = y (обратный ход)
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += L[j][i] * x[j];
            }
            x[i] = (y[i] - sum) / L[i][i];
        }

        return x;
    }

    public static void main(String[] args) {
//        double[][] A = {
//                {2.83, 2.50, 2.08},
//                {3.0, 2.55, 2.07},
//                {3.72, 3.21, 2.68}
//        };
//
//        double[] b ={33.28, 33.59, 43.43};
        double[][] A = {{10, 3},{4, 6}};
        double[] b = {3, 2};

        // Приведение системы к симметричному виду
        symmetrizeSystem(A, b);
        matrixPrint(A,2);
        vectorPrint(b, 2);
        // Выполняем разложение Холецкого и решаем систему
        double[][] L = choleskyDecomposition(A);
        matrixPrint(L,2);
        double[] x = solveWithCholesky(L, b);

        System.out.println("Решение системы:");
        for (double xi : x) {
            System.out.printf("%.5f ", xi);
        }
    }

}
