package com.vetsmart.persistance.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    private String name;
    private String phone;
    private String email;
    private String address;
}

