package br.com.ama.orderservice.service;

import br.com.ama.orderservice.domain.dto.UpdateOrderStatusRequest;
import br.com.ama.orderservice.domain.dto.UpdateOrderStatusResponse;
import br.com.ama.orderservice.entity.OrderEntity;
import br.com.ama.orderservice.exception.OrderNotFoundException;
import br.com.ama.orderservice.mapper.UpdateOrderStatusMapper;
import br.com.ama.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class UpdateOrderStatusService {

    private final OrderRepository orderRepository;

    public UpdateOrderStatusResponse execute(UUID orderId, UpdateOrderStatusRequest updateOrderStatusRequest) {

        try {
            OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));

            orderEntity.setStatus(updateOrderStatusRequest.getOrderStatus());

            OrderEntity saved = orderRepository.save(orderEntity);

            return UpdateOrderStatusMapper.toResponse(saved);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }


}
