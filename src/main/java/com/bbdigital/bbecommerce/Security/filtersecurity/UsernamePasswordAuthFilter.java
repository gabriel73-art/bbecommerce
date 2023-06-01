package com.bbdigital.bbecommerce.Security.filtersecurity;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbdigital.bbecommerce.Models.Credentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        if ("/signIn".equals(httpServletRequest.getServletPath())
                && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
            Credentials credentialsDto = MAPPER.readValue(httpServletRequest.getInputStream(), Credentials.class);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(credentialsDto.getLogin(), credentialsDto.getPassword()));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

