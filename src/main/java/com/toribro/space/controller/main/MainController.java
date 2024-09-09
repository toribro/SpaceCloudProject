package com.toribro.space.controller.main;

import com.toribro.space.service.space.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final SpaceService spaceService;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("spList",spaceService.getSpaces());
        return "main";
    }
}
