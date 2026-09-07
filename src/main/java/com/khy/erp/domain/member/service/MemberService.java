package com.khy.erp.domain.member.service;

import com.khy.erp.domain.humanResources.entity.Department;
import com.khy.erp.domain.humanResources.entity.Position;
import com.khy.erp.domain.humanResources.repository.DepartmentRepository;
import com.khy.erp.domain.humanResources.repository.PositionRepository;
import com.khy.erp.domain.member.dto.MemberCreateElement;
import com.khy.erp.domain.member.entity.Member;
import com.khy.erp.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberRepository memberRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;


    public Member createMember(MemberCreateElement memberCreateElement){
        if(!memberCreateElement.getPassword().equals(memberCreateElement.getPasswordCheck())){
            throw new IllegalArgumentException("비밀번호 확인이 잘못되었습니다.");
        }

        Position position = positionRepository.findByCode("TEMPORAL_POSITION");
        Department department = departmentRepository.findByName("임시팀");
        String cryptPassword = bCryptPasswordEncoder.encode(memberCreateElement.getPassword());

        Member member = new Member(
                memberCreateElement.getName(),
                memberCreateElement.getLoginId(),
                cryptPassword,
                memberCreateElement.getEmailAccount() + "@" + memberCreateElement.getEmailDomain(),
                memberCreateElement.getPhone(),
                memberCreateElement.getBirthday(),
                department,
                position
        );

        memberRepository.save(member);
        return null;
    }


}
