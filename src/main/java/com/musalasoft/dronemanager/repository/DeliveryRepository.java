package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.entities.Delivery;
import com.musalasoft.dronemanager.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query(value = "SELECT dm.medication_id FROM delivery_medications dm WHERE dm.delivery_id = (SELECT d.delivery_id from delivery d WHERE d.drone_id = ?1 ORDER BY d.delivery_id DESC LIMIT 1)", nativeQuery = true)
    List<Medication> findMedicationsByDroneId(String serialNo);
}
