package com.vetsmart.persistance.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private Long appointmentId;
    private Patient patient;
    private LocalDate date;
    private LocalTime time;
    private String veterinarian;
    private String reason;
    private String status;
}

