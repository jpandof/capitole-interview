package com.inditex.interview.infrastructure.persistance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Size")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeDAO {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductDAO product;

    private Boolean backSoon;
    private Boolean special;

    @OneToMany(mappedBy = "size", fetch = FetchType.EAGER)
    private List<StockDAO> stocks;

}
