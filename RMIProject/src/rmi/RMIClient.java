package rmi;

import rmi.dao.ICrudCollection;
import rmi.model.Student;
import rmi.model.Student.Department;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;


public class RMIClient {
    ICrudCollection collectiond;

    public RMIClient() throws IOException {

        try {
            init();
            mainLoop();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void init() {

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry();
            collectiond = (ICrudCollection) registry.lookup("MyRemote");

        } catch (Throwable cause) {
            System.err.println("" + cause.getMessage());
        }
    }

    private void mainLoop() throws IOException {
        char choice = 100;
        //   while (choice != '0') {
        System.out.println("0. Exit");
        System.out.println("1. Get All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Find student's surname");
        System.out.println("4. Find student's name");
        System.out.println("5. Find student's departament");
        System.out.println("6. Find student's gradebook Number");
        System.out.println("7. who have score greater than entered ");
        System.out.println("8. delete to student's gradebook Number ");
        System.out.println("9. Update student ");

        System.out.println("Введите пункт меню и нажмите Enter:");
        do {
            choice = (char) System.in.read();

            switch (choice) {
                case '1':
                    printAllStudents();
                    break;
                case '2':
                    addStudent();
                    break;
                case '3':
                    findSurname();
                    break;
                case '4':
                    findByName();
                    break;
                case '5':
                    findByDepartment();
                    break;
                case '6':
                    findByGradebookNumber();
                    break;
                case '7':
                    findWhosScoreGreater();
                    break;
                case '8':
                    removeStudent();
                    break;
                case '9':
                    updateStudent();
                    break;
            }
        }
        while (choice != '0');
    }


    private void addStudent() throws RemoteException {
        Student s = new Student(10, "Anton", "Serov", Department.AppliedMathematics, 5.0);
        collectiond.addSt(s);
        System.out.println("Добавлен студент " + s);
    }

    private void printAllStudents() throws RemoteException {
        System.out.println("Список студентов: " + collectiond.getAll());
    }

    private void findSurname() throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите фамилию: ");
        String surname = in.next();

        System.out.println("Найти по фамилии" + collectiond.findBySurname(surname));
    }

    private void findByName() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = in.next();

        System.out.println("Найти по имени" + collectiond.findByName(name));
    }

    private void findByDepartment() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите отделение: ");
        String d = in.next();
        Department high = Department.valueOf(d);
        System.out.println("Найти по отделению" + collectiond.findByDepartment(high));

    }

    private void findByGradebookNumber() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер зачетной книжки ");
        String number = in.next();
        long high = Integer.parseInt(number);
        System.out.println("Студент " + collectiond.findByGradebookNumber(high));
    }

    private void findWhosScoreGreater() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите оценку, чтобы найти студентов у которых балл выше введенной оценки ");
        String m = in.next();
        double minScore = Double.parseDouble(m);

        System.out.println("Студенты " + collectiond.findWhosScoreGreater(minScore));
    }

    private void removeStudent() throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер зачетной книжки для удаления студента ");
        String number = in.next();
        long high = Integer.parseInt(number);
        collectiond.removeStudent(high);
    }


    private void updateStudent() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер зачетной книжки ");
        String idg = in.next();
        Long ids = Long.valueOf(idg);

        Student studentUpdate = new Student(ids, "Maria", "Lokteva", Department.AppliedMathematics, 4);
        collectiond.updateStudent(studentUpdate);
    }


    public static void main(String[] args) throws IOException {
        new RMIClient();
    }
}
