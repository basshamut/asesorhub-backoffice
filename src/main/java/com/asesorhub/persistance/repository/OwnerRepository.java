package com.asesorhub.persistance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
}

