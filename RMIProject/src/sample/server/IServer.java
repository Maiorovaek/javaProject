package sample.server;

import org.xml.sax.SAXException;
import sample.client.IClient;
import sample.Student;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServer extends Remote{
    void addStudent(Student ex) throws RemoteException;
    ArrayList<Student> print() throws RemoteException;
    void deleteStudent(long kol) throws RemoteException;
    void editStudent(long id, Student book) throws RemoteException;
    ArrayList<Student> search(String ser, int mode) throws RemoteException;
    void registry(IClient client) throws RemoteException;
    void unregistry(IClient client) throws RemoteException;
}