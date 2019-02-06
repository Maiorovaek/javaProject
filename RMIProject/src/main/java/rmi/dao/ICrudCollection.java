package rmi.dao;

import rmi.model.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICrudCollection extends Remote {
    void addSt(Student student) throws RemoteException;
    List<Student> getAll() throws RemoteException;
    void removeStudent(long st) throws RemoteException;


    List<Student> findBySurname(String surname) throws RemoteException;
    List<Student> findByName(String name) throws RemoteException;
    List<Student> findByDepartment(Student.Department department) throws RemoteException;
    Student findByGradebookNumber(long number) throws RemoteException;
    List<Student> findWhosScoreGreater(double minScore) throws RemoteException;
    void updateStudent(long id, double s) throws RemoteException;
}
