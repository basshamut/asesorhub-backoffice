package com.asesorhub.dto;

import com.asesorhub.utils.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AccountRequestDto {

    private String name;
    private String phone;
    private String username;
    private String password;
    private Boolean isActive;
}
