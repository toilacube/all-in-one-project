package com.example.crud.Product;

import com.example.crud.Entity.PaginationResponse;

import java.util.List;

public interface IProductService {
    PaginationResponse getAllProducts(int page, int pageSize);
    List<ProductResponse> getProductsByCategory();

}
