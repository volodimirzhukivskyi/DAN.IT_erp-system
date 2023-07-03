package com.danit.erp.config;

import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_CREATE;
import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_DELETE;
import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_READ;
import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_UPDATE;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_CREATE;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_DELETE;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_READ;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_UPDATE;
import static com.danit.erp.domain.role.RoleEnum.ADMINISTRATOR;
import static com.danit.erp.domain.role.RoleEnum.EMPLOYEE;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.Customizer.withDefaults;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


    http.csrf(AbstractHttpConfigurer::disable).cors(withDefaults()).securityMatcher("/api/**")
      .authorizeHttpRequests((authz) -> authz.requestMatchers("/api/v0/auth/**").permitAll()
        .requestMatchers("/api/v0/personal_card/**")
        .hasAnyRole(ADMINISTRATOR.name(), EMPLOYEE.name())
        .requestMatchers(GET, "/api/v0/personal_card/**")
        .hasAnyAuthority(ADMINISTRATOR_READ.name(), EMPLOYEE_READ.name())
        .requestMatchers(POST, "/api/v0/personal_card/**")
        .hasAnyAuthority(ADMINISTRATOR_CREATE.name(), EMPLOYEE_CREATE.name())
        .requestMatchers(PUT, "/api/v0/personal_card/**")
        .hasAnyAuthority(ADMINISTRATOR_UPDATE.name(), EMPLOYEE_UPDATE.name())
        .requestMatchers(DELETE, "/api/v0/personal_card/**")
        .hasAnyAuthority(ADMINISTRATOR_DELETE.name(), EMPLOYEE_DELETE.name()).anyRequest()
        .authenticated())


      .sessionManagement(
        (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).logout()
      .logoutUrl("/api/v0/auth/logout").addLogoutHandler(logoutHandler)
      .logoutSuccessHandler((request, response, authentication) -> {
        SecurityContextHolder.clearContext();
      });


    return http.build();

  }


}
