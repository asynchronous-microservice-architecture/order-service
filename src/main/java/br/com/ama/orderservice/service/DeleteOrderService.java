package br.com.ama.orderservice.service;

import br.com.ama.orderservice.domain.dto.OrderResponse;
import br.com.ama.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteOrderService {

    private final OrderRepository orderRepository;
    private final GetOrderService getOrderService;

    public OrderResponse execute(UUID orderId) {
        try {
            OrderResponse orderResponse;

            orderResponse = getOrderService.execute(orderId);
            orderRepository.deleteById(orderResponse.getIdOrder());

            return orderResponse;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }

}
