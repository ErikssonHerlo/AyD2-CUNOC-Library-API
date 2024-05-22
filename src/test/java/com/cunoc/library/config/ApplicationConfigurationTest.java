package com.cunoc.library.config;

import com.cunoc.library.adapters.out.entities.UserEntity;
import com.cunoc.library.adapters.out.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationConfigurationTest {

    private UserRepository userRepository;
    private ApplicationConfiguration applicationConfiguration;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        applicationConfiguration = new ApplicationConfiguration(userRepository);
    }

    @Test
    void testUserDetailService() {
        UserDetailsService userDetailsService = applicationConfiguration.userDetailService();
        UserEntity userEntity = new UserEntity(); // Asegúrate de que esta clase tenga los campos necesarios.
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(userEntity));

        assertThat(userDetailsService.loadUserByUsername("testUsername")).isEqualTo(userEntity);
    }
    @Test
    void testUserDetailServiceThrowsException() {
        UserDetailsService userDetailsService = applicationConfiguration.userDetailService();
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("testUsername");
        });
    }

    @Test
    void testAuthenticationManager() throws Exception {
        AuthenticationConfiguration authenticationConfiguration = mock(AuthenticationConfiguration.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);

        assertThat(applicationConfiguration.authenticationManager(authenticationConfiguration)).isEqualTo(authenticationManager);
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = applicationConfiguration.passwordEncoder();
        assertThat(passwordEncoder).isNotNull();
        assertThat(passwordEncoder.encode("password")).isNotBlank();
    }

    @Test
    void testAuthenticationProvider() {
        UserDetailsService userDetailsService = mock(UserDetailsService.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        ApplicationConfiguration configSpy = Mockito.spy(applicationConfiguration);

        when(configSpy.userDetailService()).thenReturn(userDetailsService);
        when(configSpy.passwordEncoder()).thenReturn(passwordEncoder);

        AuthenticationProvider authenticationProvider = configSpy.authenticationProvider();

        assertThat(authenticationProvider).isNotNull();
        assertThat(authenticationProvider).hasFieldOrPropertyWithValue("userDetailsService", userDetailsService);
        assertThat(authenticationProvider).hasFieldOrPropertyWithValue("passwordEncoder", passwordEncoder);
    }
}
