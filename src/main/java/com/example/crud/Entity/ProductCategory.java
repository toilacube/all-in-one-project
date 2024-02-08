package com.example.crud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_category", schema = "ecommerce3")
public class ProductCategory {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "parent_category_id")
    private Integer parentCategoryId;

    @Column(name = "category_name", length = 200)
    private String categoryName;

    @Column(name = "category_slug", length = 200)
    private String categorySlug;

}