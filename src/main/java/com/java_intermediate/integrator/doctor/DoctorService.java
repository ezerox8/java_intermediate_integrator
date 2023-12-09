package com.java_intermediate.integrator.doctor;

import com.java_intermediate.integrator.pet.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service

public class DoctorService {

    private final DoctorRepository doctorRepository;
    HashMap<String,Object> map;

    private DoctorService(DoctorRepository doctorRepository){ this.doctorRepository = doctorRepository; }

    public ResponseEntity<Object> getDoctors(){
        List<Doctor> doctorList = this.doctorRepository.findAll();
        this.map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "doctores encontrados");
        map.put("response", doctorList);
        return new ResponseEntity<>(
                map,
                HttpStatus.FOUND
        );
    }

    public ResponseEntity<Object> getDoctor(String name){
        Doctor doctor = this.doctorRepository.findVetByName(name)
                .orElse(null);
        this.map = new HashMap<>();
        if (doctor != null){
            map.put("status", 200);
            map.put("message", "Doctor encontrado");
            map.put("response", doctor);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.FOUND
            );
        } else {
            map.put("status", 404);
            map.put("message", "Doctor no encontrado");
            map.put("response", null);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    public ResponseEntity<Object> newDoctor(Doctor doctor){
        Optional<Doctor> response = this.doctorRepository.findVetByName(doctor.getName());
        this.map = new HashMap<>();
        if (response.isPresent() && doctor.getId() == null){
            String message = doctor.getId() == null? "Doctor dado de alta exitosamente": "Datos del doctor modificados exitosamente";
            HttpStatus status = doctor.getId() == null? HttpStatus.CREATED: HttpStatus.ACCEPTED;
            map.put("status", 200);
            map.put("message", message);
            map.put("response", doctor);
            doctorRepository.save(doctor);
            return new ResponseEntity<>(
                    map,
                    status
            );
        } else {
            map.put("status", 405);
            map.put("message", "No se puede crear el doctor porque ya existe");
            map.put("response", doctor);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

    public ResponseEntity<Object> deleteDoctor(Long doctorId) {
        this.map = new HashMap<>();
        boolean exist = this.doctorRepository.existsById(doctorId);

        if (!exist){
            map.put("status", 404);
            map.put("message", "No existe un doctor con ese id");
            map.put("response", doctorId);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.CONFLICT
            );
        } else {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElse(new Doctor(false));
            if (doctor.isStatus()){
                map.put("status", 200);
                map.put("message", "Doctor dado de baja exitosamente");
                map.put("response", doctor);
                doctor.setStatus(false);
                doctorRepository.save(doctor);
                return new ResponseEntity<>(
                        map,
                        HttpStatus.OK
                );
            }
            map.put("status", 406);
            map.put("message", "El doctor ya se encontraba dada de baja previamente");
            map.put("response", doctor);
            return new ResponseEntity<>(
                    map,
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
    }

    public ResponseEntity<Object> addPet(Long doctorId, Pet pet) {
        this.map = new HashMap<>();
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElse(new Doctor(false));
        List<Pet> pets = doctor.getPets();
        if(!pets.contains(pet)){
            pets.add(pet);
        };
        doctor.setPets(pets);
        doctorRepository.save(doctor);
        map.put("status", 200);
        map.put("message", "Mascota agregada exitosamente");
        map.put("response", pet);
        return new ResponseEntity<>(
                map,
                HttpStatus.OK
        );
    }

}
