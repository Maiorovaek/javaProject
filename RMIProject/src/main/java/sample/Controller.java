package sample;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.NumberStringConverter;
import rmi.XMLParser;
import rmi.model.Student;

import java.util.List;

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
    private TextField idField;
    @FXML

    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private ComboBox<String> departmentField;
    @FXML
    private TextField avScoreField;
    ObservableList<String> langs;
    Label lbl;
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

        lbl = new Label();
        //  departmentField.setOnAction(event -> lbl.setText(departmentField.getValue()));
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Long>("gradebookNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Student, Student.Department>("departmet"));
        avScoreColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("averageScore"));


        // заполняем таблицу данными
        tableUsers.setItems(studentList);


    }


    public void addRow() {

        // get current position
        TablePosition pos = tableUsers.getFocusModel().getFocusedCell();

        // clear current selection
        tableUsers.getSelectionModel().clearSelection();

        // create new record and add it to the model
        Student data = new Student();
        tableUsers.getItems().add(data);

        // get last row
        int row = tableUsers.getItems().size() - 1;
        tableUsers.getSelectionModel().select(row, pos.getTableColumn());

        // scroll to new row
        tableUsers.scrollTo(data);

    }

    private void initData() {
        XMLParser xml = new XMLParser();
        List<Student> s = xml.readListStudent();
        studentList.addAll(s);

    }


    @FXML
    protected void addStudent(ActionEvent event) {
        studentList = tableUsers.getItems();

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
     //   long gradebookNumberDel = tableUsers.getSelectionModel().getSelectedItem().getGradebookNumber();

        xmlParser.removeStudent(st.getGradebookNumber());

    }


    public void onEditChange(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setSurname(studentStringCellEditEvent.getNewValue());

    }

    public void onEditChangeAvSc(TableColumn.CellEditEvent<Student, Double> studentDoubleCellEditEvent) {
        avScoreColumn.textProperty().bindBidirectional(avscDouble, new NumberStringConverter());
        Student student = tableUsers.getSelectionModel().getSelectedItem();
        student.setAverageScore(studentDoubleCellEditEvent.getNewValue());
        double s = avscDouble.get();
        long idGr = tableUsers.getSelectionModel().getSelectedItem().getGradebookNumber();

        xmlParser.updateStudent(idGr,s);
    }
}

