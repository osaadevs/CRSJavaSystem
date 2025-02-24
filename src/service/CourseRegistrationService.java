package service;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dto.CourseDto;

public class CourseRegistrationService {
    private CourseDAO courseDAO;
    private EnrollmentDAO enrollmentDAO;

    public CourseRegistrationService() {
       courseDAO = new CourseDAO();
       enrollmentDAO = new EnrollmentDAO();
    }

    public ArrayList<CourseDto> getAllCourses() throws ClassNotFoundException, SQLException {
        return courseDAO.getAllCourses();
    }

    public ArrayList<CourseDto> searchCourses(String title) {
        return courseDAO.searchCourses(title);
    }

    public String registerForCourse (CourseDto course , int studentId, ArrayList<String> completedCourses){
        try{
            boolean success = enrollmentDAO.registerStudent(studentId,course.getcourseId());
            if(success){
                return "Successfully registered for " + course.getCourseTitle();
            }else{
                return "Failed to register for " + course.getCourseTitle();
            }
        
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return "Error occured during Registration";

        }
    }
}
