package rmi.server;

import rmi.model.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CrudImpl extends UnicastRemoteObject implements ICrudCollection {
    public XMLParser xml = new XMLParser();
    private List<Student> studentList;


    public CrudImpl() throws RemoteException {
        xml.readListStudent();
    }


    @Override
    public void addStudent(Student student) throws RemoteException {
        xml.addStudentXML(student);
    }

    @Override

    public List<Student> getAll() throws RemoteException {
        return xml.readListStudent();
    }

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

    public Student findByGradebookNumber(long number) throws RemoteException {
        Student res = new Student();
        for (Student s : studentList) {
            if (s.getGradebookNumber() == (number)) {
                res = s;
            }
        }
        return res;
    }

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


    public void removeStudent(long idt) throws RemoteException {
        for (Student t : xml.readListStudent()) {
            if (t.getGradebookNumber() == idt) {
                xml.removeStudentXML(t.getGradebookNumber());
            }
        }
    }

    @Override
    public void updateStudentSurname(long id, String surname /*double s*/) throws RemoteException {
        for (Student t : xml.readListStudent()) {
            if (t.getGradebookNumber() == (id)) {
                System.out.println(t);
                System.out.println(t);
                t.setSurname(surname);
                xml.updateStudent(id, surname);
            }
        }
    }

    @Override
    public void updateStudentAv(long id, double st) throws RemoteException {
        xml.updateStudentAvXML(id, st);
    }
}