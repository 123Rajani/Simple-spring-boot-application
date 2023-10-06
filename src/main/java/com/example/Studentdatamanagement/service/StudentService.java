package com.example.Studentdatamanagement.service;

import com.example.Studentdatamanagement.Repo.StudentRepository;
import com.example.Studentdatamanagement.Response.AddressResponse;
import com.example.Studentdatamanagement.Response.StudentResponse;
import com.example.Studentdatamanagement.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private WebClient webClient;

    public StudentResponse getStudentById(int id) {

        Optional<Student> student = repository.findById(id);
       StudentResponse studentResponse = mapper.map(student, StudentResponse.class);

        // Using WebClient
        AddressResponse addressResponse = webClient.get().uri("/students/" + id).retrieve().bodyToMono(AddressResponse.class).block();
        studentResponse.setAddressResponse(addressResponse);
        return studentResponse;
    }


    public Student saveStudent(Student student){
      return repository.save(student);
    }
    public List<Student> saveStudents(List<Student> students){
        return repository.saveAll(students);
    }

    public List<Student> getStudents(){
        return repository.findAll();
    }

//    public Student getStudentById(Long id){
//        return repository.findById(id).orElse(null);
//    }

    public void deleteStudent(int id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    public Student UpdateStudent(Student student){
         Student exsistingStudent = repository.findById(student.getId()).orElse(null);
         exsistingStudent.setName(student.getName());
         exsistingStudent.setAge(student.getAge());
         exsistingStudent.setContactNo(student.getContactNo());
         exsistingStudent.setAddress(student.getAddress());
         return repository.save(exsistingStudent);
    }


    public class StudentNotFoundException extends RuntimeException {
        public StudentNotFoundException(String message) {
            super(message);
        }
    }


}
