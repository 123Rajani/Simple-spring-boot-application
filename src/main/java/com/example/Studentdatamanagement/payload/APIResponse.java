package com.example.Studentdatamanagement.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class APIResponse {
    private String massage;
    private HttpStatus status;
    private boolean success;
}
