package com.ankit.module3.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "product_table",
        //adding constrain
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique",columnNames = {"sku"}),
                @UniqueConstraint(name = "title_price_unique",columnNames = {"name","price"})
        },
        //adding index
        indexes = {
                @Index(name = "skuIndex",columnList = "sku")
        }
)
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 20)
    private String sku;

    @Column(name = "name")
    private String title;

    private BigDecimal price;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
