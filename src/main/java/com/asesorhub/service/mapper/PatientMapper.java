package com.asesorhub.service.mapper;

import com.asesorhub.dto.AppointmentDto;
import com.asesorhub.dto.MedicalRecordDto;
import com.asesorhub.dto.OwnerDTO;
import com.asesorhub.dto.PatientDto;
import com.asesorhub.dto.VaccinationDto;
import com.asesorhub.persistance.repository.Appointment;
import com.asesorhub.persistance.repository.MedicalRecord;
import com.asesorhub.persistance.repository.Owner;
import com.asesorhub.persistance.repository.Patient;
import com.asesorhub.persistance.repository.Vaccination;
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
