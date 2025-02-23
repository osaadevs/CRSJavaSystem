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
        CourseDto selectedCourse = CourseListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            String result = courseRegistrationService.registerForCourse(selectedCourse, "1234", completedCourses);
            lblCourseTitle.setText(result);

        }else {
            lblCourseTitle.setText("Please select a course to register");
        }
    }

}
