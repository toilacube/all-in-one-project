package com.example.crud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_category", schema = "coolmate")
public class ProductCategory {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "parent_category_id")
    private Integer parentCategoryId;

    @Size(max = 200)
    @Column(name = "category_name", length = 200)
    private String categoryName;

    @Size(max = 200)
    @Column(name = "category_slug", length = 200)
    private String categorySlug;

}