package com.inditex.interview.domain.valueobjects.product;

public record Sequence(Long value) {
    public static Sequence valueOf(Long sequence) {
        return new Sequence(sequence);
    }
}
