package com.ecommerce.order.model;

import com.ecommerce.order.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;

    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
}
