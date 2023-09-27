package com.Mymicroservice.orders_services.models.entities.dtos;

public record BaseResponse(String[] errorMessages) {

    public Boolean hasErrors(){
        return errorMessages != null && errorMessages.length>0;
    }
}
