package com.inditex.interview.domain.repositories;

import com.inditex.interview.domain.entities.Product;
import com.inditex.interview.domain.exceptions.ProductNotFoundException;
import java.util.List;

public interface ProductRepository {
    List<Product> GetProducts() throws ProductNotFoundException;
}
