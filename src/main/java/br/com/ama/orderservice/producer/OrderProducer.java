package br.com.ama.orderservice.producer;

import br.com.ama.orderservice.producer.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.order.created.name}")
    private String createdOrderRoutingKey;

    public void publishCreatedOrderEvent(OrderCreatedEvent orderCreatedEvent) {

        rabbitTemplate.convertAndSend("", createdOrderRoutingKey, orderCreatedEvent);
    }


}
