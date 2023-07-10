package com.inditex.interview.infrastructure.persistance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDAO {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="size_id")
    private SizeDAO size;

    @Column(name = "quantity")
    private Long quantity;
}
