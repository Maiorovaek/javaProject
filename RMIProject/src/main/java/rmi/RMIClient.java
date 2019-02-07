package rmi;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rmi.dao.ICrudCollection;
import rmi.model.Student;
import rmi.model.Student.Department;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Scanner;


public class RMIClient extends Application {
    private ICrudCollection collectiond;

    public RMIClient() throws IOException {

//        try {
        init();
//
//            mainLoop();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    public void init() {

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        new RMIClient();
        primaryStage.setTitle("RMI GUI Client API");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/main.fxml"));
        VBox rootBox = new VBox(5);
        rootBox.getChildren().addAll(root);
        primaryStage.setScene(new Scene(rootBox, 800, 500));
        primaryStage.show();

    }


//    private void mainLoop() throws IOException {
//        char choice = 0;

//        do {
//            System.out.println("0. Exit");
//            System.out.println("1. Get All Students");
//            System.out.println("2. Add New Student");
//            System.out.println("3. Find student's surname");
//            System.out.println("4. Find student's name");
//            System.out.println("5. Find student's departament");
//            System.out.println("6. Find student's gradebook Number");
//            System.out.println("7. who have score greater than entered ");
//            System.out.println("8. delete to student's gradebook Number ");
//            System.out.println("9. Update student ");
//
//            System.out.println("Введите пункт меню и нажмите Enter:");
//            choice = (char) System.in.read();

//            switch (choice) {
//                case '1':
//                    printAllStudents();
//                    break;
//                case '2':
//                    addStudent();
//                    break;
//                case '3':
//                    findSurname();
//                    break;
//                case '4':
//                    findByName();
//                    break;
//                case '5':
//                    findByDepartment();
//                    break;
//                case '6':
//                    findByGradebookNumber();
//                    break;
//                case '7':
//                    findWhosScoreGreater();
//                    break;
//                case '8':
//                    removeStudent();
//                    break;
//                case '9':
//                    updateStudent();
//                    break;
//            }
//            System.in.read();
//        }
//        while (choice != '0');
//        System.exit(0);
//    }


    private void addStudent() throws RemoteException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер зачетной книжки ");
        String numberS = in.next();
        long number = Integer.parseInt(numberS);
        System.out.println("Введите имя студента");
        String name = in.next();
        System.out.println("Введите фамилию студента");
        String surname = in.next();
        System.out.println("Введите отделение: ");
        System.out.println("существуют" + Arrays.toString(Department.values()));
        String d = in.next();
        Department subject = Department.valueOf(d);
        System.out.println("Введите оценку");
        String m = in.next();
        double minScore = Double.parseDouble(m);
           Student s = new Student(number, name, surname, subject, minScore);
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
        long high = Long.parseLong(number);
        collectiond.removeStudent(high);

    }


    private void updateStudent() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер зачетной книжки ");
        String idg = in.next();
        Long ids = Long.valueOf(idg);
        System.out.print("Введите обновленный балл");
        String averageScore = in.next();
        Double averageScoreD = Double.parseDouble(averageScore);
        System.out.println("Информация обновлена о студенте с номером зачетной книжки " + ids);
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
