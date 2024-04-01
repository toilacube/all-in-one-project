package com.example.crud.Product;

import com.example.crud.Entity.Product;
import com.example.crud.Entity.ProductItem;
import com.example.crud.ProductItem.ProductItemResponse;
import com.example.crud.ProductItem.ProductItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final ProductItemService productItemService;
    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = ProductResponse.fromEntity(products);

        for (ProductResponse productResponse : productResponses) {
            List<ProductItemResponse> productItems = productItemService.getItemsOfProduct(productResponse.getId());
            System.out.println(productItems.size());
            productResponse.setProductItemResponse(productItems);
        }
        return productResponses;

    }

    @Override
    public List<ProductResponse> getProductsByCategory() {
        return null;
    }

}
