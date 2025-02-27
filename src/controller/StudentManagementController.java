package controller;

import dao.StudentDAO;
import dto.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class StudentManagementController {

    @FXML
    private Button btnAddStudent;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeleteStudent;

    @FXML
    private Button btnUpdateStudent;

    @FXML
    private TableView<StudentDto> tblStudents;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtYear;

    private StudentDAO studentDAO;
    private ObservableList<StudentDto> studentList;

    public StudentManagementController() throws ClassNotFoundException, SQLException {
        studentDAO = new StudentDAO();
    }

    
    @FXML
    public void initialize() {
        // Create columns for the TableView
        TableColumn<StudentDto, String> colFirstName = new TableColumn<>("First Name");
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<StudentDto, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<StudentDto, String> colUsername = new TableColumn<>("Username");
        colUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        TableColumn<StudentDto, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        TableColumn<StudentDto, String> colProgram = new TableColumn<>("Program");
        colProgram.setCellValueFactory(cellData -> cellData.getValue().programOfStudyProperty());

        TableColumn<StudentDto, String> colYear = new TableColumn<>("Year");
        colYear.setCellValueFactory(cellData -> cellData.getValue().yearOfStudyProperty());

        TableColumn<StudentDto, String> colContact = new TableColumn<>("Contact");
        colContact.setCellValueFactory(cellData -> cellData.getValue().contactNumberProperty());

        tblStudents.getColumns().addAll(colFirstName, colLastName, colUsername, colEmail, colProgram, colYear, colContact);

        loadStudentData();
    }

    // Load student data into the TableView
    private void loadStudentData() {
        try {
            List<StudentDto> students = studentDAO.getAllStudents();
            studentList = FXCollections.observableArrayList(students);
            tblStudents.setItems(studentList);
        } catch (SQLException  e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load student data", Alert.AlertType.ERROR);
        }
    }

    // Add a new student
    @FXML
    void handleBtnAddStudent(ActionEvent event) {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String programOfStudy = txtProgram.getText();
        String contact = txtContact.getText();
        int yearOfStudy = Integer.parseInt(txtYear.getText());

      
        StudentDto newStudent = new StudentDto(0, 0, firstName, lastName, email, programOfStudy, yearOfStudy, contact);
        newStudent.setUsername(username);

        try {
            studentDAO.createStudent(newStudent);
            showAlert("Success", "Student added successfully", Alert.AlertType.INFORMATION);
            loadStudentData(); 
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add student", Alert.AlertType.ERROR);
        }
    }

    // Update selected student
    @FXML
    void handleBtnUpdateStudent(ActionEvent event) {
        StudentDto selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String programOfStudy = txtProgram.getText();
            String contact = txtContact.getText();
            int yearOfStudy = Integer.parseInt(txtYear.getText());

            selectedStudent.setFirstName(firstName);
            selectedStudent.setLastName(lastName);
            selectedStudent.setUsername(username);
            selectedStudent.setEmail(email);
            selectedStudent.setProgramOfStudy(programOfStudy);
            selectedStudent.setYearOfStudy(yearOfStudy);
            selectedStudent.setContactNumber(contact);

            try {
                studentDAO.updateStudent(selectedStudent);
                showAlert("Success", "Student updated successfully", Alert.AlertType.INFORMATION);
                loadStudentData(); // Refresh the table
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to update student", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Warning", "Please select a student to update", Alert.AlertType.WARNING);
        }
    }

    // Delete selected student
    @FXML
    void handleBtnDeleteStudent(ActionEvent event) {
        StudentDto selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            try {
                studentDAO.deleteStudent(selectedStudent.getStudentId());
                showAlert("Success", "Student deleted successfully", Alert.AlertType.INFORMATION);
                loadStudentData(); // Refresh the table
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to delete student", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Warning", "Please select a student to delete", Alert.AlertType.WARNING);
        }
    }

    
    @FXML
    void handleBackButtonAction(ActionEvent event) {
        loadScene("/view/admin/admin_dashboard.fxml");
    }

    private void loadScene(String fxmlPath) {
        try {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Show an alert with a specified message and type
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
