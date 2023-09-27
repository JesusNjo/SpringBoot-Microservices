package com.microservices.car_services.controller;


import com.microservices.car_services.models.entities.CarEntity;
import com.microservices.car_services.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List> getAll(){
        List<CarEntity> listCars = carService.getAllCar();

        if(listCars.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listCars);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getById(@PathVariable Long id){
        CarEntity c = carService.getById(id);
        if(c ==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(c);
        }
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<CarEntity>> getUserById(@PathVariable("userId") Long userId){
        List<CarEntity> getCars = carService.getByUserId(userId);
        if(getCars.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(getCars);
        }
    }

    @PostMapping
    public ResponseEntity<CarEntity> createCar(@RequestBody CarEntity car){
        carService.createCar(car);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarEntity> modifyCar(@RequestBody CarEntity car,@PathVariable Long id){
        CarEntity c = carService.getById(id);
        if (c == null){
            return ResponseEntity.notFound().build();
        }else{
            car.setId(id);
            carService.modifyCar(car);
            return ResponseEntity.ok(car);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarEntity> deleteCar(@PathVariable Long id){
        CarEntity c = carService.getById(id);
        if(c == null){
            return ResponseEntity.notFound().build();
        }else{
            carService.deleteCar(c.getId());
            return ResponseEntity.ok(c);
        }
    }


}
