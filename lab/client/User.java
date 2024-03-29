package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Callable;

import shared.*;

public class User {
    public static void main(String[] args) {
        try {
            var token = getSession();
            var printer = (IPrinter) getService("printer");
            usePrinter(printer, token);
        } catch(AuthenticationFailedException e) {
            FailMessage("Authentication erorr: " + e.getMessage());
        } catch (Exception e) {
            FailMessage("An error has occured. Contact your administrator.");
        }
    }

    public static Remote getService(String serviceName) 
    throws MalformedURLException, RemoteException, NotBoundException {
        String path = "rmi://localhost:" + Config.SERVER_PORT + "/" + serviceName;
        return Naming.lookup(path);
    }

    private static UUID getSession() throws SQLException, MalformedURLException, NotBoundException, RemoteException{
        Scanner in = new Scanner(System.in);
        System.out.println("Login to use print server");
        System.out.println("Username:");
        String username = in.next();
        System.out.println("Password:");
        String password = in.next();
        in.close();

        var sessionProvider = (ISessionProvider) getService("session");
        var token = sessionProvider.createSession(username, password);
        return token;
    }

    private static void usePrinter(IPrinter printer, UUID sessionToken) {
        LogOperation("print", () -> printer.print("authentication_lab.pdf", "best printer", sessionToken));
        LogOperation("queue", () -> printer.queue("printer", sessionToken));
        LogOperation("topQueue", () -> printer.topQueue("printer", 0, sessionToken));
        LogOperation("start", () -> printer.start(sessionToken));
        LogOperation("stop", () -> printer.stop(sessionToken));
        LogOperation("restart", () -> printer.restart(sessionToken));
        LogOperation("status", () -> printer.status("printer", sessionToken));
        LogOperation("readConfig", () -> printer.readConfig("parameter", sessionToken));
        LogOperation("setConfig", () -> printer.setConfig("parameter", "value", sessionToken));
    }

    private static <T> void LogOperation(String operation, Callable<T> op) {
        try{
            System.out.println("Calling operation: " + operation);
            op.call();
            SuccessMessage("Operation finished");
        } catch(AuthenticationFailedException e) {
            FailMessage("Authentication error: " + e.getMessage());
        } catch (Exception e) {
            FailMessage("An error has occured. Contact your administrator.");
        }
    }

    //color coded status messages for user actions
    private static void SuccessMessage(String msg) {System.out.println("\033[0;32m" + msg + "\033[0m");}
    private static void FailMessage(String msg) {System.out.println("\033[0;31m" + msg + "\033[0m");}
}
