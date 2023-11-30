package com.java_intermediate.integrator.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // es lo mismo que hacer esto:
    //@Query("SELECT * FROM Pet e WHERE e.fullName = ?1");
    Optional<Pet> findProductByFullName(String fullName);
}
