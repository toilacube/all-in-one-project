package com.example.crud.User;

import com.example.crud.Entity.User;
import com.example.crud.User.Request.UserUpdateRequest;
import com.example.crud.User.Response.UserInfoResponse;
import org.springframework.http.ResponseEntity;


public interface IUserService {
    UserInfoResponse getUserInfo();

    Iterable <UserInfoResponse> getAllUsers();

    ResponseEntity<String> updateUserInfo(UserUpdateRequest userUpdateRequest);

    User getUser();




}
