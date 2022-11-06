package com.musalasoft.dronemanager.repository;

import com.musalasoft.dronemanager.entities.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DroneRepository extends CrudRepository<Drone, String> {

    @Query(value = "SELECT * FROM drone d WHERE d.state = ?1", nativeQuery = true)
    List<Drone> findAllDronesByState(int state);

    @Query(value = "SELECT * FROM drone d WHERE d.state = ?1 AND d.capacity > ?2", nativeQuery = true)
    List<Drone> findAllAvailableDrones(int state, int battery);
}
