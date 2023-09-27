package com.Mymicroservice.orders_services.models.entities.dtos;

import com.Mymicroservice.orders_services.models.entities.Order;

import java.util.List;

public record OrderResponse(Long id, String orderNumber, List<OrderItemsResponse> orderItems) {


}
