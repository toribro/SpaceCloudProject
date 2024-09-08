package com.toribro.space.controller.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@Slf4j
public class SessionController {

    @GetMapping
    public String invaildAlertSession(HttpSession session) {

         session.removeAttribute("alertMsg");
         return "OK";
    }

}
