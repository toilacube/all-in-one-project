package com.example.crud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "oauth_refresh_tokens", schema = "ecommerce3")
public class OauthRefreshToken {
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @Column(name = "access_token_id", nullable = false, length = 100)
    private String accessTokenId;

    @Column(name = "revoked", nullable = false)
    private Boolean revoked = false;

    @Column(name = "expires_at")
    private Instant expiresAt;

}