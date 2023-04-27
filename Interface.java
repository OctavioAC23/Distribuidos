import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
    void sendMatrixA(float[][] A, int partNumber) throws RemoteException;
    void sendMatrixB(float[][] B, int par) throws RemoteException;
    void borrarMatrices() throws RemoteException;
    float[][][] getMatricesC1() throws RemoteException;
    float[][][] getMatricesC2() throws RemoteException;
    float[][][] getMatricesC3() throws RemoteException;   
}

