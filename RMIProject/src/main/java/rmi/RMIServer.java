package rmi;

import rmi.server.CrudImpl;
import rmi.server.ICrudCollection;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(final String[] args) throws RemoteException  /*throws IOException, AlreadyBoundException*/ {

        CrudImpl crudClass = new CrudImpl();

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.bind(ICrudCollection.NAME, crudClass);
            System.out.println("RMI-Server runs");
        } catch (Throwable cause) {
            System.err.println("RMI-Server couldn't runs: " + cause.getMessage());

        }
    }
}
