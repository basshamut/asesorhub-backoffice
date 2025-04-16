package com.asesorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AccountResponseDto {

    private String id;
    private String name;
    private String phone;
    private String username;
    private String role;
    private Boolean isAdvisor;
    private Boolean isAdvisee;
    private Boolean isActive;
    private String createdAt;
    private String updatedAt;

}
