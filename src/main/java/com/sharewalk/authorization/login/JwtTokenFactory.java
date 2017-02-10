package com.sharewalk.authorization.login;

import com.sharewalk.authorization.SecuritySettings;
import com.sharewalk.authorization.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFactory {

    private final SecuritySettings securitySettings;

    @Autowired
    public JwtTokenFactory(SecuritySettings securitySettings) {
        this.securitySettings = securitySettings;
    }

    public String createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(
                        SignatureAlgorithm.HS256,
                        securitySettings.getTokenSigningKey()
                )
                .compact();
    }
}