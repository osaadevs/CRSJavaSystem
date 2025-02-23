package dto;

public class CourseDto {
    private int courseId;
    private String courseCode;
    private String courseTitle;
    private int creditHours;
    private String department;
    private int maxEnrollment;
    private int facId;
    
    public CourseDto() {
    }
    
    public CourseDto(int courseId, String courseCode, String courseTitle, int creditHours, String department,
            int maxEnrollment, int facId) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.department = department;
        this.maxEnrollment = maxEnrollment;
        this.facId = facId;
    }

    public int getcourseId() {
        return courseId;
    }

    public void setcourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getcourseCode() {
        return courseCode;
    }

    public void setcourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public int getFacId() {
        return facId;
    }

    public void setFacId(int facId) {
        this.facId = facId;
    }

    @Override
    public String toString() {
        return "CourseDto [courseId=" + courseId + ", courseCode=" + courseCode + ", courseTitle=" + courseTitle
                + ", creditHours=" + creditHours + ", department=" + department + ", maxEnrollment=" + maxEnrollment
                + ", facId=" + facId + "]";
    } 

}

