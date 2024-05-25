package com.example.crud.Product;

import com.example.crud.Entity.PaginationResponse;
import com.example.crud.Entity.Product;
import com.example.crud.Exception.ProductNotFoundException;
import com.example.crud.ProductItem.ProductItemResponse;
import com.example.crud.ProductItem.ProductItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final ProductItemService productItemService;
    private final CacheManager cacheManager;

    @Override
    public PaginationResponse getAllProducts(int page, int pageSize) {

        Cache cache = cacheManager.getCache("productCache");
        if(cache != null){
            if(cache.get("products") != null){
                Logger.getLogger(ProductService.class.getName()).info("Cache hit");
                return (PaginationResponse) cache.get("products").get();
            }
        }


        Pageable paging = PageRequest.of( page, pageSize);

        Page <Product> products = productRepository.findAll(paging);

        Logger.getLogger(ProductService.class.getName()).info("Database hit");

        List<ProductResponse> productResponses = ProductResponse.fromEntity(products.getContent());

        for (ProductResponse productResponse : productResponses) {
            List<ProductItemResponse> productItems = productItemService.getItemsOfProduct(productResponse.getId());
            productResponse.setProductItemResponse(productItems);
        }

        // return products with pagination
        int totalPages = products.getTotalPages() - 1; // because page starts from 0

        PaginationResponse paginationResponse = PaginationResponse.builder()
                .totalPages(totalPages)
                .data(productResponses)
                .build();

        if(cache != null){
            cache.put("products", paginationResponse);
        }

        return paginationResponse;

    }


    @Override
    public List<ProductResponse> getProductsByCategory() {
        return null;
    }

    @Override
    public ProductResponse getProduct(int id) {
        Product product = productRepository.findById(id);

        if(product == null) throw new ProductNotFoundException(id);
        List<ProductItemResponse> productItems = productItemService.getItemsOfProduct(id);

        return ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .priceInt(product.getPriceInt())
                        .priceStr(product.getPriceStr())
                        .productItemResponse(productItems)
                        .build();
    }

}
