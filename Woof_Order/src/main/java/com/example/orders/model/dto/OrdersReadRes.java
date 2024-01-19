package com.example.orders.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class OrdersReadRes {
    private Long idx;
    private Long phoneNumber;
    private Integer time;
    private String place;
    private String reservation_status;
}
