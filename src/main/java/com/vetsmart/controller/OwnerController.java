package com.vetsmart.controller;

import com.vetsmart.dto.OwnerRequestDto;
import com.vetsmart.dto.OwnerResponseDto;
import com.vetsmart.repository.Owner;
import com.vetsmart.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<Page<OwnerResponseDto>> getAllOwners(Pageable pageable) {
        Page<OwnerResponseDto> owners = ownerService.getAllOwners(pageable);
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OwnerResponseDto>> getAllOwners() {
        List<OwnerResponseDto> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> findById(@PathVariable Long id) {
        try {
            OwnerResponseDto owner = ownerService.getOwnerById(id);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OwnerResponseDto> createOwner(@RequestBody OwnerRequestDto owner) {
        OwnerResponseDto createdOwner = ownerService.createOwner(owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable Long id, @RequestBody Owner ownerDetails) {
        Optional<OwnerResponseDto> updatedOwner = ownerService.updateOwner(id, ownerDetails);
        return updatedOwner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
