package rmi.dao;

import rmi.model.Student;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import java.util.List;


public class CrudImpl extends UnicastRemoteObject implements ICrudCollection {
    private List<Student> studentList;

    public CrudImpl() throws RemoteException {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "Alex", "Stasov", Student.Department.AppliedMathematics, 5.0));
        studentList.add(new Student(4, "Andre", "Stasov", Student.Department.AppliedMathematics, 5.0));
        studentList.add(new Student(2, "Anton", "Deverin", Student.Department.AppliedMathematics, 5.0));
        studentList.add(new Student(3, "Dima", "Bozhenkinn", Student.Department.AppliedMathematics, 5.0));
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
    public List<Student> findByDepartment(Student.Department surname) throws RemoteException {
        return null;
    }

    @Override
    public Student findByGradebookNumber(long number) throws RemoteException {
        return null;
    }

    @Override
    public List<Student> findWhosScoreGreater(double minScore) throws RemoteException {
        return null;
    }




    @Override
    public void removeStudent(String id) {

//
//        It works as expected,
//
//        Try checking IdeOneDemo
//
//        public static void main(String[] args) {
//            long a = 1111;
//            Long b = 1113l;
//
//            if (a == b) {
//                System.out.println("Equals");
//            } else {
//                System.out.println("not equals");
//            }
//        }
//        prints
//
//        not equals for me
//
//        Use compareTo() to compare Long, == wil not work in all case as far as the value is cached

//        List<Student> res = new ArrayList<>();
//        for (Student s : studentList) {
//            if (s.getName().equals(id)) {
//                res.add(s);
//                System.out.println("Equals");
//                studentList.remove(res);
//
//            } else {
//                System.out.println("Not equals");
//            }
//        }
//        return res;
    }


    public static void main(String[] args) throws RemoteException {

        CrudImpl crud = new CrudImpl();
        crud.addSt(new Student(8, "ggf", "fbdfb", Student.Department.AppliedMathematics, 25.0));
        //crud.getAll();
        crud.removeStudent("ggf");

      //  crud.getAll();


    }


}
