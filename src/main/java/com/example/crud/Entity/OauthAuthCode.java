package com.example.crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "oauth_auth_codes", schema = "ecommerce3")
public class OauthAuthCode {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Lob
    @Column(name = "scopes")
    private String scopes;

    @Column(name = "revoked", nullable = false)
    private Boolean revoked = false;

    @Column(name = "expires_at")
    private Instant expiresAt;

}