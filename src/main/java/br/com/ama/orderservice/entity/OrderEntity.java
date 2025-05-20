package br.com.ama.orderservice.entity;

import br.com.ama.orderservice.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    private UUID idOrder;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @Column(nullable = false)
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
