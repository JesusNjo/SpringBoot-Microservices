package com.microservices.bike_services.repository;


import com.microservices.bike_services.models.entities.BikeEntity;

import java.util.List;

public interface BikeRepository {

    public List getAllBikes();

    public BikeEntity getById(Long id);

    public void createBike(BikeEntity bike);

    public void modifyBike(BikeEntity bike);

    public void deleteBike(Long id);

    public List<BikeEntity> findByUserId(Long id);


}
