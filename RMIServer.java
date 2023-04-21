import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements Interface {
    private static final long serialVersionUID = 1L;
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static final String SERVICE_NAME = "MatrixMultiplicationService";
    
    protected RMIServer() throws RemoteException {
        super();
    }
    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(PORT);
            RMIServer server = new RMIServer();
            String url = String.format("rmi://%s:%d/%s", HOST, PORT, SERVICE_NAME);
            Naming.rebind(url, server);
            System.out.println("Server ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public float[][] multiplyMatrix(float[][] A, float[][] B) throws RemoteException {
        return new PClass().multiplyMatrix(A, B);
    }

    @Override
    public float[][] getMatrixA(int N, int M) throws RemoteException {
        return new PClass().getMatrixA(N, M);
    }

    @Override
    public float[][] getMatrixB(int M, int N) throws RemoteException {
        return new PClass().getMatrixB(M, N);
    }
}

