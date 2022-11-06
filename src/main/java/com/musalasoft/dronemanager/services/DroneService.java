package com.musalasoft.dronemanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.musalasoft.dronemanager.entities.enums.DroneState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.musalasoft.dronemanager.entities.Drone;
import com.musalasoft.dronemanager.repository.DroneRepository;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DroneRepository droneRepository;

    public DroneService() {
    }

    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public Optional<Drone> getDroneBySerialNo(String serialNo) {
        return droneRepository.findById(serialNo);
    }

    public List<Drone> getAllDronesByStatus(int state) {
        return droneRepository.findAllDronesByState(state);
    }

    public List<Drone> getAvailableDrones(int state, int battery) {
        return droneRepository.findAllAvailableDrones(state, battery);
    }

    public void updateDroneState(String serialNo, DroneState state) {
        Optional<Drone> drone = droneRepository.findById(serialNo);
        drone.get().setState(state);
        droneRepository.save(drone.get());
    }

}
