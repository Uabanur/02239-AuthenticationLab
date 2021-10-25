package shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import server.IUserAuthenticator;
import server.SessionManager;

public class Printer extends UnicastRemoteObject implements IPrinter{
    private IUserAuthenticator _auth;

    public Printer(IUserAuthenticator auth) throws RemoteException {
        super();
        this._auth = auth;
    }

    private void validateSessionOrThrow(byte[] sessionToken) {
        if(!SessionManager.validateSessionToken(sessionToken)) 
            throw new AuthenticationFailedException("Session expired");
    }
    
    public byte[] createSession(String username, String password) 
    throws RemoteException, AuthenticationFailedException, SQLException {
        if(!_auth.verifyPassword(username, password)) throw new AuthenticationFailedException("Failed to authenticate. Please try again.");
        return SessionManager.createSession(username);
    }

    public void print(String filename, String printer, byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("print: " + filename + " - " + printer);
    }

    public String[] queue(String printer, byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("queue: " + printer);
        return null;
    }

    public void topQueue(String printer, int job, byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("topQueue: " + printer + " - " + job);
    }

    public void start(byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("start printer");
    }

    public void stop(byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("stop printer");
    }

    public void restart(byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("restart printer");
    }

    public String status(String printer, byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("status: " + printer);
        return null;
    }

    public String readConfig(String parameter, byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("readConfig: " + parameter);
        return null;
    }

    public void setConfig(String parameter, String value, byte[] sessionToken)
    throws RemoteException, AuthenticationFailedException {
        validateSessionOrThrow(sessionToken);
        System.out.println("setConfig: " + parameter + " - " + value);
    }
}
