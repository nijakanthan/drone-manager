package com.musalasoft.dronemanager.entities;

import com.musalasoft.dronemanager.entities.enums.DroneModel;
import com.musalasoft.dronemanager.entities.enums.DroneState;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @Column(name = "serial_no", length = 100, nullable = false)
    private String serialNo;

    @Column(name = "model")
    @Enumerated(EnumType.ORDINAL)
    private DroneModel model;

    @Column(name = "weight_limit")
    private int weightLimit;

    @Column(name = "capacity")
    private int batteryCapacity;

    @Column(name = "state")
    @Enumerated(EnumType.ORDINAL)
    private DroneState state;

    public Drone() {
    }

    public Drone(String serialNo, DroneModel model, int weightLimit, int batteryCapacity, DroneState state) {
        this.serialNo = serialNo;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}
