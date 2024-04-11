package com.example.crud.Product;

import com.example.crud.Entity.PaginationResponse;
import com.example.crud.Entity.Product;
import com.example.crud.Entity.ProductItem;
import com.example.crud.ProductItem.ProductItemResponse;
import com.example.crud.ProductItem.ProductItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.crud.Product.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final ProductItemService productItemService;
    @Override
    public PaginationResponse getAllProducts(int page, int pageSize) {


        Pageable paging = PageRequest.of( page, pageSize);

        Page <Product> products = productRepository.findAll(paging);

        int totalPages = products.getTotalPages() - 1; // because page starts from 0

        List<ProductResponse> productResponses = ProductResponse.fromEntity(products.getContent());

        for (ProductResponse productResponse : productResponses) {
            List<ProductItemResponse> productItems = productItemService.getItemsOfProduct(productResponse.getId());
            productResponse.setProductItemResponse(productItems);
        }

        PaginationResponse paginationResponse = PaginationResponse.builder()
                .totalPages(totalPages)
                .data(productResponses)
                .build();

        return paginationResponse;

    }

    @Override
    public List<ProductResponse> getProductsByCategory() {
        return null;
    }

}
