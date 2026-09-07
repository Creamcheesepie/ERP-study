package com.khy.erp.domain.auth.service;

import com.khy.erp.domain.auth.dto.AuthLoginElement;
import com.khy.erp.domain.member.entity.Member;
import com.khy.erp.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;

    public void login(AuthLoginElement authLoginElement){
        Member member = memberRepository.findByLoginId(authLoginElement.getLoginId());
        if(!bCryptPasswordEncoder.matches(authLoginElement.getPassword(),member.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

    }



}
