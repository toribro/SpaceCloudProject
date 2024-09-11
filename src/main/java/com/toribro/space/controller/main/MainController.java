package com.toribro.space.controller.main;

import com.toribro.space.common.PageInfo;
import com.toribro.space.common.Pagination;
import com.toribro.space.service.space.SpaceService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final SpaceService spaceService;

    @GetMapping
    public String mainPage(@RequestParam(defaultValue = "1") int cpage, Model model) {

        int count=spaceService.getCount();
        PageInfo spacePage= Pagination.getPageInfo(count,cpage,10,9);
        log.info("{}",count);
        log.info("current:{}",spacePage.getCurrentPage());
        log.info("start:{}",spacePage.getStartPage());
        log.info("end: {}",spacePage.getEndPage());
        log.info("max: {}",spacePage.getMaxPage());

        model.addAttribute("pi",spacePage);
        model.addAttribute("spList",spaceService.getSpaces(spacePage));
        return "main";
    }
}
