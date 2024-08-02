package com.vetsmart.service.mapper;

import com.vetsmart.dto.AppointmentDto;
import com.vetsmart.dto.MedicalRecordDto;
import com.vetsmart.dto.OwnerDTO;
import com.vetsmart.dto.PatientDto;
import com.vetsmart.dto.VaccinationDto;
import com.vetsmart.persistance.repository.Appointment;
import com.vetsmart.persistance.repository.MedicalRecord;
import com.vetsmart.persistance.repository.Owner;
import com.vetsmart.persistance.repository.Patient;
import com.vetsmart.persistance.repository.Vaccination;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper MAPPER = Mappers.getMapper(PatientMapper.class);

    PatientDto toPatientDTO(Patient patient);

    Patient toPatient(PatientDto patientDTO);

    OwnerDTO toOwnerDTO(Owner owner);

    Owner toOwner(OwnerDTO ownerDTO);

    MedicalRecordDto toMedicalRecordDTO(MedicalRecord medicalRecord);

    MedicalRecord toMedicalRecord(MedicalRecordDto medicalRecordDTO);

    VaccinationDto toVaccinationDTO(Vaccination vaccination);

    Vaccination toVaccination(VaccinationDto vaccinationDTO);

    AppointmentDto toAppointmentDTO(Appointment appointment);

    Appointment toAppointment(AppointmentDto appointmentDTO);
}
