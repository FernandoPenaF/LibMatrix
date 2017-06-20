/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.LinkedList;
import java.util.Queue;

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
    
    public static boolean isUnitaryMatrix(Matrix matrix){
        if(matrix.getRowsLength() == matrix.getColumnsLength() && matrix.getRowsLength() == 1)
            return true;
        return false;
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
    
    public static Matrix multiplication(Matrix A, Matrix B){
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
    
    public static float getDeterminant(Matrix matrix){
        if(isNxN(matrix)){ //Si la matriz es cuadrada se procede con el cálculo de su determinante.
            if(isUnitaryMatrix(matrix)) //Si la matriz es de 1 x 1 se devuelve el valor de su determinante. (Caso base)
                return getUnitaryDeterminant(matrix);
            else{
                Queue <Matrix> menores = getMenores(matrix); //Se obtienen los menores correspondientes a la matriz.
                float resul = 0;
                int coefficientSign = -1;
                int coefficientIndex = 0;
                
                /*
                Se calcula el determinante de cada menor, es decir,
                su determinante por su respectivo coefficiente con cambio de signo.
                */
                while(!menores.isEmpty()) {
                    resul += (Math.pow(coefficientSign, coefficientIndex) * matrix.getMatrixValue(0, coefficientIndex)) * getDeterminant(menores.remove());
                    coefficientIndex++;
                }
                return resul;
            }
        }else
            throw new ArithmeticException("Matrix is not square.");
    }
    
    /*
    Devuelve el valor del determinantes de una matrix de 1 x 1
    */
    private static float getUnitaryDeterminant(Matrix matrix){
        if(isUnitaryMatrix(matrix))
            return matrix.getMatrixValue(0, 0);
        else
            throw new ArithmeticException("The matrix is not 1 x 1.");
    }
    
    /*
    Regresa una cola que contiene los menores de la matriz de n x n
    respecto a los elementos de la primera fila.
    Dichos menores son regresados en el orden de cola
    1, 2, 3, ... , n
    */
    private static Queue <Matrix> getMenores(Matrix matrix){
        Queue <Matrix> menores = new LinkedList();
        int columnLen = matrix.getColumnsLength();
        int rowLen = matrix.getRowsLength();
        
        for (int i = 0; i < columnLen; i++) {
            Matrix menor = new Matrix(rowLen - 1, columnLen - 1); //Se instancia la matriz menor con el tamaño adecuado.
            int columnInsertIndex = 0; //Indice para insertar datos en la columna respectiva, iniciando desde 0.
            
            for (int j = 0; j < columnLen; j++) {
                if(j == i) //Si estoy en la columna de mi valor menor entonces no se inserta a la matriz menor.
                    continue;
                for (int k = 1; k < rowLen; k++) { //Se omite la columna de coeficientes de los menores.
                    float value = matrix.getMatrixValue(k, j);
                    menor.insertValue(value, k - 1, columnInsertIndex);
                }
                columnInsertIndex++; //Se aumenta el indice de columna de inserción.
            }
            menores.add(menor); //Se añade el menor correspondiente a la cola de menores.
        }
        return menores;
    }
    
    public static void main(String[] args) {
//        Matrix m0 = new Matrix(2, 2);
//        m0.setValue(2, 1, 1);
//        m0.setValue(2, 1, 2);
//        m0.setValue(3, 2, 1);
//        m0.setValue(5, 2, 2);
//        m0.print();
//        System.out.println("");
//        System.out.println(MatrixOperations.getDeterminant(m0));
//        System.out.println("");
        
        Matrix m1 = new Matrix(4, 4);
        Matrix mt;
        m1.setValue(2, 1, 1);
        m1.setValue(3, 1, 2);
        m1.setValue(3, 1, 3);
        m1.setValue(6, 1, 4);
        m1.setValue(2, 2, 1);
        m1.setValue(3, 2, 2);
        m1.setValue(6, 2, 3);
        m1.setValue(7, 2, 4);
        m1.setValue(4, 3, 1);
        m1.setValue(82, 3, 2);
        m1.setValue(0, 3, 3);
        m1.setValue(3, 3, 4);
        m1.setValue(2, 4, 1);
        m1.setValue(23, 4, 2);
        m1.setValue(2, 4, 3);
        m1.setValue(3, 4, 4);
        mt = m1.getTraspose();
        m1.print();
        System.out.println("");
        mt.print();
        System.out.println("");
        System.out.println(MatrixOperations.getDeterminant(m1));
        System.out.println(MatrixOperations.getDeterminant(mt));
    }
}