package com.toribro.space.controller.space;

import com.toribro.space.service.space.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/space")
@RequiredArgsConstructor
public class spaceController {

    private final SpaceService spaceService;

    @GetMapping("/detail")
    public String spaceDetail(@RequestParam(defaultValue = "1") int spaceNo, Model model) {


        return "space/spaceDetail";
    }
}
