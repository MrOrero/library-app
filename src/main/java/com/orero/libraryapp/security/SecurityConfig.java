package com.orero.libraryapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.orero.libraryapp.security.filter.AuthenticationFilter;
import com.orero.libraryapp.security.filter.ExceptionHandlerFilter;
import com.orero.libraryapp.security.filter.JWTAuthorizationFilter;
import com.orero.libraryapp.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public JWTAuthorizationFilter authenticationJwtTokenFilter() {
        return new JWTAuthorizationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");

        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/books/add-favorite/**").authenticated()
                .requestMatchers("/books/favorites").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    // @Bean
    // @Primary
    // public CorsFilter corsFilter() {
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // CorsConfiguration config = new CorsConfiguration();
    // config.setAllowCredentials(true);
    // config.setAllowedOriginPatterns(Collections.singletonList("*"));
    // config.setAllowedHeaders(Collections.singletonList("*"));
    // config.setAllowedMethods(Collections.singletonList("*"));
    // source.registerCorsConfiguration("/**", config);
    // return new CorsFilter(source);
    // }
}