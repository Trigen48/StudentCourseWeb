package za.co.tumelon.student_course_api.Utilities;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import za.co.tumelon.student_course_api.Data.CourseRepository;
import za.co.tumelon.student_course_api.Data.StudentCourseRepository;
import za.co.tumelon.student_course_api.Data.StudentRepository;
import za.co.tumelon.student_course_api.Model.CourseModel;
import za.co.tumelon.student_course_api.Model.StudentCourseID;
import za.co.tumelon.student_course_api.Model.StudentCourseModel;
import za.co.tumelon.student_course_api.Model.StudentModel;

public class CsvImportUtil {

    private Map<Integer, StudentModel> students;
    private Map<String, CourseModel> courses;
    private Map<String, StudentCourseModel> studentCourses;

    private ArrayList<String> errorList;

    public CsvImportUtil() {
        students = new HashMap<>();
        courses = new HashMap<>();
        studentCourses = new HashMap<>();
        errorList = new ArrayList<>();
    }

    public void readInput(MultipartFile file) {
        String line = "";
        String[] col;
        try {

            BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(file.getBytes()), "UTF-8"));
            fileReader.readLine(); // Read Header

            while ((line = fileReader.readLine()) != null) {
                col = line.split(";");

                if (col.length >= 5) {
                    readEntry(col);
                }

            }

            fileReader.close();
        } catch (IOException e) {
            errorList.add("Error reading from file: " + file.getName());
        }

    }

    private void readEntry(String[] entry) {

        try {
            int studentNumber = Integer.parseInt(entry[0]);
            String firstname = entry[1];
            String surname = entry[2];
            String courseCode = entry[3];
            String courseDescription = entry[4];
            String grade = entry[5];
            String studentCourseKey = studentNumber + "_" + courseCode;

            if (!students.containsKey(studentNumber)) {
                students.put(studentNumber, new StudentModel(studentNumber, firstname, surname));
            }

            if (!courses.containsKey(courseCode)) {
                courses.put(courseCode, new CourseModel(courseCode, courseDescription));
            }

            if (!studentCourses.containsKey(studentCourseKey)) {
                studentCourses.put(studentCourseKey, new StudentCourseModel(studentNumber, courseCode, grade));
            }
        } catch (NumberFormatException e) {

        }
    }

    public void processEntries(StudentRepository studentRepository, CourseRepository courseRepository,
            StudentCourseRepository studentCourseRepository) {

        ;
        for (int studentNumber : students.keySet()) {
            StudentModel student = students.get(studentNumber);

            if (!studentRepository.findById(studentNumber).isPresent()) {
                studentRepository.save(student);
            }
        }

        for (String courseCode : courses.keySet()) {
            CourseModel course = courses.get(courseCode);

            if (!courseRepository.findById(courseCode).isPresent()) {

                courseRepository.save(course);
            }
        }

        for (String studentCourseId : studentCourses.keySet()) {
            StudentCourseModel studentCourse = studentCourses.get(studentCourseId);

            if (!studentCourseRepository
                    .findById(new StudentCourseID(studentCourse.getStudentNumber(), studentCourse.getCourseCode()))
                    .isPresent()) {

                try {
                    studentCourseRepository.save(studentCourse);
                } catch (Exception ex) {
                    errorList.add("Error adding Student Course: " + studentCourse.toString());
                }
            }
        }

    }

    public void clear() {
        studentCourses.clear();
        students.clear();
        courses.clear();

    }
}
