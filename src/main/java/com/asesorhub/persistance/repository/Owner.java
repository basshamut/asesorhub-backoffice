package com.asesorhub.persistance.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Owner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
}

