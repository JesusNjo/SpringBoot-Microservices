package com.Mymicroservice.orders_services.controllers;

import com.Mymicroservice.orders_services.models.entities.dtos.OrderRequest;
import com.Mymicroservice.orders_services.models.entities.dtos.OrderResponse;
import com.Mymicroservice.orders_services.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        this.orderService.placerOrder(orderRequest);
        return "Order placed succefully";
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders(){
        return this.orderService.getAllOrders();
    }
}
