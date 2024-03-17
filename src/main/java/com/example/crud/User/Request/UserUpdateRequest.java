package com.example.crud.User.Request;

import lombok.Builder;
import lombok.Data;

@Builder
public record UserUpdateRequest(String name, String email, String phoneNumber, String birthday, String gender, Integer height, Integer weight) {}
