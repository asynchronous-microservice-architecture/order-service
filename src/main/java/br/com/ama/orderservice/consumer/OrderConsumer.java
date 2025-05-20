package br.com.ama.orderservice.consumer;

import br.com.ama.orderservice.consumer.event.PaymentApprovedEvent;
import br.com.ama.orderservice.consumer.event.PaymentRejectedEvent;
import br.com.ama.orderservice.domain.OrderStatus;
import br.com.ama.orderservice.domain.dto.UpdateOrderStatusRequest;
import br.com.ama.orderservice.service.UpdateOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private final UpdateOrderStatusService updateOrderStatusService;

    @RabbitListener(queues = "${broker.queue.payment.approved.name}")
    public void listenPaymentApprovedEvent(@Payload PaymentApprovedEvent paymentApprovedEvent){
        UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest();
        updateOrderStatusRequest.setOrderStatus(OrderStatus.APPROVED);

        updateOrderStatusService.execute(paymentApprovedEvent.getIdOrder(), updateOrderStatusRequest);
    }

    @RabbitListener(queues = "${broker.queue.payment.rejected.name}")
    public void listenPaymentRejectedEvent(@Payload PaymentRejectedEvent paymentRejectedEvent){
        UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest();
        updateOrderStatusRequest.setOrderStatus(OrderStatus.REJECTED);

        updateOrderStatusService.execute(paymentRejectedEvent.getIdOrder(), updateOrderStatusRequest);
    }

}
