package org.hurc.cms.config;

import lombok.AllArgsConstructor;
import org.hurc.cms.security.JwtAuthenticationEntryPoint;
import org.hurc.cms.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig  {
  private UserDetailsService userDetailsService;

  private static final String[] WHITE_LIST = {
      "/api/v1/auth/**",
      "/access-denied",
      "/user/home",
      "/uploads/**",
      "/assets/**",
      "/",
      "/api-docs/**",
      "/swagger-ui-custom.html",
      "/swagger-ui/**"
  };
  private JwtAuthenticationEntryPoint authenticationEntryPoint;

  private JwtAuthenticationFilter authenticationFilter;

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize -> {
      authorize.requestMatchers(WHITE_LIST).permitAll();
      authorize.requestMatchers("/api/v1/admin/**").hasRole("ADMIN");
      authorize.requestMatchers("/api/v1/courses/**").hasRole("ADMIN");
      authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
      authorize.anyRequest().authenticated();
    });

    http.exceptionHandling(exception -> exception
        .authenticationEntryPoint(authenticationEntryPoint));

    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

}
