package com.microservices.user_services.service;


import com.microservices.user_services.models.dtos.BikeEntity;
import com.microservices.user_services.models.dtos.CarEntity;
import com.microservices.user_services.models.entities.UserEntity;
import com.microservices.user_services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;


    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity getForId(Long id){

        return userRepository.findById(id).orElse(null);
    }

    public void createUser(UserEntity user){
        userRepository.save(user);
    }

    public void modifyUser(UserEntity user){
        UserEntity newUser = userRepository.findById(user.getId()).orElse(null);
        newUser.setId(user.getId());
        newUser.setName(user.getName());
        newUser.setEdad(user.getEdad());
        userRepository.save(newUser);
    }

    public void deleteUser(Long id){
        UserEntity u = userRepository.findById(id).orElse(null);
        userRepository.delete(u);
    }

    public List<CarEntity> getCars(Long id){
        List<CarEntity> cars = restTemplate.getForObject("http://localhost:8002/api/car/byuser/"+id,List.class);
        return cars;
    }

    public List<BikeEntity> getBikes(Long id){
        List<BikeEntity> bikes = restTemplate.getForObject("http://localhost:8003/api/bike/byuser/"+id,List.class);

        return bikes;
    }
}
