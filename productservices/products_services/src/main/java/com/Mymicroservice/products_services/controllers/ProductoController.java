package com.Mymicroservice.products_services.controllers;

import com.Mymicroservice.products_services.models.dtos.ProductRequest;
import com.Mymicroservice.products_services.models.dtos.ProductResponse;
import com.Mymicroservice.products_services.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductService productService;

   /* public ProductoController(ProductService productService){
        this.productService = productService;
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest){
        this.productService.addProduct(productRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return this.productService.getAllProducts();
    }


}
