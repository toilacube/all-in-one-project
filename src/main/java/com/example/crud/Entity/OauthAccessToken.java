package com.example.crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "oauth_access_tokens", schema = "ecommerce3")
public class OauthAccessToken {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "scopes")
    private String scopes;

    @Column(name = "revoked", nullable = false)
    private Boolean revoked = false;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "expires_at")
    private Instant expiresAt;

}