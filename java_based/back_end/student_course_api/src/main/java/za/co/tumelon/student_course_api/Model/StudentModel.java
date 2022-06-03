package za.co.tumelon.student_course_api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "student")
@Table(name = "student")
public class StudentModel {

    @Id
    @Column(name = "StudentNumber")
    private int studentNumber;

    @Column(name = "Firstname")
    private String firstname;

    @Column(name = "Surname")
    private String surname;

    public StudentModel() {
        studentNumber = 0;
        firstname = "";
        surname = "";

    }

    public StudentModel(int studentNumber, String firstname, String surname) {
        setStudentNumber(studentNumber);
        setFirstname(firstname);
        setSurname(surname);
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
