package com.toribro.space.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @GetMapping
    public String noticeViewList(){
        return "notice/noticeListView";
    }

    @GetMapping("/{noticeNo}")
    public String noticeDetail(@PathVariable int noticeNo) {

        return "notice/noticeDetailView";
    }

    @PostMapping
    public String createNotice() {

        return "redirect:/notice";
    }

    @PatchMapping("/{noticeNo}")
    public String updateBoard(@PathVariable int noticeNo) {

        return "redirect:/notice/{noticeNo}";
    }

    @DeleteMapping("/{noticeNo}")
    public String DeleteBoard(@PathVariable int noticeNo) {

        return "redirect:/notice";
    }


    @GetMapping("/enroll")
    public String boardEnrollForm(){
        return "notice/noticeEnrollForm";
    }

    @GetMapping("/update/{noticeNo}")
    public String boardUpdateForm(@PathVariable int noticeNo) {

        return "notice/noticeUpdateForm";
    }


}
