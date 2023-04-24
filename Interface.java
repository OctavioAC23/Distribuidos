import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
    void sendMatrixA(float[][] A) throws RemoteException;
    void sendMatrixB(float[][] B) throws RemoteException;
    void borrarMatrices() throws RemoteException;
}
