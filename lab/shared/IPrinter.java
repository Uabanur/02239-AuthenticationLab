package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IPrinter extends Remote {

    /**
     * Creates a limited authentication session token for the user.
     * @return Session token.
     * @throws RemoteException
     * @throws AuthenticationFailedException if username/password pair is invalid.
     * @throws SQLException
     */
    public byte[] createSession(String username, String password) throws RemoteException, AuthenticationFailedException, SQLException;

    /**
     * Prints file filename on the specified printer
     * @param filename
     * @param printer
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void print(String filename, String printer, byte[] sessionToken) throws RemoteException, AuthenticationFailedException;   

    /**
     * Lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>
     * @param printer
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @return
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public String[] queue(String printer, byte[] sessionToken) throws RemoteException, AuthenticationFailedException;  

    /** 
     * Moves job to the top of the queue
     * @param printer
     * @param job
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void topQueue(String printer, int job, byte[] sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Starts the print server
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void start(byte[] sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Stops the print server
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void stop(byte[] sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Stops the print server, clears the print queue and starts the print server again
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void restart(byte[] sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Prints status of printer on the user's display
     * @param printer
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @return
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public String status(String printer, byte[] sessionToken) throws RemoteException, AuthenticationFailedException;  

    /** 
     * Prints the value of the parameter on the user's display
     * @param parameter
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @return
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public String readConfig(String parameter, byte[] sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Sets the parameter to value
     * @param parameter
     * @param value
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void setConfig(String parameter, String value, byte[] sessionToken) throws RemoteException, AuthenticationFailedException;   
}