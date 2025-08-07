package com.example.springsecurityloginandregistration.config;

import com.example.springsecurityloginandregistration.filter.AppFilter;
import com.example.springsecurityloginandregistration.service.security.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AppFilter appFilter;

    @Value("${app.security.patternsPermitted}")
    private String[] urlPatternsPermitted;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests((req) -> {
                req.requestMatchers(urlPatternsPermitted).permitAll()
                        .anyRequest().authenticated();
            }).csrf(AbstractHttpConfigurer::disable)
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
