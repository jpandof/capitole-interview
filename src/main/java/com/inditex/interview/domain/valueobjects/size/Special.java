package com.inditex.interview.domain.valueobjects.size;

public record Special(Boolean value) {
    public static Special valueOf(Boolean backSoon) {
        return new Special(backSoon);
    }
}