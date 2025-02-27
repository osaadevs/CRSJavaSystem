package dao;

import db.DBConnection;
import dto.StudentDto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Connection connection;

    public StudentDAO() throws ClassNotFoundException, SQLException {
        connection = DBConnection.getInstance().getConnection();
    }

    // Create a new student (Insert into Users and Students tables)
    public void createStudent(StudentDto student) throws SQLException {
        String userInsertQuery = "INSERT INTO Users (first_name, last_name, email, username, password, role) VALUES (?, ?, ?, ?, ?, 'student')";
        String studentInsertQuery = "INSERT INTO Students (user_id, program_of_study, year_of_study, contact_number) VALUES (?, ?, ?, ?)";

        try (PreparedStatement userStatement = connection.prepareStatement(userInsertQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement studentStatement = connection.prepareStatement(studentInsertQuery)) {

            // Insert into Users table
            userStatement.setString(1, student.getFirstName());
            userStatement.setString(2, student.getLastName());
            userStatement.setString(3, student.getEmail());
            userStatement.setString(4, student.getUsername());

            userStatement.executeUpdate();

            // Get the generated user_id
            ResultSet userRs = userStatement.getGeneratedKeys();
            int userId = 0;
            if (userRs.next()) {
                userId = userRs.getInt(1);
            }

            // Insert into Students table
            studentStatement.setInt(1, userId); // Use the generated user_id
            studentStatement.setString(2, student.getProgramOfStudy());
            studentStatement.setInt(3, student.getYearOfStudy());
            studentStatement.setString(4, student.getContactNumber());
            studentStatement.executeUpdate();
        }
    }

    // Get all students (Join Users and Students tables)
    public List<StudentDto> getAllStudents() throws SQLException {
        List<StudentDto> students = new ArrayList<>();
        String query = "SELECT s.student_id, u.user_id, u.first_name, u.last_name, u.email, u.username, " +
                       "s.program_of_study, s.year_of_study, s.contact_number " +
                       "FROM Students s " +
                       "JOIN Users u ON s.user_id = u.user_id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                if (username == null) {
                    username = "Unknown"; // Set a default value if username is null
                }
                String programOfStudy = rs.getString("program_of_study");
                int yearOfStudy = rs.getInt("year_of_study");
                String contactNumber = rs.getString("contact_number");

                StudentDto student = new StudentDto(studentId, userId, firstName, lastName, email, programOfStudy, yearOfStudy, contactNumber);
                student.setUsername(username);
                students.add(student);
            }
        }
        return students;
    }

    public int getStudentId(int userId) {
        String query = "SELECT student_id FROM Students WHERE user_id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("student_id");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Get student by ID
    public StudentDto getStudentById(int studentId) throws SQLException {
        String query = "SELECT s.student_id, u.user_id, u.first_name, u.last_name, u.email, u.username, " +
                       "s.program_of_study, s.year_of_study, s.contact_number " +
                       "FROM Students s " +
                       "JOIN Users u ON s.user_id = u.user_id WHERE s.student_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String programOfStudy = rs.getString("program_of_study");
                int yearOfStudy = rs.getInt("year_of_study");
                String contactNumber = rs.getString("contact_number");

                return new StudentDto(studentId, userId, firstName, lastName, email, programOfStudy, yearOfStudy, contactNumber);
            }
        }
        return null;
    }

    // Update student details (Updates both Users and Students tables)
    public void updateStudent(StudentDto student) throws SQLException {
        String userUpdateQuery = "UPDATE Users SET first_name = ?, last_name = ?, email = ?, username = ? WHERE user_id = ?";
        String studentUpdateQuery = "UPDATE Students SET program_of_study = ?, year_of_study = ?, contact_number = ? WHERE student_id = ?";

        try (PreparedStatement userStatement = connection.prepareStatement(userUpdateQuery);
             PreparedStatement studentStatement = connection.prepareStatement(studentUpdateQuery)) {

            // Update Users table
            userStatement.setString(1, student.getFirstName());
            userStatement.setString(2, student.getLastName());
            userStatement.setString(3, student.getEmail());
            userStatement.setString(4, student.getUsername());
            userStatement.setInt(5, student.getUserId());
            userStatement.executeUpdate();

            // Update Students table
            studentStatement.setString(1, student.getProgramOfStudy());
            studentStatement.setInt(2, student.getYearOfStudy());
            studentStatement.setString(3, student.getContactNumber());
            studentStatement.setInt(4, student.getStudentId());
            studentStatement.executeUpdate();
        }
    }

    // Delete a student (Deletes from both Students and Users tables)
    public void deleteStudent(int studentId) throws SQLException {
        String studentDeleteQuery = "DELETE FROM Students WHERE student_id = ?";
        String userDeleteQuery = "DELETE FROM Users WHERE user_id = (SELECT user_id FROM Students WHERE student_id = ?)";

        try (PreparedStatement studentStatement = connection.prepareStatement(studentDeleteQuery);
             PreparedStatement userStatement = connection.prepareStatement(userDeleteQuery)) {

            // Delete from Students table
            studentStatement.setInt(1, studentId);
            studentStatement.executeUpdate();

            // Delete from Users table
            userStatement.setInt(1, studentId);
            userStatement.executeUpdate();
        }
    }

    // Get Password by Username (For Login Handling)
    public String getPasswordByUsername(String username) throws SQLException {
        String query = "SELECT password FROM Users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        }
        return null;
    }
}
