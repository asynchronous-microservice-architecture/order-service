package br.com.ama.orderservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItem
{
    private Long idProduct;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
}
