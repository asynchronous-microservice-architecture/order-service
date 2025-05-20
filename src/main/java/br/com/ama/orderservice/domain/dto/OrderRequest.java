package br.com.ama.orderservice.domain.dto;

import br.com.ama.orderservice.domain.OrderItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    @NotNull(message = "customerName cannot be null")
    @NotBlank(message = "customerName cannot be blank")
    private String customerName;
    @NotNull(message = "orderDate cannot be null")
    private LocalDateTime orderDate;
    @NotNull(message = "items cannot be null")
    @NotEmpty(message = "items cannot be empty")
    private List<OrderItem> items;
}
