package com.toribro.space.host;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/host")
@Slf4j
public class HostController {

    @GetMapping
    public String hostCenter(){
        return "host/hostMainPage";
    }
    @GetMapping("/pre")
    public String hostEnrollPre(){
        return "host/hostEnrollFormPre";
    }

    @PostMapping("/enroll")
    public String hostEnrollPost(){

        return "host/hostEnrollFormMain";
    }
}
