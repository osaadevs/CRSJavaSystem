package controller;

import dao.EnrollmentDAO;
import dto.EnrollmentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentManagementController {

    @FXML
    private TextField txtCourseTitle, txtCourseCode, txtCreditHours, txtDepartment, txtMaxEnroll, txtFacId;

    @FXML
    private TextField txtStudentId, txtEnrollmentDate;

    @FXML
    private Button btnAddCourse, btnUpdateCourse, btnDeleteCourse, btnBackButton;

    @FXML
    private TableView<EnrollmentDto> tblEnrollments;

    @FXML
    private TableColumn<EnrollmentDto, Integer> colEnrollmentId, colStudentId, colCourseId;

    @FXML
    private TableColumn<EnrollmentDto, String> colCourseTitle, colEnrollmentDate, colDepartment, colFacultyId;

    private EnrollmentDAO enrollmentDAO;
    private ObservableList<EnrollmentDto> enrollmentList;

    public EnrollmentManagementController() {
        enrollmentDAO = new EnrollmentDAO();
    }

    @FXML
    public void initialize() {
        colEnrollmentId.setCellValueFactory(cellData -> cellData.getValue().enrollmentIdProperty().asObject());
        colStudentId.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty().asObject());
        colCourseId.setCellValueFactory(cellData -> cellData.getValue().courseIdProperty().asObject());
        colCourseTitle.setCellValueFactory(cellData -> cellData.getValue().courseTitleProperty());
        colEnrollmentDate.setCellValueFactory(cellData -> cellData.getValue().enrollmentDateProperty());
        colDepartment.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        colFacultyId.setCellValueFactory(cellData -> cellData.getValue().facultyIdProperty());

        loadEnrollmentData();

        tblEnrollments.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFields(newValue);
            }
        });
    }

    private void loadEnrollmentData() {
        try {
            List<EnrollmentDto> enrollments = enrollmentDAO.getAllEnrollments();
            enrollmentList = FXCollections.observableArrayList(enrollments);
            tblEnrollments.setItems(enrollmentList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void populateFields(EnrollmentDto enrollment) {
        txtStudentId.setText(String.valueOf(enrollment.getStudentId()));
        txtCourseCode.setText(String.valueOf(enrollment.getCourseId()));
        txtCourseTitle.setText(enrollment.getCourseTitle());
        txtEnrollmentDate.setText(enrollment.getEnrollmentDate());
        txtDepartment.setText(enrollment.getDepartment());
        txtFacId.setText(enrollment.getFacultyId());
    }

    @FXML
    private void handleEnrollStudent(ActionEvent event) {
        try {
            int studentId = Integer.parseInt(txtStudentId.getText());
            int courseId = Integer.parseInt(txtCourseCode.getText());
            String enrollmentDate = txtEnrollmentDate.getText();

            enrollmentDAO.enrollStudent(studentId, courseId, enrollmentDate);
            loadEnrollmentData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateEnrollment(ActionEvent event) {
        EnrollmentDto selectedEnrollment = tblEnrollments.getSelectionModel().getSelectedItem();
        if (selectedEnrollment != null) {
            try {
                int studentId = Integer.parseInt(txtStudentId.getText());
                int courseId = Integer.parseInt(txtCourseCode.getText());
                String enrollmentDate = txtEnrollmentDate.getText();

                enrollmentDAO.updateEnrollment(selectedEnrollment.getEnrollmentId(), studentId, courseId,
                        enrollmentDate);
                loadEnrollmentData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleDropStudent(ActionEvent event) {
        EnrollmentDto selectedEnrollment = tblEnrollments.getSelectionModel().getSelectedItem();
        if (selectedEnrollment != null) {
            try {
                enrollmentDAO.dropStudent(selectedEnrollment.getEnrollmentId());
                loadEnrollmentData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/admin_dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnBackButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showAlert("Error", "Failed to return to Admin Dashboard", Alert.AlertType.ERROR);
        }
    }
    
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
