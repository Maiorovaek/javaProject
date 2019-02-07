package rmi.dao;

import rmi.XMLParser;
import rmi.model.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CrudImpl extends UnicastRemoteObject implements ICrudCollection {
    private List<Student> studentList;
    private XMLParser xml = new XMLParser();

    public CrudImpl() throws RemoteException {
        studentList = xml.readListStudent();
    }


    @Override
    public void addSt(Student student) throws RemoteException {
        studentList.add(student);
        xml.addStudent(student);
    }

    @Override

    public List<Student> getAll() throws RemoteException {
        for (Student s : studentList) {
            System.out.println(s);
        }
        return studentList;
    }

    @Override
    public List<Student> findBySurname(String surname) throws RemoteException {
        List<Student> res = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getSurname().equals(surname)) {
                System.out.println(s);
                res.add(s);
            }
        }
        return res;
    }

    @Override
    public List<Student> findByName(String name) throws RemoteException {
        List<Student> res = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getName().equals(name)) {
                System.out.println(s);
                res.add(s);
            }
        }
        return res;
    }

    @Override
    public List<Student> findByDepartment(Student.Department department) throws RemoteException {
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

    @Override
    public Student findByGradebookNumber(long number) throws RemoteException {
        Student res = new Student();
        for (Student s : studentList) {
            if (s.getGradebookNumber() == (number)) {
                res = s;
            }
        }
        return res;
    }

    @Override
    public List<Student> findWhosScoreGreater(double minScore) throws RemoteException {
        List<Student> res = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getAverageScore() >= (minScore)) {
                System.out.println(s);
                res.add(s);
            }
        }
        return res;
    }


    @Override
    public void removeStudent(long idt) throws RemoteException {
        // studentList.removeIf(p -> p.getGradebookNumber() == (idt));
        // xml.removeStudent(idt);
        for (Student t : studentList) {

            if (t.getGradebookNumber() == idt) {
                xml.removeStudent(idt);
            }
        }
    }

    @Override
    public void updateStudent(long id, String surname /*double s*/) throws RemoteException {
        for (Student t : studentList) {
            if (t.getGradebookNumber() == (id)) {
                System.out.println(t);
                // t.setAverageScore(s);
                System.out.println(t);
                t.setSurname(surname);
                xml.updateStudent(id, surname);
            }
        }
    }

    @Override
    public void updateStudentAver(long id, double st) throws RemoteException {
        for (Student t : studentList) {
            if (t.getGradebookNumber() == (id)) {
                System.out.println(t);
                t.setAverageScore(st);
                System.out.println(t);
                t.setAverageScore(st);
                xml.updateStudentAv(id, st);
            }
        }
    }
}