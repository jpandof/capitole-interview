package com.inditex.interview.domain.valueobjects.stock;

public record Quantity(Long quantity) {
    
    public static Quantity valueOf(Long quantity) {
        return new Quantity(quantity);
    }

}
