package matrix;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public interface MatrixInterface {
    //Getters y setters
    double getMatrixValue(int row, int column); //Devuelve el valor de la matriz en cualquier entrada
    int getRowsLength(); //Devuelve el número de filas
    int getColumnsLength(); //Devuelve el número de columnas
    void insertValue(double value, int row, int column); //Inserta valor en el arreglo bidimensional, no en la "matriz"
    
    //Operaciones elementales, todas devuelve una nueva matriz.
    Matrix getTraspose(); //Devuelve la matriz transpuesta
    Matrix escalarMultiplication(double escalar); //Devuelve la matriz multiplicada por un escalar.
    Matrix sum(Matrix B); //Devuelve la suma de la matriz y B
    Matrix substraction(Matrix B); //Devuelve la resta de la matriz y B
    Matrix multiplication(Matrix B); //Devuelve la multiplicación de la matriz y B
    
    //Funcionalidad mínima
    @Override
    boolean equals(Object obj); //Dado un objeto, si es matriz, devuelve si es igual a esta.
    @Override
    String toString(); //Devuelve una cadena con el contenido de la matriz.
}