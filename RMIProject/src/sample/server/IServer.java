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
    void editStudent(int id, Student book) throws RemoteException;
    ArrayList<Student> search(String ser, int mode) throws RemoteException;
    void registry(IClient client) throws RemoteException;
    void unregistry(IClient client) throws RemoteException;
    void updateSurname(long id, String surname) throws RemoteException;
    void updateStudentAv(long id, double s) throws RemoteException;
}