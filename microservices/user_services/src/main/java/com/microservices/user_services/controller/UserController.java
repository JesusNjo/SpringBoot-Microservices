package com.microservices.user_services.controller;
import com.microservices.user_services.models.dtos.BikeEntity;
import com.microservices.user_services.models.dtos.CarEntity;
import com.microservices.user_services.models.entities.UserEntity;
import com.microservices.user_services.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    UserServices us;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> users = us.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id){
        UserEntity user = us.getForId(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(user);
        }

    }

    @PostMapping
    public ResponseEntity<UserEntity> createNewUser(@RequestBody UserEntity user){
        us.createUser(user);
        return ResponseEntity.ok(user);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> modifyUser(@RequestBody UserEntity user, @PathVariable("id") Long id){

        UserEntity u = us.getForId(id);
        if(u == null){
            return ResponseEntity.notFound().build();
        }else{
            user.setId(id);
            us.modifyUser(user);
            return ResponseEntity.ok(user);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable("id") Long id){


        UserEntity u = us.getForId(id);

        if(u == null){
            return ResponseEntity.notFound().build();
        }else{
            us.deleteUser(u.getId());
            return ResponseEntity.ok(u);
        }
    }

    @GetMapping("car/{id}")
    public ResponseEntity<List<CarEntity>> getCars(@PathVariable("id") Long id){
        UserEntity user = us.getForId(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }else{
            List<CarEntity> cars = us.getCars(id);
            return ResponseEntity.ok(cars);
        }
    }

    @GetMapping("bike/{id}")
    public ResponseEntity<List<BikeEntity>> getBikes(@PathVariable("id") Long id){
        UserEntity user = us.getForId(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }else{
            List<BikeEntity> bikes = us.getBikes(id);
            return ResponseEntity.ok(bikes);
        }
    }
}
