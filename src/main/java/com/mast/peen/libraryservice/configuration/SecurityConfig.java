package com.mast.peen.libraryservice.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/books", "/signup", "/public/**").permitAll()
            .requestMatchers("/user/**").hasRole("USER")
            .requestMatchers("/books/manage/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        );
    return http.build();
  }

}
