package com.Mymicroservice.products_services.repositories;

import com.Mymicroservice.products_services.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
