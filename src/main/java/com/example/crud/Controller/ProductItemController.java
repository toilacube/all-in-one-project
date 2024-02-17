package com.example.crud.Controller;

import com.example.crud.DTO.Response.ProductItemDto;
import com.example.crud.Entity.ProductItem;
import com.example.crud.Entity.ProductItemImage;
import com.example.crud.Repository.ProductItemImageRepository;
import com.example.crud.Repository.ProductItemRepository;
import com.example.crud.Service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/productItem")
@RestController
public class ProductItemController {

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private ProductItemImageRepository prodItImgRepository;

    @Autowired
    private ProductItemService productItemService;

    @GetMapping("{id}")
    public ProductItemDto getProductItemById(@PathVariable Integer id) {
        return productItemService.getProductItemById(id);
    }

    @PutMapping("/updateQtyInStock")
    public ResponseEntity<String> updateQtyInStock(@RequestParam Integer productItemId, @RequestParam Integer newQty) {
        return ResponseEntity.ok(productItemService.updateQtyInStock(productItemId, newQty));
    }
}
