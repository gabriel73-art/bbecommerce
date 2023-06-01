package com.bbdigital.bbecommerce.Security.filtersecurity;

import java.util.Collections;

import com.bbdigital.bbecommerce.Models.Credentials;
import com.bbdigital.bbecommerce.Models.User;
import com.bbdigital.bbecommerce.Services.AuthenticationService;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationService authenticationService;

    public UserAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = null;
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            // authentication by username and password
            user = authenticationService.authenticate(
                    new Credentials((String) authentication.getPrincipal(), (char[]) authentication.getCredentials()));
        } else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            // authentication by cookie
            user = authenticationService.findByToken((String) authentication.getPrincipal());
        }

        if (user == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

