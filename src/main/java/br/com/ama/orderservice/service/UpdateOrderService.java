package br.com.ama.orderservice.service;

import br.com.ama.orderservice.domain.dto.OrderRequest;
import br.com.ama.orderservice.domain.dto.OrderResponse;
import br.com.ama.orderservice.entity.OrderEntity;
import br.com.ama.orderservice.exception.OrderNotFoundException;
import br.com.ama.orderservice.mapper.OrderMapper;
import br.com.ama.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class UpdateOrderService {

    private final OrderRepository orderRepository;

    public OrderResponse execute(UUID orderId, OrderRequest orderRequest) {
        try {
            OrderEntity existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));

            OrderEntity updatedOrderData = OrderMapper.toEntity(orderRequest);

            OrderEntity mergedOrder = partialUpdateOrder(existingOrder, updatedOrderData);

            return OrderMapper.toResponse(orderRepository.save(mergedOrder));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    public OrderEntity partialUpdateOrder(OrderEntity existingOrder, OrderEntity updatedOrderData) {

        Optional.ofNullable(updatedOrderData.getCustomerName()).ifPresent(existingOrder::setCustomerName);
        Optional.ofNullable(updatedOrderData.getOrderDate()).ifPresent(existingOrder::setOrderDate);
        Optional.ofNullable(updatedOrderData.getStatus()).ifPresent(existingOrder::setStatus);

        return existingOrder;
    }


}
