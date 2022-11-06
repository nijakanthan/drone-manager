package com.musalasoft.dronemanager.controllers;

import com.musalasoft.dronemanager.entities.Delivery;
import com.musalasoft.dronemanager.entities.Drone;
import com.musalasoft.dronemanager.entities.ManagerResponse;
import com.musalasoft.dronemanager.entities.Medication;
import com.musalasoft.dronemanager.entities.enums.DeliveryState;
import com.musalasoft.dronemanager.entities.enums.DroneState;
import com.musalasoft.dronemanager.repository.MedicationRepository;
import com.musalasoft.dronemanager.services.DeliveryService;
import com.musalasoft.dronemanager.services.DroneService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/manager")
public class ManagerController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private MedicationRepository medicationRepository;

    @RequestMapping(value = "/drone/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ManagerResponse> registerDrone(@RequestBody Drone drone) {
        ManagerResponse response = new ManagerResponse();
        try {
            Drone registeredDrone = droneService.registerDrone(drone);
            response.setSuccess(true);
            response.setData(registeredDrone);
            response.setMessage("New drone details registered successfully");
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.OK);
        } catch (Exception exp) {
            response.setSuccess(false);
            response.setMessage("Error while registering drone details: " + exp.getMessage());
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/drone/load", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ManagerResponse> loadDrone(@RequestBody Delivery delivery) {
        ManagerResponse response = new ManagerResponse();
        try {
            Optional<Drone> selectedDrone = droneService.getDroneBySerialNo(delivery.getDrone());
            Drone drone = selectedDrone.get();
            if (drone.getState() == DroneState.IDLE & drone.getBatteryCapacity() > 25) {
                if (deliveryService.getMedicationsToTalWeight(delivery.getMedications()) > drone.getWeightLimit()) {
                    response.setSuccess(false);
                    response.setMessage("Medications weight is more the drone weight limit");
                    return new ResponseEntity<ManagerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                droneService.updateDroneState(drone.getSerialNo(), DroneState.LOADING);
                Set<Medication> newMedications = new HashSet<Medication>();
                delivery.getMedications().forEach(medication -> {
                    newMedications.add(medicationRepository.save(medication));
                });
                delivery.setState(DeliveryState.IN_PROGRESS);
                delivery.setMedications(newMedications);
                Delivery newDelivery = deliveryService.createDelivery(delivery);
                droneService.updateDroneState(drone.getSerialNo(), DroneState.LOADED);
                response.setSuccess(true);
                response.setData(newDelivery);
                response.setMessage("Successfully medications loaded into the drone");
            } else {
                response.setSuccess(false);
                response.setMessage("Given drone is not available at the moment.");
            }
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.OK);
        } catch (Exception exp) {
            response.setSuccess(false);
            response.setMessage("Error while loading medications into drone: " + exp.getMessage());
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/drone/check/{drone}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ManagerResponse> checkDroneMedications(@PathVariable String drone) {
        ManagerResponse response = new ManagerResponse();
        try {
            List<Delivery> deliveries = deliveryService.findByDrone(drone);
            if (deliveries.size() != 0) {
                response.setSuccess(true);
                response.setData(deliveries.get(0).getMedications());
                response.setMessage("Successfully fetched drone medication details.");
            } else {
                response.setSuccess(false);
                response.setMessage("Given drone does not have any medications");
            }
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.OK);
        } catch (Exception exp) {
            response.setSuccess(false);
            response.setMessage("Error while checking drone medication details: " + exp.getMessage());
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/drone/available", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ManagerResponse> getAvailableDrones() {
        ManagerResponse response = new ManagerResponse();
        try {
            DroneState idleState = DroneState.IDLE;
            List<Drone> availableDrones = droneService.getAvailableDrones(idleState.getValue(), 25);
            response.setSuccess(true);
            response.setData(availableDrones);
            response.setMessage("Successfully fetched available drone details.");
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.OK);
        } catch (Exception exp) {
            response.setSuccess(false);
            response.setMessage("Error while getting available drone details: " + exp.getMessage());
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/drone/battery/{drone}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ManagerResponse> getBatteryLevel(@PathVariable String drone) {
        ManagerResponse response = new ManagerResponse();
        try {
            Optional<Drone> selectedDrone = droneService.getDroneBySerialNo(drone);
            int batteryLevel = selectedDrone.get().getBatteryCapacity();
            JSONObject responseData = new JSONObject();
            responseData.put("level", batteryLevel);
            response.setSuccess(true);
            response.setData(responseData);
            response.setMessage("Successfully fetched drone battery level");
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.OK);
        } catch (Exception exp) {
            response.setSuccess(false);
            response.setMessage("Error while getting drone battery level: " + exp.getMessage());
            return new ResponseEntity<ManagerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
