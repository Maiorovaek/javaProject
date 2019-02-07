package rmi.server;

import rmi.model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICrudCollection extends Remote {
    String NAME = "MyRemote";
    void addStudent(Student student) throws RemoteException;
    List<Student> getAll() throws RemoteException;
    void removeStudent(long st) throws RemoteException;
    void updateStudentSurname(long id, String surname) throws RemoteException;
    void updateStudentAv(long id, double s) throws RemoteException;

    List<Student> findBySurname(String surname) throws RemoteException;
    List<Student> findByName(String name) throws RemoteException;
    List<Student> findWhosScoreGreater(double minScore) throws RemoteException;
    List<Student> findByDepartment(Student.Department department) throws RemoteException;
    Student findByGradebookNumber(long number) throws RemoteException;
}
