package com.danit.erp.config;

import static org.springframework.security.config.Customizer.withDefaults;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;


//  @Bean
//  public InMemoryUserDetailsManager userDetailsService() {
//    UserDetails user1 =
//      User.withUsername("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//        .build();
//    UserDetails user2 =
//      User.withUsername("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//        .build();
//    UserDetails admin =
//      User.withUsername("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN")
//        .build();
//    return new InMemoryUserDetailsManager(user1, user2, admin);
//  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //TODO  -ролі налаштовуються тут в http builder
    /*як заборонити певним ролям мати заборону доступу до певних ролей
     *https://docs.spring.io/spring-security/reference/servlet/integrations/cors.html#page-title
     * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
     *
     *1. стаття https://www.baeldung.com/spring-security-login
     *2. стаття https://www.baeldung.com/spring-security-login-react
     *
     * https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
     * https://gist.github.com/GabrielCzar/fa7e1cb523ba9e740945f5020cb06d11/revisions
     * приклад форми атентифікації react
     *
     * */
    http.csrf(AbstractHttpConfigurer::disable).cors(withDefaults()).securityMatcher("/api/**")
      .authorizeHttpRequests(
        (authz) -> authz.requestMatchers("/api/v0/auth/**").permitAll().anyRequest()
          .authenticated()).sessionManagement(
            (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//      .formLogin(
//        form -> form.loginPage("/login").loginProcessingUrl("/login").d
//        efaultSuccessUrl("/welcome")
////          .failureUrl("/error")    TODO реалізація ендпоінта помилки і 1 коментар
////          .failureHandler(authenticationFailureHandler())
//          .permitAll())
//      .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////          .deleteCookies("JSESSIONID") TODO глянкти, що це за фігня, реалізувати хендлер
////          .logoutSuccessHandler(logoutSuccessHandler())
//        .permitAll());

//    http.csrf()
//      .disable()
//      .authorizeRequests()
//      .antMatchers("/admin/**")
//      .hasRole("ADMIN")
//      .antMatchers("/anonymous*")
//      .anonymous()
//      .antMatchers("/login*")
//      .permitAll()
//      .anyRequest()
//      .authenticated();
    return http.build();

  }

//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return (web) -> web.ignoring().requestMatchers("/h2-console/**");
//  }


}
