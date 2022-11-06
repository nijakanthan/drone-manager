package com.musalasoft.dronemanager.services;

import com.musalasoft.dronemanager.entities.Delivery;
import com.musalasoft.dronemanager.entities.Medication;
import com.musalasoft.dronemanager.repository.DeliveryRepository;
import com.musalasoft.dronemanager.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DeliveryService {
    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    public DeliveryService() {
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> findByDrone(String drone) {
        return deliveryRepository.findItemsByDrone(drone);
    }

    public int getMedicationsToTalWeight(Set<Medication> medications) {
        int weight = medications.stream().mapToInt(Medication::getWeight).sum();
        return weight;
    }
}
