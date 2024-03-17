package com.example.crud.Service;

import com.example.crud.DTO.Response.ProductItemDto;
import com.example.crud.Entity.ProductItem;
import com.example.crud.Entity.ProductItemImage;
import com.example.crud.Repository.ProductItemImageRepository;
import com.example.crud.Repository.ProductItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductItemService {

    private final ProductItemRepository productItemRepository;

    private final ProductItemImageRepository prodItImgRepository;

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

    public String updateQtyInStock(Integer id, Integer qtyInStock) {
        ProductItem productItem = productItemRepository.findById(id).orElse(null);
        if (productItem == null) {
            return "Product item not found";
        }
        productItem.setQtyInStock(qtyInStock);
        productItemRepository.save(productItem);
        return "Qty in stock updated successfully";
    }
}
