package com.example.crud.Auth;

import com.example.crud.Auth.AuthenToken.EmailPasswordAuthenticationToken;
import com.example.crud.Entity.Role;
import com.example.crud.Entity.User;
import com.example.crud.User.UserRepository;
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



@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) {

        if (userRepository.existsUserByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder().email(registerRequest.getEmail())
                .password(encoder.encode(registerRequest.getPassword()))
                .role(Role.user)
                .build();

        try {
            userRepository.save(user);

            Authentication authentication = authenticationManager.authenticate(
                    new EmailPasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtService.generateToken(authentication);
            return ResponseEntity.ok(AuthResponse.builder().accessToken(jwtToken).build());

        } catch (Exception e) {
            throw new RuntimeException("Failed to register and authenticate user", e);
        }
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
