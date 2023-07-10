package com.inditex.interview.domain.valueobjects.size;

public record BackSoon(Boolean value) {
    public static BackSoon valueOf(Boolean backSoon) {
        return new BackSoon(backSoon);
    }
}