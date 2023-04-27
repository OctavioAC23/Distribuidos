import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIClient {
    private static final String[] SERVERS = {"localhost", "localhost", "localhost"};
    private static final int []PORT = {1099,1234,5678};
    private static final String SERVICE_NAME = "MatrixMultiplier";

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese la opcion 1 o 2");
            int opcion = scanner.nextInt();
            //Thread[] threads =new Thread[multipliers.length];
            if (opcion == 1) {
                Interface[] multipliers = new Interface[SERVERS.length];
                Thread[] threads = new Thread[SERVERS.length];
                MatrixThread[] threads2 = new MatrixThread[SERVERS.length];
                    for (int i = 0; i < SERVERS.length; i++) {
                        multipliers[i] = (Interface) Naming.lookup(String.format("rmi://%s:%d/%s", SERVERS[i], PORT[i], SERVICE_NAME));
                    }
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads[i] = new VaciarMatrices(multipliers[i]);
                        threads[i].start();
                    }
            
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads[i].join();
                    }
                try {
                    
                
                    int N = 9;
                    int M = 4;
                
                    float[][] A = new float[N][M];
                    float[][] B = new float[M][N];
                
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < M; j++) {
                            A[i][j] = 2 * i + 3 * j;
                            B[j][i] = 3 * i - 2 * j;
                        }
                    }
                
                    int numParts = 9;
                    int partSize = N / numParts;

                    for (int k = 0; k < numParts; k++) {
                        float[][] A_part = new float[partSize][M];
                        float[][] B_part = new float[M][partSize];
                        int startRow = k * partSize;
                        for (int i = startRow; i < startRow + partSize; i++) {
                            for (int j = 0; j < M; j++) {
                                A_part[i - startRow][j] = A[i][j];
                                B_part[j][i - startRow] = B[j][i];
                            }
                        }

                        for (int i = 0; i < SERVERS.length; i++) {
                            threads[i] = new SendThread(A_part, B_part, multipliers[i], k);
                            threads[i].start();
                        }
                
                        for (int i = 0; i < SERVERS.length; i++) {
                            threads[i].join();
                        }
                        
                    }

                    // Create the threads
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads2[i] = new  MatrixThread(multipliers[i], i);
                        threads2[i].start();
                    }
            
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads2[i].join();
                    }
                    // Get the matrices calculated by the threads
                    float[][][] matricesC1 = threads2[0].getMatrices();
                    float[][][] matricesC2 = threads2[1].getMatrices();
                    float[][][] matricesC3 = threads2[2].getMatrices();


                    float [][] C = new float[N][N];
                   // Mostrar la matriz C
                    System.out.println("Matriz C:"); 
                    int g=0;
                    for(int i=0;i<N;i++){
                        for(int j=0;j<N;j++){
                            if(g<27){
                            C[i][j] =   matricesC1[g][0][0];
                            C[i+3][j]   =   matricesC2[g][0][0];
                            C[i+6][j]  =   matricesC3[g][0][0];
                            g++;
                        }
                    }
                }
                    mostrarMatriz(C);
                    
                    double checksum = 0.0;
                for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    checksum += (double) C[i][j];
                }
                }
                checksum %= 1000000007.0;
                System.out.println("Checksum: " + checksum);

                


                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                
            }else if (opcion == 2) {
                    Interface[] multipliers = new Interface[SERVERS.length];
                    Thread[] threads = new Thread[SERVERS.length];
                    MatrixThread[] threads2 = new MatrixThread[SERVERS.length];
                    for (int i = 0; i < SERVERS.length; i++) {
                        multipliers[i] = (Interface) Naming.lookup(String.format("rmi://%s:%d/%s", SERVERS[i], PORT[i], SERVICE_NAME));
                    }
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads[i] = new VaciarMatrices(multipliers[i]);
                        threads[i].start();
                    }
            
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads[i].join();
                    }
                try {
                
                    int N = 900;
                    int M = 400;
                
                    float[][] A = new float[N][M];
                    float[][] B = new float[M][N];
                
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < M; j++) {
                            A[i][j] = 2 * i + 3 * j;
                            B[j][i] = 3 * i - 2 * j;
                        }
                    }
                
                    int numParts = 9;
                    int partSize = N / numParts;

                    for (int k = 0; k < numParts; k++) {
                        float[][] A_part = new float[partSize][M];
                        float[][] B_part = new float[M][partSize];
                        int startRow = k * partSize;
                        for (int i = startRow; i < startRow + partSize; i++) {
                            for (int j = 0; j < M; j++) {
                                A_part[i - startRow][j] = A[i][j];
                                B_part[j][i - startRow] = B[j][i];
                            }
                        }
                        for (int i = 0; i < SERVERS.length; i++) {
                            threads[i] = new SendThread(A_part, B_part, multipliers[i], k);
                            threads[i].start();
                        }
                
                        for (int i = 0; i < SERVERS.length; i++) {
                            threads[i].join();
                        }
                        
                    }
                    
                    // Create the threads
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads2[i] = new  MatrixThread(multipliers[i], i);
                        threads2[i].start();
                    }
            
                    for (int i = 0; i < SERVERS.length; i++) {
                        threads2[i].join();
                    }
                    // Get the matrices calculated by the threads
                    float[][][] matricesC1 = threads2[0].getMatrices();
                    float[][][] matricesC2 = threads2[1].getMatrices();
                    float[][][] matricesC3 = threads2[2].getMatrices();

                    // Perform the checksum
                    checksum(matricesC1, matricesC2, matricesC3);


                    

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Opcion invalida.");
            }
    
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
public static void mostrarMatriz(float[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

public static void checksum(float[][][]matriz1,float[][][]matriz2,float[][][]matriz3){
        double checksumTotal = 0.0;

        for (float[][] matriz : matriz1) {
            for (float[] fila : matriz) {
                for (float valor : fila) {
                    checksumTotal += valor;
                }
            }
        }

        for (float[][] matriz : matriz2) {
            for (float[] fila : matriz) {
                for (float valor : fila) {
                    checksumTotal += valor;
                }
            }
        }

        for (float[][] matriz : matriz3) {
            for (float[] fila : matriz) {
                for (float valor : fila) {
                    checksumTotal += valor;
                }
            }
        }

        checksumTotal = checksumTotal % (Math.pow(2, 32) - 1);

        System.out.println("Checksum total: " + checksumTotal);
    }
}

class SendThread extends Thread {
    private float[][] A_part;
    private float[][] B_part;
    private Interface multiplier;
    private int k;

    public SendThread(float[][] A_part, float[][] B_part, Interface multiplier, int k) {
        this.A_part = A_part;
        this.B_part = B_part;
        this.multiplier = multiplier;
        this.k = k;
    }

    public void run() {
            try {
                multiplier.sendMatrixA(A_part, k);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        
            try {
                multiplier.sendMatrixB(B_part, k);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            
    }
}

class MatrixThread extends Thread {
    private Interface multiplier;
    private int matrixNumber;
    private float[][][] matrices;

    public MatrixThread(Interface multiplier, int matrixNumber) {
        this.multiplier = multiplier;
        this.matrixNumber = matrixNumber;
    }

    public float[][][] getMatrices() {
        return matrices;
    }

    public void run() {
        try {
            if(matrixNumber == 0) {
                matrices = multiplier.getMatricesC1();
            } else if(matrixNumber == 1) {
                matrices = multiplier.getMatricesC2();
            } else if(matrixNumber == 2) {
                matrices = multiplier.getMatricesC3();
            }
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}

class VaciarMatrices extends Thread {
    private Interface multiplier;

    public VaciarMatrices(Interface multiplier) {

        this.multiplier = multiplier;
 
    }

    public void run() {
            try {
                multiplier.borrarMatrices();
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}