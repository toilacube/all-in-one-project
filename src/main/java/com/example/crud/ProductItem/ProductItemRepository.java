package com.example.crud.ProductItem;

import com.example.crud.Entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {
    List<ProductItem> findAllByProductId(int productId);
}
