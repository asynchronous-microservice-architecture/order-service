package br.com.ama.orderservice.controller;

import br.com.ama.orderservice.domain.dto.OrderRequest;
import br.com.ama.orderservice.domain.dto.OrderResponse;
import br.com.ama.orderservice.domain.dto.UpdateOrderStatusRequest;
import br.com.ama.orderservice.domain.dto.UpdateOrderStatusResponse;
import br.com.ama.orderservice.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

     private final CreateOrderService createOrderService;
     private final GetOrderService getOrderService;
     private final DeleteOrderService deleteOrderService;
     private final GetAllOrdersService getAllOrdersService;
     private final UpdateOrderService updateOrderService;
     private final UpdateOrderStatusService updateOrderStatusService;

     @PostMapping()
     public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest){
         return ResponseEntity.status(HttpStatus.CREATED).body(createOrderService.execute(orderRequest));
     }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID orderId){
        return ResponseEntity.status(HttpStatus.OK).body(getOrderService.execute(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderResponse> deleteOrderById(@PathVariable UUID orderId){
        return ResponseEntity.status(HttpStatus.OK).body(deleteOrderService.execute(orderId));
    }

    @GetMapping()
    public ResponseEntity<Page<OrderResponse>> getOrders(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(getAllOrdersService.execute(pageable));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest orderRequest, @PathVariable UUID orderId){
        return ResponseEntity.status(HttpStatus.OK).body(updateOrderService.execute(orderId, orderRequest));
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(@RequestBody @Valid UpdateOrderStatusRequest updateOrderStatusRequest, @PathVariable UUID orderId){
        return ResponseEntity.status(HttpStatus.OK).body(updateOrderStatusService.execute(orderId, updateOrderStatusRequest));
    }


}
