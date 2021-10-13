package example;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
        int port = 5099;
        String name = "hello";
        HelloService service = (HelloService) Naming.lookup("rmi://localhost:"+port+"/"+name);
        System.out.println("--- " + service.echo("hey server"));
    }
}
