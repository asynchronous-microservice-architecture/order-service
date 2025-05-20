package br.com.ama.orderservice.mapper;

import br.com.ama.orderservice.domain.dto.UpdateOrderStatusResponse;
import br.com.ama.orderservice.entity.OrderEntity;

public class UpdateOrderStatusMapper {

    public static UpdateOrderStatusResponse toResponse(OrderEntity orderEntity) {

        return UpdateOrderStatusResponse.builder().orderId(orderEntity.getIdOrder()).orderStatus(orderEntity.getStatus()).
                updatedAt(orderEntity.getUpdatedAt().toLocalDateTime()).build();

    }
}
