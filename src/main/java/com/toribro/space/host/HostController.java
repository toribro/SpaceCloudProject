package com.toribro.space.host;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/host")
public class HostController {

    @GetMapping
    public String hostCenter(){
        return "host/hostMainPage";
    }

}
