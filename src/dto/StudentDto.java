package dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentDto {

    private int studentId;
    private int userId;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private StringProperty programOfStudy;
    private int yearOfStudy;
    private StringProperty contactNumber;
    private StringProperty username;

    // Constructor
    public StudentDto(int studentId, int userId, String firstName, String lastName, String email, String programOfStudy,
                      int yearOfStudy, String contactNumber) {
        this.studentId = studentId;
        this.userId = userId;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.programOfStudy = new SimpleStringProperty(programOfStudy);
        this.yearOfStudy = yearOfStudy;
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.username = new SimpleStringProperty("");

    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getProgramOfStudy() {
        return programOfStudy.get();
    }

    public void setProgramOfStudy(String programOfStudy) {
        this.programOfStudy.set(programOfStudy);
    }

    public StringProperty programOfStudyProperty() {
        return programOfStudy;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public StringProperty yearOfStudy() {
        return programOfStudy;
    }

    public StringProperty yearOfStudyProperty() {
        return programOfStudy;
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        if (username != null) {
            this.username.set(username);
        } else {
            this.username.set("Unknown"); // Prevent null pointer if username is null
        }
    }

    public StringProperty usernameProperty() {
        return username;
    }

    
}
