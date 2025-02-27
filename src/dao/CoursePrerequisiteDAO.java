package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class CoursePrerequisiteDAO {
    public boolean completedPrerequisites(int studentId, int courseId) {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            
            String checkPrerequisitesQuery = "SELECT COUNT(*) FROM Course_Prerequisites WHERE course_id = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkPrerequisitesQuery)) {
                checkStatement.setInt(1, courseId);
                ResultSet checkResult = checkStatement.executeQuery();
                if (checkResult.next() && checkResult.getInt(1) == 0) {
                    return true;
                }
            }

           
            String query = "SELECT COUNT(*) FROM Course_Prerequisites cp " +
                           "LEFT JOIN Enrollment e ON e.course_id = cp.prerequisite_course_id AND e.student_id = ? " +
                           "WHERE cp.course_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, studentId);
                statement.setInt(2, courseId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int completedPrerequisites = resultSet.getInt(1);
                    return completedPrerequisites > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
