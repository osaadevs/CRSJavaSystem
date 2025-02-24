package controller;

import java.util.ArrayList;

import dto.CourseDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.CourseRegistrationService;

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
    private ArrayList<String> completedCourses;

    public CourseController() {
        courseRegistrationService = new CourseRegistrationService();
        completedCourses = new ArrayList<>();
    }
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        String query = txtCourseTitle.getText();
        if (query == null || query.trim().isEmpty()) {
            lblMessage.setText("Please enter a course title to search");
            return;
        }
        try {
            ArrayList<CourseDto> courses = courseRegistrationService.searchCourses(query);
            CourseListView.getItems().clear();
            if(courses.isEmpty()){
                lblMessage.setText("No courses found");
            }else{
                CourseListView.getItems().addAll(courses);
                lblMessage.setText(courses.size() + " courses found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblMessage.setText("Error occured while searching for courses");
        }
    }

}
