package com.musalasoft.dronemanager.services;

import com.musalasoft.dronemanager.entities.Medication;
import com.musalasoft.dronemanager.repository.DeliveryRepository;
import com.musalasoft.dronemanager.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DeliveryService {
    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    public DeliveryService() {
    }

    public List<Medication> getLoadedMedicationByDrone(String serialNo) {
        return deliveryRepository.findMedicationsByDroneId(serialNo);
    }
}
