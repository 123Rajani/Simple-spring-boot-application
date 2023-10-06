package com.example.Studentdatamanagement.controller;

import com.example.Studentdatamanagement.Response.StudentResponse;
import com.example.Studentdatamanagement.entity.Student;
import com.example.Studentdatamanagement.payload.APIResponse;
import com.example.Studentdatamanagement.service.ReportService;
import com.example.Studentdatamanagement.service.StudentService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;
    @Autowired
    private ReportService reportService;

    @PostMapping("/AddStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        try{
            return new ResponseEntity<Student>(service.saveStudent(student),HttpStatus.CREATED) ;
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/AddStudents")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students){
        try{
            return new ResponseEntity<List<Student>>(service.saveStudents(students), HttpStatus.CREATED) ;
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>>  findAllStudents(){
        try{
            return ResponseEntity.ok(service.getStudents()) ;
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

//    @GetMapping("/getStudentById/{id}")
//    public Student findStudentById(@PathVariable int id ){
//        return service.getStudentById(id);
//    }

    @GetMapping("/students/{id}")
    private ResponseEntity<StudentResponse> getStudentDetails(@PathVariable("id") int id){
        StudentResponse student = service.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
//        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        try {
            Student updatedStudent = service.UpdateStudent(student);
            if (updatedStudent != null) {
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public ResponseEntity<APIResponse> deleteStudent(@PathVariable int id) {
        try {
            service.deleteStudent(id);
            APIResponse apiResponse = APIResponse.builder()
                    .massage("STUDENT DELETED SUCCESSFULLY!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            APIResponse apiResponse = APIResponse.builder()
                    .massage("An error occurred: " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .success(false)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }

}
