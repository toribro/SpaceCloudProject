package com.toribro.space.controller.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    //회원가입페이지이동
    @GetMapping("/join")
    public String joinForm(){
        return "member/memberJoinForm";
    }


    //탈퇴페이지이동
    @GetMapping("/out")
    public String outForm(){
        return "member/memberOutForm";
    }


    //마이페이지(회원조회 R)
    @GetMapping
    public String memberInfo(){
        log.info("회원조회");
        return "member/memberPage";
    }



    //회원가입(회원 생성C)
    @PostMapping
    public String join(){
        log.info("회원가입");
        return "redirect:/";
    }


    //업데이트 페이지로 이동
    @GetMapping("/{userNo}")
    public String updateForm(){
        log.info("회원업데이트페이지");
        return "member/memberUpdateForm";
    }

    //회원수정(u)
    @PatchMapping("/{userNo}")
    public String update(@PathVariable int userNo){
        log.info("회원업데이트");
        return "redirect:/";//마이페이지로 리다이렉트 할것
    }


    //회원 탈퇴(d)
    @DeleteMapping("/{userNo}")
    public String delete(@PathVariable int userNo){
        log.info("회원탈퇴");
        return "redirect:/";
    }



    //////

    //로그인페이지
    @GetMapping("/login")
    public String loginForm(){
        return "member/memberLoginForm";
    }

    //로그인
    @PostMapping("/login")
    public String login(){
        return "redirect:/";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }







}
