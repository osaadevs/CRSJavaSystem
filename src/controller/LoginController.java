package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
        // Verify user
        if (authenticateUser(username, password)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Invalid username or password");
        }
    }
    private boolean authenticateUser(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

}
