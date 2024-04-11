package com.example.crud.Auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity <AuthenResponse> register(  @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }


    @PostMapping("/login")
    public ResponseEntity <AuthenResponse> authenticate(  @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok( authService.authenticate(authRequest));

    }
}

