package com.example.vktest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails admin = User.withUsername("A1")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails albums = User.withUsername("A1_ALBUMS")
                .password(encoder.encode("password"))
                .roles("ALBUMS")
                .build();

        UserDetails posts = User.withUsername("A1_POSTS")
                .password(encoder.encode("password"))
                .roles("POSTS")
                .build();

        UserDetails users = User.withUsername("A1_USERS")
                .password(encoder.encode("password"))
                .roles("USERS")
                .build();

//        UserDetails user = UserResponse.withUsername("Ejaz")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();

        return new InMemoryUserDetailsManager(admin, albums, posts, users);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/api/albums/**").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/api/users/**").authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/api/posts/**").authenticated()
                .and().formLogin()
                .and()
                .authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .permitAll()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
