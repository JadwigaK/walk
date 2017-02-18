package com.sharewalk.authorization.check;

import com.sharewalk.authorization.SecuritySettings;
import com.sharewalk.authorization.model.JwtAuthenticationToken;
import com.sharewalk.authorization.model.RawAccessJwtToken;
import com.sharewalk.authorization.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class RequestAuthenticationProvider implements AuthenticationProvider {

    private final SecuritySettings securitySettings;

    @Autowired
    public RequestAuthenticationProvider(SecuritySettings securitySettings) {
        this.securitySettings = securitySettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(securitySettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        UserContext context = UserContext.create(subject);
        return new JwtAuthenticationToken(context);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
