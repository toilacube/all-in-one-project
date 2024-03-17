package com.example.crud.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "coolmate")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price_int")
    private Integer priceInt;

    @Size(max = 10)
    @Column(name = "price_str", length = 10)
    private String priceStr;

}