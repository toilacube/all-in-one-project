package com.example.crud.Product;

import com.example.crud.Entity.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping("")
    public PaginationResponse getAllProducts(
            @RequestParam(name="page", defaultValue = "0")int page,
            @RequestParam(name="pageSize", defaultValue = "20")int pageSize){
        return productService.getAllProducts(page, pageSize);
    }
    @GetMapping("{id}")
    public ProductResponse getProduct(@PathVariable(name="id")int id){
        return productService.getProduct(id);
    }

}
