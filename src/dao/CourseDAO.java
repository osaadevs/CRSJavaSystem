package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import dto.CourseDto;

public class CourseDAO {
    //get all courses
    public ArrayList<CourseDto> getAllCourses() throws ClassNotFoundException, SQLException {
        ArrayList<CourseDto> courses = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT course_id, course_code, course_title, credit_hours, department, max_enrollment, faculty_id FROM Courses";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            int courseId = rs.getInt("course_id");
            String courseCode = rs.getString("course_code");
            String courseTitle = rs.getString("course_title");
            int creditHours = rs.getInt("credit_hours");
            String department = rs.getString("department");
            int maxEnrollment = rs.getInt("max_enrollment");
            int facId = rs.getInt("faculty_id");

            CourseDto course = new CourseDto(courseId, courseCode, courseTitle, creditHours, department, maxEnrollment, facId);
            courses.add(course);
            }
            rs.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
        }
        return courses;
    }

    public ArrayList<CourseDto> searchCourses(String title) {
        ArrayList<CourseDto> courses = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT course_id, course_code, course_title, credit_hours, department, max_enrollment, faculty_id FROM Courses WHERE course_title LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + title + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseCode = rs.getString("course_code");
                String courseTitle = rs.getString("course_title");
                int creditHours = rs.getInt("credit_hours");
                String department = rs.getString("department");
                int maxEnrollment = rs.getInt("max_enrollment");
                int facId = rs.getInt("faculty_id");

                CourseDto course = new CourseDto(courseId, courseCode, courseTitle, creditHours, department, maxEnrollment, facId);
                courses.add(course);
            }
            
            
            rs.close();
            statement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return courses;
    }
    public void addCourse(String courseTitle, String courseCode, int creditHours, String department, int maxEnrollment, int facultyId) {
        String query = "INSERT INTO Courses (course_title, course_code, credit_hours, department, max_enrollment, faculty_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, courseTitle);
            statement.setString(2, courseCode);
            statement.setInt(3, creditHours);
            statement.setString(4, department);
            statement.setInt(5, maxEnrollment);
            statement.setInt(6, facultyId);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void updateCourse(int courseId, String courseTitle, String courseCode, int creditHours, String department, int maxEnrollment, int facultyId) {
        String query = "UPDATE Courses SET course_title = ?, course_code = ?, credit_hours = ?, department = ?, max_enrollment = ?, faculty_id = ? WHERE course_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, courseTitle);
            statement.setString(2, courseCode);
            statement.setInt(3, creditHours);
            statement.setString(4, department);
            statement.setInt(5, maxEnrollment);
            statement.setInt(6, facultyId);
            statement.setInt(7, courseId);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(int courseId) {
        String query = "DELETE FROM Courses WHERE course_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
}
