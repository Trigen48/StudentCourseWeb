package za.co.tumelon.student_course_api.Model;

import java.io.Serializable;

public class StudentCourseID implements Serializable {

    private int studentNumber;

    private String courseCode;

    public StudentCourseID() {
        studentNumber = 0;
        courseCode = "";
    }

    public StudentCourseID(int studentNumber, String courseCode) {
        setStudentNumber(studentNumber);
        setCourseCode(courseCode);
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

}
