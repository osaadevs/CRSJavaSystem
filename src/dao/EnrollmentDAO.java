package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import db.DBConnection;

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
}
