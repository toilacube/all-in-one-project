package com.example.crud.ProductItem;

import com.example.crud.Entity.ProductItem;
import com.example.crud.Entity.ProductItemImage;
import com.example.crud.Product.ProductResponse;
import com.example.crud.Product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductItemService implements IProductItemService {
    private final ProductItemRepository productItemRepository;
    private final ItemImageRepository itemImageRepository;
    public List<ProductItemResponse> getItemsOfProduct(int productId){

        List<ProductItem> productItems = productItemRepository.findAllByProductId(productId);
        List<ProductItemResponse> productItemResponses = new ArrayList<>();


        Map<String, List<Integer>> listColorsAndIds = new HashMap<>();
        Map<String, List<String>> listSizesAndIds = new HashMap<>();

        for (ProductItem productItem : productItems) {
            listColorsAndIds.computeIfAbsent(productItem.getColor(), k -> new ArrayList<>()).add(productItem.getId());
            listSizesAndIds.computeIfAbsent(productItem.getColor(), k -> new ArrayList<>()).add(productItem.getSize());
        }

        List <String> uniqueColor = new ArrayList<>();

        for (ProductItem productItem : productItems) {

            if(uniqueColor.contains(productItem.getColor())) {
                continue;
            }
            else {
                uniqueColor.add(productItem.getColor());
            }
            //get all the Images of this items

            List<String> imageUrls = new ArrayList<>();
            List<ProductItemImage> productItemImages = itemImageRepository.findAllByProductItemId(productItem.getId());

            for(ProductItemImage productItemImage : productItemImages){
                imageUrls.add(productItemImage.getUrl());
            }

            ProductItemResponse productItemResponse = ProductItemResponse.builder()
                    .id(productItem.getId())
                    .color(productItem.getColor())
                    .colorImage(productItem.getColorImage())
                    .qtyInStock(productItem.getQtyInStock())
                    .productItemImages(imageUrls)
                    .itemIds(listColorsAndIds.get(productItem.getColor()))
                    .sizes(listSizesAndIds.get(productItem.getColor()))
                    .build();

            productItemResponses.add(productItemResponse);
        }
        return productItemResponses;

    }
//    public String getItem(int id){
//        return productItemRepository.findById(id).get().getSize();
//    }
}
