package za.co.tumelon.student_course_api.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import za.co.tumelon.student_course_api.Data.CourseRepository;
import za.co.tumelon.student_course_api.Data.StudentCourseRepository;
import za.co.tumelon.student_course_api.Data.StudentRepository;
import za.co.tumelon.student_course_api.Model.ResponseModel;
import za.co.tumelon.student_course_api.Utilities.CsvImportUtil;

@RestController
public class UploadController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseModel> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {

            List<String> fileNames = new ArrayList<>();

            CsvImportUtil csvRead = new CsvImportUtil();
            for (MultipartFile file : files) {
                csvRead.readInput(file);
            }

            csvRead.processEntries(studentRepository, courseRepository, studentCourseRepository);
            csvRead.clear();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseModel("Files Uploaded" + fileNames));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseModel("Could not upload files"));
        }
    }

}
