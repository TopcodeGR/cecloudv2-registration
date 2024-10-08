package com.ptopalidis.cecloud.registration.config;


import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers(HttpMethod.POST,"/register").permitAll()
                .anyRequest().denyAll());

        http.csrf((csrf)->csrf.disable());

        http.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable());

        http.cors(c->{
            CorsConfigurationSource source =request -> {
                CorsConfiguration corsConfiguration =  new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(List.of("*"));
                corsConfiguration.setAllowedMethods(List.of("*"));
                corsConfiguration.setAllowedHeaders(List.of("*"));
                return  corsConfiguration;
            };
            c.configurationSource(source);
        });
        return  http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(@Value("#{${custom.password.encoders}}")List<String> encoders,
                                           @Value("#{${custom.password.idless.encoder}}") String idlessEncoderName){



        Map<String, PasswordEncoder > encodersMapping = new HashMap<>();

        if(encoders.contains("bcrypt")){
            encodersMapping.put("bcrypt", new BCryptPasswordEncoder());
        }

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encoders.get(0), encodersMapping);

        passwordEncoder.setDefaultPasswordEncoderForMatches(getEncoderForIdlessHash(idlessEncoderName));
        return passwordEncoder;
    }

    private PasswordEncoder getEncoderForIdlessHash(String encoderName){
        switch (encoderName){
            case "bcrypt":
                return new BCryptPasswordEncoder();
            default:
                return new BCryptPasswordEncoder();
        }
    }
}
