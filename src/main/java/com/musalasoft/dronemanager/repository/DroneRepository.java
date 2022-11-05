package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, String> {

    @Query(value = "SELECT * FROM drone d WHERE d.state = 'IDLE' and d.capacity > 25", nativeQuery = true)
    List<Drone> findAllIdleDrones();
}
