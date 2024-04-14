package com.example.crud.Auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok( authService.loginByEmailPassword(authRequest));

    }

    @GetMapping("/cube")
    public ResponseEntity <String> authenticateGoogle(@AuthenticationPrincipal OidcUser user){
        System.out.println("Google login success");
        return ResponseEntity.ok( user.getFullName());
    }
}

