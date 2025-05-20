package br.com.ama.orderservice.consumer.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRejectedEvent {

    private UUID idOrder;
    private LocalDateTime rejectedAt;
    private String reason;

}
