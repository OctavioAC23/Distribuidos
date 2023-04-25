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
    

    private int countPartsReceived = 0;

public void sendMatrixA(float[][] A, int partNumber) throws RemoteException {
    switch(partNumber) {
        case 0:
            A1 = A;
            break;
        case 1:
            A2 = A;
            break;
        case 2:
            A3 = A;
            break;
        case 3:
            A4 = A;
            break;
        case 4:
            A5 = A;
            break;
        case 5:
            A6 = A;
            break;
        case 6:
            A7 = A;
            break;
        case 7:
            A8 = A;
            break;
        case 8:
            A9 = A;
            break;
    }
    countPartsReceived++;
    System.out.println("Part " + partNumber + " of matrix A received.");
}


public void sendMatrixB(float[][] B, int partNumber) throws RemoteException {
    switch(partNumber) {
        case 0:
            B1 = B;
            break;
        case 1:
            B2 = B;
            break;
        case 2:
            B3 = B;
            break;
        case 3:
            B4 = B;
            break;
        case 4:
            B5 = B;
            break;
        case 5:
            B6 = B;
            break;
        case 6:
            B7 = B;
            break;
        case 7:
            B8 = B;
            break;
        case 8:
            B9 = B;
            break;
    }
    countPartsReceived++;
    System.out.println("Part " + partNumber + " of matrix B received.");
    if (A9 != null) {
        if(node==0){
        System.out.println("entra aqui?");
        multiplicarMatrices0();          
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
    
    public float[][][] multiplicarMatrices0() throws RemoteException {
        int rowsA = A1.length;
        int colsA = A1[0].length;
        int colsB = B1[0].length;
        
        float[][][] A = new float[][][] {A1, A2, A3, A4, A5, A6, A7, A8, A9};
        float[][][] B = new float[][][] {B1, B2, B3, B4, B5, B6, B7, B8, B9};
        float[][][] matricesC = new float[9][rowsA][colsB];
    
        for (int c = 0; c < matricesC.length; c++) {
            float[][] currentB = B[c];
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        matricesC[c][i][j] += A1[i][k] * currentB[k][j];
                    }
                }
            }
            mostrarMatriz(matricesC[c]);
        }
    
        return matricesC;
    }
    
    

    public float[][] multiplicarMatrices1() throws RemoteException {
        int rowsA = A4.length;
        int colsA = A4[0].length;
        int colsB = B4[0].length;
        float[][] C28 = new float[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C28[i][j] += A4[i][k] * B4[k][j];
                }
            }
        }
        mostrarMatriz(C28);
        return C28;
    }

    public float[][] multiplicarMatrices2() throws RemoteException {
        int rowsA = A9.length;
        int colsA = A9[0].length;
        int colsB = B9[0].length;
        float[][] C81 = new float[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C81[i][j] += A9[i][k] * B9[k][j];
                }
            }
        }
        mostrarMatriz(C81);
        return C81;
    }
    
}

