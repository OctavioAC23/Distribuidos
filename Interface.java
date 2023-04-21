import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
    float[][] getMatrixA(int N, int M) throws RemoteException;
    float[][] getMatrixB(int M, int N) throws RemoteException;
    float[][] multiplyMatrix(float[][] A, float[][] B) throws RemoteException;
}


