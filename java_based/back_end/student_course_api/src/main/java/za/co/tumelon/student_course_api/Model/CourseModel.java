package za.co.tumelon.student_course_api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "course")
@Table(name = "course")
public class CourseModel {

    @Id
    @Column(name = "CourseCode")
    private String courseCode;
    @Column(name = "CourseDescription")
    private String courseDescription;

    public CourseModel() {
        courseCode = "";
        courseDescription = "";
    }

    public CourseModel(String courseCode, String courseDescription) {
        setCourseCode(courseCode);
        setCourseDescription(courseDescription);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

}
