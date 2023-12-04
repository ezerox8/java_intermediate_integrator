package com.java_intermediate.integrator.vet;

import com.java_intermediate.integrator.doctor.Doctor;
import com.java_intermediate.integrator.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vets")
@Controller
public class VetController {
    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService){ this.vetService = vetService; }

    @GetMapping
    public ResponseEntity<Object> getVets(){ return this.vetService.getVets(); };

    @GetMapping(path = "{vetAddress}")
    public ResponseEntity<Object> getVet(@PathVariable("vetAddress") String vetAddress) {
        return this.vetService.getVet(vetAddress);
    };

    @PostMapping
    public ResponseEntity<Object> registryVet(@RequestBody Vet vet){ return this.vetService.newVet(vet); }

    @PutMapping
    public ResponseEntity<Object> updateVet(@RequestBody Vet vet){ return this.vetService.newVet(vet); }

    @PutMapping(path = "{vetId}")
    public ResponseEntity<Object> deleteVet(@PathVariable("vetId") Long vetId){
        return this.vetService.deleteVet(vetId);
    }

    @PutMapping(path = "doctors/{vetId}?{doctorsList}")
    public ResponseEntity<Object> addVetDoctors(@PathVariable("vetId") Long vetId, @PathVariable("doctorsList") List<Doctor> doctorsList){
        return this.vetService.addDoctors(vetId, doctorsList);
    }

    @PutMapping(path = "pet/{vetId}?{pet}")
    public ResponseEntity<Object> addVetPet(@PathVariable("vetId") Long vetId, @PathVariable("pet") Pet pet){
        return this.vetService.addPet(vetId, pet);
    }
}
