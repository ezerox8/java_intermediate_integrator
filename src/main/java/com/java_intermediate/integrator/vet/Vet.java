package com.java_intermediate.integrator.vet;

import com.java_intermediate.integrator.doctor.Doctor;
import com.java_intermediate.integrator.pet.Pet;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String address;


    @OneToMany
    @JoinColumn(name ="doctors", referencedColumnName ="id")
    private List<Doctor> doctors;


    @OneToMany
    @JoinColumn(name ="pets", referencedColumnName ="id")
    private List<Pet> pets;

    private boolean status;

    public Vet() {
    }

    public Vet(boolean status) {
        this.status = status;
    }

    public Vet(String address, List<Doctor> doctors, List<Pet> pets, boolean status) {
        this.address = address;
        this.doctors = doctors;
        this.pets = pets;
        this.status = status;
    }

    public Vet(Long id, String address, List<Doctor> doctors, List<Pet> pets, boolean status) {
        this.id = id;
        this.address = address;
        this.doctors = doctors;
        this.pets = pets;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
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
