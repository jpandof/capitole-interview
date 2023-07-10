package com.inditex.interview.domain.valueobjects.size;

import com.inditex.interview.domain.valueobjects.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class SizeId extends Id<Long> {
    private SizeId(Long value) {
        super(value);
    }

    public static SizeId valueOf(Long sizeId) {
        return new SizeId(sizeId);
    }
}

