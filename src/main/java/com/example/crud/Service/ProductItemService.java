package com.example.crud.Service;

import com.example.crud.DTO.Response.ProductItemDto;
import com.example.crud.Entity.ProductItem;
import com.example.crud.Entity.ProductItemImage;
import com.example.crud.Repository.ProductItemImageRepository;
import com.example.crud.Repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProductItemService {
    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private ProductItemImageRepository prodItImgRepository;

    public ProductItemDto getProductItemById(Integer id) {
        ProductItem productItem = productItemRepository.findById(id).orElse(null);
        if (productItem == null) {
            return null;
        }
        int itemId = productItem.getId();
        List<ProductItemImage> productItemImage = prodItImgRepository.findByProductItemId(itemId);
        List<String> urls = productItemImage.stream().map(ProductItemImage::getUrl).toList();

        ProductItemDto productItemDto = new ProductItemDto(productItem.getId(),
                productItem.getId(),
                productItem.getSize(),
                productItem.getColor(),
                productItem.getColorImage(),
                productItem.getQtyInStock(),
                urls);
        return productItemDto;
    }

    public ResponseEntity<String> updateQtyInStock(Integer id, Integer qtyInStock) {
        ProductItem productItem = productItemRepository.findById(id).orElse(null);
        if (productItem == null) {
            return ResponseEntity.badRequest().body("Product item not found");
        }
        productItem.setQtyInStock(qtyInStock);
        productItemRepository.save(productItem);
        return ResponseEntity.ok("Qty in stock updated successfully");
    }
}
