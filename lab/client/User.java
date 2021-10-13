package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
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
        } catch (Exception e){
            System.err.println("Critical error:");
            e.printStackTrace();
        }
    }

    private static void usePrinter(IPrinter printer) throws RemoteException {
        printer.print("my file", "best printer");
        printer.queue("printer");
        printer.topQueue("printer", 0);
        printer.start();
        printer.stop();
        printer.restart();
        printer.status("printer");
        printer.readConfig("parameter");
        printer.setConfig("parameter", "value");
    }
}
