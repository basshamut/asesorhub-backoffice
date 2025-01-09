package com.asesorhub.persistance.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    private String id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private Owner owner;
    private Set<MedicalRecord> medicalRecords;
    private Set<Vaccination> vaccinations;
    private Set<Appointment> appointments;
}

