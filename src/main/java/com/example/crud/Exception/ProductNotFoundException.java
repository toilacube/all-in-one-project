package com.example.crud.Exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(int productId) {
        super("Product with id " + productId + " not found.");
    }
}
