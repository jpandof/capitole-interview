package com.inditex.interview.domain.entities;

import com.inditex.interview.domain.valueobjects.size.BackSoon;
import com.inditex.interview.domain.valueobjects.size.Special;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    private String id;
    private BackSoon backSoon;
    private Special special;
    private Product product;
}
