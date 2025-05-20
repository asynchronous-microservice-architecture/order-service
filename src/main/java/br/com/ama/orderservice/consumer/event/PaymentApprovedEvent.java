package br.com.ama.orderservice.consumer.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentApprovedEvent {

    private UUID idOrder;
    private LocalDateTime approvedAt;

}
