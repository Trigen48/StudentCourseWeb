package za.co.tumelon.student_course_api.Data;

import org.springframework.data.repository.CrudRepository;

import za.co.tumelon.student_course_api.Model.CourseModel;

public interface CourseRepository extends CrudRepository<CourseModel, String> {

}
