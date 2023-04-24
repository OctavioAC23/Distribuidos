import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class RMIServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elige un nodo (0-2):");
        int node = scanner.nextInt();
        switch(node){
            case 0:
                System.out.println("Nodo 0");
                try {
                    int port = 1099; // Puerto por defecto
                    String ipAddress = "localhost";
                    LocateRegistry.createRegistry(port);
                    PClass multiplier = new PClass(node);
                    Naming.rebind("rmi://" + ipAddress + ":" + port + "/MatrixMultiplier", multiplier);
                    System.out.println("Servidor RMI listo en " + ipAddress + ":" + port);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;

            case 1:
                System.out.println("Nodo 1");
                try {
                    int port = 1234; // Puerto 1234
                    String ipAddress = "localhost";
                    LocateRegistry.createRegistry(port);
                    PClass multiplier = new PClass(node);
                    Naming.rebind("rmi://" + ipAddress + ":" + port + "/MatrixMultiplier", multiplier);
                    System.out.println("Servidor RMI listo en " + ipAddress + ":" + port);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;

            case 2:
                System.out.println("Nodo 2");
                try {
                    int port = 5678; // Puerto 5678
                    String ipAddress = "localhost";
                    LocateRegistry.createRegistry(port);
                    PClass multiplier = new PClass(node);
                    Naming.rebind("rmi://" + ipAddress + ":" + port + "/MatrixMultiplier", multiplier);
                    System.out.println("Servidor RMI listo en " + ipAddress + ":" + port);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Nodo inválido");
        }
    }
}
