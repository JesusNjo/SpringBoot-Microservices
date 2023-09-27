package com.Mymicroservice.orders_services.services;


import com.Mymicroservice.orders_services.models.entities.Order;
import com.Mymicroservice.orders_services.models.entities.OrderItems;
import com.Mymicroservice.orders_services.models.entities.dtos.*;
import com.Mymicroservice.orders_services.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placerOrder(OrderRequest orderRequest) {

        //Check for inventory
        BaseResponse result =this.webClientBuilder.build().post().uri("https://localhost:8083/api/inventory/in-stock").bodyValue(orderRequest.getOrderItems()).retrieve().bodyToMono(BaseResponse.class).block();

        if(result != null && !result.hasErrors()){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream().map(orderItemsRequest -> mapOrderItemRequestToOrderItem(orderItemsRequest,order)).toList());

            this.orderRepository.save(order);
        }else{
            new IllegalAccessException("Algo esta mal");
        }


    }
    public List<OrderResponse> getAllOrders(){
        List<Order> orders =this.orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(order.getId(),order.getOrderNumber(),order.getOrderItems().stream().map(this::mapToOrderItemRequest).toList());
    }

    private OrderItemsResponse mapToOrderItemRequest(OrderItems orderItems) {
        return new OrderItemsResponse(orderItems.getId(),orderItems.getSku(),orderItems.getPrice(),orderItems.getQuantity());

    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemsRequest orderItemsRequest, Order order){
        return OrderItems.builder().id(orderItemsRequest.getId()).sku(orderItemsRequest.getSku())
                .price(orderItemsRequest.getPrice()).quantity(orderItemsRequest.getQuantity()).order(order).build();

    }
}
