package com.musalasoft.dronemanager.controllers;

import com.musalasoft.dronemanager.entities.Drone;
import com.musalasoft.dronemanager.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/manager")
public class ManagerController {

    @Autowired
    private DroneService droneService;

    @RequestMapping(value = "/drone/register", method = RequestMethod.POST, produces = "application/json")
    public Drone registerDrone(@RequestBody Drone drone) {
        return droneService.registerDrone(drone);
    }

    @RequestMapping(value = "/battery/{drone}", method = RequestMethod.GET, produces = "application/json")
    public String getBatteryLevel(@PathVariable String drone) {
        return String.format("Battery level of Drone %s", drone);
    }
}
