package controller;

import java.util.ArrayList;

import dto.CourseDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.CourseRegistrationService;
import util.SessionManager;

public class CourseController {

    @FXML
    private ListView<CourseDto> CourseListView;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSearch;

    @FXML
    private Label lblCourseTitle;

    @FXML
    private Label lblCourseTitle1;

    @FXML
    private Label lblHeading;

    @FXML
    private Label lblMessage;

    @FXML
    private TextField txtCourseTitle;

    private CourseRegistrationService courseRegistrationService;

    public CourseController() {
        courseRegistrationService = new CourseRegistrationService();

    }

    @FXML
    private void initialize() {
        btnRegister.setDisable(true);
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

        CourseDto selectedCourse = CourseListView.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) {
            lblMessage.setText("Please select a course to register");
            return;
        }
        try {
           
            int studentId = SessionManager.getStudentId(); 
            ArrayList<String> completedCourses = new ArrayList<>();

            
            String registrationResult = courseRegistrationService.registerForCourse(selectedCourse, studentId,
                    completedCourses);
            lblMessage.setText(registrationResult);

        } catch (Exception e) {
            e.printStackTrace();
            lblMessage.setText("Error occurred while registering for the course");
        }
    }

    @FXML
    void listViewMouseClick(MouseEvent event) {

        CourseDto selectedCourse = CourseListView.getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            lblMessage.setText("Selected Course: " + selectedCourse.getCourseTitle());
            btnRegister.setDisable(false);
        } else {
            btnRegister.setDisable(true);
        }
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {

        String query = txtCourseTitle.getText().trim();
        if (query.isEmpty()) {
            lblMessage.setText("Please enter a course title to search");
            return;
        }
        try {
            ArrayList<CourseDto> courses = courseRegistrationService.searchCourses(query);
            CourseListView.getItems().clear();
            if (courses.isEmpty()) {
                lblMessage.setText("No courses found");
            } else {
                CourseListView.getItems().addAll(courses);
                lblMessage.setText(courses.size() + " courses found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblMessage.setText("Error occured while searching for courses");
        }
    }

}
