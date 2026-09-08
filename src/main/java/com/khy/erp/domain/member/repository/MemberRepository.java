package com.khy.erp.domain.member.repository;

import com.khy.erp.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByLoginId(String loginId);

}
