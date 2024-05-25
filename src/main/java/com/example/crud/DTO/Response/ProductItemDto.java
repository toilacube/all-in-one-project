package com.example.crud.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class ProductItemDto implements Serializable {

    private Integer id;
    private Integer productId;
    private String size;
    private String color;
    private String colorImage;
    private Integer qtyInStock;
    private List <String> productItemImages;

    public String toString() {
        return "ProductItemDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", colorImage='" + colorImage + '\'' +
                ", qtyInStock=" + qtyInStock +
                ", productItemImages=" + productItemImages +
                '}';
    }
}
