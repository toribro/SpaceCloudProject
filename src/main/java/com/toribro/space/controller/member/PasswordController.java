package com.toribro.space.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password")
public class PasswordController {

    //비밀번호 찾기 페이지
    @GetMapping
    public String findPasswordForm(){
        return "member/memberFindPassword";
    }
    @PostMapping
    public String findPassword(){
        return "redirect:/password";
    }
}
