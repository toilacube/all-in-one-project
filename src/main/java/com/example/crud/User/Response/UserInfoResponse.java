package com.example.crud.User.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
public record UserInfoResponse(Integer id, String googleId, String role, String name, String email, String phoneNumber, String gender, String weight, String height, String birth) {}
