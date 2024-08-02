package com.vetsmart.persistance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Patient findByOwnerEmail(String email);

    boolean existsByOwnerEmailAndNameAndSpecies(String email, String name, String species);
}

