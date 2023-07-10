package com.inditex.interview.infrastructure.persistance.repository;

import com.inditex.interview.infrastructure.persistance.entities.StockDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAStockRepository extends JpaRepository<StockDAO, Long> {

}
