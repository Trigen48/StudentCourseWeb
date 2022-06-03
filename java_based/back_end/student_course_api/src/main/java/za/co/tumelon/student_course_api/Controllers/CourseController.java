package za.co.tumelon.student_course_api.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import za.co.tumelon.student_course_api.Data.CourseRepository;
import za.co.tumelon.student_course_api.Model.CourseModel;
import za.co.tumelon.student_course_api.Model.ResponseModel;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/course")
    public Iterable<CourseModel> getCourse() {
        return courseRepository.findAll();
    }

    @GetMapping("/course/{id}")
    public Optional<CourseModel> getCourse(@PathVariable String id) {
        return courseRepository.findById(id);
    }

    @PostMapping("/course")
    public ResponseModel addCourse(@RequestBody CourseModel course) {
        Optional<CourseModel> _course = courseRepository.findById(course.getCourseCode());
        if (_course.isPresent()) {
            return new ResponseModel("Course Code Already Exists");
        }

        try {
            courseRepository.save(course);
        } catch (Exception e) {
            return new ResponseModel("Could not add new course");
        }

        return new ResponseModel(1, "Course Added");
    }

    @PutMapping("/course/{id}")
    public ResponseModel updateCourse(@RequestBody CourseModel course, @PathVariable String id) {
        Optional<CourseModel> _course = courseRepository.findById(id);
        if (!_course.isPresent()) {
            return new ResponseModel("Course does not exist");
        }

        try {
            courseRepository.save(course);
        } catch (Exception e) {
            return new ResponseModel("Could not update course");
        }

        return new ResponseModel(1, "Course Updated");
    }

    @DeleteMapping("/course/{id}")
    public ResponseModel deleteCourse(@PathVariable String id) {
        Optional<CourseModel> _course = courseRepository.findById(id);
        if (!_course.isPresent()) {
            return new ResponseModel("Course does not exist");
        }

        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseModel("Could not delete course");
        }

        return new ResponseModel(1, "Course Deleted");
    }
}
