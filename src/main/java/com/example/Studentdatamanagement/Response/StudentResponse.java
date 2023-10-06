package com.example.Studentdatamanagement.Response;

import lombok.Data;

@Data
public class StudentResponse {
    private int id;
    private String name;
    private int age;
    private String address;
    private String contactNo;

    public void setAddressResponse(AddressResponse addressResponse) {
    }
}
