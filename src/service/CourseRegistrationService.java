package service;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CourseDAO;
import dto.CourseDto;

public class CourseRegistrationService {
    private ArrayList<CourseDto> availableCourses;
    private CourseDAO courseDAO;

    public CourseRegistrationService() {
       courseDAO = new CourseDAO();
    }

    public ArrayList<CourseDto> getAllCourses() throws ClassNotFoundException, SQLException {
        return courseDAO.getAllCourses();
    }

    public ArrayList<CourseDto> searchCourses(String title) {
        return courseDAO.searchCourses(title);
    }

    public String registerForCourse (CourseDto course , String studentId, ArrayList<String> completedCourses){
        //test if the student has completed the prerequisites

        return "Successfully registered for " + course.getCourseTitle(); 
    }
}
