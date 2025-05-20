package br.com.ama.orderservice.domain.dto;

import br.com.ama.orderservice.domain.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateOrderStatusRequest {

    @NotNull
    @JsonProperty("status")
    private OrderStatus orderStatus;
}
