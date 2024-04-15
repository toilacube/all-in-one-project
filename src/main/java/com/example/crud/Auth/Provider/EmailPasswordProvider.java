package com.example.crud.Auth.Provider;

import com.example.crud.Auth.AuthenToken.EmailPasswordAuthenticationToken;
import com.example.crud.Entity.User;
import com.example.crud.User.CustomUserDetails;
import com.example.crud.User.CustomUserDetailsService;
import com.example.crud.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailPasswordProvider implements AuthenticationProvider {
    private final PasswordEncoder encoder;
    private final CustomUserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Input: Authentication object is passed from AuthService with princial is email and credentials is password


        String email = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        System.out.println("Email: " + email);

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("1000");
        }
        if (!userDetails.isEnabled()) {

            throw new DisabledException("1001");
        }

        return new EmailPasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    

  //   Check if this provider is appropriate for current authentication
    @Override
    public boolean supports(Class<?> authentication) {

        return EmailPasswordAuthenticationToken.class.equals(authentication);
    }


}
