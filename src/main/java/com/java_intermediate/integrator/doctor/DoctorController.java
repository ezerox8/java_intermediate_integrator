package com.java_intermediate.integrator.doctor;

import com.java_intermediate.integrator.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/doctors")
@Controller

public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){ this.doctorService = doctorService; }

    @GetMapping
    public ResponseEntity<Object> getVets(){ return this.doctorService.getDoctors(); };

    @GetMapping(path = "{doctorName}")
    public ResponseEntity<Object> getDoctor(@PathVariable("doctorName") String doctorName) {
        return this.doctorService.getDoctor(doctorName);
    };

    @PostMapping
    public ResponseEntity<Object> registryDoctor(@RequestBody Doctor doctor){ return this.doctorService.newDoctor(doctor); }

    @PutMapping
    public ResponseEntity<Object> updateVet(@RequestBody Doctor doctor){ return this.doctorService.newDoctor(doctor); }

    @PutMapping(path = "{doctorId}")
    public ResponseEntity<Object> deleteVet(@PathVariable("doctorId") Long doctorId){
        return this.doctorService.deleteDoctor(doctorId);
    }

    @PutMapping(path = "pet/{doctorId}?{pet}")
    public ResponseEntity<Object> addDoctorPet(@PathVariable("doctorId") Long doctorId, @PathVariable("pet") Pet pet){
        return this.doctorService.addPet(doctorId, pet);
    }
}
