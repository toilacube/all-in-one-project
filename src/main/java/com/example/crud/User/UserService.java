package com.example.crud.User;

import com.example.crud.Address.UserAddressRepository;
import com.example.crud.Entity.User;
import com.example.crud.User.Request.UserUpdateRequest;
import com.example.crud.User.Response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    @Override
    public User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElse(null);
    }
    @Override
    public UserInfoResponse getUserInfo() {
        User user = this.getUser();

        if (user != null) {
            return UserInfoResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
        } else {

            return null;
        }
    }

    @Override
    public ResponseEntity<String> updateUserInfo(UserUpdateRequest userUpdateRequest) {
        User user = this.getUser();
        if(user != null) {
            user.setName(userUpdateRequest.name());
            user.setPhoneNumber(userUpdateRequest.phoneNumber());
            user.setBirth(userUpdateRequest.birthday());
            user.setGender(userUpdateRequest.gender());
            user.setWeight(userUpdateRequest.weight());
            user.setHeight(userUpdateRequest.height());
            userRepository.save(user);
            return ResponseEntity.ok("success");
        }
        return null;
    }


    public Iterable <UserInfoResponse> getAllUsers() {
        List <User> users = userRepository.findAll();
        List <UserInfoResponse> userInfoResponses = new java.util.ArrayList<>();
        for (User user : users) {
            UserInfoResponse userInfoResponse = UserInfoResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
            userInfoResponses.add(userInfoResponse);
        }
        return userInfoResponses;
    }


}
