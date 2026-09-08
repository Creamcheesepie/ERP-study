package com.khy.erp.domain.auth.controller;

import com.khy.erp.domain.auth.dto.AuthLoginElement;
import com.khy.erp.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public void authLoginRequest(
            @ModelAttribute AuthLoginElement authLoginElement
    ) {
        authService.login(authLoginElement);
    }

}
