package za.co.tumelon.student_course_api.Data;

import org.springframework.data.repository.CrudRepository;

import za.co.tumelon.student_course_api.Model.StudentCourseID;
import za.co.tumelon.student_course_api.Model.StudentCourseModel;

public interface StudentCourseRepository extends CrudRepository<StudentCourseModel, StudentCourseID> {

    Iterable<StudentCourseModel> findByStudentNumber(int studentNumber);

    Iterable<StudentCourseModel> findByCourseCode(String CourseCode);
}
