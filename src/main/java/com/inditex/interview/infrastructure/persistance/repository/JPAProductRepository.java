package com.inditex.interview.infrastructure.persistance.repository;

import com.inditex.interview.infrastructure.persistance.entities.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAProductRepository extends JpaRepository<ProductDAO, Long> {

}
