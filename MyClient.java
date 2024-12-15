import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyClient {
    public static void main(String[] args) {
        try {
            // Conecta al registro RMI en localhost y puerto 1099
            System.out.println("Connecting to RMI registry...");
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Busca el objeto remoto con el nombre "MyRemote"
            System.out.println("Looking up the remote object...");
            MyRemoteInterface stub = (MyRemoteInterface) registry.lookup("MyRemote");

            // Invoca el método remoto y muestra la respuesta
            System.out.println("Invoking remote method...");
            String response = stub.sayHello();
            System.out.println("Response from server: " + response);

        } catch (java.rmi.NotBoundException e) {
            System.err.println("The specified remote object is not bound in the registry.");
            e.printStackTrace();
        } catch (java.rmi.RemoteException e) {
            System.err.println("A RemoteException occurred. Check if the server is running and reachable.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}
