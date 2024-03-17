package com.example.crud.User;

import com.example.crud.Address.UserAddressRepository;
import com.example.crud.User.Request.UserUpdateRequest;
import com.example.crud.User.Response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;

    @GetMapping("")
    public UserInfoResponse getUser() {
        return userService.getUserInfo();
    }

    @PutMapping("/updateInfo")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUserInfo(userUpdateRequest);
    }


    @GetMapping("/getAllUsers")
    public Iterable<UserInfoResponse> getAllUsers() {
        return userService.getAllUsers();
    }


}

