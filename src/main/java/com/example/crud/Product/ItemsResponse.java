package com.example.crud.Product;

import lombok.Builder;

import java.util.List;

@Builder
public record ItemsResponse (int id, String size, List<String> sizes,
                             List<Integer> itemIds, String color, String colorImage,
                             int qtyInStock, List<String> productItemsImages) {
}
