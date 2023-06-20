package com.danit.erp.config;

import static org.springframework.security.config.Customizer.withDefaults;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
        (authz) -> authz.requestMatchers("/api/v0/auth/**").permitAll().anyRequest().authenticated())
      .sessionManagement(
        (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//      .formLogin(
//        form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/welcome")
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

/*
!!! 1 коментар
* authenticationFailureHandler() в представленном коде представляет собой метод, который возвращает экземпляр объекта, реализующего интерфейс AuthenticationFailureHandler. Этот интерфейс используется для обработки события неудачной аутентификации пользователя.

AuthenticationFailureHandler позволяет вам определить пользовательскую логику обработки неудачной аутентификации, например, для записи журнала, отправки уведомлений или перенаправления пользователя на определенную страницу.

Вам нужно реализовать интерфейс AuthenticationFailureHandler и определить метод onAuthenticationFailure(), который будет вызываться при неудачной попытке аутентификации. В этом методе вы можете выполнять необходимые действия в зависимости от вашей бизнес-логики и требований приложения.

Вот пример простой реализации AuthenticationFailureHandler:

java
Copy code
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // Ваша логика обработки неудачной аутентификации
        // Например, запись в журнал, отправка уведомлений, перенаправление на страницу с ошибкой и т.д.

        response.sendRedirect("/login.html?error=true");
    }
}
В этом примере при неудачной аутентификации мы перенаправляем пользователя на страницу /login.html?error=true.

Вы можете создать собственную реализацию AuthenticationFailureHandler, а затем использовать ее в вашей конфигурации Spring Security, как в предоставленном коде:

java
Copy code
.and()
.failureHandler(authenticationFailureHandler())
Здесь authenticationFailureHandler() представляет вашу реализацию AuthenticationFailureHandler, которую вы определили в вашем приложении.

Обратите внимание, что это только пример, и вы можете настроить AuthenticationFailureHandler в соответствии с вашими потребностями и требованиями проекта.
*
*
*
!!!! 2 коментар
`.deleteCookies("JSESSIONID")` в представленном коде указывает, что при выполнении выхода из системы (логауте) должны быть удалены cookies с именем "JSESSIONID".

"JSESSIONID" - это стандартное имя для cookies, используемого для отслеживания сеанса пользователя в Java-веб-приложениях. Удаление этого cookie обычно используется для завершения сеанса пользователя при выполнении выхода из системы.

Чтобы реализовать соответствующий хендлер для успешного выполнения выхода, вы можете создать собственную реализацию `LogoutSuccessHandler`, которая будет выполнять необходимые действия после успешного выполнения выхода пользователя из системы.

Вот пример простой реализации `LogoutSuccessHandler`:

```java
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // Ваша логика после успешного выполнения выхода из системы
        // Например, перенаправление на страницу успешного выхода, очистка сеансов и т.д.

        response.sendRedirect("/logout-success.html");
    }
}
```

В этом примере после успешного выполнения выхода из системы мы перенаправляем пользователя на страницу `/logout-success.html`.

Вы можете создать свою реализацию `LogoutSuccessHandler`, а затем использовать ее в вашей конфигурации Spring Security, как в предоставленном коде:

```java
.and()
.logoutSuccessHandler(logoutSuccessHandler());
```

Здесь `logoutSuccessHandler()` представляет вашу реализацию `LogoutSuccessHandler`, которую вы определили в вашем приложении.

Обратите внимание, что это только пример, и вы можете настроить `LogoutSuccessHandler` в соответствии с вашими потребностями и требованиями проекта.
* */