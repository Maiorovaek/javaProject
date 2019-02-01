package rmi.dao;

import rmi.model.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CrudImpl extends UnicastRemoteObject implements ICrudCollection {
    private List<Student> studentList;

    public CrudImpl() throws RemoteException {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "Alex", "Stasov", Student.Department.AppliedMathematics, 4.0));

        studentList.add(new Student(2, "Anton", "Deverin", Student.Department.AppliedMathematics, 4.9));
        studentList.add(new Student(3, "Anton", "Barsukov", Student.Department.InformationalRadiosystems, 4.5));
        studentList.add(new Student(4, "Dima", "Bozhenkinn", Student.Department.AppliedMathematics, 4.2));
        studentList.add(new Student(5, "Andre", "Stasov", Student.Department.AppliedMathematics, 4.4));
    }


    @Override
    public void addSt(Student student) throws RemoteException {
        studentList.add(student);

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
    public void removeStudent(long id) {
        studentList.removeIf(p -> p.getGradebookNumber() == (id));

    }

    @Override
    public void updateStudent(Student s) {
        for (Student t : studentList) {
            if (t.getGradebookNumber() == (s.getGradebookNumber())) {
                System.out.println(t);
                t.setGradebookNumber(s.getGradebookNumber());
                t.setAverageScore(s.getAverageScore());
                t.setDepartmet(s.getDepartmet());
                t.setName(s.getName());
                t.setSurname(s.getSurname());
                System.out.println(t);
            }
        }
    }
}