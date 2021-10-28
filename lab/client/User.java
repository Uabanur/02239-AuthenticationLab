package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

import shared.*;

public class User {
    public static void main(String[] args) {
        try {
            var sessionProvider = (ISessionProvider) getService("session");
            Scanner in = new Scanner(System.in);
            System.out.println("Introduce the username and the password:");
            String username = in.next(), password = in.next();
            var token = sessionProvider.createSession(username, password);
            var printer = (IPrinter) getService("printer");
            usePrinter(printer, token);
        } catch (RemoteException e) {
            System.err.println("Remote exception:");
            e.printStackTrace();
        } catch(AuthenticationFailedException e) {
            System.err.println("Authentication exception:");
            e.printStackTrace();
        } catch(SQLException e) {
            System.err.println("SQL exception:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Critical error:");
            e.printStackTrace();
        }
    }

    public static Remote getService(String serviceName) 
    throws MalformedURLException, RemoteException, NotBoundException {
        String path = "rmi://localhost:" + Config.SERVER_PORT + "/" + serviceName;
        return Naming.lookup(path);
    }

    private static void usePrinter(IPrinter printer, UUID sessionToken) 
    throws RemoteException, AuthenticationFailedException, SQLException {
        printer.print("my file", "best printer", sessionToken);
        printer.queue("printer", sessionToken);
        printer.topQueue("printer", 0, sessionToken);
        printer.start(sessionToken);
        printer.stop(sessionToken);
        printer.restart(sessionToken);
        printer.status("printer", sessionToken);
        printer.readConfig("parameter", sessionToken);
        printer.setConfig("parameter", "value", sessionToken);
    }
}
