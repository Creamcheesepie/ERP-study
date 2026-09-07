package com.khy.erp.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberCreateElement {
    private String name;
    private String loginId;
    private String password;
    private String passwordCheck;
    private String emailAccount;
    private String emailDomain;
    private String phone;
    private LocalDate birthday;
}
