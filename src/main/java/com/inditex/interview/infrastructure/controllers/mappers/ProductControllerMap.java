package com.inditex.interview.infrastructure.controllers.mappers;

import com.inditex.interview.domain.entities.Product;
import com.inditex.interview.infrastructure.controllers.entities.ProductDTO;
import java.util.List;

public class ProductControllerMap {
    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
            product.getId().getValue()
        );
    }

    public List<ProductDTO> toDTOList(List<Product> productList){
        return productList.stream().map(this::toDTO).toList();
    }
}
