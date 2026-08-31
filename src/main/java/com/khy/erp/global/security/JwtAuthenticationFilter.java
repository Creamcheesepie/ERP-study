package com.khy.erp.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = resolveToken(request);

        log.info("[JwtFilter] URI = {}", request.getRequestURI());
        log.info("[JwtFilter] ACCESS_TOKEN = {}", accessToken);

        if(StringUtils.hasText(accessToken)){
            try{
                log.info("[JwtFilter] validating token");


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    private String resolveToken(HttpServletRequest request){
        if(request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(c -> "ACCESS_TOKEN".equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}
