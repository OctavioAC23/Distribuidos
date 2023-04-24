import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PClass extends UnicastRemoteObject implements Interface {
    private static final long serialVersionUID = 1L;
    private float[][] A1, A2, A3, A4, A5, A6, A7, A8, A9;
    private float[][] B1, B2, B3, B4, B5, B6, B7, B8, B9;
    private int node;

    public PClass(int node) throws RemoteException {
        super();
        this.node = node;
    }

    public void borrarMatrices() throws RemoteException {
        A1 = null;
        A2 = null;
        A3 = null;
        A4 = null;
        A5 = null;
        A6 = null;
        A7 = null;
        A8 = null;
        A9 = null;
        B1 = null;
        B2 = null;
        B3 = null;
        B4 = null;
        B5 = null;
        B6 = null;
        B7 = null;
        B8 = null;
        B9 = null;
        System.out.println("Matrices borradas");
    }
    

    @Override
    public void sendMatrixA(float[][] A) throws RemoteException {
        if (node == 0) {
            if (A1 == null) {
                A1 = A;
            }
            else if (A2 == null) {
                A2 = A;
            }
            else if (A3 == null) {
                A3 = A;
                System.out.println("Matriz A1, A2, A3 recibida");
            }
            
        }
        else if (node == 1) {
            if (A4 == null) {
                A4 = A;
            }
            else if (A5 == null) {
                A5 = A;
            }
            else if (A6 == null) {
                A6 = A;
                System.out.println("Matriz A4, A5, A6 recibida");
            }
            
        } else if (node == 2) {
            if (A7 == null) {
                A7 = A;
            }
            else if (A8 == null) {
                A8 = A;
            }
            else if (A9 == null) {
                A9 = A;
                System.out.println("Matriz A7, A8, A9 recibida");
            }
        }
    }

    @Override
    public void sendMatrixB(float[][] B) throws RemoteException {
        if (node == 0) {
            if (B1 == null) {
                B1 = B;
            }
            else if (B2 == null) {
                B2 = B;
            }
            else if (B3 == null) {
                B3 = B;
                System.out.println("Matriz B1, B2, B3 recibida");
            }
        }
        else if (node == 1) {
            if (B4 == null) {
                B4 = B;
            }
            else if (B5 == null) {
                B5 = B;
            }
            else if (B6 == null) {
                B6 = B;
                System.out.println("Matriz B4, B5, B6 recibida");
            }
        } else if (node == 2) {
            if (B7 == null) {
                B7 = B;
            }
            else if (B8 == null) {
                B8 = B;
            }
            else if (B9 == null) {
                B9 = B;
                System.out.println("Matriz B7, B8, B9 recibida");
            }
            
        }
    }
    
    private void mostrarMatriz(float[][] matriz) {
        int rows = matriz.length;
        int cols = matriz[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

