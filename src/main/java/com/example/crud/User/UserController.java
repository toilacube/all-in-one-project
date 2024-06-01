package com.example.crud.User;

import com.example.crud.Address.UserAddressRepository;
import com.example.crud.User.Request.UserUpdateRequest;
import com.example.crud.User.Response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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

    @GetMapping("/getAuthorities")
    public Collection<? extends GrantedAuthority> getCurrentUserAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities();
    }


    @GetMapping("/getAllUsers")
    public Iterable<UserInfoResponse> getAllUsers() {
        return userService.getAllUsers();
    }


}

