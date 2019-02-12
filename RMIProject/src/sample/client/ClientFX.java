package sample.client;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.DoubleStringConverter;
import sample.Student;

import java.io.IOException;
import java.rmi.RemoteException;

public class ClientFX extends Application {

    private Client client;


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

    public ClientFX() throws RemoteException {
        client = new Client();
        client.connect(client);

    }


    //То, что будет выполнено при старте приложения
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            client.Update();
            primaryStage.setTitle("RMI GUI ClientImpl API");

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/main.fxml"));
            VBox rootBox = new VBox(5);
            rootBox.getChildren().addAll(root);
            primaryStage.setScene(new Scene(rootBox, 800, 500));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void initialize() throws RemoteException {


        langs = FXCollections.observableArrayList("AppliedMathematics", "InformationalRadiosystems", "Chemistry", "ForeignLanguages");
        // tableUsers.setEditable(true);

        surnameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        departmentField.setItems(langs);
        avScoreColumn.setCellFactory(TextFieldTableCell.<Student, Double>forTableColumn(new DoubleStringConverter()));

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("gradebookNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Student, Student.Department>("departmet"));
        avScoreColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("averageScore"));
        initFilter();

        //   List<Student> s = collectiond.getAll();
        //  studentListTable.addAll(s);
        if (client == null) System.out.println("Client null");
        tableUsers.setItems(client.data);
        //tableUsers.getColumns().addAll(idColumn,nameColumn, surnameColumn,departmentColumn,avScoreColumn);


    }

    private void initFilter() {
        searchField.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchField.textProperty().get().isEmpty()) {
                    tableUsers.setItems(client.data);
                    return;
                }
                ObservableList<Student> tableItemas = FXCollections.observableArrayList();
                ObservableList<TableColumn<Student, ?>> cols = tableUsers.getColumns();
                for (int i = 0; i < client.data.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(client.data.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchField.textProperty().get().toLowerCase())) {
                            tableItemas.add(client.data.get(i));
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

            System.out.println(studentAdd);

            client.addData(studentAdd);

            idField.setText("");
            nameField.setText("");
            surnameField.setText("");
            avScoreField.setText("");

        }

    }

    @FXML
    protected void delStudent(ActionEvent event) throws RemoteException {
        client.data = tableUsers.getItems();
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        int st = tableUsers.getSelectionModel().getSelectedIndex();
        if (student != null) {
            client.deleteDate(student.getGradebookNumber());

            System.out.println(student);

        }
    }


    @FXML
    public void getAllBtn(ActionEvent actionEvent) throws RemoteException {
        try {
            client.Update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onEditChange(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) throws RemoteException {
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setSurname(studentStringCellEditEvent.getNewValue());
        client.updateStudentSurname(student.getGradebookNumber(), student.getSurname());
    }

    public void onEditChangeAvSc(TableColumn.CellEditEvent<Student, Double> studentDoubleCellEditEvent) throws RemoteException {
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setAverageScore(studentDoubleCellEditEvent.getNewValue());
        // collectiond.updateStudentAv(student.getGradebookNumber(), student.getAverageScore());
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

    @Override
    public void stop() {
        client.disconnetc(client);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

//


            }


