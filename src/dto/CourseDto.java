package dto;

import javafx.beans.property.*;

public class CourseDto {
    private final IntegerProperty courseId;
    private final StringProperty courseCode;
    private final StringProperty courseTitle;
    private final IntegerProperty creditHours;
    private final StringProperty department;
    private final IntegerProperty maxEnrollment;
    private final IntegerProperty facId;

   
    public CourseDto() {
        this.courseId = new SimpleIntegerProperty();
        this.courseCode = new SimpleStringProperty();
        this.courseTitle = new SimpleStringProperty();
        this.creditHours = new SimpleIntegerProperty();
        this.department = new SimpleStringProperty();
        this.maxEnrollment = new SimpleIntegerProperty();
        this.facId = new SimpleIntegerProperty();
    }

    public CourseDto(int courseId, String courseCode, String courseTitle, int creditHours, String department,
                     int maxEnrollment, int facId) {
        this.courseId = new SimpleIntegerProperty(courseId);
        this.courseCode = new SimpleStringProperty(courseCode);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.creditHours = new SimpleIntegerProperty(creditHours);
        this.department = new SimpleStringProperty(department);
        this.maxEnrollment = new SimpleIntegerProperty(maxEnrollment);
        this.facId = new SimpleIntegerProperty(facId);
    }


    public int getCourseId() {
        return courseId.get();
    }

    public void setCourseId(int courseId) {
        this.courseId.set(courseId);
    }

    public IntegerProperty courseIdProperty() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode.get();
    }

    public void setCourseCode(String courseCode) {
        this.courseCode.set(courseCode);
    }

    public StringProperty courseCodeProperty() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle.get();
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle.set(courseTitle);
    }

    public StringProperty courseTitleProperty() {
        return courseTitle;
    }

    public int getCreditHours() {
        return creditHours.get();
    }

    public void setCreditHours(int creditHours) {
        this.creditHours.set(creditHours);
    }

    public IntegerProperty creditHoursProperty() {
        return creditHours;
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public int getMaxEnrollment() {
        return maxEnrollment.get();
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment.set(maxEnrollment);
    }

    public IntegerProperty maxEnrollmentProperty() {
        return maxEnrollment;
    }

    public int getFacId() {
        return facId.get();
    }

    public void setFacId(int facId) {
        this.facId.set(facId);
    }

    public IntegerProperty facIdProperty() {
        return facId;
    }

    @Override
    public String toString() {
        return courseCode.get() + " - " + courseTitle.get() + " (" + creditHours.get() + " Credits)";
    }
}
