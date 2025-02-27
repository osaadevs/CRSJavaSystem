package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class CoursePrerequisiteDAO {
    public boolean completedPrerequisites(int studentId, int courseId) {
        String query = "SELECT * FROM Course_Prerequisites cp " +
                       "JOIN Enrollment e ON e.course_id = cp.prerequisite_course_id " +
                       "WHERE e.student_id = ? AND cp.course_id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
       
    }
}
