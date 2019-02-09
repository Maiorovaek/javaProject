package rmi.server;

import rmi.ClientInterface;
import rmi.model.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CrudImpl extends UnicastRemoteObject implements ICrudCollection {

    private XMLParser xml = new XMLParser();
    private List<Student> studentList;

    public CrudImpl() throws RemoteException {
        this.xml.readListStudent();
    }

    @Override
    public synchronized void addStudent(Student student) throws RemoteException {
        this.xml.addStudentXML(student);
    }

    @Override

    public synchronized List<Student> getAll() throws RemoteException {
        return this.xml.readListStudent();
    }

    public synchronized List<Student> findBySurname(String surname) throws RemoteException {
        List<Student> res = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getSurname().equals(surname)) {
                System.out.println(s);
                res.add(s);
            }
        }
        return res;
    }

    public synchronized List<Student> findByName(String name) throws RemoteException {
        List<Student> res = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getName().equals(name)) {
                System.out.println(s);
                res.add(s);
            }

        }
        return res;
    }

    public synchronized List<Student> findByDepartment(Student.Department department) throws RemoteException {
        List<Student> res = new ArrayList<Student>();

        for (Student s : studentList) {
            if (s.getDepartmet().equals(department)) {
                System.out.println("Account found");
                res.add(s);
            } else {
                System.out.println("Account not found");
            }
        }

        return res;
    }

    public synchronized Student findByGradebookNumber(long number) throws RemoteException {
        Student res = new Student();
        for (Student s : studentList) {
            if (s.getGradebookNumber() == (number)) {
                res = s;
            }
        }
        return res;
    }

    public synchronized List<Student> findWhosScoreGreater(double minScore) throws RemoteException {
        List<Student> res = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getAverageScore() >= (minScore)) {
                System.out.println(s);
                res.add(s);
            }
        }
        return res;
    }


    public synchronized void removeStudent(long idt) throws RemoteException {
        this.xml.removeStudentXML(idt);
    }

    @Override
    public synchronized void updateStudentSurname(long id, String surname) throws RemoteException {
        for (Student t : this.xml.readListStudent()) {
            if (t.getGradebookNumber() == (id)) {
                t.setSurname(surname);
                this.xml.updateStudent(id, surname);
            }
        }
    }

    @Override
    public synchronized void updateStudentAv(long id, double st) throws RemoteException {
        this.xml.updateStudentAvXML(id, st);
    }




}