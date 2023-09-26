package com.example.Studentdatamanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="STUDENT_TBL")
public class Student {

    @Id
    @GeneratedValue
    int id;
    String name;
    int age;
    String address;
    String contactNo;


}
