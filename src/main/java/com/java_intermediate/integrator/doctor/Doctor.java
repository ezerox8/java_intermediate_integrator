package com.java_intermediate.integrator.doctor;

import com.java_intermediate.integrator.pet.Pet;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private List<Pet> pets;

    private boolean status;

    public Doctor() {
    }

    public Doctor(boolean status) {
        this.status = status;
    }

    public Doctor(String name, List<Pet> pets, boolean status) {
        this.name = name;
        this.pets = pets;
        this.status = status;
    }

    public Doctor(Long id, String name, List<Pet> pets, boolean status) {
        this.id = id;
        this.name = name;
        this.pets = pets;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
