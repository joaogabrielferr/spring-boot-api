package com.gabriel.apispringboot.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

	@Autowired
	SecurityFilter securityFilter;
	
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers( "/v3/api-docs/**",
								"/swagger-ui/**",
								"/v2/api-docs/**",
								"/swagger-resources/**").permitAll()
						.requestMatchers(HttpMethod.POST,"/login").permitAll()
						.requestMatchers(HttpMethod.POST,"/register").permitAll()
						.requestMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()
				.anyRequest().authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
    	return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEnconder()
    {
    	return new BCryptPasswordEncoder();
    }



	
}
