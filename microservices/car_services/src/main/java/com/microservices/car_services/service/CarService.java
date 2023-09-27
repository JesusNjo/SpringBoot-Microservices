package com.microservices.car_services.service;


import com.microservices.car_services.models.entities.CarEntity;
import com.microservices.car_services.respository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List getAllCar(){
        return carRepository.findAll();
    }

    public CarEntity getById(Long id){
        return carRepository.findById(id).orElse(null);
    }

    public void createCar(CarEntity car){
        carRepository.save(car);
    }
    public void modifyCar(CarEntity car){
        CarEntity c= carRepository.findById(car.getId()).orElse(null);

        c.setId(car.getId());
        c.setBrand(car.getBrand());
        c.setModel(car.getModel());
        c.setUserId(car.getUserId());
        carRepository.save(c);


    }

    public void deleteCar(Long id){
        CarEntity x = carRepository.findById(id).orElse(null);


        carRepository.delete(x);

    }

    public List<CarEntity> getByUserId(Long id){
        return carRepository.findByUserId(id);
    }
}
