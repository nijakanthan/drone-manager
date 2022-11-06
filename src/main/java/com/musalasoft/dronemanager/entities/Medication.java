package com.musalasoft.dronemanager.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @Column(name = "medication_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Column(name = "code")
    private String code;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToMany(mappedBy = "medications")
    private Set<Delivery> deliveries;

    public Medication() {
    }

    public Medication(String name, int weight, String code, byte[] image, Set<Delivery> deliveries) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.deliveries = deliveries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
