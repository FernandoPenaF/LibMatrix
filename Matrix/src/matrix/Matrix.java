package matrix;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class Matrix implements MatrixInterface{
    private double[][] matrix;
    private final int rows, columns;
    
    public Matrix(int m, int n){
        this.rows = m;
        this.columns = n;
        this.matrix = new double [this.rows][this.columns];
    }
    
    @Override
    public double getMatrixValue(int row, int column) {
        return matrix[row][column];
    }

    @Override
    public int getRowsLength() {
        return rows;
    }

    @Override
    public int getColumnsLength() {
        return columns;
    }
    
    /*
    Se inserta un número a la matriz con la notación normal de matrices
    iniciando con fila = 1 y columna = 1
    */
    public void setValue(double value, int row, int column){
        int indexRow = row - 1;
        int indexColumn = column - 1;
        
        if(indexRow >= 0 && indexRow < this.rows && indexColumn >= 0 && indexColumn < this.columns)
            this.matrix[indexRow][indexColumn] = value;
        else
            throw new IndexOutOfBoundsException(row + " " + column);
    }
    
    /*
    Se inserta un número a la matriz
    iniciando con fila = 0 y columna = 0.
    Se busca facilitar el manejo de indices a los programadores.
    */
    @Override
    public void insertValue(double value, int row, int column){
        if(row >= 0 && row < this.rows && column >= 0 && column < this.columns)
            this.matrix[row][column] = value;
        else
            throw new IndexOutOfBoundsException(row + " " + column);
    }
    
    /*
    Devuelve la matriz traspuesta.
    */
    @Override
    public Matrix getTraspose(){
        int rowLen = this.getRowsLength();
        int columnLen = this.getColumnsLength();
        Matrix traspose = new Matrix(columnLen, rowLen);
        
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                double value = this.getMatrixValue(i, j);
                
                traspose.insertValue(value, j, i);
            }
        }
        return traspose;
    }
    
    /*
    Se devuele la matriz multiplicada por el escalar dado.
    */
    @Override
    public Matrix escalarMultiplication(double escalar){
        int rowLen = this.getRowsLength();
        int columnLen = this.getColumnsLength();
        Matrix resul = new Matrix(rowLen, columnLen);
        
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                double multiplication = escalar * this.getMatrixValue(i, j);
                
                resul.insertValue(multiplication, i, j);
            }
        }
        return resul;
    }
    
    /*
    Regresa verdadero si esta matriz tiene
    el mismo número de filas y columnas que la matriz dada.
    Regresa falso en cualquier otro caso.
    */
    private boolean isSameSize(Matrix B){
        return this.getRowsLength() == B.getRowsLength() && this.getColumnsLength() == B.getColumnsLength();
    }
    
    /*
    Si la matriz A es del mismo tamaño que la matriz B,
    devuelve la matriz suma A + B
    */
    @Override
    public Matrix sum(Matrix B){
        if(this.isSameSize(B)){
            int rowLen = this.getRowsLength();
            int columnLen = this.getColumnsLength();
            Matrix resul = new Matrix(rowLen, columnLen);
            
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < columnLen; j++) {
                    double suma = this.getMatrixValue(i, j) + B.getMatrixValue(i, j);
                    
                    resul.insertValue(suma, i, j);
                }
            }
            return resul;
        }else
            throw new ArithmeticException("The matrices are not the same size.");
    }
    
    /*
    Si la matriz A es del mismo tamaño que la matriz B,
    devuelve la matriz resta A - B.
    Hacer
        return sum(A, escalarMultiplication(B, -1));
    tomaría más tiempo que solo hacer la suma de A y del simétrico de cada elemento de B
    */
    @Override
    public Matrix substraction(Matrix B){
        if(this.isSameSize(B)){
            int rowLen = this.getRowsLength();
            int columnLen = this.getColumnsLength();
            Matrix resul = new Matrix(rowLen, columnLen);
            
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < columnLen; j++) {
                    double suma = this.getMatrixValue(i, j) + (-1) * B.getMatrixValue(i, j);
                    
                    resul.insertValue(suma, i, j);
                }
            }
            return resul;
        }else
            throw new ArithmeticException("The matrices are not the same size.");
    }
    
    /*
    Regresa verdadero si el número de columnas de esta matriz
    es igual al número de filas de la matriz dada.
    Regresa falso en cualquier otro caso.
    */
    private boolean isMultiplicable(Matrix B){
        return this.getColumnsLength() == B.getRowsLength();
    }
    
    /*
    Si las matrices son del tamaño adecuado
    se regresa la matriz producto A * B
    */
    @Override
    public Matrix multiplication(Matrix B){
        if(this.isMultiplicable(B)){
            int rowLenA = this.getRowsLength();
            int rowLenB = B.getRowsLength();
            int columnLenB = B.getColumnsLength();
            Matrix resul = new Matrix(rowLenA, columnLenB);
            
            for (int i = 0; i < rowLenA; i++) {
                for (int j = 0; j < columnLenB; j++) {
                    float value = 0;
                    for (int k = 0; k < rowLenB; k++) {
                        value += this.getMatrixValue(i, k) * B.getMatrixValue(k, j);
                    }
                    resul.insertValue(value, i, j);
                }
            }
            return resul;
        }else
            throw new ArithmeticException("The columns of matrix this matrix do not match the rows of matrix B.");
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof Matrix){
            Matrix compare = (Matrix) obj;
            int rowLen = compare.getRowsLength();
            int columnLen = compare.getColumnsLength();
            
            if(rowLen == this.rows && columnLen == this.columns){
                for (int i = 0; i < rowLen; i++) {
                    for (int j = 0; j < columnLen; j++) {
                        if(compare.getMatrixValue(i, j) != this.matrix[i][j])
                            return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String output = "";
        
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                output += this.matrix[i][j] + " ";
            }
            output = output.substring(0, output.length() - 1); //Elimina espacio al final de cada renglón
            if(i < this.matrix.length - 1) //Aplica salto de línea excepto al último renglón.
                output += "\n";
        }
        return output;
    }
    
    public void print(){
        System.out.println(this.toString());
    }
}