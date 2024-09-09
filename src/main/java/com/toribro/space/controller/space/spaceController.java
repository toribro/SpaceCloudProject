package com.toribro.space.controller.space;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/space")
public class spaceController {

    @GetMapping("/detail")
    public String spaceDetail(@RequestParam(defaultValue = "1") int spaceNo) {



        return "space/spaceDetail";
    }
}
