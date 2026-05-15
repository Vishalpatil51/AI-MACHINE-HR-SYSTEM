package com.employee.assignments.securityConfigTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.security.config.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.employee.assignments.securityConfig.SecurityConfig;

class SecurityConfigTest {

    private final SecurityConfig securityConfig = new SecurityConfig();

    @Test
    void testPasswordEncoder() {

        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);

        String encodedPassword = passwordEncoder.encode("password");

        assertTrue(passwordEncoder.matches("password", encodedPassword));
    }

    @Test
    void testSecurityFilterChain() throws Exception {

        HttpSecurity http = new HttpSecurity(
                mock(ObjectPostProcessor.class),
                mock(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder.class),
                new java.util.HashMap<>()
        );

        SecurityFilterChain filterChain =
                securityConfig.securityFilterChain(http);

        assertNotNull(filterChain);
        assertTrue(filterChain instanceof DefaultSecurityFilterChain);
    }
}