package com.example.Studentdatamanagement.service;

import com.example.Studentdatamanagement.Repo.StudentRepository;
import com.example.Studentdatamanagement.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private WebClient webClient;

    public Student saveStudent(Student student){
      return repository.save(student);
    }
    public List<Student> saveStudents(List<Student> students){
        return repository.saveAll(students);
    }

    public List<Student> getStudents(){
        return repository.findAll();
    }

    public Student getStudentById(Long id){
        return repository.findById(id).orElse(null);
    }




    public Student getStudentByName(String name){
        return repository.findByName(name);
    }

    public String deleteStudent(Long id){
        repository.deleteById(id);
        return "Student removed: " +id;
    }

    public Student UpdateStudent(Student student){
         Student exsistingStudent = repository.findById(student.getId()).orElse(null);
         exsistingStudent.setName(student.getName());
         exsistingStudent.setAge(student.getAge());
         exsistingStudent.setContactNo(student.getContactNo());
         exsistingStudent.setAddress(student.getAddress());
         return repository.save(exsistingStudent);
    }



}
