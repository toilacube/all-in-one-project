package com.example.crud.Auth;

import com.example.crud.Auth.AuthenToken.EmailPasswordAuthenticationToken;
import com.example.crud.Entity.Role;
import com.example.crud.Entity.User;
import com.example.crud.User.UserRepository;
import com.example.crud.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public ResponseEntity<RegisterResponse> register(RegisterRequest registerRequest) {

        if (userRepository.existsUserByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder().email(registerRequest.getEmail())
                .password(encoder.encode(registerRequest.getPassword()))
                .role(Role.user)
                .build();
        userRepository.save(user);

        User createdUser = userService.getUserByEmail(registerRequest.getEmail());

        return ResponseEntity.ok(RegisterResponse.builder().id(createdUser.getId()).email(createdUser.getEmail()).build());
    }


    public AuthResponse loginByEmailPassword(AuthRequest authRequest)  {
        Authentication authentication = authenticationManager.authenticate(
                new EmailPasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtService.generateToken(authentication);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .build();
    }


}
