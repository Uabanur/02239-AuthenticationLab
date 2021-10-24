package shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Printer extends UnicastRemoteObject implements IPrinter{
    HashMap<String, Token> tokenMap = new HashMap<>();

    public Printer() throws RemoteException {
        super();
    }

    public boolean loginUser(String username, String hash) throws RemoteException {
//        if (PasswordHash.)
        return true;
    }

    public void print(String filename, String printer) throws RemoteException {
        System.out.println("print: " + filename + " - " + printer);
    }

    public String[] queue(String printer) throws RemoteException {
        System.out.println("queue: " + printer);
        return null;
    }

    public void topQueue(String printer, int job) throws RemoteException {
        System.out.println("topQueue: " + printer + " - " + job);
    }

    public void start() throws RemoteException {
        System.out.println("start printer");
    }

    public void stop() throws RemoteException {
        System.out.println("stop printer");
    }

    public void restart() throws RemoteException {
        System.out.println("restart printer");
    }

    public String status(String printer) throws RemoteException {
        System.out.println("status: " + printer);
        return null;
    }

    public String readConfig(String parameter) throws RemoteException {
        System.out.println("readConfig: " + parameter);
        return null;
    }

    public void setConfig(String parameter, String value) throws RemoteException {
        System.out.println("setConfig: " + parameter + " - " + value);
    }
}
