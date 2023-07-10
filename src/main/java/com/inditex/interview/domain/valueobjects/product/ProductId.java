package com.inditex.interview.domain.valueobjects.product;

import com.inditex.interview.domain.valueobjects.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProductId extends Id<Long> {
    private ProductId(Long value) {
        super(value);
    }

    public static ProductId valueOf(Long value) {
        return new ProductId(value);
    }
}
