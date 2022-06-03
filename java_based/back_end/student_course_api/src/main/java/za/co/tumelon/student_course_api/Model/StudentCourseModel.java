package za.co.tumelon.student_course_api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "student_course")
@Table(name = "student_course")
@IdClass(StudentCourseID.class)
public class StudentCourseModel {

    @Id
    @Column(name = "StudentNumber")
    private int studentNumber;

    @Id
    @Column(name = "CourseCode")
    private String courseCode;

    @Column(name = "Grade")
    private String grade;

    @ManyToOne
    @JoinColumn(name = "CourseCode", insertable = false, updatable = false)
    private CourseModel course;

    public StudentCourseModel() {
        studentNumber = 0;
        courseCode = "";
        grade = "F";
    }

    public StudentCourseModel(int studentNumber, String courseCode, String grade) {
        setStudentNumber(studentNumber);
        setCourseCode(courseCode);
        setGrade(grade);
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public CourseModel getCourse() {
        return course;
    }
}
