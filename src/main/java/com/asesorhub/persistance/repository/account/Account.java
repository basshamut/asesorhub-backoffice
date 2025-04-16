package com.asesorhub.persistance.repository.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String role;
    private Boolean isAdvisor;
    private Boolean isAdvisee;
    private Boolean isActive;
    private Date createdAt;
    private Date updatedAt;
}
