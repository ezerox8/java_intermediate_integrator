package com.java_intermediate.integrator.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="api/v1/pets")
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService){
        this.petService = petService;
    };

    @GetMapping
    public List<Pet> getPet(){
        return  this.petService.getPets();
    }

    @PostMapping
    public ResponseEntity<Object> registryPet(@RequestBody Pet pet){
        return this.petService.newPet(pet);
    }
    @PutMapping
    public ResponseEntity<Object> updatePet(@RequestBody Pet pet){
        return this.petService.newPet(pet);
    }
    @PutMapping(path = "{petId}")
    public ResponseEntity<Object> deletePet(@PathVariable("petId") Long petId){
        return this.petService.deletePet(petId);
    }
}
