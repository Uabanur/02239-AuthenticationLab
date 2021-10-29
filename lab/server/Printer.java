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

    public boolean print(String filename, String printer, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("print: " + filename + " - " + printer);
        return true;
    }

    public String[] queue(String printer, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("queue: " + printer);
        return new String[]{};
    }

    public boolean topQueue(String printer, int job, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("topQueue: " + printer + " - " + job);
        return true;
    }

    public boolean start(UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("start printer");
        return true;
    }

    public boolean stop(UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("stop printer");
        return true;
    }

    public boolean restart(UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("restart printer");
        return true;
    }

    public String status(String printer, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("status: " + printer);
        return "ready";
    }

    public String readConfig(String parameter, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("readConfig: " + parameter);
        return "configs";
    }

    public boolean setConfig(String parameter, String value, UUID sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("setConfig: " + parameter + " - " + value);
        return true;
    }
}
