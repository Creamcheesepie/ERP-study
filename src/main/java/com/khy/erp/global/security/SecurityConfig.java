package com.khy.erp.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 기본 설정
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .headers(headers -> headers.disable())
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                                // 전체 허용 경로
                                .requestMatchers("/*")
                                .permitAll()
                                // 관리자 허용 경로
                                .requestMatchers("/admin/**")
                                .hasRole("ADMIN")
                                // 그외 다른 요청시 인증 요구
                                .anyRequest()
                                .authenticated()
                )
                .addFilterBefore()


        ;
        return httpSecurity.build();
    }

}
