package com.musalasoft.dronemanager.entities;

import com.musalasoft.dronemanager.entities.enums.DeliveryState;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @Column(name = "delivery_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "serial_no")
    private String drone;

    @Column(name = "state")
    private DeliveryState state;

    @ManyToMany
    @JoinTable(
            name = "delivery_medications",
            joinColumns = @JoinColumn(name = "delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"))
    Set<Medication> medications;

    public Delivery() {
    }

    public Delivery(Long id, String drone, DeliveryState state, Set<Medication> medications) {
        this.id = id;
        this.drone = drone;
        this.state = state;
        this.medications = medications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public DeliveryState getState() {
        return state;
    }

    public void setState(DeliveryState state) {
        this.state = state;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }
}
