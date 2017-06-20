package matrix;

import java.util.ArrayList;
/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class LinearAlgebraApplications {
    
    /*
    Si la matriz es cuadrada regresa una lista que contiene dos matrices,
    una simétrica y otra antisimétrica tales que
        matriz = simétrica + antisimétrica
    utilizando el teorema
        A = (1/2) { (A + A(traspuesta)) + (A - A(traspuesta)) }
        si A es cuadrada
    */
    public static ArrayList<Matrix> simetricAndAntisimetricDecomposition(Matrix matrix){
        if(MatrixOperations.isNxN(matrix)){
            ArrayList<Matrix> descomposicion = new ArrayList();
            Matrix traspose = matrix.getTraspose();
            Matrix simetric = MatrixOperations.sum(matrix, traspose);
            Matrix antisimetric = MatrixOperations.substraction(matrix, traspose);
            
            descomposicion.add(MatrixOperations.escalarMultiplication(simetric, (float) 0.5));
            descomposicion.add(MatrixOperations.escalarMultiplication(antisimetric, (float) 0.5));
            
            return descomposicion;
        }else
            throw new ArithmeticException("Matrix is not square.");
    }
}
