package matrix;

import java.util.ArrayList;
/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class LinearAlgebraApplications {
    
    /*
    Dada una matriz cuadrada, regresa una lista que contiene dos matrices,
    una simétrica y otra antisimétrica tales que
        matriz = simétrica + antisimétrica
    utilizando el teorema.
        A = (1/2) { (A + A(traspuesta)) + (A - A(traspuesta)) }
    */
    public static ArrayList<SquareMatrix> simetricAndAntisimetricDecomposition(SquareMatrix matrix){
        ArrayList<SquareMatrix> descomposicion = new ArrayList();
        SquareMatrix traspose = matrix.getTraspose();
        //Construcción de las matrices según el teorema.
        SquareMatrix simetric = matrix.sum(traspose);
        SquareMatrix antisimetric = matrix.substraction(traspose);

        descomposicion.add(simetric.escalarMultiplication(0.5));
        descomposicion.add(antisimetric.escalarMultiplication(0.5));
        
        return descomposicion;
    }
}