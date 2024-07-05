package com.vetsmart.service;

import com.vetsmart.dto.OwnerRequestDto;
import com.vetsmart.dto.OwnerResponseDto;
import com.vetsmart.repository.Owner;
import com.vetsmart.repository.OwnerRepository;
import com.vetsmart.service.mapper.OwnerMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Page<OwnerResponseDto> getAllOwners(Pageable pageable) {
        var list = ownerRepository.findAll(pageable).map(OwnerMapper.MAPPER::mapToDto);
        var countConstruction = ownerRepository.count();
        return new PageImpl<>(list.getContent(), pageable, countConstruction);
    }

    public List<OwnerResponseDto> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(OwnerMapper.MAPPER::mapToDto)
                .collect(Collectors.toList());
    }

    public OwnerResponseDto getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .map(OwnerMapper.MAPPER::mapToDto)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    public OwnerResponseDto createOwner(OwnerRequestDto owner) {
        Owner savedOwner = ownerRepository.save(OwnerMapper.MAPPER.mapToEntity(owner));
        return OwnerMapper.MAPPER.mapToDto(savedOwner);
    }

    public Optional<OwnerResponseDto> updateOwner(Long ownerId, Owner ownerDetails) {
        return ownerRepository.findById(ownerId).map(owner -> {
            owner.setName(ownerDetails.getName());
            owner.setPhone(ownerDetails.getPhone());
            owner.setEmail(ownerDetails.getEmail());
            owner.setAddress(ownerDetails.getAddress());
            Owner updatedOwner = ownerRepository.save(owner);
            return OwnerMapper.MAPPER.mapToDto(updatedOwner);
        });
    }

    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }
}
