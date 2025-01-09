package com.asesorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private String id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private OwnerDTO owner;
    private Set<MedicalRecordDto> medicalRecords;
    private Set<VaccinationDto> vaccinations;
    private Set<AppointmentDto> appointments;
}
