package dto;

import javafx.beans.property.*;

public class EnrollmentDto {
    private final IntegerProperty enrollmentId;
    private final IntegerProperty studentId;
    private final IntegerProperty courseId;
    private final StringProperty courseTitle;
    private final StringProperty enrollmentDate;
    private final StringProperty department;
    private final StringProperty facultyId;

    public EnrollmentDto(int enrollmentId, int studentId, int courseId, String courseTitle, String enrollmentDate, String department, String facultyId) {
        this.enrollmentId = new SimpleIntegerProperty(enrollmentId);
        this.studentId = new SimpleIntegerProperty(studentId);
        this.courseId = new SimpleIntegerProperty(courseId);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.enrollmentDate = new SimpleStringProperty(enrollmentDate);
        this.department = new SimpleStringProperty(department);
        this.facultyId = new SimpleStringProperty(facultyId);
    }

    public int getEnrollmentId() { return enrollmentId.get(); }
    public IntegerProperty enrollmentIdProperty() { return enrollmentId; }

    public int getStudentId() { return studentId.get(); }
    public IntegerProperty studentIdProperty() { return studentId; }

    public int getCourseId() { return courseId.get(); }
    public IntegerProperty courseIdProperty() { return courseId; }

    public String getCourseTitle() { return courseTitle.get(); }
    public StringProperty courseTitleProperty() { return courseTitle; }

    public String getEnrollmentDate() { return enrollmentDate.get(); }
    public StringProperty enrollmentDateProperty() { return enrollmentDate; }

    public String getDepartment() { return department.get(); }
    public StringProperty departmentProperty() { return department; }

    public String getFacultyId() { return facultyId.get(); }
    public StringProperty facultyIdProperty() { return facultyId; }
}
