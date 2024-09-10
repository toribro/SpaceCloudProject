package com.toribro.space.controller.member;

import com.toribro.space.domain.dto.member.MemberDto;
import com.toribro.space.domain.entity.member.Member;
import com.toribro.space.service.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String update(@PathVariable Long userNo,@ModelAttribute MemberDto.updateDto updateInfo){
        log.info("회원업데이트");
        log.info("userNo={}",userNo);
        log.info("updateInfo.pwd={}",updateInfo.getUserPwd());//값이 안넘어오면 빈값으로 넘어온다.

        memberService.updateMember(userNo,updateInfo);


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
    public String loginForm(Model model){
        model.addAttribute("login",new MemberDto.findDto());
        return "member/memberLoginForm";
    }

    //로그인
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute MemberDto.findDto loginInfo,
                        BindingResult bindingResult, Model model,HttpSession session){

        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);//에러 확인
            return "member/memberLoginForm";
        }

        Member member = memberService.findMember(loginInfo);
        log.info("{}",member);
        if(member!=null){
            session.setAttribute("loginUser",member);
            session.setAttribute("alertMsg","로그인되었습니다.");
            return "redirect:/";
        }else{
            session.setAttribute("alertMsg","아이디나 비밀번호가 일치하지 않습니다.");
            return "redirect:/member/login";
        }   
        
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        session.setAttribute("alertMsg","로그아웃되었습니다.");
        return "redirect:/";
    }







}
