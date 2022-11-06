package com.musalasoft.dronemanager.entities.enums;

public enum DroneModel {
    LIGHTWEIGHT(0),
    MIDDLEWEIGHT(1),
    CRUISERWEIGHT(2),
    HEAVYWEIGHT(3);

    private final int value;

    private DroneModel(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
