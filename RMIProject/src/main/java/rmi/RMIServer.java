package rmi;

import rmi.server.CrudImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {

            Registry registry = LocateRegistry.createRegistry(1099);
            ((Registry) registry).bind("MyRemote", new CrudImpl());
            System.out.println("bound 'RMIServer'");
        } catch (Throwable cause) {
            System.err.println("couldn't bind - cause " + cause.getMessage());

        }
    }
}
