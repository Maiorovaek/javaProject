package sample.client;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
        surnameColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        departmentField.setItems(langs);
        avScoreColumn.setCellFactory(TextFieldTableCell.<Student, Double>forTableColumn(new DoubleStringConverter()));

        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("gradebookNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Student, Student.Department>("departmet"));
        avScoreColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("averageScore"));
        initFilter();
        if (client == null) System.out.println("Client null");
        tableUsers.setItems(client.data);

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
        if (student != null) {
            client.deleteDate(student.getGradebookNumber());


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


    public boolean showStudentEditDialog(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(ClientFX.class.getClassLoader().getResource("sample/EditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @FXML
    public void editStudent(ActionEvent actionEvent) {
        Student selectedStudent = tableUsers.getSelectionModel().getSelectedItem();

        long idSt = selectedStudent.getGradebookNumber();

        if (selectedStudent != null) {
            boolean okClicked = showStudentEditDialog(selectedStudent);
            if (okClicked) {
                showStudentDetails(selectedStudent, idSt);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
    }


    private void showStudentDetails(Student student, long id) {

        if (student != null) {
            client.editStudentC(id, student);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error fields");
            alert.showAndWait();
        }
    }
}