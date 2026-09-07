package com.khy.erp.domain.member.controller;

import com.khy.erp.domain.member.dto.MemberCreateElement;
import com.khy.erp.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("login")
    public String getLoginPage(Model model) {
        log.info("member[login] : get login page");
        return "member/login";
    }


    @GetMapping("join")
    public String getJoinPage(Model model) {
        log.info("member[create] : get create page");
        model.addAttribute("memberCreateElement", new MemberCreateElement());
        return "member/join";
    }

    @PostMapping("join")
    public String join(
            @ModelAttribute MemberCreateElement memberCreateElement
    ) {

        memberService.createMember(memberCreateElement);
        return "redirect:/member/login";
    }


}
