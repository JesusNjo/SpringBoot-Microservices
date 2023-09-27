package com.microservices.car_services.respository;

import com.microservices.car_services.models.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {

    List<CarEntity> findByUserId(Long id);
}
