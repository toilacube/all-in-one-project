package com.example.crud.Service;

import com.example.crud.Entity.Role;
import com.example.crud.Entity.User;
import com.example.crud.User.Response.UserInfoResponse;
import com.example.crud.User.UserRepository;
import com.example.crud.User.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    private User user = User.builder()
            .email("test@gmail.com")
            .build();

    @BeforeEach
    public void setup() {

        SecurityContextHolder.setContext(securityContext);
    }
    @Test
    public void UserService_GetUser() {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@gmail.com");

        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));

        // Call the method and verify the result
        User result = userService.getUser();

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void UserService_GetUserInfo() {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@gmail.com");

        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));

        // Call the method and verify the result
        UserInfoResponse userInfoResponse = userService.getUserInfo();

        Assertions.assertThat(userInfoResponse).isNotNull();
    }


}

