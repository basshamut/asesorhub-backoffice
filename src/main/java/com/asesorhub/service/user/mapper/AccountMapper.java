package com.asesorhub.service.user.mapper;

import com.asesorhub.dto.AccountRequestDto;
import com.asesorhub.dto.AccountResponseDto;
import com.asesorhub.persistance.repository.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", imports = {Account.class, AccountResponseDto.class})
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    // Formato de entrada (para interpretar "10/2/25, 10:28")
    DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yy, H:mm");

    // Formato de salida (para devolver "10/02/2025 10:28:00")
    DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "username", source = "email")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "isAdvisor", source = "isAdvisor")
    @Mapping(target = "isAdvisee", source = "isAdvisee")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "stringToFormattedString")
    @Mapping(target = "updatedAt", source = "updatedAt", qualifiedByName = "stringToFormattedString")
    AccountResponseDto mapToDto(Account entity);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "username")
    Account mapToEntity(AccountRequestDto accountRequestDto);

    @Named("stringToFormattedString")
    default String stringToFormattedString(String dateString) {
        try {
            // Convertir el string de entrada en LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateString, INPUT_FORMATTER);

            // Formatear al formato de salida deseado
            return dateTime.format(OUTPUT_FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear la fecha: " + dateString, e);
        }
    }
}
