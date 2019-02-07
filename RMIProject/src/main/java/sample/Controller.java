package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import rmi.XMLParser;
import rmi.model.Student;

import java.util.List;
import java.util.function.Predicate;

public class Controller {

    XMLParser xmlParser = new XMLParser();
    public ObservableList<Student> studentList = FXCollections.observableArrayList();
    @FXML
    private TableView<Student> tableUsers;

    @FXML
    private TableColumn<Student, Long> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> surnameColumn;

    @FXML
    private TableColumn<Student, Student.Department> departmentColumn;

    @FXML
    private TableColumn<Student, Double> avScoreColumn;

    @FXML
    private TextField idField, nameField, surnameField, avScoreField;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> departmentField;

    ObservableList<String> langs;
    @FXML
    private Label lbl;

    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

    }


    private void initData() {
        XMLParser xml = new XMLParser();
        List<Student> s = xml.readListStudent();
        studentList.addAll(s);
        langs = FXCollections.observableArrayList("AppliedMathematics", "InformationalRadiosystems", "Chemistry", "ForeignLanguages");
        tableUsers.setEditable(true);

        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        departmentField.setItems(langs);
        avScoreColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        lbl = new Label();


        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("gradebookNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Student, Student.Department>("departmet"));
        avScoreColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("averageScore"));


        FilteredList<Student> filteredData = new FilteredList<Student>(studentList, e -> true);
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
        tableUsers.setItems(studentList);

    }


    @FXML
    protected void addStudent(ActionEvent event) {
        studentList = tableUsers.getItems();
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
            studentList.add(studentAdd);

            xmlParser.addStudent(studentAdd);
            idField.setText("");
            nameField.setText("");
            surnameField.setText("");

            avScoreField.setText("");
        }

    }


    @FXML
    protected void delStudent(ActionEvent event) {
        studentList = tableUsers.getItems();
        Student st = tableUsers.getSelectionModel().getSelectedItem();
        if (st != null) {
            int row = tableUsers.getSelectionModel().getSelectedIndex();

            tableUsers.getItems().remove(row);
            xmlParser.removeStudent(st.getGradebookNumber());
        }


    }


    public void onEditChange(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setSurname(studentStringCellEditEvent.getNewValue());
        xmlParser.updateStudent(student.getGradebookNumber(), student.getSurname());
    }

    public void onEditChangeAvSc(TableColumn.CellEditEvent<Student, Double> studentDoubleCellEditEvent) {
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setAverageScore(studentDoubleCellEditEvent.getNewValue());
        xmlParser.updateStudentAv(student.getGradebookNumber(), student.getAverageScore());
    }


    private boolean validation() {

        boolean result = true;
        String alert = "Please fill fields. \n";
        if (idField.getText().equals(null) || idField.getText().isEmpty()) {

            idField.setStyle("-fx-border-color:red;");
            alert += "id is null";

            result = false;
        }
        if (nameField.getText().equals(null) || nameField.getText().isEmpty()) {

            String regex = "[A-Za-z\\s]+";
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