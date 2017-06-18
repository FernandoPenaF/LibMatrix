/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author Fernando Peña
 */
public class MatrixOperations {
    
    public static boolean isSameSize(Matrix A, Matrix B){
        return A.getRowsLength() == B.getRowsLength() && A.getColumnsLength() == B.getColumnsLength();
    }
    
    public static boolean isNxN(Matrix matrix){
        return matrix.getRowsLength() == matrix.getColumnsLength();
    }
    
    public static boolean isMultiplicable(Matrix A, Matrix B){
        return A.getColumnsLength() == B.getRowsLength();
    }
    
    /*
    Si la matriz es cuadrada, devuelve su traza.
    */
    public static float getTrace(Matrix matrix){
        if(isNxN(matrix)){
            float resul = 0;
            
            for (int i = 0; i < matrix.getRowsLength(); i++) {
                resul += matrix.getMatrixValue(i, i);
            }
            return resul;
        }else
            throw new ArithmeticException("Matrix is not square.");
    }
    
    /*
    Multiplica la matriz dada por el escalar dado.
    */
    public static Matrix escalarMultiplication(Matrix matrix, float escalar){
        int rowLen = matrix.getRowsLength();
        int columnLen = matrix.getColumnsLength();
        Matrix resul = new Matrix(rowLen, columnLen);
        
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                float multiplication = escalar * matrix.getMatrixValue(i, j);
                
                resul.insertValue(multiplication, i, j);
            }
        }
        return resul;
    }
    
    /*
    Si la matriz A es del mismo tamaño que la matriz B,
    devuelve la suma A + B
    */
    public static Matrix sum(Matrix A, Matrix B){
        if(isSameSize(A, B)){
            int rowLen = A.getRowsLength();
            int columnLen = A.getColumnsLength();
            Matrix resul = new Matrix(rowLen, columnLen);
            
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < columnLen; j++) {
                    float suma = A.getMatrixValue(i, j) + B.getMatrixValue(i, j);
                    
                    resul.insertValue(suma, i, j);
                }
            }
            return resul;
        }else
            throw new ArithmeticException("The matrices are not the same size.");
    }
    
    /*
    Si la matriz A es del mismo tamaño que la matriz B,
    devuelve la resta A - B.
    Hacer
        return sum(A, escalarMultiplication(B, -1));
    tomaría más tiempo que solo hacer la suma de A y del simétrico de cada elemento de B
    */
    public static Matrix substraction(Matrix A, Matrix B){
        if(isSameSize(A, B)){
            int rowLen = A.getRowsLength();
            int columnLen = A.getColumnsLength();
            Matrix resul = new Matrix(rowLen, columnLen);
            
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < columnLen; j++) {
                    float suma = A.getMatrixValue(i, j) - B.getMatrixValue(i, j);
                    
                    resul.insertValue(suma, i, j);
                }
            }
            return resul;
        }else
            throw new ArithmeticException("The matrices are not the same size.");
    }
    
    public static Matrix multiplicacion(Matrix A, Matrix B){
        if(isMultiplicable(A, B)){
            int rowLenA = A.getRowsLength();
            int rowLenB = B.getRowsLength();
            int columnLenB = B.getColumnsLength();
            Matrix resul = new Matrix(rowLenA, columnLenB);
            
            for (int i = 0; i < rowLenA; i++) {
                for (int j = 0; j < columnLenB; j++) {
                    float value = 0;
                    for (int k = 0; k < rowLenB; k++) {
                        value += A.getMatrixValue(i, k) * B.getMatrixValue(k, j);
                    }
                    resul.insertValue(value, i, j);
                }
            }
            return resul;
        }else
            throw new ArithmeticException("The columns of matrix A do not match the rows of matrix B.");
    }
    
    public static void main(String[] args) {
        Matrix m1 = new Matrix(2, 2);
        m1.setValue(2, 1, 1);
        m1.setValue(3, 1, 2);
        m1.setValue(8, 2, 1);
        m1.setValue(9, 2, 2);
        m1.print();
        System.out.println("");
        Matrix m2 = new Matrix(2, 1);
        m2.setValue(2, 1, 1);
        m2.setValue(8, 2, 1);
        m2.print();
        System.out.println("");
        Matrix mul = MatrixOperations.multiplicacion(m1, m2);
        mul.print();
    }
}