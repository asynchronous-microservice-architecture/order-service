package br.com.ama.orderservice.service;

import br.com.ama.orderservice.domain.dto.OrderResponse;
import br.com.ama.orderservice.entity.OrderEntity;
import br.com.ama.orderservice.exception.OrderNotFoundException;
import br.com.ama.orderservice.mapper.OrderMapper;
import br.com.ama.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetOrderService {

    private final OrderRepository orderRepository;

    public OrderResponse execute(UUID orderId) {

        try {
            OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
            return OrderMapper.toResponse(orderEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }


}
