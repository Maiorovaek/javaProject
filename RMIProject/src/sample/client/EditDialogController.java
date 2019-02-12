package sample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Student;

public class EditDialogController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameField;


    @FXML
    private TextField departmentField;

    @FXML
    private TextField graFrield;

    @FXML
    private TextField avScoreField;


    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;


    @FXML
    private void initialize() {
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setStudent(Student student) {
        this.student = student;
        graFrield.setText(String.valueOf(student.getGradebookNumber()));
        nameTextField.setText(student.getName());
        lastNameField.setText(student.getSurname());
        departmentField.setText(String.valueOf(student.getDepartmet()));
        avScoreField.setText(String.valueOf(student.getAverageScore()));
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            student.setName(nameTextField.getText());
            student.setSurname(lastNameField.getText());
            student.setGradebookNumber(Long.parseLong(graFrield.getText()));
            student.setAverageScore(Double.parseDouble(avScoreField.getText()));
            student.setDepartmet(Student.Department.valueOf(departmentField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameTextField.getText() == null || nameTextField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }


}
