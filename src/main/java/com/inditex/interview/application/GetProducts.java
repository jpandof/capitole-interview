package com.inditex.interview.application;

import com.inditex.interview.domain.entities.Product;
import com.inditex.interview.domain.exceptions.ProductNotFoundException;
import com.inditex.interview.domain.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProducts {
    private final ProductRepository productRepository;

    @Autowired
    public GetProducts(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> execute() throws ProductNotFoundException {
        return productRepository.GetProducts();
    }

}
