package com.example.Studentdatamanagement.controller;

import com.example.Studentdatamanagement.entity.Student;
import com.example.Studentdatamanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/Student")
    public Student addStudent(@RequestBody Student student){
        return service.saveStudent(student);
    }

    @PostMapping("/Students")
    public List<Student> addStudents(@RequestBody List<Student> students){
        return service.saveStudents(students);
    }

    @GetMapping("/getAllStudents")
    public  List<Student> findAllStudents(){
        return service.getStudents();
    }

    @GetMapping("/getStudentById/{id}")
    public Student findStudentById(@PathVariable int id ){
        return service.getStudentById(id);
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
    public String deleteStudent(@PathVariable int id){
         return service.deleteStudent(id);
    }


}
