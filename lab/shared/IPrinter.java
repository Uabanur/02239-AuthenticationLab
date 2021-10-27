package shared;

import java.rmi.RemoteException;
import java.util.UUID;

import server.IRemoteService;

public interface IPrinter extends IRemoteService {
    /**
     * Prints file filename on the specified printer
     * @param filename
     * @param printer
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void print(String filename, String printer, UUID sessionToken) throws RemoteException, AuthenticationFailedException;   

    /**
     * Lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>
     * @param printer
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @return
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public String[] queue(String printer, UUID sessionToken) throws RemoteException, AuthenticationFailedException;  

    /** 
     * Moves job to the top of the queue
     * @param printer
     * @param job
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void topQueue(String printer, int job, UUID sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Starts the print server
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void start(UUID sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Stops the print server
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void stop(UUID sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Stops the print server, clears the print queue and starts the print server again
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void restart(UUID sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Prints status of printer on the user's display
     * @param printer
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @return
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public String status(String printer, UUID sessionToken) throws RemoteException, AuthenticationFailedException;  

    /** 
     * Prints the value of the parameter on the user's display
     * @param parameter
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @return
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public String readConfig(String parameter, UUID sessionToken) throws RemoteException, AuthenticationFailedException;   

    /** 
     * Sets the parameter to value
     * @param parameter
     * @param value
     * @param sessionToken the session token authenticated using {@link #createSession}
     * @throws RemoteException
     * @throws AuthenticationFailedException
     */
    public void setConfig(String parameter, String value, UUID sessionToken) throws RemoteException, AuthenticationFailedException;   
}