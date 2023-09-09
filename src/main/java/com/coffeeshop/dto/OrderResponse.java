package com.coffeeshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OrderResponse {
    private Long orderId;
    private Long shopId;
    private Long queueId;
    private Long positionInQueue;
    private Long waitingTime;
}
