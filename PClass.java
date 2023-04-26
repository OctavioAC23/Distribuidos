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

    public float[][][] getMatricesC1() throws RemoteException {
        return multiplicarMatrices0();
    }
    
    public float[][][] getMatricesC2() throws RemoteException {
        return multiplicarMatrices1();
    }

    public float[][][] getMatricesC3() throws RemoteException {
        return multiplicarMatrices2();
    }

    public float[][][] multiplicarMatrices0() throws RemoteException {
        int rowsA = A1.length;
        int colsA = A1[0].length;
        int colsB = B1[0].length;
        
        float[][][] A = new float[][][] {A1, A2, A3};
        float[][][] B = new float[][][] {B1, B2, B3, B4, B5, B6, B7, B8, B9};
        float[][][] matricesC1 = new float[27][rowsA][colsB];

        for (int c = 0; c < 27; c++) { // solo itera 27 veces
            float[][] currentB = B[c % 9]; // utiliza la matriz de B correspondiente
            float[][] currentA = A[c/9]; // utiliza la matriz A correspondiente
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        matricesC1[c][i][j] += currentA[i][k] * currentB[k][j]; // utiliza la matriz A y B correspondientes
                    }
                }
            }
        }
        return matricesC1;


    }

    public float[][][] multiplicarMatrices1() throws RemoteException {
        int rowsA = A4.length;
        int colsA = A4[0].length;
        int colsB = B4[0].length;
        float[][][] A = new float[][][] {A4, A5, A6};
        float[][][] B = new float[][][] {B1, B2, B3, B4, B5, B6, B7, B8, B9};
        float[][][] matricesC2 = new float[27][rowsA][colsB];

        for (int c = 0; c < 27; c++) { // solo itera 27 veces
            float[][] currentB = B[c % 9]; // utiliza la matriz de B correspondiente
            float[][] currentA = A[c/9]; // utiliza la matriz A correspondiente
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        matricesC2[c][i][j] += currentA[i][k] * currentB[k][j]; // utiliza la matriz A y B correspondientes
                    }
                }
            }
        }

        return matricesC2;

    }

    public float[][][] multiplicarMatrices2() throws RemoteException {
        int rowsA = A7.length;
        int colsA = A7[0].length;
        int colsB = B7[0].length;
        float[][][] A = new float[][][] {A7, A8, A9};
        float[][][] B = new float[][][] {B1, B2, B3, B4, B5, B6, B7, B8, B9};
        float[][][] matricesC3 = new float[27][rowsA][colsB];

        for (int c = 0; c < 27; c++) { // solo itera 27 veces
            float[][] currentB = B[c % 9]; // utiliza la matriz de B correspondiente
            float[][] currentA = A[c/9]; // utiliza la matriz A correspondiente
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        matricesC3[c][i][j] += currentA[i][k] * currentB[k][j]; // utiliza la matriz A y B correspondientes
                    }
                }
            }
        }

        return matricesC3;

    }
    
}

