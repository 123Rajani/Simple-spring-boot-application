package com.example.Studentdatamanagement.Response;

import lombok.Data;

@Data
public class StudentResponse {
    int id;
    String name;
    int age;
    String address;
    String contactNo;
    private AddressResponse addressResponse;


}
