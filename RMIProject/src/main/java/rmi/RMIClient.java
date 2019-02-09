package rmi;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class RMIClient extends Application  {
    protected ICrudCollection collectiond;
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
    private TextField idField, nameField, surnameField, avScoreField, searchField;

    @FXML
    private ComboBox<String> departmentField;

    ObservableList<String> langs;


    public RMIClient() {
        init();
    }

    public void init() {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            collectiond = (ICrudCollection) registry.lookup(ICrudCollection.NAME);
            Registry registry2 = LocateRegistry.getRegistry("localhost");
            collectiond = (ICrudCollection) registry2.lookup(ICrudCollection.NAME2);
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

        Parent secondRoot = FXMLLoader.load(getClass().getClassLoader().getResource("sample/main.fxml"));
        Scene secondScene = new Scene(secondRoot);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    public static void main(String[] args) {
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
        initFilter();


        tableUsers.setItems(studentListTable);
    }

    private void initFilter() {
        searchField.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchField.textProperty().get().isEmpty()) {
                    tableUsers.setItems(studentListTable);
                    return;
                }
                ObservableList<Student> tableItemas = FXCollections.observableArrayList();
                ObservableList<TableColumn<Student, ?>> cols = tableUsers.getColumns();
                for (int i = 0; i < studentListTable.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(studentListTable.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchField.textProperty().get().toLowerCase())) {
                            tableItemas.add(studentListTable.get(i));
                            break;
                        }
                    }
                }
                tableUsers.setItems(tableItemas);
            }
        });
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
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        int st = tableUsers.getSelectionModel().getSelectedIndex();
        if (student != null) {
            studentListTable.remove(st);
            collectiond.removeStudent(student.getGradebookNumber());
        }
    }

    @FXML
    public void getAll(ActionEvent actionEvent) throws RemoteException {

        studentListTable.clear();
        List<Student> s = collectiond.getAll();
        studentListTable.addAll(s);
        collectiond.getAll();
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
        if (surnameField.getText().isEmpty() || surnameField.getText().equals(null)) {
            surnameField.setStyle("-fx-border-color:red;");
            result = false;
        }

        if (avScoreField.getText().isEmpty() || Double.valueOf(avScoreField.getText()) > 5 || avScoreField.getText().equals(null)) {
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