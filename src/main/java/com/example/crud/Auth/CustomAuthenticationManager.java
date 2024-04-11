package com.example.crud.Auth;

import com.example.crud.Entity.User;
import com.example.crud.User.CustomUserDetails;
import com.example.crud.User.CustomUserDetailsService;
import com.example.crud.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String email = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with email : " + email));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("1001");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
