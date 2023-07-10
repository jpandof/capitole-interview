package com.inditex.interview.domain.entities;

import com.inditex.interview.domain.valueobjects.stock.Quantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Quantity quantity;
    private Size size;
}
