package com.example.crud.ProductItem;

import com.example.crud.Entity.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/productItem")
@RequiredArgsConstructor
@RestController
public class ProductItemController {
    private final ProductItemService productItemService;

    @GetMapping("/{productId}")
    public List<ProductItemResponse> getItemsOfProduct(@PathVariable int productId) {
        return productItemService.getItemsOfProduct(productId);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getItem(@PathVariable int id) {
//        return ResponseEntity.ok(productItemService.getItem(id));
//    }
}
