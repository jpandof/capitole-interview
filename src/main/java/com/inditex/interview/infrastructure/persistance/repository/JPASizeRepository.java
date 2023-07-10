package com.inditex.interview.infrastructure.persistance.repository;

import com.inditex.interview.infrastructure.persistance.entities.SizeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPASizeRepository extends JpaRepository<SizeDAO, Long> {

}
