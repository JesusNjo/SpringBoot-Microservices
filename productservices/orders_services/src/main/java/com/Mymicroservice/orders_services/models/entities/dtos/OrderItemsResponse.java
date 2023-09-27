package com.Mymicroservice.orders_services.models.entities.dtos;

public record OrderItemsResponse(Long id, String sku, Double price, Long quantity) {

}
