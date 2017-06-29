package matrix;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class Main {
    public static void main(String[] args) {
        SquareMatrix m3 = new SquareMatrix(2);
        m3.print();
        m3.setToIdentityMatrix();
        System.out.println("");
        m3.print();
        System.out.println("");
        m3.setToZeroMatrix();
        m3.print();
    }
}