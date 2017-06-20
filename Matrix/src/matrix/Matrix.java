package matrix;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class Matrix {
    private float[][] matrix;
    private int rows, columns;
    
    public Matrix(int m, int n){
        this.rows = m;
        this.columns = n;
        this.matrix = new float [this.rows][this.columns];
    }
    
    public float getMatrixValue(int row, int column) {
        return matrix[row][column];
    }

    public int getRowsLength() {
        return rows;
    }

    public int getColumnsLength() {
        return columns;
    }
    
    /*
    Se inserta a la matriz con la notación normal de matrices
    iniciando con fila = 1 y columna = 1
    */
    public void setValue(float value, int row, int column){
        int indexRow = row - 1;
        int indexColumn = column - 1;
        
        if(indexRow >= 0 && indexRow < this.rows && indexColumn >= 0 && indexColumn < this.columns)
            this.matrix[indexRow][indexColumn] = value;
        else
            throw new IndexOutOfBoundsException(row + " " + column);
    }
    
    /*
    Inserción indexada desde 0,0 para programadores de otros métodos.
    */
    public void insertValue(float value, int row, int column){
        if(row >= 0 && row < this.rows && column >= 0 && column < this.columns)
            this.matrix[row][column] = value;
        else
            throw new IndexOutOfBoundsException(row + " " + column);
    }
    
    /*
    Devuelve la matriz traspuesta.
    */
    public Matrix getTraspose(){
        int rowLen = this.getRowsLength();
        int columnLen = this.getColumnsLength();
        Matrix traspose = new Matrix(columnLen, rowLen);
        
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                float value = this.getMatrixValue(i, j);
                
                traspose.insertValue(value, j, i);
            }
        }
        return traspose;
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