package br.com.ama.orderservice.mapper;

import br.com.ama.orderservice.domain.OrderItem;
import br.com.ama.orderservice.domain.dto.OrderRequest;
import br.com.ama.orderservice.domain.dto.OrderResponse;
import br.com.ama.orderservice.entity.OrderEntity;
import br.com.ama.orderservice.entity.OrderItemEntity;
import br.com.ama.orderservice.producer.event.OrderCreatedEvent;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderCreatedEvent toOrderCreatedEvent(OrderEntity orderEntity) {
        return OrderCreatedEvent.builder().idOrder(orderEntity.getIdOrder()).customerName(orderEntity.getCustomerName())
                .totalAmount(orderEntity.getTotalAmount()).build();
    }

    public static OrderEntity toEntity(OrderRequest orderRequest) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderRequest, orderEntity);

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        List<OrderItem> items = orderRequest.getItems();

        if (items != null) {
            OrderItemEntity orderItemEntity;

            for (OrderItem item : items) {
                orderItemEntity = new OrderItemEntity();
                BeanUtils.copyProperties(item, orderItemEntity);
                orderItemEntity.setOrders(orderEntity);
                orderItemEntities.add(orderItemEntity);
            }

        }

        orderEntity.setItems(orderItemEntities);
        return orderEntity;
    }

    public static OrderResponse toResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(orderEntity, orderResponse);

        List<OrderItem> orderItems = new ArrayList<>();

        List<OrderItemEntity> items = orderEntity.getItems();

        if (items != null) {
            OrderItem orderItem;

            for (OrderItemEntity item : items) {
                orderItem = new OrderItem();
                BeanUtils.copyProperties(item, orderItem);
                orderItems.add(orderItem);
            }

        }

        orderResponse.setItems(orderItems);

        return orderResponse;
    }


}
