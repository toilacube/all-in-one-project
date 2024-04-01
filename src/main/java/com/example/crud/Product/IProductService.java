package com.example.crud.Product;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsByCategory();

}
