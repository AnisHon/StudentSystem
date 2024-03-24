package com.anishan.config;

import com.anishan.entity.RestEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();
//
                })
                .formLogin(login -> {
                    login.loginProcessingUrl("/api/auth/login");
                    login.successHandler(this::successHandler);
                    login.failureHandler(this::failureHandler);
                    login.usernameParameter("username");
                    login.passwordParameter("password");
                })
                .logout(logout -> {
                    logout.logoutUrl("/api/auth/logout");
                    logout.logoutSuccessHandler(this::successHandler);
                })
                .cors(cors -> {
                    CorsConfiguration corsConfigurer = new CorsConfiguration();
                    corsConfigurer.addAllowedOriginPattern("*");
                    corsConfigurer.setAllowCredentials(true);
                    corsConfigurer.addAllowedHeader("*");
                    corsConfigurer.addAllowedMethod("*");
                    corsConfigurer.addExposedHeader("*");
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", corsConfigurer);  //直接针对于所有地址生效
                    cors.configurationSource(source);
                })
                .csrf(CsrfConfigurer::disable)
                .build();
    }

    public void successHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        if (request.getRequestURI().endsWith("login")) {
            writer.write(RestEntity.success("", "登陆成功").toJson());
        } else  {
            writer.write(RestEntity.success("", "注销成功").toJson());
        }
    }

    public void failureHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception exception) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        RestEntity message = null;
        if (exception instanceof BadCredentialsException bad ) {
            message = RestEntity.failure(403, "账户或密码错误");
        } else if (true) {

        } else if (false) {

        } else {

        }
        writer.write(message.toJson());


    }




}
