package com.Mymicroservice.inventory_services.utils;

import com.Mymicroservice.inventory_services.models.entities.Inventory;
import com.Mymicroservice.inventory_services.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data..");
        if(inventoryRepository.findAll().size()==0){
            inventoryRepository.saveAll(
                    List.of(
                            Inventory.builder().sku("00001").quantity(10L).build(),
                            Inventory.builder().sku("00002").quantity(20L).build(),
                            Inventory.builder().sku("00003").quantity(30L).build(),
                            Inventory.builder().sku("00004").quantity(0L).build()
                    )
            );
        }
        log.info("Data loaded");

    }
}
