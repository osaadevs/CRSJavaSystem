package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.SessionManager;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblMessage;

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        String role = authenticateUser(username, password);

        if (role != null) {
            System.out.println("Login successful");
            try {
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                FXMLLoader loader;

                // Navigate based on the user role
                if (role.equals("student")) {
                    loader = new FXMLLoader(getClass().getResource("/view/course.fxml"));
                } else if (role.equals("admin")) {
                    loader = new FXMLLoader(getClass().getResource("/view/admin/admin_dashboard.fxml"));
                } else if (role.equals("faculty")) {
                    loader = new FXMLLoader(getClass().getResource("/view/faculty_dash.fxml"));
                } else {
                    lblMessage.setText("Invalid role");
                    return;
                }

                // Load the scene and show it
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblMessage.setText("Invalid username or password");  // Show error message
        }
    }

    private String authenticateUser(String username, String password) {

        String query = "SELECT user_id, role FROM Users WHERE username = ? AND password = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set username and password parameters
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rst = statement.executeQuery();

            if (rst.next()) {
                // If user exists, retrieve role and user_id
                int userId = rst.getInt("user_id");
                String role = rst.getString("role");
                System.out.println("Login Successful! User ID: " + userId + " Role: " + role);

                // Set session variables
                SessionManager.setStudentId(userId);
                SessionManager.setUserRole(role);

                return role;
            } else {
                // No matching user found
                System.out.println("Login Failed! Incorrect username or password.");
                lblMessage.setText("Incorrect username or password");  // Update the error message
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            lblMessage.setText("Database error. Please try again later.");
        }

        return null;
    }
}
