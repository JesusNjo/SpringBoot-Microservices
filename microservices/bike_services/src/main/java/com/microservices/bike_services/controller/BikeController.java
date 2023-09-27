package com.microservices.bike_services.controller;


import com.microservices.bike_services.models.entities.BikeEntity;
import com.microservices.bike_services.service.BikePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bike")
public class BikeController {

    @Autowired
    BikePersistence bikePersistence;

    @GetMapping
    public ResponseEntity<List<BikeEntity>> getAllBikes(){
        List<BikeEntity> listBikes = bikePersistence.getAllBikes();
        if(listBikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listBikes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeEntity> getBikeById(@PathVariable Long id){
        BikeEntity bike = bikePersistence.getById(id);
        if(bike == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(bike);
        }
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<BikeEntity>> getBikesById(@PathVariable("userId")Long id){
        List<BikeEntity> bike = bikePersistence.findByUserId(id);
        if(bike.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(bike);
        }
    }

    @PostMapping
    public ResponseEntity<BikeEntity> createBike(@RequestBody BikeEntity bike){
        bikePersistence.createBike(bike);
        return ResponseEntity.ok(bike);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeEntity> modifyBike(@RequestBody BikeEntity bike,@PathVariable Long id){

        BikeEntity bikeToDelete = bikePersistence.getById(id);
        if(bikeToDelete == null){
            return ResponseEntity.notFound().build();
        }else{
            bike.setId(id);
            bikePersistence.modifyBike(bike);
            return ResponseEntity.ok(bike);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BikeEntity> deleteBike(@PathVariable Long id){
        BikeEntity bikeToDelete = bikePersistence.getById(id);
        if(bikeToDelete == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(bikeToDelete);
        }
    }
}
