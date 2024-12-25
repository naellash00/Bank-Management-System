package com.example.bankmanagementwithsecurity.ConfigurationSecurity;

import com.example.bankmanagementwithsecurity.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {
    private final MyUserDetailsService myUserDetailsService;

    // this method to check username and password of user
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        // create new object with my own settings
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        System.out.println("DAO Authentication Provider initialized.");
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        // part1: close csrf attack - part2: authentication and authorization - part 3: log out and delete session
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/customer/register", "/api/v1/employee/register").permitAll()
                .requestMatchers("/api/v1/account/create/bank-account",
                        "/api/v1/account/get/account-details/**",
                        "/api/v1/customer/deposit/**",
                        "/api/v1/customer/withdraw/**",
                        "/api/v1/customer/transfer/**",
                        "/api/v1/customer/update",
                        "/api/v1/customer/delete/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/account/get/customer/accounts").hasAnyAuthority("CUSTOMER", "ADMIN")
                .requestMatchers("/api/v1/account/activate-account/**", "/api/v1/account/block-account/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
