import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
    private static final String[] SERVERS = {"localhost", "localhost", "localhost"};
    private static final int []PORT = {1099,1234,5678};
    private static final String SERVICE_NAME = "MatrixMultiplier";

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        try {
            Interface[] multipliers = new Interface[SERVERS.length];
            for (int i = 0; i < SERVERS.length; i++) {
                multipliers[i] = (Interface) Naming.lookup(String.format("rmi://%s:%d/%s", SERVERS[i], PORT[i], SERVICE_NAME));
            }
            multipliers[0].borrarMatrices();
            multipliers[1].borrarMatrices();
            multipliers[2].borrarMatrices();
            System.out.println("Ingrese la opcion 1 o 2");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
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
                    
                        if (k < 3) {
                            multipliers[0].sendMatrixA(A_part); // enviar al nodo 0
                            multipliers[0].sendMatrixB(B_part);
                        } else if (k < 6) {
                            multipliers[1].sendMatrixA(A_part); // enviar al nodo 1
                            multipliers[1].sendMatrixB(B_part);
                        } else {
                            multipliers[2].sendMatrixA(A_part); // enviar al nodo 2
                            multipliers[2].sendMatrixB(B_part);
                        }
                    }
                    
                    int m = A.length;
                    int n = B[0].length;
                    int o = B.length;
            
                    float[][] result = new float[m][n];
            
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < n; j++) {
                            for (int k = 0; k < o; k++) {
                                result[i][j] += A[i][k] * B[k][j];
                            }
                        }
                    }
                    
                    // Mostrar matriz C
                    System.out.println("Matriz C resultante:");
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            System.out.print(result[i][j] + " ");
                        }
                        System.out.println();
                    }

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                
            }else if (opcion == 2) {
                
                System.out.println("PROXIMAMENTE.");
            } else {
                System.out.println("Opcion invalida.");
            }
    
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

}
