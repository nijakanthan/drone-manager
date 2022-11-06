package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.entities.Delivery;
import com.musalasoft.dronemanager.entities.Medication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    List<Delivery> findItemsByDrone(String drone);
}
