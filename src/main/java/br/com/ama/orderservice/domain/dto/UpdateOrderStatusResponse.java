package br.com.ama.orderservice.domain.dto;

import br.com.ama.orderservice.domain.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderStatusResponse {

    private OrderStatus orderStatus;
    private UUID orderId;
    private LocalDateTime updatedAt;
}
