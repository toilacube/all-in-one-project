package com.example.crud.Auth;

import com.example.crud.Entity.User;
import com.example.crud.User.CustomUserDetails;
import com.example.crud.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class JwtService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final JwtEncoder accessTokenEncoder;
    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;


    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

//        String scope = authentication.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("roles", roles)
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String extractEmail(String token) {

        Jwt jwt = jwtDecoder.decode(token);

        Map<String, Object> claims = jwt.getClaims();

        return claims.get("sub").toString();
    }




    public boolean isTokenExpired(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        Instant expiration = jwt.getExpiresAt();
        Instant now = Instant.now();
        assert expiration != null;
        return expiration.isBefore(now);
    }

    public boolean isTokenValid(String token, CustomUserDetails userDetails) {
        String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
