package com.toribro.space.controller.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.service.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입페이지이동
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("member",new MemberDto.CreateDto());
        return "member/memberJoinForm";
    }


    //탈퇴페이지이동
    @GetMapping("/out")
    public String outForm() {
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
    public String join(@Validated @ModelAttribute("member") MemberDto.CreateDto member,
                       BindingResult bindingResult, Model model , HttpSession session){
        log.info("회원가입");
        log.info("{}",member);
        Map<String, String> errors = new HashMap<>();

        if(!member.getUserPwd().equals(member.getUserPwdCheck())){
//            bindingResult.reject("passworderror",new Object[]{"checkError"},null);
             errors.put("userPwdCheck","비밀번호가 일치하지 않습니다.");
             model.addAttribute("pwdError",errors);
        }


        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "member/memberJoinForm";
        }

        memberService.save(member);
        session.setAttribute("alertMsg","회원가입 되었습니다.");

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
