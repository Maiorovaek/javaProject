package sample.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.server.IServer;
import sample.Student;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

class Client extends UnicastRemoteObject implements IClient {

    static ObservableList<Student> data = FXCollections.observableArrayList();
    Registry reg;
    IServer rmi;

    public Client() throws RemoteException {
        super();
    }

    public boolean connect(Client cl) {
        try {
            reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            rmi = (IServer) reg.lookup("server");
            System.out.println("Connected");
            rmi.registry(cl);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        } catch (NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnetc(Client c) {
        try {
            rmi.unregistry(c);
            System.out.println("disconnect Client");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addData(Student student) throws RemoteException {
        try {
            data.add(student);
            rmi.addStudent(student);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void deleteDate(long n) throws RemoteException {
        rmi.deleteStudent(n);
    }


    public void updateStudentSurname(long id, String surname) throws RemoteException {
        rmi.updateSurname(id, surname);


    }


    public void updateStudentC(long id, double s) throws RemoteException {
        rmi.updateStudentAv(id, s);
    }

    public void edit(int index, Student b) {
        try {
            rmi.editStudent(index, b);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> search(String kategory, String what) {
        try {
            if (kategory.equals("Name"))
                return rmi.search(what, 1);
            if (kategory.equals("Publisher"))
                return rmi.search(what, 2);
            if (kategory.equals("Date"))
                return rmi.search(what, 3);
            if (kategory.equals("Pages"))
                return rmi.search(what, 4);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void Update() throws RemoteException {
        data.clear();
        //вывод таблицы
        ArrayList<Student> sdd = rmi.print();
        data.addAll(sdd);
        rmi.print();
    }
}