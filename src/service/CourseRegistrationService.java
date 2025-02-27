package service;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CourseDAO;
import dao.CoursePrerequisiteDAO;
import dao.EnrollmentDAO;
import dto.CourseDto;

public class CourseRegistrationService {
    private CourseDAO courseDAO;
    private EnrollmentDAO enrollmentDAO;
    private CoursePrerequisiteDAO prerequisiteDAO;

    public CourseRegistrationService() {
       courseDAO = new CourseDAO();
       enrollmentDAO = new EnrollmentDAO();
       prerequisiteDAO= new CoursePrerequisiteDAO();
    }

    public ArrayList<CourseDto> getAllCourses() throws ClassNotFoundException, SQLException {
        return courseDAO.getAllCourses();
    }

    public ArrayList<CourseDto> searchCourses(String title) {
        return courseDAO.searchCourses(title);
    }

    public String registerForCourse(CourseDto course, int studentId, ArrayList<String> completedCourses) {
        try {
            
            boolean alreadyEnrolled = enrollmentDAO.isStudentEnrolled(studentId, course.getCourseId());
            if (alreadyEnrolled) {
                return "You are already enrolled in this course.";
            }
    
            
            boolean isFull = enrollmentDAO.isCourseFull(course.getCourseId());
            if (isFull) {
                return "Course is already full. Registration failed.";
            }
    
            
            boolean hasPrerequisites = prerequisiteDAO.completedPrerequisites(studentId, course.getCourseId());
            if (!hasPrerequisites) {
                return "You need to complete prerequisites before registering for this course.";
            }
    
            
            boolean success = enrollmentDAO.registerStudent(studentId, course.getCourseId());
            if (success) {
                return "Successfully registered for " + course.getCourseTitle();
            } else {
                return "Failed to register for " + course.getCourseTitle();
            }
    
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Error occurred during Registration";
        }
    }
    
}
