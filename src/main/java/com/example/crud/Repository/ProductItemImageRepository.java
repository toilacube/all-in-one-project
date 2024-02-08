package com.example.crud.Repository;

import com.example.crud.Entity.ProductItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductItemImageRepository extends JpaRepository<ProductItemImage, Integer> {

    List <ProductItemImage> findByProductItemId(int itemId);
}