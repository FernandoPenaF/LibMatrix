package matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class SquareMatrix extends Matrix{
    private final int size;
    
    public SquareMatrix(int n) {
        super(n, n);
        this.size = n;
    }

    public int getSize() {
        return size;
    }
    
    public void setToZeroMatrix(){
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                super.insertValue(0, i, j);
            }
        }
    }
    
    public void setToIdentityMatrix(){
        double delta;
        
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if(i == j)
                    delta = 1;
                else
                    delta = 0;
                super.insertValue(delta, i, j);
            }
        }
    }
    
    /*
    Dada una matriz, devuelve verdadero si esta es simétrica y falso en otro caso.
    Hacer
        return this.equals(this.getTraspose());
    tomaría más tiempo por el cálculo de la traspuesta y la comparación entre ambas.
    */
    public boolean isSimetric(){
        int rowLen = super.getRowsLength();
        int columnLen = super.getColumnsLength();

        for (int i = 0; i < rowLen; i++) {
            for (int j = i + 1; j < columnLen; j++) {
                if(super.getMatrixValue(i, j) != super.getMatrixValue(j, i))
                    return false;
            }
        }
        return true;
    }
    
    /*
    Dada una matriz, devuelve verdadero si esta es antisimétrica y falso en otro caso.
    Es decir, A (Traspuesta) = -A.
    */
    public boolean isAntisimetric(){
        int rowLen = super.getRowsLength();
        int columnLen = super.getColumnsLength();

        for (int i = 0; i < rowLen; i++) {
            for (int j = i; j < columnLen; j++) { //Similar a isSimetric(), pero ahora se analiza también el elemento (i,i)
                if(super.getMatrixValue(i, j) !=  (-1) * super.getMatrixValue(j, i))
                    return false;
            }
        }
        return true;
    }
    
    /*
    Devuelve la traza de la matrix.
    */
    public double getTrace(){
        double resul = 0;
        
        for (int i = 0; i < super.getRowsLength(); i++) {
            resul += super.getMatrixValue(i, i);
        }
        return resul;
    }
    
    /*
    Devuelve el valor del determinante de la matriz.
    */
    public double getDeterminant(){
        return computeDeterminant(this);
    }
    
    /*
    Cálcula el determinante de una matriz por el método de menores.
    */
    private double computeDeterminant(SquareMatrix matrix){
        if(matrix.getSize() == 1) //Si la matriz es de 1 x 1 se devuelve el valor de su determinante. (Caso base)
            return getUnitaryDeterminant(matrix);
        else{
            Queue <SquareMatrix> menores = getMenores(matrix); //Se obtienen los menores correspondientes a la matriz.
            double resul = 0;
            int coefficientSign = -1;
            int coefficientIndex = 0;

            /*
            Se calcula el determinante de cada menor, es decir,
            su determinante por su respectivo coefficiente y alternando los signos.
            */
            while(!menores.isEmpty()) {
                resul += (Math.pow(coefficientSign, coefficientIndex) * matrix.getMatrixValue(0, coefficientIndex)) * computeDeterminant(menores.remove());
                coefficientIndex++;
            }
            return resul;
        }
    }
    
    /*
    Devuelve el valor del determinante de una matrix de 1 x 1
    */
    private double getUnitaryDeterminant(SquareMatrix matrix){
        if(matrix.getSize() == 1)
            return matrix.getMatrixValue(0, 0);
        else
            throw new ArithmeticException("The matrix is not 1 x 1.");
    }
    
    /*
    Regresa una cola que contiene los menores de la matriz de n x n
    respecto a los elementos de la primera fila.
    Dichos menores son regresados en el orden de cola
    1, 2, 3, ... , n donde n es el tamaño de la matriz
    */
    private Queue <SquareMatrix> getMenores(SquareMatrix matrix){
        Queue <SquareMatrix> menores = new LinkedList();
        int columnLen = matrix.getColumnsLength();
        int rowLen = matrix.getRowsLength();
        
        for (int i = 0; i < columnLen; i++) {
            SquareMatrix menor = new SquareMatrix(rowLen - 1); //Se instancia la matriz menor con el tamaño adecuado.
            int columnInsertIndex = 0; //Indice para insertar datos en la columna respectiva, iniciando desde 0.
            
            for (int j = 0; j < columnLen; j++) {
                if(j == i) //Si estoy en la columna de mi valor menor entonces no se inserta a la matriz menor.
                    continue;
                for (int k = 1; k < rowLen; k++) { //Se omite la columna de coeficientes de los menores.
                    double value = matrix.getMatrixValue(k, j);
                    menor.insertValue(value, k - 1, columnInsertIndex);
                }
                columnInsertIndex++; //Se aumenta el indice de columna de inserción.
            }
            menores.add(menor); //Se añade el menor correspondiente a la cola de menores.
        }
        return menores;
    }
    
    /*
    Métodos sobrecargados.
    Se garantiza que las operaciones elementales devuelvan
    una matriz cuadrada, utilizando un método de copiado.
    */
    private SquareMatrix copyMatrixToSquareMatrix(Matrix matrix){
        int rowLen = matrix.getRowsLength();
        int columnLen = matrix.getRowsLength();
        
        if(rowLen == columnLen){
            SquareMatrix resul = new SquareMatrix(rowLen);
            
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < columnLen; j++) {
                    double value = matrix.getMatrixValue(i, j);
                    
                    resul.insertValue(value, i, j);
                }
            }
            return resul;
        }else
            throw new ArithmeticException("The matrix is not square.");
    }
    
    @Override
    public SquareMatrix getTraspose(){
        return copyMatrixToSquareMatrix(super.getTraspose());
    }
    
    @Override
    public SquareMatrix escalarMultiplication(double escalar){
        return copyMatrixToSquareMatrix(super.escalarMultiplication(escalar));
    }
    
    @Override
    public SquareMatrix sum(Matrix B){
        return copyMatrixToSquareMatrix(super.sum(B));
    }
    
    @Override
    public SquareMatrix substraction(Matrix B){
        return copyMatrixToSquareMatrix(super.substraction(B));
    }
    
    @Override
    public SquareMatrix multiplication(Matrix B){
        return copyMatrixToSquareMatrix(super.multiplication(B));
    }
}