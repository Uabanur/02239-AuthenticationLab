package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;

import shared.*;

public class User {
    public static void main(String[] args) {
        try{
            String path = "rmi://localhost:"+Config.SERVER_PORT+"/"+Config.SERVICE_NAME;
            IPrinter printer = (IPrinter) Naming.lookup(path);
            usePrinter(printer);
        } catch (RemoteException e){
            System.err.println("Remote exception:");
            e.printStackTrace();
        } catch(AuthenticationFailedException e){
            System.err.println("Authentication exception:");
            e.printStackTrace();
        } catch(SQLException e){
            System.err.println("SQL exception:");
            e.printStackTrace();
        } catch (Exception e){
            System.err.println("Critical error:");
            e.printStackTrace();
        }
    }

    private static void usePrinter(IPrinter printer) 
    throws RemoteException, AuthenticationFailedException, SQLException {
        byte[] sessionToken = printer.createSession("roar", "roar_pass");
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
