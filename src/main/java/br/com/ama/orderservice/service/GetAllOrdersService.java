package br.com.ama.orderservice.service;

import br.com.ama.orderservice.domain.dto.OrderResponse;
import br.com.ama.orderservice.entity.OrderEntity;
import br.com.ama.orderservice.mapper.OrderMapper;
import br.com.ama.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllOrdersService {

    private final OrderRepository orderRepository;

    public Page<OrderResponse> execute(Pageable pageable) {

        try {
            Page<OrderEntity> orders = orderRepository.findAll(pageable);
            return orders.map(OrderMapper::toResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }
}
