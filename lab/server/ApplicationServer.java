package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.sql.Connection;

import shared.*;

public class ApplicationServer {
    public static void main(String[] args) throws RemoteException, SQLException, ClassNotFoundException{
        Printer service = new Printer();

        Connection con = AuthDatabase.getConnection();

        System.out.println("Creating registry for port: " + Config.SERVER_PORT);
        Registry registry = LocateRegistry.createRegistry(Config.SERVER_PORT);
        System.out.println("rebinding service to name: " + Config.SERVICE_NAME);
        registry.rebind(Config.SERVICE_NAME, service);
    }
}
