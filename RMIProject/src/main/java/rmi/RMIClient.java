package rmi;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import rmi.server.ICrudCollection;
import rmi.model.Student;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.function.Predicate;


public class RMIClient extends Application {
    private ICrudCollection collectiond;
    public ObservableList<Student> studentListTable = FXCollections.observableArrayList();
    @FXML
    private TableView<Student> tableUsers;

    @FXML
    private TableColumn<Student, Long> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn, surnameColumn;

    @FXML
    private TableColumn<Student, Student.Department> departmentColumn;

    @FXML
    private TableColumn<Student, Double> avScoreColumn;

    @FXML
    private TextField idField, nameField, surnameField, avScoreField,searchField;

    @FXML
    private ComboBox<String> departmentField;

    ObservableList<String> langs;


    public RMIClient(){
        init();
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
    public static void main(String[] args)  {
        launch(args);
    }

    // инициализируем форму данными
    @FXML
    private void initialize() throws RemoteException {
        initData();

    }

    private void initData() throws RemoteException {
         List<Student> s = collectiond.getAll();
        studentListTable.addAll(s);
        langs = FXCollections.observableArrayList("AppliedMathematics", "InformationalRadiosystems", "Chemistry", "ForeignLanguages");
        tableUsers.setEditable(true);

        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        departmentField.setItems(langs);
        avScoreColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("gradebookNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Student, Student.Department>("departmet"));
        avScoreColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("averageScore"));


        FilteredList<Student> filteredData = new FilteredList<Student>(studentListTable, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Student>) student -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    String high = String.valueOf(student.getGradebookNumber());
                    if (student.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (high.toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Student> sortedList = new SortedList<>(filteredData);
            sortedList.comparatorProperty().bind(tableUsers.comparatorProperty());
            tableUsers.setItems(sortedList);
        });
        tableUsers.setItems(studentListTable);

    }


    @FXML
    protected void addStudent(ActionEvent event) throws RemoteException {
        studentListTable = tableUsers.getItems();
        setDefaultFieldStyle();
        if (validation() == true) {
            String idFieldl = idField.getText();
            long idFieldLong = Long.parseLong(idFieldl);
            Student.Department dDepartment = Student.Department.valueOf(departmentField.getValue());
            double avScoreDouble = Double.parseDouble(avScoreField.getText());
            Student studentAdd = new Student(idFieldLong,
                    nameField.getText(),
                    surnameField.getText(),
                    dDepartment,
                    avScoreDouble);
            studentListTable.add(studentAdd);
            collectiond.addStudent(studentAdd);
            idField.setText("");
            nameField.setText("");
            surnameField.setText("");
            avScoreField.setText("");
        }
    }


    @FXML
    protected void delStudent(ActionEvent event) throws RemoteException {
        studentListTable = tableUsers.getItems();
        Student st = tableUsers.getSelectionModel().getSelectedItem();
        if (st != null) {
            int row = tableUsers.getSelectionModel().getSelectedIndex();
            tableUsers.getItems().remove(row);
            collectiond.removeStudent(st.getGradebookNumber());
        }
    }


    public void onEditChange(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) throws RemoteException {
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setSurname(studentStringCellEditEvent.getNewValue());
        collectiond.updateStudentSurname(student.getGradebookNumber(), student.getSurname());
    }

    public void onEditChangeAvSc(TableColumn.CellEditEvent<Student, Double> studentDoubleCellEditEvent) throws RemoteException {
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setAverageScore(studentDoubleCellEditEvent.getNewValue());
         collectiond.updateStudentAv(student.getGradebookNumber(), student.getAverageScore());
    }


    private boolean validation() {

        boolean result = true;
        if (idField.getText().equals(null) || idField.getText().isEmpty()) {
            idField.setStyle("-fx-border-color:red;");
            result = false;
        }
        if (nameField.getText().equals(null) || nameField.getText().isEmpty()) {
            nameField.setStyle("-fx-border-color:red;");
            result = false;

        }
        if (surnameField.getText().isEmpty() || surnameField.getText().equals(null) ) {
            surnameField.setStyle("-fx-border-color:red;");
            result = false;
        }

        if (avScoreField.getText().isEmpty() || Double.valueOf(avScoreField.getText()) > 5 || avScoreField.getText().equals(null) ) {
            avScoreField.setStyle("-fx-border-color:red;");
            result = false;
        }
        return result;
    }

    private void setDefaultFieldStyle() {
        idField.setStyle("-fx-border-color:darkgray;");
        nameField.setStyle("-fx-border-color:darkgray;");
        surnameField.setStyle("-fx-border-color:darkgray;");
        departmentField.setStyle("-fx-border-color:darkgray;");
        avScoreField.setStyle("-fx-border-color:darkgray;");
    }
}