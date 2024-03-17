package com.example.crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_review", schema = "coolmate")
public class UserReview {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_product_id")
    private ProductItem orderedProduct;

    @Column(name = "rating_value")
    private Integer ratingValue;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private Instant createdAt;

}