package example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import example.HelloService;

public class HelloServant extends UnicastRemoteObject implements HelloService {

    protected HelloServant() throws RemoteException {
        super();
    }

    public String echo(String input) throws RemoteException {
        return "Return from server: " + input;
    }
}
