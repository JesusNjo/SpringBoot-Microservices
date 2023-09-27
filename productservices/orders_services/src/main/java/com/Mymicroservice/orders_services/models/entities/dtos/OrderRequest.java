package com.Mymicroservice.orders_services.models.entities.dtos;


import com.Mymicroservice.orders_services.models.entities.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderItemsRequest> orderItems;
}
