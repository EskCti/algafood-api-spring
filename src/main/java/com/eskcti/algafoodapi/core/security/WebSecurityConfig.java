package com.eskcti.algafoodapi.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = bCryptPasswordEncoder();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("edson")
//                .password(bCryptPasswordEncoder.encode("123"))
//                .roles("USER")
//                .build());
//        manager.createUser(User.withUsername("admin")
//                .password(bCryptPasswordEncoder.encode("123"))
//                .roles("ADMIN")
//                .build());
//        return manager;
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(
//            HttpSecurity http,
//            BCryptPasswordEncoder bCryptPasswordEncoder,
//            UserDetailsService userDetailService
//    ) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
//
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(HttpMethod.POST, "/v1/kitchens/**").hasAuthority("EDIT_KITCHENS")
//                        .requestMatchers(HttpMethod.PUT, "/v1/kitchens/**").hasAuthority("EDIT_KITCHENS")
//                        .requestMatchers(HttpMethod.GET, "/v1/kitchens/**").authenticated()
//                        .anyRequest().denyAll())
                .cors().and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                authorities = Collections.emptyList();
            }

            return authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return jwtAuthenticationConverter;
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        var secretKey = new SecretKeySpec("5MU8/YgrnHELFHEypwoIfstdqvzxUE4KoZe1vpG+6S8=".getBytes(), "HmacSHA256");
//
//        JwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey).build();
//
//        return jwtDecoder;
//    }
}

