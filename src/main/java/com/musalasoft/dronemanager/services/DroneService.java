package com.musalasoft.dronemanager.services;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.musalasoft.dronemanager.entities.Drone;
import com.musalasoft.dronemanager.repository.DroneRepository;

public class DroneService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DroneRepository droneRepository;

    public DroneService() {
    }

    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public Optional<Drone> getDrone(String serialNo) {
        return droneRepository.findById(serialNo);
    }

    public void updateDrone(Drone drone) {
        droneRepository.save(drone);
    }

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public List<Drone> getAvailableDrones() {
        return droneRepository.findAllIdleDrones();
    }

}
