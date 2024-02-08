package com.example.crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "personal_access_tokens", schema = "ecommerce3")
public class PersonalAccessToken {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tokenable_type", nullable = false)
    private String tokenableType;

    @Column(name = "tokenable_id", nullable = false)
    private Long tokenableId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "token", nullable = false, length = 64)
    private String token;

    @Lob
    @Column(name = "abilities")
    private String abilities;

    @Column(name = "last_used_at")
    private Instant lastUsedAt;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}