package com.inditex.interview.infrastructure.persistance.mappers;

import com.inditex.interview.domain.entities.Product;
import com.inditex.interview.domain.valueobjects.product.ProductId;
import com.inditex.interview.domain.valueobjects.product.Sequence;
import com.inditex.interview.infrastructure.persistance.entities.ProductDAO;

public class ProductMapper {

    public Product toDomain(ProductDAO productDAO) {
        return new Product(
            ProductId.valueOf(productDAO.getId()),
            Sequence.valueOf(productDAO.getSequence())
        );
    }
}
