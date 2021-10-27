package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import shared.AuthenticationFailedException;
import shared.IPrinter;

public class Printer extends UnicastRemoteObject implements IPrinter{
    public String ServiceRouteName() { return "printer"; }

    public Printer() throws RemoteException {
        super();
    }

    private void validateSessionOrThrow(UUID sessionToken) {
        if(!SessionManager.validateSessionToken(sessionToken)) 
            throw new AuthenticationFailedException("Session expired");
    }

    public void print(String filename, String printer, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("print: " + filename + " - " + printer);
    }

    public String[] queue(String printer, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("queue: " + printer);
        return null;
    }

    public void topQueue(String printer, int job, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("topQueue: " + printer + " - " + job);
    }

    public void start(UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("start printer");
    }

    public void stop(UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("stop printer");
    }

    public void restart(UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("restart printer");
    }

    public String status(String printer, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("status: " + printer);
        return null;
    }

    public String readConfig(String parameter, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("readConfig: " + parameter);
        return null;
    }

    public void setConfig(String parameter, String value, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("setConfig: " + parameter + " - " + value);
    }
}
