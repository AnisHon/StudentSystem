package com.anishan.config;

import com.anishan.entity.Account;
import com.anishan.entity.RestEntity;
import com.anishan.service.AccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig {
    @Resource
    AccountService accountService;

    final String[] teacherOnlyAuth = {
            "/api/scores", "/students", "/api/exist"
    };

    final String[] studentOnlyAuth = {
            "/api/my-score"

    };

    @Resource
    DataSource dataSource;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, @Autowired PersistentTokenRepository tokenRepository) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/me").permitAll();
                    auth.requestMatchers(studentOnlyAuth).hasRole("student");
                    auth.requestMatchers(teacherOnlyAuth).hasRole("teacher");
                    auth.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginProcessingUrl("/api/auth/login");
                    login.successHandler(this::successHandler);
                    login.permitAll();

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
                .rememberMe(conf -> {
                    conf.rememberMeParameter("remember");
                    conf.tokenRepository(tokenRepository);
                    conf.tokenValiditySeconds(3600 * 24 * 3);

                })
                .exceptionHandling(config -> {
                    config.authenticationEntryPoint(this::failureHandler);
                })
                .csrf(CsrfConfigurer::disable)
                .build();
    }


    @Bean
    protected PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    public void successHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        if (request.getRequestURI().endsWith("login")) {

            User principal = (User) authentication.getPrincipal();
            writer.write(RestEntity.success(
                    accountService.getUserInfoByUserId(principal.getUsername()),
                    "登陆成功").toJson());

        } else  {
            writer.write(RestEntity.success("注销成功", "注销成功").toJson());
        }
    }

    public void failureHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception exception) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        RestEntity<String> message = null;
        if (exception instanceof BadCredentialsException badCredentialsException ) {
            message = RestEntity.failure(401, "账户或密码错误");
        } else if (exception instanceof UsernameNotFoundException usernameNotFoundException) {
            message = RestEntity.failure(401, "账户或密码错误");
        } else if (exception instanceof AuthenticationException authenticationException) {
            message = RestEntity.failure(403, "拒绝访问");
        } else  {
            message = RestEntity.failure(401, "错误");
        }
        writer.write(message.toJson());
    }




}
