package com.example.crud.ProductItem;

import com.example.crud.Entity.ProductItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ProductItemImage, Integer> {
    List<ProductItemImage> findAllByProductItemId(Integer productItemId);
}
