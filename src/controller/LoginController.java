package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

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
        
        // Verify user
        if (authenticateUser(username, password)) {
            System.out.println("Login successful");
            try{
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                Scene courseScene = new Scene(FXMLLoader.load(getClass().getResource("/view/Course.fxml")));
                stage.setScene(courseScene);
                stage.show();
            }catch(Exception e){
                e.printStackTrace();
            }
        } else {
            lblMessage.setText("Invalid username or password");
        }
    }
    private boolean authenticateUser(String username, String password) {
        String query = "SELECT role FROM Users WHERE username = ? AND password = ?";

        try(Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2,password);

            ResultSet rst = statement.executeQuery();

            if(rst.next()){
                String role = rst.getString("role");
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
