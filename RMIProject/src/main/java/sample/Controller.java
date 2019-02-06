package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;
import rmi.XMLParser;
import rmi.model.Student;

import java.util.List;
import java.util.function.Predicate;

public class Controller {

    XMLParser xmlParser = new XMLParser();
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
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

    //  nameSearchField, surnameSearchField, AvScFieldSearch,idSearchField,
    @FXML
    private TextField searchField;


    @FXML
    private ComboBox<String> departmentField;

    ObservableList<String> langs;
    @FXML
    private Label lbl;


    private final DoubleProperty avscDouble = new SimpleDoubleProperty();

//    @FXML
//    public TextField avScDoubleField;

    // инициализируем форму данными
    @FXML
    private void initialize() {

        initData();
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
                    }
                    else if (student.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if(high.toLowerCase().contains(lowerCaseFilter)){
                        return true;
                   }
                    return false;
                });
            });

            SortedList<Student> sortedList = new SortedList<>(filteredData);
            sortedList.comparatorProperty().bind(tableUsers.comparatorProperty());
            tableUsers.setItems(sortedList);

        });


        // заполняем таблицу данными
        tableUsers.setItems(studentList);
    }


    private void initData() {
        XMLParser xml = new XMLParser();
        List<Student> s = xml.readListStudent();
        studentList.addAll(s);

    }


    @FXML
    protected void addStudent(ActionEvent event) {
        studentList = tableUsers.getItems();

        if (validation()) {
            long idFieldLong = Long.parseLong(idField.getText());
            Student.Department dDepartment = Student.Department.valueOf(departmentField.getValue());
            double avScoreDouble = Double.parseDouble(avScoreField.getText());

            Student studentAdd = new Student(idFieldLong,
                    nameField.getText(),
                    surnameField.getText(),
                    dDepartment,
                    avScoreDouble);
            studentList.add(studentAdd);

            xmlParser.addStudent(studentAdd);
        } else {
            lbl.setText("INVALID");
        }
        idField.setText("");
        nameField.setText("");
        surnameField.setText("");

        avScoreField.setText("");
    }


    @FXML
    protected void delStudent(ActionEvent event) {

        Student st = tableUsers.getSelectionModel().getSelectedItem();
        int row = tableUsers.getSelectionModel().getSelectedIndex();
        tableUsers.getItems().remove(row);
        xmlParser.removeStudent(st.getGradebookNumber());

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
        if (idField.getText() == (null)) {
            System.out.println("invalid id");
            // idField.setStyle();
            // nameField.setStyleName("status-error");

            result = false;
        }
        if (nameField.getText().equals(null)) {

            String regex = "[A-Za-z\\s]+";
            System.out.println("invalid name");
            return nameField.getText().matches(regex);
        }
        if (surnameField.getText().isEmpty()) {

            result = false;
        }
        if (departmentColumn.getText().isEmpty()/*||*//*!isNumber(numberPageTextBox.getText())*/) {

            result = false;
        }
        if (avScoreField.getText().isEmpty() /*||  !isNumber(yearTextBox.getText()) || Integer.valueOf(yearTextBox.getText()) > 2019 */) {
            result = false;
        }
        if (result == false) {
            lbl.setText(alert);
        }

        return result;

    }




}