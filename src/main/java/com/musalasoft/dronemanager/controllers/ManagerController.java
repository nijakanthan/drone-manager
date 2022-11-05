package com.musalasoft.dronemanager.controllers;

import com.musalasoft.dronemanager.entities.Drone;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerController {

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public Drone registerDrone(@RequestBody Drone drone) {
        return drone;
    }

    @RequestMapping(value = "/battery/{drone}", method = RequestMethod.GET, produces = "application/json")
    public String getBatteryLevel(@PathVariable String drone) {
        return String.format("Battery level of Drone %s", drone);
    }
}
