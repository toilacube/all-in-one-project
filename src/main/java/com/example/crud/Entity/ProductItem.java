package com.example.crud.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_item", schema = "coolmate")
public class ProductItem {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Size(max = 10)
    @Column(name = "size", length = 10)
    private String size;

    @Size(max = 50)
    @Column(name = "color", length = 50)
    private String color;

    @Size(max = 1000)
    @Column(name = "color_image", length = 1000)
    private String colorImage;

    @Column(name = "qty_in_stock")
    private Integer qtyInStock;

}