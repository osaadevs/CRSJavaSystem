package controller;

import dao.CourseDAO;
import dto.CourseDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class CourseManagementController {

    @FXML
    private TextField txtCourseCode, txtCourseTitle, txtCreditHours, txtDepartment, txtMaxEnroll, txtFacId;

    @FXML
    private Button btnAddCourse, btnUpdateCourse, btnDeleteCourse, btnBackButton;

    @FXML
    private TableView<CourseDto> tblCourses;

    @FXML
    private TableColumn<CourseDto, String> colCourseCode, colCourseTitle, colDepartment;

    @FXML
    private TableColumn<CourseDto, Integer> colCreditHours, colMaxEnrollment, colFacId;

    private CourseDAO courseDAO;
    private ObservableList<CourseDto> courseList;

    public CourseManagementController() {
        courseDAO = new CourseDAO();
    }

    @FXML
    public void initialize() {
        // Initialize table columns
        colCourseCode.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty());
        colCourseTitle.setCellValueFactory(cellData -> cellData.getValue().courseTitleProperty());
        colCreditHours.setCellValueFactory(cellData -> cellData.getValue().creditHoursProperty().asObject());
        colDepartment.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        colMaxEnrollment.setCellValueFactory(cellData -> cellData.getValue().maxEnrollmentProperty().asObject());
        colFacId.setCellValueFactory(cellData -> cellData.getValue().facIdProperty().asObject());

        loadCourseData();

        // Load course data when a row is selected
        tblCourses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFields(newValue);  // ✅ Call method to populate text fields
            }
        });
    }

    private void loadCourseData() {
        try {
            List<CourseDto> courses = courseDAO.getAllCourses();
            courseList = FXCollections.observableArrayList(courses);
            tblCourses.setItems(courseList);
            tblCourses.refresh(); 
            System.out.println("Table updated with " + courses.size() + " courses.");

        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Failed to load course data", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleBtnAddCourse(ActionEvent event) {
        try {
            CourseDto newCourse = new CourseDto(
                    0,
                    txtCourseCode.getText(),
                    txtCourseTitle.getText(),
                    Integer.parseInt(txtCreditHours.getText()),
                    txtDepartment.getText(),
                    Integer.parseInt(txtMaxEnroll.getText()),
                    Integer.parseInt(txtFacId.getText())
            );
            courseDAO.addCourse(newCourse.getCourseTitle(), newCourse.getCourseCode(), newCourse.getCreditHours(), newCourse.getDepartment(), newCourse.getMaxEnrollment(), newCourse.getFacId());
            loadCourseData();
            tblCourses.refresh();
            showAlert("Success", "Course added successfully", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to add course", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleBtnUpdateCourse(ActionEvent event) {
        CourseDto selectedCourse = tblCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            try {
                selectedCourse.setCourseCode(txtCourseCode.getText());
                selectedCourse.setCourseTitle(txtCourseTitle.getText());
                selectedCourse.setCreditHours(Integer.parseInt(txtCreditHours.getText()));
                selectedCourse.setDepartment(txtDepartment.getText());
                selectedCourse.setMaxEnrollment(Integer.parseInt(txtMaxEnroll.getText()));
                selectedCourse.setFacId(Integer.parseInt(txtFacId.getText()));

                courseDAO.updateCourse(selectedCourse.getCourseId(), selectedCourse.getCourseTitle(), selectedCourse.getCourseCode(), selectedCourse.getCreditHours(), selectedCourse.getDepartment(), selectedCourse.getMaxEnrollment(), selectedCourse.getFacId());
                loadCourseData();
                showAlert("Success", "Course updated successfully", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                showAlert("Error", "Failed to update course", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void handleBtnDeleteCourse(ActionEvent event) {
        CourseDto selectedCourse = tblCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            try {
                courseDAO.deleteCourse(selectedCourse.getCourseId());
                loadCourseData();
                showAlert("Success", "Course deleted successfully", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                showAlert("Error", "Failed to delete course", Alert.AlertType.ERROR);
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

    private void populateFields(CourseDto course) {
        txtCourseCode.setText(course.getCourseCode());
        txtCourseTitle.setText(course.getCourseTitle());
        txtCreditHours.setText(String.valueOf(course.getCreditHours()));
        txtDepartment.setText(course.getDepartment());
        txtMaxEnroll.setText(String.valueOf(course.getMaxEnrollment()));
        txtFacId.setText(String.valueOf(course.getFacId()));
    }
}
