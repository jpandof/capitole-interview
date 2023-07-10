package com.inditex.interview.infrastructure.persistance.repository;

import com.inditex.interview.domain.entities.Product;
import com.inditex.interview.domain.exceptions.ProductNotFoundException;
import com.inditex.interview.domain.repositories.ProductRepository;
import com.inditex.interview.infrastructure.persistance.entities.ProductDAO;
import com.inditex.interview.infrastructure.persistance.entities.SizeDAO;
import com.inditex.interview.infrastructure.persistance.mappers.ProductMapper;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final JPAProductRepository jpaProductRepository;

    @Autowired
    public ProductRepositoryImpl(JPAProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public List<Product> GetProducts() throws ProductNotFoundException {
        ProductMapper productMapper = new ProductMapper();
        List<ProductDAO> allProducts = jpaProductRepository.findAll();
        return allProducts.parallelStream()
            .filter(product -> product.getSizes().stream()
                .anyMatch(size -> (size.getBackSoon() || hasStock(size)) && specialCondition(size)))
            .sorted(Comparator.comparingLong(ProductDAO::getSequence))
            .map(productMapper::toDomain)
            .toList();
    }

    public static boolean hasStock(SizeDAO size) {
        return size.getStocks().stream().anyMatch(stock -> stock.getQuantity() > 0);
    }

    public static boolean specialCondition(SizeDAO size) {
        if (size.getSpecial()) {
            return size.getStocks().stream().anyMatch(stock -> !stock.getSize().getSpecial() && (stock.getQuantity() > 0 || stock.getSize().getBackSoon()));
        }
        return true;
    }
}
