package rmi;
import rmi.server.CrudImpl;
import rmi.server.ICrudCollection;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    private volatile ICrudCollection client;

    public static void main(final String[] args) throws RemoteException {
// Создание удаленного RMI объекта
        CrudImpl crudClass = new CrudImpl();
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            /* Регистрация удаленного RMI объекта ICrudCollection.NAME
             * в реестре rmiregistry
             */
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            //Для связывания удаленного RMI объекта с реестром сервера используется метод bind
            registry.bind(ICrudCollection.NAME, crudClass);
            registry.bind(ICrudCollection.NAME2, crudClass);
            System.out.println("RMI-Server runs");
        } catch (Throwable cause) {
            System.err.println("RMI-Server couldn't runs: " + cause.getMessage());
        }
    }
}