import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PClass extends UnicastRemoteObject implements Interface {
    private static final long serialVersionUID = 1L;
    private float[][] A;
    private float[][] B;

    protected PClass() throws RemoteException {
        super();
    }
    
    public float[][] getMatrixA(int N, int M) {
        A = new float[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = 2 * i + 3 * j;
            }
        }
        return A;
    }
    
    public float[][] getMatrixB(int M, int N) {
        B = new float[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                B[i][j] = 3 * i - 2 * j;
            }
        }
        return B;
    }
    
    @Override
    public float[][] multiplyMatrix(float[][] A, float[][] B) throws RemoteException {
        int N = A.length;
        int M = B.length;
        int K = B[0].length;
        float[][] BT = new float[K][M];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < M; j++) {
                BT[i][j] = B[j][i];
            }
        }
        float[][] C = new float[N][K];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                float sum = 0;
                for (int k = 0; k < M; k++) {
                    sum += A[i][k] * BT[j][k];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }
}    

