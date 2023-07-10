package com.inditex.interview.domain.valueobjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Id<T> {

    protected final T value;

    protected Id(T value) {
        if (value == null) {
            throw new IllegalArgumentException("The ID value cannot be null.");
        }
        this.value = value;
    }
}
