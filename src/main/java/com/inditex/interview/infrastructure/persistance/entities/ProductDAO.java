package com.inditex.interview.infrastructure.persistance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDAO {
    @Id
    private Long id;
    private Long sequence;

    @OneToMany(mappedBy = "product",  fetch = FetchType.EAGER)
    private List<SizeDAO> sizes;
}
