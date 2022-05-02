package com.project.shop.session;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.swing.*;

@RequiredArgsConstructor
public class SessionConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
/*
    private final CustomProvider customProvider;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SessionFilter sessionFilter = new SessionFilter(customProvider);
        builder.addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class);
    }*/
}
