import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIClient {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static final String SERVICE_NAME = "MatrixMultiplicationService";

    public static void main(String[] args) {
        try {
            Interface server = (Interface) Naming.lookup(String.format("rmi://%s:%d/%s", HOST, PORT, SERVICE_NAME));
            
            int option = 0;
            Scanner scanner = new Scanner(System.in);
            
            do {
                System.out.println("Choose an option:");
                System.out.println("1. Case 1 (N=9, M=4)");
                System.out.println("2. Case 2 (N=900, M=400)");
                System.out.println("0. Exit");
                System.out.print("> ");
                
                option = scanner.nextInt();
                
                switch (option) {
                    case 1:
                        int N = 9;
                        int M = 4;
                        float[][] A = server.getMatrixA(N, M);
                        float[][] B = server.getMatrixB(M, N);
                        float[][] C = server.multiplyMatrix(A, B);
                        System.out.println("Matrix C:");
                        printMatrix(C);
                        System.out.println("Checksum of C: " + calculateChecksum(C));
                        System.out.println();
                        break;
                    case 2:
                        N = 900;
                        M = 400;
                        A = server.getMatrixA(N, M);
                        B = server.getMatrixB(M, N);
                        C = server.multiplyMatrix(A, B);
                        System.out.println("Checksum of C: " + calculateChecksum(C));
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
                
            } while (option != 0);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static double calculateChecksum(float[][] matrix) {
        double checksum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                checksum += matrix[i][j];
            }
        }
        return checksum;
    }
    
    private static void printMatrix(float[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}


