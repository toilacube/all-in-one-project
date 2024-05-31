package com.example.crud.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private Integer id;
    private String email;

}
