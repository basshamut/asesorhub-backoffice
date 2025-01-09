package com.asesorhub.controller;

import com.asesorhub.dto.OwnerRequestDto;
import com.asesorhub.dto.PatientDto;
import com.asesorhub.dto.PatientResponseDto;
import com.asesorhub.exception.ServiceException;
import com.asesorhub.persistance.repository.Owner;
import com.asesorhub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Page<PatientDto>> getPatients(@RequestParam int page, @RequestParam int size) {
        if (!isValidPagination(page, size)) {
            throw new ServiceException("Invalid pagination parameters", HttpStatus.BAD_REQUEST.value());
        }

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PatientDto> owners = patientService.getPatients(pageable);
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable String id) {
        try {
            PatientDto owner = patientService.getPatientById(id);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* TODO
    *    Refactorizar usando /api/patients?ownerId={ownerId}. Lo ideal es meter el filtro en getPatients
    *  */
    @GetMapping("/owners/{email}")
    public ResponseEntity<Set<PatientDto>> findByEmail(@PathVariable String email) {
        try {
            var owner = patientService.getPatientByEmail(email);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PatientDto> createOwner(@RequestBody PatientDto dto) {
        PatientDto created = patientService.createPatient(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updateOwner(@PathVariable String id, @RequestBody PatientDto dto) {
        Optional<PatientDto> updated = patientService.updatePatient(id, dto);
        return updated.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private static boolean isValidPagination(int page, int size) {
        return page >= 0 && size > 0;
    }
}
