package com.toribro.space.controller.space;

import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import com.toribro.space.service.space.SpaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/space")
@RequiredArgsConstructor
@Slf4j
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping("/detail")
    public String spaceDetail(@RequestParam(defaultValue = "1") int spaceNo,Model model) {

        SpaceDetail spaceByOne = spaceService.getSpaceByOne(spaceNo);
        log.info("space:{}",spaceByOne);
        model.addAttribute("space", spaceByOne);

        return "space/spaceDetail";
    }
}
