package com.asesorhub.persistance.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccination {

    private LocalDate date;
    private String vaccine;
    private String description;
    private LocalDate nextVaccination;
}
