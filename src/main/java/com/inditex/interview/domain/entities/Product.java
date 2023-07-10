package com.inditex.interview.domain.entities;

import com.inditex.interview.domain.valueobjects.product.ProductId;
import com.inditex.interview.domain.valueobjects.product.Sequence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private ProductId id;
    private Sequence sequence;
}
