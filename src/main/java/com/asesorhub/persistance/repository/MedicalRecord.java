package com.asesorhub.persistance.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {

    private Long recordId;
    private LocalDate date;
    private String description;
    private String treatment;
    private LocalDate nextAppointment;
}
