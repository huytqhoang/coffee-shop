package com.coffeeshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long shopId;
    private Long customerId;
    private List<CoffeeMenuDTO> items;
}
