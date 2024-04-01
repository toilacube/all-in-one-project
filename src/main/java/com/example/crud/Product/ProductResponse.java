package com.example.crud.Product;

import com.example.crud.Entity.Product;
import com.example.crud.ProductItem.ProductItemResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    private int id;
    private String name;
    private String description;
    private int categoryId;
    private Integer priceInt;
    private String priceStr;
    private List<ProductItemResponse> productItemResponse;

    public static List<ProductResponse> fromEntity(List<Product> products) {
        return products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .categoryId(product.getCategory().getId())
                        .priceInt(product.getPriceInt())
                        .priceStr(product.getPriceStr())
                        .build())
                .toList();
    }
}

