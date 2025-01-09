package com.asesorhub.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import static com.asesorhub.utils.Constants.ISSUER_INFO;
import static com.asesorhub.utils.Constants.SUPER_SECRET_KEY;
import static com.asesorhub.utils.Constants.TOKEN_EXPIRATION_TIME_IN_MINUTES;

@Component
public class JwtService {

    public String generateToken(Authentication auth) {
        var cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + TOKEN_EXPIRATION_TIME_IN_MINUTES);

        return buildToken(auth, cal);
    }

    private static String buildToken(Authentication auth, Calendar cal) {
        var preToken = Jwts.builder()
                .setIssuedAt(new Date())
                .setIssuer(ISSUER_INFO)
                .setSubject(auth.getName())
                .setExpiration(cal.getTime())
                .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY.getBytes());
        preToken.claim("roles", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return preToken.compact();
    }
}