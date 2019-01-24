package rmi;

import rmi.dao.ICrudCollection;
import rmi.model.Student;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
        // ...
        System.out.println("7. who have score greater than entered ");
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

                    break;
                case '5':

                    break;
            }
        }
        while (choice != '0');
    }



    private void addStudent() throws RemoteException {
        Student s = new Student(1, "Anton", "Serov", Student.Department.AppliedMathematics, 5.0);
        collectiond.addSt(s);
        System.out.println("Добавлен студент " + s);
    }

    private void printAllStudents() throws RemoteException {
        System.out.println("Список студентов: "+ collectiond.getAll());
    }

    private void findSurname() throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите фамилию: ");
        String surname = in.next();

        System.out.println("Найти по фамилии" + collectiond.findBySurname(surname));
    }




    public static void main(String[] args) throws IOException {
        new RMIClient();

    }
}
