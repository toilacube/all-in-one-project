package com.example.crud.Repository;

import com.example.crud.Entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {
}