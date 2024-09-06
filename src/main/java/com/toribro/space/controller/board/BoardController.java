package com.toribro.space.controller.board;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping
    public String boardList() {

        return "board/boardListView";
    }
    @GetMapping("/{boardNo}")
    public String boardDetail(@PathVariable int boardNo) {

        return "board/boardDetailView";
    }

    @PostMapping
    public String createBoard() {

        return "redirect:/board";
    }

    @PatchMapping("/{boardNo}")
    public String updateBoard(@PathVariable String boardNo) {

        return "redirect:/board/{boardNo}";
    }

    @DeleteMapping("/{boardNo}")
    public String DeleteBoard(@PathVariable String boardNo) {

        return "redirect:/board";
    }


    @GetMapping("/enroll")
    public String boardEnrollForm(){
        return "board/boardEnrollForm";
    }

    @GetMapping("/update/{boardNo}")
    public String boardUpdateForm(@PathVariable int boardNo) {

        return "board/boardUpdateForm";
    }





}
