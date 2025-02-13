package com.asesorhub.utils;

import com.asesorhub.dto.AccountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class PasswordUtils {

    private final PasswordEncoder passwordEncoder;

    public String buildPassword(String password) {
        var passDecoded = new String(Base64.getDecoder().decode(password));
        var passEncripted = passwordEncoder.encode(passDecoded);
        return Base64.getEncoder().encodeToString(passEncripted.getBytes());
    }

}
