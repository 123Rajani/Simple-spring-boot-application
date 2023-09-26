package com.example.Studentdatamanagement.Repo;

import com.example.Studentdatamanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByName(String name);
}
