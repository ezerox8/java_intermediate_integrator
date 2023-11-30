package com.java_intermediate.integrator.pet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    HashMap<String, Object> map;

    @Autowired
    public PetService(PetRepository petRepository){
        this.petRepository = petRepository;
    }
    public List<Pet> getPets(){
        //Forma de retornar datos sin implementar el repositorio
//        return List.of(
//                new Pet(
//                        2541L,
//                        "Manuel",
//                        "Jara",
//                        LocalDate.of(2019, Month.MARCH, 1),
//                        2
//                )
//        );
        return this.petRepository.findAll();
    }

    public ResponseEntity<Object> newPet(Pet pet) {
        Optional<Pet> response = petRepository.findProductByFullName(pet.getFullName());
        this.map = new HashMap<>();

        if (response.isPresent() && pet.getId() == null){
            map.put("error", true);
            map.put("message", "Ya existe un empleado con ese nombre");
            return new ResponseEntity<>(
                    map,
                    HttpStatus.CONFLICT
            );
        } else {
            String message = pet.getId() != null? "Actualizacion exitosa": "Guardado con exito";
            map.put("message", message);
            map.put("data", pet);
            petRepository.save(pet);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.CREATED
            );
        }
    }

    public ResponseEntity<Object> deletePet(Long petId) {
        this.map = new HashMap<>();
        boolean exist = this.petRepository.existsById(petId);

        if (!exist){
            map.put("error", true);
            map.put("message", "No existe un empleado con ese id");
            return new ResponseEntity<>(
                    map,
                    HttpStatus.CONFLICT
            );
        } else {
            Pet pet = petRepository.findById(petId)
                    .orElse(new Pet());
            if (pet.isFirstConsultation()){
                map.put("message", "Empleado dado de baja");
                map.put("data", pet);
                pet.setFirstConsultation(false);
                petRepository.save(pet);
                return new ResponseEntity<>(
                        map,
                        HttpStatus.CREATED
                );
            }
            map.put("message", "el empleado ya se encuentra dado de baja");
            map.put("data", pet);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.CREATED
            );
        }
    }
}
