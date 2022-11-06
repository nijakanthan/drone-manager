package com.musalasoft.dronemanager.entities.enums;

public enum DroneState {
    IDLE(0),
    LOADING(1),
    LOADED(2),
    DELIVERING(3),
    DELIVERED(4),
    RETURNING(5);

    private final int value;

    private DroneState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
