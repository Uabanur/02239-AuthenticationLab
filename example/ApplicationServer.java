package example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import example.HelloServant;

public class ApplicationServer {
    public static void main(String[] args) throws RemoteException{
        int port = 5099;
        String name = "hello";
        HelloServant servant = new HelloServant();

        System.out.println("Creating registry for port: " + port);
        Registry registry = LocateRegistry.createRegistry(port);
        System.out.println("rebinding servant to name: " + name);
        registry.rebind(name, servant);
    }
}
