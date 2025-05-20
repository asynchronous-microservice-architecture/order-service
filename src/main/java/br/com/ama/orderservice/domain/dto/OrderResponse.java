package br.com.ama.orderservice.domain.dto;

import br.com.ama.orderservice.domain.OrderItem;
import br.com.ama.orderservice.domain.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private UUID idOrder;
    private String customerName;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItem> items;
}
