package com.microservices.bike_services.service;


import com.microservices.bike_services.models.entities.BikeEntity;
import com.microservices.bike_services.repository.BikeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BikePersistence implements BikeRepository {

    @PersistenceContext
    EntityManager em;


    @Override
    public List getAllBikes() {
        return em.createQuery("From BikeEntity").getResultList();
    }

    @Override
    public BikeEntity getById(Long id) {
        return em.find(BikeEntity.class,id);
    }

    @Override
    public void createBike(BikeEntity bike) {
        em.merge(bike);
    }

    @Override
    public void modifyBike(BikeEntity bike) {
    BikeEntity bike2 = em.find(BikeEntity.class,bike.getId());
    bike2.setBrand(bike.getBrand());
    bike2.setModel(bike.getModel());
    bike2.setUserId(bike.getUserId());
    em.persist(bike2);
    }

    @Override
    public void deleteBike(Long id) {
        BikeEntity bike = em.find(BikeEntity.class,id);
        em.remove(bike);
    }

    @Override
    public List<BikeEntity> findByUserId(Long id) {
        return em.createQuery("FROM BikeEntity WHERE userId = :userId").setParameter("userId",id)
                .getResultList();

    }
}
