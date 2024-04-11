package com.example.crud.Auth;

import com.example.crud.Entity.Role;
import com.example.crud.User.CustomUserDetails;
import com.example.crud.Entity.User;
import com.example.crud.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CustomAuthenticationManager customAuthenticationManager;

    public ResponseEntity<AuthenResponse> register(RegisterRequest registerRequest) {

        if (userRepository.existsUserByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        User user = User.builder().email(registerRequest.getEmail())
                .password(encoder.encode(registerRequest.getPassword()))
                .role(Role.user)
                .build();

        userRepository.save(user);
        CustomUserDetails userDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(AuthenResponse.builder().token(jwtToken).build());
    }


    public AuthenResponse authenticate(AuthRequest authRequest)  {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Authentication authentication = customAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

//        User user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
//        if(user.getPassword() == null || !encoder.matches(authRequest.getPassword(), user.getPassword())) {
//            throw new BadCredentialsException("Invalid email or password");
//        }
//
//        CustomUserDetails userDetails = new CustomUserDetails(user);

        var jwtToken = jwtService.generateToken(userDetails);
        return AuthenResponse.builder().token(jwtToken).build();
    }
}
