package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.EnrollmentDto;

public class EnrollmentDAO {
    public boolean registerStudent(int studentId, int courseId)throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Enrollment (student_id, course_id) VALUES (?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            int rowsAffected=statement.executeUpdate();
            return rowsAffected>0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isCourseFull(int courseId) throws SQLException, ClassNotFoundException {
        
        String query = "SELECT max_enrollment FROM Courses WHERE course_id = ?";
        
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, courseId);
            ResultSet rst = statement.executeQuery();
           
            if (rst.next()) {
               int maxEnrollment = rst.getInt("max_enrollment");
               String countQuery = "SELECT COUNT(*) as count FROM Enrollment WHERE course_id = ?";
               try(PreparedStatement countStatement = connection.prepareStatement(countQuery)){
                   countStatement.setInt(1, courseId);
                   ResultSet countRst = countStatement.executeQuery();
                   if(countRst.next()){
                       int count = countRst.getInt("count");
                       return count >= maxEnrollment;
                   }
                }
            }
            
            return false;
        
        }
    }
     public void enrollStudent(int studentId, int courseId, String enrollmentDate) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Enrollment (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setString(3, enrollmentDate);
            statement.executeUpdate();
        }
    }

    public void updateEnrollment(int enrollmentId, int studentId, int courseId, String enrollmentDate) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Enrollment SET student_id = ?, course_id = ?, enrollment_date = ? WHERE enrollment_id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setString(3, enrollmentDate);
            statement.setInt(4, enrollmentId);
            statement.executeUpdate();}
        }

    public void dropStudent(int enrollmentId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM Enrollment WHERE enrollment_id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, enrollmentId);
            statement.executeUpdate();
        }
    }

    public List<EnrollmentDto> getAllEnrollments() throws SQLException, ClassNotFoundException {
        List<EnrollmentDto> enrollments = new ArrayList<>();
        String query = "SELECT * FROM Enrollment";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                enrollments.add(new EnrollmentDto(rs.getInt("enrollment_id"), rs.getInt("student_id"), rs.getInt("course_id"), "", rs.getString("enrollment_date"), "", ""));
            }
        }
        return enrollments;
    }
}
