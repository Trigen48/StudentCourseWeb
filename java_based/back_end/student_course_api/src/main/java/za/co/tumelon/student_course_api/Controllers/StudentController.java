package za.co.tumelon.student_course_api.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import za.co.tumelon.student_course_api.Data.StudentCourseRepository;
import za.co.tumelon.student_course_api.Data.StudentRepository;
import za.co.tumelon.student_course_api.Model.ResponseModel;
import za.co.tumelon.student_course_api.Model.StudentCourseID;
import za.co.tumelon.student_course_api.Model.StudentCourseModel;
import za.co.tumelon.student_course_api.Model.StudentModel;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @GetMapping("/")
    public Iterable<StudentModel> getStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<StudentModel> getStudent(@PathVariable int id) {
        return studentRepository.findById(id);
    }

    @PostMapping("/")
    public ResponseModel addStudent(@RequestBody StudentModel student) {
        Optional<StudentModel> _student = studentRepository.findById(student.getStudentNumber());
        if (_student.isPresent()) {
            return new ResponseModel("Student Number Already Exists");
        }

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            return new ResponseModel("Could not add new student");
        }

        return new ResponseModel(1, "Student Added");
    }

    @PutMapping("/{id}")
    public ResponseModel updateStudent(@RequestBody StudentModel student, @PathVariable int id) {
        Optional<StudentModel> _student = studentRepository.findById(id);
        if (!_student.isPresent()) {
            return new ResponseModel("Student does not exist");
        }

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            return new ResponseModel("Could not update student");
        }

        return new ResponseModel(1, "Student Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseModel deleteStudent(@PathVariable int id) {
        Optional<StudentModel> _student = studentRepository.findById(id);
        if (!_student.isPresent()) {
            return new ResponseModel("Student does not exist");
        }

        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseModel("Could not delete student");
        }

        return new ResponseModel(1, "Student Deleted");
    }

    @GetMapping("/course/{id}")
    public Iterable<StudentCourseModel> getStudentCourse(@PathVariable int id) {

        return studentCourseRepository.findByStudentNumber(id);
    }

    @GetMapping("/course/{id}/{courseid}")
    public Optional<StudentCourseModel> getStudentCourse(@PathVariable int id, @PathVariable String courseid) {

        return studentCourseRepository.findById(new StudentCourseID(id, courseid));
    }

    @PostMapping("/course/{id}")
    public ResponseModel addStudentCourse(@RequestBody StudentCourseModel studentCourse) {
        Optional<StudentCourseModel> _studentCourse = studentCourseRepository
                .findById(new StudentCourseID(studentCourse.getStudentNumber(), studentCourse.getCourseCode()));
        if (_studentCourse.isPresent()) {
            return new ResponseModel("Student is already registered to course");
        }

        try {
            studentCourseRepository.save(studentCourse);
        } catch (Exception e) {
            return new ResponseModel("Could not add course to student");
        }

        return new ResponseModel(1, "Student Added");
    }

    @PutMapping("/course/{id}/{courseid}")
    public ResponseModel updateStudentGrade(@PathVariable int id, @PathVariable String courseid,
            @RequestBody String grade) {

        Optional<StudentCourseModel> _studentCourse = studentCourseRepository
                .findById(new StudentCourseID(id, courseid));

        if (!_studentCourse.isPresent()) {
            return new ResponseModel("Student Course not found");
        }

        StudentCourseModel studentCourse = _studentCourse.get();
        studentCourse.setGrade(grade);
        try {
            studentCourseRepository.save(studentCourse);
        } catch (Exception e) {
            return new ResponseModel("Could not update student course");
        }

        return new ResponseModel(1, "Student Course Saved");
    }

    @DeleteMapping("/course/{id}/{courseid}")
    public ResponseModel deleteStudentCourse(@PathVariable int id, @PathVariable String courseid) {

        Optional<StudentCourseModel> _studentCourse = studentCourseRepository
                .findById(new StudentCourseID(id, courseid));

        if (!_studentCourse.isPresent()) {
            return new ResponseModel("Student Course not found");
        }

        StudentCourseModel studentCourse = _studentCourse.get();
        try {
            studentCourseRepository.delete(studentCourse);
        } catch (Exception e) {
            return new ResponseModel("Could not delete student course");
        }

        return new ResponseModel(1, "Student Course Deleted");
    }

}
