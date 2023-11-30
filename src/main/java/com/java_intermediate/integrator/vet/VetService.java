package com.java_intermediate.integrator.vet;

import com.java_intermediate.integrator.doctor.Doctor;
import com.java_intermediate.integrator.pet.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VetService {

    private final VetRepository vetRepository;
    HashMap<String,Object> map;

    private VetService(VetRepository vetRepository){ this.vetRepository = vetRepository; }

    public ResponseEntity<Object> getVets(){
        List<Vet> vetList = this.vetRepository.findAll();
        this.map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "Veterinaria encontrada");
        map.put("response", vetList);
        return new ResponseEntity<>(
                map,
                HttpStatus.FOUND
        );
    }

    public ResponseEntity<Object> getVet(String address){
       Vet vet = this.vetRepository.findVetByAddress(address)
               .orElse(null);
       this.map = new HashMap<>();
       if (vet != null){
           map.put("status", 200);
           map.put("message", "Veterinaria encontrada");
           map.put("response", vet);
           return new ResponseEntity<>(
                   map,
                   HttpStatus.FOUND
           );
       } else {
           map.put("status", 404);
           map.put("message", "Veterinaria no encontrada");
           map.put("response", null);
           return new ResponseEntity<>(
                   map,
                   HttpStatus.NOT_FOUND
           );
       }
    }

    public ResponseEntity<Object> newVet(Vet vet){
        Optional<Vet> response = this.vetRepository.findVetByAddress(vet.getAddress());
        this.map = new HashMap<>();
        if (response.isPresent() && vet.getId() != null){
            String message = vet.getId() != null? "Veterinaria creada exitosamente": "Veterinaria modificada exitosamente";
            HttpStatus status = vet.getId() != null? HttpStatus.CREATED: HttpStatus.ACCEPTED;
            map.put("status", 200);
            map.put("message", message);
            map.put("response", vet);
            vetRepository.save(vet);
            return new ResponseEntity<>(
                    map,
                    status
            );
        } else {
            map.put("status", 405);
            map.put("message", "No se puede crear la veterinaria porque ya existe");
            map.put("response", vet);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

    public ResponseEntity<Object> deleteVet(Long vetId) {
        this.map = new HashMap<>();
        boolean exist = this.vetRepository.existsById(vetId);

        if (!exist){
            map.put("status", 404);
            map.put("message", "No existe una veterinaria con ese id");
            map.put("response", vetId);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.CONFLICT
            );
        } else {
            Vet vet = vetRepository.findById(vetId)
                    .orElse(new Vet(false));
            if (vet.isStatus()){
                map.put("status", 200);
                map.put("message", "Veterinaria dada de baja exitosamente");
                map.put("response", vet);
                vet.setStatus(false);
                vetRepository.save(vet);
                return new ResponseEntity<>(
                        map,
                        HttpStatus.OK
                );
            }
            map.put("status", 406);
            map.put("message", "La veterinaria ya se encontraba dada de baja previamente");
            map.put("response", vet);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

    public ResponseEntity<Object> addDoctors(Long vetId, List<Doctor> doctorsList) {
        this.map = new HashMap<>();
        Vet vet = vetRepository.findById(vetId)
                .orElse(new Vet(false));
        List<Doctor> doctors = vet.getDoctors();
        doctorsList.forEach((Doctor doctor) -> {
            if(!doctors.contains(doctor)){
                doctors.add(doctor);
            };
        });
        vet.setDoctors(doctors);
        vetRepository.save(vet);
        map.put("status", 200);
        map.put("message", "Veterinarios agregados exitosamente");
        map.put("response", doctors);
        return new ResponseEntity<>(
                map,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> addPet(Long vetId, Pet pet) {
        this.map = new HashMap<>();
        Vet vet = vetRepository.findById(vetId)
                .orElse(new Vet(false));
        List<Pet> pets = vet.getPets();
        if(!pets.contains(pet)){
            pets.add(pet);
        };
        vet.setPets(pets);
        vetRepository.save(vet);
        map.put("status", 200);
        map.put("message", "Mascota agregada exitosamente");
        map.put("response", pet);
        return new ResponseEntity<>(
                map,
                HttpStatus.OK
        );
    }
}
