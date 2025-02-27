package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.SessionManager;

public class AdminDashboardController {

    @FXML
    private Label lblAdminDashboard;
    @FXML
    private Label lblManageCourses;
    @FXML
    private Label lblManageStudents;
    @FXML
    private Label lblViewReport;
    @FXML
    private Label lblViewReport1;
    @FXML
    private Label lblAdminName;

    @FXML
    private Button btnManageCourses;
    @FXML
    private Button btnManageStudents;
    @FXML
    private Button btnEnrollmentRebtport;
    @FXML
    private Button btnEnrollment;

    @FXML
    public void initialize() {
        // Get the Admin name from the session
        String adminName = SessionManager.getAdminName(); 
        if (adminName != null) {
            lblAdminName.setText("Admin Name: " + adminName);
        } else {
            lblAdminName.setText("Admin Name: Not Available");
        }
    }

    // Event handlers for the buttons
    @FXML
    private void handleManageCourses() {
        loadScene("/view/admin/course_management.fxml");
    }

    @FXML
    private void handleManageStudents() {
        loadScene("/view/admin/student_management.fxml");
    }

    @FXML
    private void handleEnrollment() {
        loadScene("/view/admin/enrollment_management.fxml");
    }

    @FXML
    private void handleReporting() {
        loadScene("/view/admin/reporting.fxml");
    }
    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header
        alert.setContentText(message); // The message you pass
        alert.showAndWait(); // Show the alert and wait for user response
    }
    // Load the scene
    private void loadScene(String fxmlPath) {
    try {
        System.out.println("Loading: " + fxmlPath);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        if (loader.getLocation() == null) {
            throw new RuntimeException("FXML file not found at: " + fxmlPath);
        }
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) btnManageStudents.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Error", "Failed to load view: " + fxmlPath, Alert.AlertType.ERROR);
    }
}
}
