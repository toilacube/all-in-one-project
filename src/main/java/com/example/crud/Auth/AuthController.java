package com.example.crud.Auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity <AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }


    @PostMapping("/login")
    public ResponseEntity <AuthResponse> authenticate(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok( authService.loginByEmailPassword(authRequest));

    }

}

