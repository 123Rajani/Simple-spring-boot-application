package com.example.Studentdatamanagement.controller;

import com.example.Studentdatamanagement.entity.Student;
import com.example.Studentdatamanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/AddStudent")
    public Student addStudent(@RequestBody Student student){
        return service.saveStudent(student);
    }

    @PostMapping("/AddStudents")
    public List<Student> addStudents(@RequestBody List<Student> students){
        return service.saveStudents(students);
    }

    @GetMapping("/students")
    public  List<Student> findAllStudents(){
        return service.getStudents();
    }

//    @GetMapping("/getStudentById/{id}")
//    public Student findStudentById(@PathVariable int id ){
//        return service.getStudentById(id);
//    }
    @GetMapping("/students/{id}")
    private ResponseEntity<Student> getStudentDetails(@PathVariable("id") Long id){
        Student student = service.getStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }


    @GetMapping("/getStudentByName/{name}")
    public Student findStudentByName(@PathVariable  String name){
        return service.getStudentByName(name);
    }
    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student){
        return service.UpdateStudent(student);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public String deleteStudent(@PathVariable Long id){
         return service.deleteStudent(id);
    }


}
