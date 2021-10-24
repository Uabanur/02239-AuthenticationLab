package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import shared.*;

public class ApplicationServer {
    public static void main(String[] args) throws RemoteException, SQLException{

        IUserAuthenticator auth = new UserAuthenticator();
        System.out.println("Should be true: " + auth.checkEncryptedPassword("nicu", "test123"));
        System.out.println("Should be false: " + auth.checkEncryptedPassword("nicu", "test124"));
        System.out.println("Should be true: " + auth.checkEncryptedPassword("roar", "roar_pass"));
        System.out.println("Should be false: " + auth.checkEncryptedPassword("roar", "filip_pass"));

        System.out.println();

        Printer service = new Printer();
        System.out.println("Creating registry for port: " + Config.SERVER_PORT);
        Registry registry = LocateRegistry.createRegistry(Config.SERVER_PORT);
        System.out.println("rebinding service to name: " + Config.SERVICE_NAME);
        registry.rebind(Config.SERVICE_NAME, service);
    }
}
