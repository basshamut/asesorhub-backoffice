package com.asesorhub.service;

import com.asesorhub.dto.PatientDto;
import com.asesorhub.exception.ServiceException;
import com.asesorhub.persistance.repository.Patient;
import com.asesorhub.persistance.repository.PatientRepository;
import com.asesorhub.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Page<PatientDto> getPatients(Pageable pageable) {
        var list = patientRepository.findAll(pageable).map(PatientMapper.MAPPER::toPatientDTO);
        var countConstruction = patientRepository.count();
        return new PageImpl<>(list.getContent(), pageable, countConstruction);
    }

    public PatientDto getPatientById(String ownerId) {
        return patientRepository.findById(ownerId)
                .map(PatientMapper.MAPPER::toPatientDTO)
                .orElseThrow(() -> new ServiceException("Patient not found", 404));
    }

    public Set<PatientDto> getPatientByEmail(String email) {
        var patients = patientRepository.findPatientByOwnerEmail(email);
        if (patients.isEmpty()) {
            throw new ServiceException("Patient not found", 404);
        }
        return patients.stream()
                .map(PatientMapper.MAPPER::toPatientDTO)
                .collect(Collectors.toSet());
    }

    public PatientDto createPatient(PatientDto dto) {
        if (patientRepository.existsByOwnerEmailAndNameAndSpecies(dto.getOwner().getEmail(), dto.getName(), dto.getSpecies())) {
            throw new ServiceException("The animal is already registered to this owner.", 409);
        }

        Patient saved = patientRepository.save(PatientMapper.MAPPER.toPatient(dto));
        return PatientMapper.MAPPER.toPatientDTO(saved);
    }

    public Optional<PatientDto> updatePatient(String id, PatientDto patientDTO) {
        return patientRepository.findById(id).map(patient -> {
            patient.setName(patientDTO.getName());
            patient.setSpecies(patientDTO.getSpecies());
            patient.setBreed(patientDTO.getBreed());
            patient.setAge(patientDTO.getAge());
            patient.setOwner(PatientMapper.MAPPER.toOwner(patientDTO.getOwner()));
            patient.setMedicalRecords(patientDTO.getMedicalRecords().stream()
                    .map(PatientMapper.MAPPER::toMedicalRecord)
                    .collect(Collectors.toSet()));
            patient.setVaccinations(patientDTO.getVaccinations().stream()
                    .map(PatientMapper.MAPPER::toVaccination)
                    .collect(Collectors.toSet()));
            patient.setAppointments(patientDTO.getAppointments().stream()
                    .map(PatientMapper.MAPPER::toAppointment)
                    .collect(Collectors.toSet()));

            Patient updated = patientRepository.save(patient);
            return PatientMapper.MAPPER.toPatientDTO(updated);
        });
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}
