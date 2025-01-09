package com.asesorhub.persistance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    Set<Patient> findPatientByOwnerEmail(String email);

    boolean existsByOwnerEmailAndNameAndSpecies(String email, String name, String species);
}

