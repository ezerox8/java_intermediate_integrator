package com.java_intermediate.integrator.pet;

import jakarta.persistence.*;

@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String breed;

    protected int age;

    protected String symptoms;

    protected boolean firstConsultation;

    @Column(unique = true)
    protected String fullName;

    protected String vet;

    protected String doctor;

    public Pet() {
    }

    public Pet(String breed, int age, String symptoms, boolean firstConsultation, String fullName, String vet, String doctor) {
        this.breed = breed;
        this.age = age;
        this.symptoms = symptoms;
        this.firstConsultation = firstConsultation;
        this.fullName = fullName;
        this.vet = vet;
        this.doctor = doctor;
    }

    public Pet(Long id, String breed, int age, String symptoms, boolean firstConsultation, String fullName, String vet, String doctor) {
        this.id = id;
        this.breed = breed;
        this.age = age;
        this.symptoms = symptoms;
        this.firstConsultation = firstConsultation;
        this.fullName = fullName;
        this.vet = vet;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public boolean isFirstConsultation() {
        return firstConsultation;
    }

    public void setFirstConsultation(boolean firstConsultation) {
        this.firstConsultation = firstConsultation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getVet() {
        return vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
