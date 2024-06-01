package com.example.crud.Service;

import com.example.crud.Auth.*;
import com.example.crud.Auth.AuthenToken.EmailPasswordAuthenticationToken;
import com.example.crud.Entity.Role;
import com.example.crud.Entity.User;
import com.example.crud.User.UserRepository;
import com.example.crud.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void testRegister_UserAlreadyExists() {
        // Given
        RegisterRequest request = RegisterRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();
        when(userRepository.existsUserByEmail(request.getEmail())).thenReturn(true);

        // When
        ResponseEntity<RegisterResponse> response = authService.register(request);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testRegister_Success() {
        // Given
        RegisterRequest request = RegisterRequest.builder()
                .email("test@example.com")
                .password("password")
                .role(Role.USER)
                .build();
        when(userRepository.existsUserByEmail(request.getEmail())).thenReturn(false);

       User user = User.builder()
                .id(1)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        doReturn(user).when(userRepository).save(any(User.class));
        doReturn(user).when(userService).getUserByEmail(request.getEmail());

        // When
        ResponseEntity<RegisterResponse> response = authService.register(request);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getEmail(), response.getBody().getEmail());
    }


    @Test
    public void testLoginByEmailPassword_Success() {
        // Given
        AuthRequest request = new AuthRequest("test@example.com", "password");
        Authentication authentication = new EmailPasswordAuthenticationToken(request.getEmail(), request.getPassword());
        when(authenticationManager.authenticate(any(EmailPasswordAuthenticationToken.class))).thenReturn(authentication);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(jwtService.generateToken(authentication)).thenReturn("token");

        // When
        AuthResponse response = authService.loginByEmailPassword(request);

        // Then
        assertNotNull(response);
        assertEquals("token", response.getAccessToken());
    }
}
