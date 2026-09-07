package com.khy.erp.global.rq;

import com.khy.erp.domain.member.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final boolean isProd;

    public Rq(
            HttpServletRequest request,
            HttpServletResponse response,
            @Value("${spring.profiles.active:local}") String activeProfiles) {
        this.request = request;
        this.response = response;
        this.isProd = activeProfiles.equals("prod");
    }

    // 현재 인증된 사용자의 정보를 가져옵니다.
    public Member getMemeber(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return null;
    }
}
