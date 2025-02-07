package com.asesorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponseDto {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
