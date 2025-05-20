package br.com.ama.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order_item")
public class OrderItemEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderItem;
    @Column(nullable = false)
    private Long idProduct;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity orders;
}
