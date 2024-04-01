package com.example.crud.ProductItem;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductItemResponse (
        Integer id, String color, String colorImage,
        List<String> sizes, List<Integer> itemIds,
        Integer qtyInStock, List<String> productItemImages){ }
