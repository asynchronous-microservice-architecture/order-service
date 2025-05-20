package br.com.ama.orderservice.service;

import br.com.ama.orderservice.domain.OrderItem;
import br.com.ama.orderservice.domain.OrderStatus;
import br.com.ama.orderservice.domain.dto.OrderRequest;
import br.com.ama.orderservice.domain.dto.OrderResponse;
import br.com.ama.orderservice.entity.OrderEntity;
import br.com.ama.orderservice.mapper.OrderMapper;
import br.com.ama.orderservice.producer.OrderProducer;
import br.com.ama.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderResponse execute(OrderRequest orderRequest) {

        try {

            BigDecimal totalAmount = calculateTotalAmount(orderRequest.getItems());

            OrderEntity orderEntity = OrderMapper.toEntity(orderRequest);
            orderEntity.setTotalAmount(totalAmount);
            orderEntity.setStatus(OrderStatus.PENDING);

            OrderEntity saved = orderRepository.save(orderEntity);

            orderProducer.publishCreatedOrderEvent(OrderMapper.toOrderCreatedEvent(orderEntity));

            return OrderMapper.toResponse(saved);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    public BigDecimal calculateTotalAmount(List<OrderItem> items) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItem orderItem : items) {
            totalAmount = orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        }

        return totalAmount;

    }


}
